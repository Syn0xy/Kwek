package view.scene;

import java.awt.Color;

import network.NetworkManager;
import view.gui.AxisAlignment;
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

        Panel buttons = new Panel(getWidth() / 2, getHeight() / 2);
        buttons.setAxisAlignment(AxisAlignment.X_AXIS);
        buttons.setColor(Color.GRAY);
        buttons.add(getServerCreationButton(nameField, maxPlayersField));
        buttons.add(getBackServerCreationButton());
        add(nameField);
        add(maxPlayersField);
        add(buttons);
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
