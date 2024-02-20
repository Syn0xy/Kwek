package view.scene;

import java.awt.Color;
import java.awt.Graphics;

import network.NetworkManager;
import network.dao.Server;
import view.gui.AxisAlignment;
import view.gui.Button;
import view.gui.GUIComponent;
import view.gui.GUIContainer;
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
        Panel buttons = new Panel(500, 200);
        serversListPanel = new Panel();
        serverSelectedLabel = new Text("");
        serverSelectedLabel.setTextVerticalAlignment(VerticalAlignment.UPPER);
        serverSelectedLabel.setHeight(40);
        
        buttons.setAxisAlignment(AxisAlignment.X_AXIS);
        
        buttons.setColor(Color.LIGHT_GRAY);
        serversListPanel.setColor(Color.RED);
        serverSelectedLabel.setColor(Color.BLUE);
        
        refreshServersListPanel();
        buttons.add(getServerCreateButton());
        buttons.add(getJoinButton());
        buttons.add(getReloadButton());
        buttons.add(getBackButton());
        add(getNameText());
        add(serverSelectedLabel);
        add(serversListPanel);
        add(buttons);
    }

    private Text getNameText(){
        Text text = new Text("League of Losers");
        text.setColor(Color.BLACK);
        text.setSize(200, 50);
        return text;
    }

    private void refreshServersListPanel(){
        serversListPanel.removeAll();
        Server[] servers = networkManager.getListOfServers();

        if(servers == null){
            return;
        }
        
        int maxSize = 0;

        for (int i = 0; i < servers.length; i++) {
            GUIComponent crntServerButton = getServerSelectButton(servers[i]);
            serversListPanel.add(crntServerButton);
            maxSize += crntServerButton.getHeight();
        }

        serversListPanel.setHeight(maxSize);
        refresh();
    }

    private void refresh(){
        if(serverSelected != null){
            serverSelectedLabel.setText("Serveur selectionné: " + serverSelected.getName());
        }else{
            serverSelectedLabel.setText("Aucun serveur n'est selectionné");
        }
    }

    private GUIComponent getServerSelectButton(Server server){
        String desc = "id: " + server.getId() + " | name: " + server.getName() + " | currentPlayers: " + server.getCurrentPlayers() + " | maxPlayers: " + server.getMaxPlayers();
        return new Button(desc, () -> {
            serverSelected = server;
            refresh();
        }){{
            setWidth(475);
        }};
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
        networkManager.connect(serverSelected);
        parentScene.setScene(3);
    }
    
    private Button getReloadButton(){
        return new Button("Refresh", () -> {
            serverSelected = null;
            refreshServersListPanel();
        });
    }
    
    private Button getBackButton(){
        return new Button("Back", () -> {
            parentScene.setScene(0);
        });
    }

}
