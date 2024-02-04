package view.gui;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

public abstract class GUI extends JPanel implements Scene, MouseListener, MouseMotionListener {

    private static final String SCENE_INDEX_OUT_OF_BOUNDS = "L'indice de la scene n'est pas valide: ";

    private Container container;
    
    private GUIComponent[] scenes;

    private int currentScene;
    
    public GUI(Container container){
        this.container = container;
        this.currentScene = 0;
    }

    public int getWidth() {
        return container.getWidth();
    }

    public int getHeight() {
        return container.getHeight();
    }

    protected void init(){
        this.scenes = getScenes();
        addMouseListener(this);
        addMouseMotionListener(this);
        setScene(currentScene);
    }

    public void mousePressed(int button, int x, int y){}
    public void mouseClicked(int button, int x, int y){}
    public void mouseEntered(int x, int y){}
    public void mouseExited(int x, int y){}
    public void mouseReleased(int button, int x, int y){}
    public void mouseDragged(int button, int x, int y){}
    public void mouseMoved(int x, int y){}

    public final void paint(Graphics g) {
        GUIContainer container = new GUIContainer(getWidth(), getHeight());
        scenes[currentScene].paint(g, container);
    }
    
    public void setScene(int sceneIndex){
        if(isValidSceneIndex(sceneIndex)){
            currentScene = sceneIndex;
            container.repaint();
        }else{
            throw new IndexOutOfBoundsException(SCENE_INDEX_OUT_OF_BOUNDS + sceneIndex);
        }
    }
    
    private boolean isValidSceneIndex(int sceneIndex){
        return sceneIndex >= 0 && sceneIndex < scenes.length;
    }

    private boolean clickComponents(int mx, int my){
        return scenes[currentScene].clickComponents(mx, my);
    }

    private void hoverComponent(int mx, int my){
        GUIComponent hoverComponent = scenes[currentScene].hoverComponents(mx, my);
        if(hoverComponent != null){
            hoverComponent.hover = true;
        }
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        if(!clickComponents(mx, my)){
            mousePressed(e.getButton(), mx, my);
        }
    }
    
    @Override
    public final void mouseClicked(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        mouseClicked(e.getButton(), mx, my);
    }

    @Override
    public final void mouseEntered(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        mouseEntered(mx, my);
    }

    @Override
    public final void mouseExited(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        mouseExited(mx, my);
    }

    @Override
    public final void mouseReleased(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        mouseReleased(e.getButton(), mx, my);
    }

    @Override
    public final void mouseDragged(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        mouseDragged(e.getButton(), mx, my);
    }

    @Override
    public final void mouseMoved(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        hoverComponent(mx, my);
        mouseMoved(mx, my);
    }
}
