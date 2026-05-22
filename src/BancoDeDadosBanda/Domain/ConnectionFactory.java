package BancoDeDadosBanda.Domain;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionFactory {

    public static Connection getConnection() {
        try {
            Properties props = new Properties();

            InputStream input = ConnectionFactory.class
                    .getClassLoader()
                    .getResourceAsStream("db.properties");

            props.load(input);

            String url = props.getProperty("db.url");
            String username = props.getProperty("db.user");
            String password = props.getProperty("db.password");

            return DriverManager.getConnection(url, username, password);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
