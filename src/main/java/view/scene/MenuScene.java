package view.scene;

import view.gui.Button;
import view.gui.Panel;
import view.gui.Scene;

public class MenuScene extends Panel {

    private Scene parentScene;
    
    protected MenuScene(Scene parentScene){
        this.parentScene = parentScene;
        init();
    }

    private void init(){
        add(getPlayButton());
        add(getExitButton());
    }

    protected void setPlayScene(){
        parentScene.setScene(1);
    }

    private Button getPlayButton(){
        return new Button("Play", () -> {
            setPlayScene();
        });
    }

    private Button getExitButton(){
        return new Button("Exit", () -> {
            System.exit(0);
        });
    }

}
