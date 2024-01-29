package view;

import java.awt.Graphics;
import java.awt.Point;

import model.GameScene;
import view.gui.GUI;
import view.scene.GameGUI;
import view.util.Observer;
import view.util.Subject;

public class GameView extends View implements Observer {

    private static final int WIDTH = (int)(SCREEN_WIDTH * (2.0 / 3.0));

    private static final int HEIGHT = (int)(SCREEN_HEIGHT * (2.0 / 3.0));
    
    private static final String TITLE = "League of Losers";
    
    private GUI gui;
    
    public GameView(GameScene gameScene){
        super(WIDTH, HEIGHT);
        this.gui = new GameGUI(this, gameScene);
        gameScene.attach(this);
        repaint();
    }

    @Override
    public String title() {
        return TITLE;
    }

    @Override
    public Point position() {
        return center();
    }

    @Override
    public void paint(Graphics g) {
        gui.paint(g);
        System.out.println("Paint !");
    }

    @Override
    public void update(Subject subj) {
        update();        
    }

    @Override
    public void update(Subject subj, Object data) {
        update();
    }

    public void update(){
        repaint();
        refreshFrames();
    }
    
}