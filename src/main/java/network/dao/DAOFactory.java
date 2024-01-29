package network.dao;

import java.sql.Connection;

public class DAOFactory {

    private static final String CONFIG_PATH = "config.local.prop";

    private static Connection connection;

    private static Connection getConnection(){
        if(connection == null){
            try {
                connection = new DataSource(CONFIG_PATH).getConnection();
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return connection;
    }

    public static ServerDAO getServerDAO(){
        return new ServerDAO(getConnection());
    }
}
