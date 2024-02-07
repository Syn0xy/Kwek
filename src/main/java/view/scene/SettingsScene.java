package view.scene;

import java.awt.Color;

import view.gui.Button;
import view.gui.Panel;
import view.gui.Scene;
import view.gui.Text;

public class SettingsScene extends Panel {

    private Scene parentScene;
    
    protected SettingsScene(Scene parentScene){
        this.parentScene = parentScene;
        init();
    }

    private void init(){
        setColor(Color.BLACK);
        Panel p = new Panel(500, 250);
        p.setColor(Color.LIGHT_GRAY);
        p.add(getNameText());
        p.add(getBackButton());
        add(p);
    }

    private Text getNameText(){
        Text text = new Text("League of Losers");
        text.setColor(Color.BLACK);
        text.setSize(200, 50);
        return text;
    }

    private Button getBackButton(){
        Button button = new Button("Back", () -> {
            parentScene.setScene(0);
        });
        button.setSize(200, 50);
        button.setBackgroundColor(Color.BLACK);
        return button;
    }

}
