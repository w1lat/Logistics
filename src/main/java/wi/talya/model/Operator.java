package wi.talya.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "operators")
public class Operator extends GeneratedIdentifierEntity{

    @Column(nullable = false)
    private String pass;
    @Column(name = "full_name")
    private String fullName;

    public Operator() {}

    public Operator(int id, String pass, String fullName) {
        this.id = id;
        this.pass = pass;
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "Operator{" +
                "id=" + id +
                ", pass='" + pass + '\'' +
                ", fullName='" + fullName + '\'' +
                '}';
    }

    public String getFullName() {
        return fullName;
    }

}
