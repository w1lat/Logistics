package wi.talya.view;




import org.apache.log4j.Logger;
import wi.talya.controller.OperatorService;
import wi.talya.model.Cargo;
import wi.talya.model.WayBill;

import java.util.List;
import java.util.Scanner;

/**
 * Created by Таня on 03.06.2015.
 */
public class OperatorConsoleView implements View {

    private static final Logger LOG = Logger.getLogger(OperatorConsoleView.class);
    private OperatorService operatorService;
    private Scanner scanner = new Scanner(System.in);
    private int choice;
    public OperatorConsoleView(OperatorService operatorService) {
        this.operatorService = operatorService;
    }

    public void start() {
        showMainMenu();

        choice = scanner.nextInt();
        switch (choice){
            case(1) : {
                showAddCargoMenu();
                break;
            }
            case(2) : {
                returnAll();
                break;
            }
            case(3) : {
                showIssueCargoMenu();
                break;
            }
            case(4) : {
                formConsignments();
                break;
            }
        }
    }

    private void formConsignments() {
        WayBill wayBill = operatorService.formWayBill();
        if(wayBill.getListSize() > 0)
            System.out.println(wayBill.toString());
        else LOG.info("Нет доступных по обьему товаров");
    }

    private void showIssueCargoMenu() {
        System.out.println("Введите id товара");
        long id = Long.parseLong(scanner.next());
        System.out.println("Введите id получателя");
        String receiverId = scanner.next().trim();
        if(operatorService.findById(id).getReceiverId().equals(receiverId)){
            System.out.println(operatorService.removeFromDb(id));
        }else
            System.out.println("Извините Вы не можете забрать товар");
    }

    private void returnAll() {
        List<Cargo> list = operatorService.getAll();
        System.out.println(" C A R G O ");
        System.out.println(String.format("%10s %6s %3s %10s %10s ", "name", "id", "volume", "destination", "receiverId"));
        for(Cargo c : list){
            System.out.println(c);
        }
    }

    private void showMainMenu() {
        System.out.println("1. добавить груз");//create new cargo in db
        System.out.println("2. показать весь груз на складе");//list of cargo
        System.out.println("3. выдать груз получателю");//check id then receiver id if same then delete from db
        System.out.println("4. сформировать накладную для доставки");//add cargo to list to deliver to another city, deleting them from db
    }

    private synchronized void showAddCargoMenu(){
        //scanner = new Scanner(System.in);
        Cargo cargo = new Cargo();
        System.out.println("Введите название груза");
        cargo.setName(scanner.next());
        System.out.println("Введите место доставки");
        cargo.setDestination(scanner.next());
        System.out.println("Введите обьем груза");
        cargo.setVolume(Integer.parseInt(scanner.next()));
        System.out.println("Введите id получателя");
        cargo.setReceiverId(scanner.next());

        System.out.println(operatorService.addCargo(cargo) + " груз добавлен в базу");

    }
}
