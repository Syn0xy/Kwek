package network;

import network.dao.DAOFactory;
import network.dao.Server;
import network.dao.ServerDAO;

public class NetworkManager {

    private static NetworkManager singleton;

    public static NetworkManager getInstance(){
        if(singleton == null) singleton = new NetworkManager();
        return singleton;
    }

    private Server currentServer;

    public void update(){
        SyncValue.refresh();
    }

    public void connect(Server server){
        currentServer = server;
        System.out.println("Vous êtes connecté au server: " + currentServer.getId());
    }

    public Server[] getListOfServers(){
        return DAOFactory.getServerDAO().getAll();
    }

    public void createServer(String name, int maxPlayers){
        ServerDAO dao = DAOFactory.getServerDAO();
        boolean result = dao.create(name, maxPlayers);
        System.out.println("Le server a bien été crée ? " + result);
    }

}
