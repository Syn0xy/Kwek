package view.scene;

import network.NetworkManager;
import network.dao.Server;
import view.gui.Button;
import view.gui.GUIComponent;
import view.gui.Panel;
import view.gui.Scene;
import view.gui.Text;

public class ServerListScene extends Panel {

    private Scene parentScene;

    private NetworkManager networkManager;

    private Panel serversListPanel;

    private Text serverSelectedLabel;

    private Server serverSelected;

    protected ServerListScene(Scene parentScene, NetworkManager networkManager){
        this.parentScene = parentScene;
        this.networkManager = networkManager;
        init();
    }

    private void init(){
        serversListPanel = new Panel();
        serverSelectedLabel = new Text("Aucun serveur n'est selectionné");
        
        refreshServersListPanel();
        add(serverSelectedLabel);
        add(serversListPanel);
        add(getServerCreateButton());
        add(getJoinButton());
        add(getReloadButton());
        add(getBackButton());
    }

    private void refreshServersListPanel(){
        serversListPanel.removeAll();
        Server[] servers = networkManager.getListOfServers();
        
        for (int i = 0; i < servers.length; i++) {
            serversListPanel.add(getServerSelectButton(servers[i]));
        }
        serverSelected = servers[0];
    }

    private GUIComponent getServerSelectButton(Server server){
        String desc = "id: " + server.getId() + " | name: " + server.getName() + " | currentPlayers: " + server.getCurrentPlayers() + " | maxPlayers: " + server.getMaxPlayers();
        return new Button(desc, () -> {
            serverSelected = server;
            serverSelectedLabel.setText("Serveur selectionné: " + server.getName());
        });
    }
    
    private Button getServerCreateButton(){
        return new Button("Create", () -> {
            parentScene.setScene(2);
        });
    }
    
    private Button getJoinButton(){
        return new Button("Join", () -> {
            play();
        });
    }
    
    private void play(){
        if(serverSelected == null){
            System.out.println("Il n'y a pas de serveur selectionné !");
            return;
        }
        parentScene.setScene(3);
    }
    
    private Button getReloadButton(){
        return new Button("Refresh", () -> {
            refreshServersListPanel();
        });
    }
    
    private Button getBackButton(){
        return new Button("Back", () -> {
            parentScene.setScene(0);
        });
    }

}
