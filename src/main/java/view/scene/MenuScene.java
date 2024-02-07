package view.scene;

import java.awt.Color;

import view.gui.Button;
import view.gui.Panel;
import view.gui.Scene;
import view.gui.Text;

public class MenuScene extends Panel {

    private Scene parentScene;
    
    protected MenuScene(Scene parentScene){
        this.parentScene = parentScene;
        init();
    }

    private void init(){
        setColor(Color.BLACK);
        setSize(1000, 500);
        Panel p = new Panel(500, 250);
        p.setColor(Color.LIGHT_GRAY);
        p.add(getNameText());
        p.add(getPlayButton());
        p.add(getSettingsButton());
        p.add(getExitButton());
        add(p);
    }

    private Text getNameText(){
        Text text = new Text("League of Losers");
        text.setColor(Color.BLACK);
        text.setSize(200, 50);
        return text;
    }

    private Button getPlayButton(){
        Button button = new Button("Play", () -> {
            parentScene.setScene(1);
        });
        button.setSize(200, 50);
        button.setBackgroundColor(Color.BLACK);
        return button;
    }

    private Button getSettingsButton(){
        Button button = new Button("Settings", () -> {
            parentScene.setScene(4);
        });
        button.setSize(200, 50);
        button.setBackgroundColor(Color.BLACK);
        return button;
    }

    private Button getExitButton(){
        Button button = new Button("Exit", () -> {
            System.exit(0);
        });
        button.setSize(200, 50);
        button.setBackgroundColor(Color.BLACK);
        return button;
    }

}
