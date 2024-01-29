package network;

import java.util.ArrayList;
import java.util.List;

import network.dao.DAOFactory;
import network.dao.Server;
import network.dao.ServerDAO;

public class NetworkManager {

    private static NetworkManager singleton;

    public static NetworkManager getInstance(){
        if(singleton == null) singleton = new NetworkManager();
        return singleton;
    }

    private List<NetworkEntity> entities;

    private NetworkManager(){
        this.entities = new ArrayList<>();
    }

    public void update(){
        for(NetworkEntity entity : entities){
            entity.update();
        }
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
