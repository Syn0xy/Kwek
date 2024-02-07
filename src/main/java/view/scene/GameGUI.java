package view.scene;

import java.awt.Container;

import model.GameScene;
import network.NetworkManager;
import view.gui.GUI;
import view.gui.GUIComponent;

public class GameGUI extends GUI {

    private GameScene gameScene;

    private NetworkManager networkManager;

    public GameGUI(GameScene gameScene){
        this.gameScene = gameScene;
        this.networkManager = gameScene.getNetworkManager();
        init();
    }

    @Override
    public GUIComponent[] getScenes() {
        return new GUIComponent[]{
            new MenuScene(this),
            new ServerListScene(this, networkManager),
            new ServerCreateScene(this, networkManager),
            new GameCanvas(this, gameScene),
            new SettingsScene(this)
        };
    }

}
