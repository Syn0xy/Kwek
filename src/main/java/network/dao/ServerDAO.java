package network.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ServerDAO extends DAO<Server> {

    private static final String DATABASE_TALBE = "server";

    protected ServerDAO(Connection connection){
        super(connection);
    }

    public Server[] getAll(){
        try {
            String query = "select * from " + DATABASE_TALBE + ";";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            List<Server> servers = new ArrayList<>();
            while(rs.next()){
                Server server = new Server(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getInt(3),
                    rs.getInt(4)
                );
                servers.add(server);
            }
            return servers.toArray(new Server[servers.size()]);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    public boolean create(String name, int maxPlayers) {
        try {
            String query = "insert into " + DATABASE_TALBE + "(name, current_players, max_players) values(?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, name);
            ps.setInt(2, 0);
            ps.setInt(3, maxPlayers);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return false;
    }
}
