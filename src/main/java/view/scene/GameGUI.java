package view.scene;

import java.awt.Container;

import model.GameScene;
import network.NetworkManager;
import view.gui.GUI;
import view.gui.GUIComponent;
import view.gui.Panel;

public class GameGUI extends GUI {

    private GameScene gameScene;

    private NetworkManager networkManager;

    public GameGUI(Container container, GameScene gameScene){
        super(container);
        this.gameScene = gameScene;
        this.networkManager = gameScene.getNetworkManager();
        init();
    }

    @Override
    public GUIComponent[] getScenes() {
        return new Panel[]{
            new MenuScene(this),
            new ServerListScene(this, networkManager),
            new ServerCreateScene(this, networkManager),
            new GameCanvas(this, gameScene)
        };
    }

}
