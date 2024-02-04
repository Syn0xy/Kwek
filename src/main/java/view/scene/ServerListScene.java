package view.scene;

import java.awt.Color;

import network.NetworkManager;
import network.dao.Server;
import view.gui.AxisAlignment;
import view.gui.Button;
import view.gui.GUIComponent;
import view.gui.HorizontalAlignment;
import view.gui.Panel;
import view.gui.Scene;
import view.gui.Text;
import view.gui.VerticalAlignment;

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
        setColor(Color.BLACK);
        setSize(1000, 500);
        Panel p = new Panel(getWidth() / 4, getHeight() / 8, getWidth() / 2, getHeight() / 4);
        Panel buttons = new Panel(getWidth() / 4, getHeight() / 4, getWidth() / 2, getHeight() / 4);
        buttons.setAxisAlignment(AxisAlignment.X_AXIS);
        buttons.setHorizontalAlignment(HorizontalAlignment.LEFT);
        buttons.setVerticalAlignment(VerticalAlignment.UPPER);
        p.setColor(Color.WHITE);
        buttons.setColor(Color.CYAN);
        
        serversListPanel = new Panel();
        serversListPanel.setColor(Color.RED);
        serverSelectedLabel = new Text("Aucun serveur n'est selectionné");
        
        refreshServersListPanel();
        buttons.add(getServerCreateButton());
        buttons.add(getJoinButton());
        buttons.add(getReloadButton());
        buttons.add(getBackButton());
        p.add(serversListPanel);
        p.add(buttons);
        add(p);
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
