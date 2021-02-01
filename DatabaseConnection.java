package BitSim;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    public Connection connection;

    public Connection getConnection(){
        String databaseUser = "luna";
        String databasePassword = "123";
        String URL = "jdbc:sqlserver://localhost:1433;database=LifeSimulationGame";

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(URL, databaseUser, databasePassword);
        } catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
        return connection;
    }
}
