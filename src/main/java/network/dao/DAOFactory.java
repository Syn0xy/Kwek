package network.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DAOFactory {

    private static final String BDD_CONFIG_PATH = "bdd_config.sql";

    private static final String CONFIG_PATH = "config.local.prop";

    private static Connection connection;

    private static Connection getConnection(){
        if(connection == null){
            try {
                connection = new DataSource(CONFIG_PATH).getConnection();
            } catch (IOException | ClassNotFoundException | SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return connection;
    }

    public static void refreshDatabase(){
        try {
            Connection connection = getConnection();
            Statement stmt = connection.createStatement();
            String query = "";

            File file = new File("res" + File.separator + BDD_CONFIG_PATH);
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                while(br.ready()){
                    query += br.readLine();
                }
            } catch (Exception e) {
                // TODO: handle exception
            }

            stmt.executeUpdate(query);
            System.out.println("Update effectué !");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public static ServerDAO getServerDAO(){
        return new ServerDAO(getConnection());
    }

}
