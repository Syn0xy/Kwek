package view;

import java.awt.Point;

import model.GameScene;
import model.input.Input;
import view.scene.GameGUI;
import view.util.Observer;
import view.util.Subject;

public class GameView extends View implements Observer {

    private static final int WIDTH = (int)(SCREEN_WIDTH * (2.0 / 3.0));

    private static final int HEIGHT = (int)(SCREEN_HEIGHT * (2.0 / 3.0));
    
    private static final String TITLE = "League of Losers";
    
    public GameView(GameScene gameScene){
        super(WIDTH, HEIGHT);
        gameScene.attach(this);
        add(new GameGUI(this, gameScene));
        addKeyListener(Input.getInstance());
        addMouseListener(Input.getInstance());
        addMouseMotionListener(Input.getInstance());
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