package view;

import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import model.GameScene;
import model.input.Input;
import view.scene.GameGUI;
import view.util.Observer;
import view.util.Subject;

public class GameView extends View implements Observer {

    private static final int WIDTH = (int)(SCREEN_WIDTH * (2.0 / 3.0));

    private static final int HEIGHT = (int)(SCREEN_HEIGHT * (2.0 / 3.0));
    
    private static final String TITLE = "League of Losers";
    
    private static final String ICON_PATH = "res" + File.separator + "icon.png";
    
    public GameView(GameScene gameScene){
        super(WIDTH, HEIGHT);
        gameScene.attach(this);
        add(new GameGUI(gameScene));
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
    public Image icon() {
        try {
            File f = new File(ICON_PATH);
            return ImageIO.read(f);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return null;
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
        revalidate();
        refreshFrames();
    }
    
}