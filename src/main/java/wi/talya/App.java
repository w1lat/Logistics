package wi.talya;

import wi.talya.controller.OperatorService;
import wi.talya.controller.OperatorServiceImpl;
import wi.talya.dao.CargoDAO;
import wi.talya.dao.DriverDAO;
import wi.talya.dao.HibernateCargoDAOImpl;
import wi.talya.dao.HibernateDriverDAOImpl;
import wi.talya.view.OperatorConsoleView;
import wi.talya.view.View;


public class App {
    public static void main(String[] args) {
        CargoDAO operatorDAO = new HibernateCargoDAOImpl();
        DriverDAO driverDAO = new HibernateDriverDAOImpl();
        OperatorService operatorService = new OperatorServiceImpl(operatorDAO, driverDAO);
        View operatorView = new OperatorConsoleView(operatorService);

        while (true) {
            operatorView.start();
        }
    }
}
