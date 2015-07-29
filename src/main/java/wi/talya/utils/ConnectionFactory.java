package wi.talya.utils;


import org.apache.log4j.Logger;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConnectionFactory {

    private static final Logger LOG = Logger.getLogger(ConnectionFactory.class);


    private static EntityManagerFactory factory = null;

    private static String url;
    private static String user;
    private static String pass;
    private static String driver;

    static {

        try {

            Properties properties = new Properties();
            properties.load(new FileInputStream("E:\\Programming\\Logistics\\src\\main\\resources\\app.properties"));
            url = properties.getProperty("db.url");
            user = properties.getProperty("db.user");
            pass = properties.getProperty("db.pass");
            driver = properties.getProperty("db.driver");

            Class.forName(driver);
        } catch (IOException e) {
            LOG.error(e);
        } catch (ClassNotFoundException e) {
            LOG.error(e);
        }
    }

    private ConnectionFactory() {
    }

    public static EntityManagerFactory getConnection() {

        if (factory == null) {
            factory = Persistence.createEntityManagerFactory("my_unit");
        }
        return factory;
    }

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPass() {
        return pass;
    }
}
