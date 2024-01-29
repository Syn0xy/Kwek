package view.scene;

import network.NetworkManager;
import view.gui.Button;
import view.gui.Panel;
import view.gui.Scene;
import view.gui.TextField;

public class ServerCreateScene extends Panel {

    private Scene parentScene;

    private NetworkManager networkManager;

    protected ServerCreateScene(Scene parentScene, NetworkManager networkManager){
        this.parentScene = parentScene;
        this.networkManager = networkManager;
        init();
    }

    private void init(){
        TextField nameField = new TextField("Name");
        TextField maxPlayersField = new TextField("Name");
        add(nameField);
        add(maxPlayersField);
        add(getServerCreationButton(nameField, maxPlayersField));
        add(getBackServerCreationButton());
    }
    
    private Button getServerCreationButton(TextField nameField, TextField maxPlayersField){
        return new Button("Create", () -> {
            networkManager.createServer(nameField.getText(), Integer.parseInt(maxPlayersField.getText()));
            parentScene.setScene(1);
        });
    }
    
    private Button getBackServerCreationButton(){
        return new Button("Back", () -> {
            parentScene.setScene(1);
        });
    }

}
