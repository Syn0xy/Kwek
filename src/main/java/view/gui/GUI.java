package view.gui;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import model.input.Input;

public abstract class GUI extends GUIContainer implements Scene {

    private static final String SCENE_INDEX_OUT_OF_BOUNDS = "L'indice de la scene n'est pas valide: ";

    private Container container;
    
    private GUIComponent[] scenes;

    private int currentScene;
    
    public GUI(Container container){
        this.container = container;
        this.scenes = getScenes();
        this.currentScene = 0;
        container.addKeyListener(Input.getInstance());
        container.addMouseListener(Input.getInstance());
        container.addMouseMotionListener(Input.getInstance());
        setSize(container.getWidth(), container.getHeight());
        setScene(currentScene);
        init();
    }

    public int getWidth() {
        return container.getWidth();
    }

    public int getHeight() {
        return container.getHeight();
    }

    protected abstract void init();

    public void mousePressed(int button, int x, int y){}
    public void mouseClicked(int button, int x, int y){}
    public void mouseEntered(int x, int y){}
    public void mouseExited(int x, int y){}
    public void mouseReleased(int button, int x, int y){}
    public void mouseDragged(int button, int x, int y){}
    public void mouseMoved(int x, int y){}

    public final void paint(Graphics g) {
        scenes[currentScene].paint(g, this);
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

    // private boolean clickComponents(int mx, int my){
    //     GUIComponent hover = hoverComponents(mx, my);
    //     boolean isNotNull = hover != null;
        
    //     if(isNotNull && hover instanceof Button){
    //         return ((Button)hover).click(mx, my);
    //     }

    //     return isNotNull;
    // }

    // private GUIComponent hoverComponents(int mx, int my){
    //     Stack<GUIComponent> crntComponents = new Stack<>();
    //     GUIComponent lastComponent = null;
        
    //     for(GUIComponent component : components){
    //         component.hover = false;
    //         if(component.isHover(mx, my)){
    //             crntComponents.add(component);
    //         }
    //     }
        
    //     if(!crntComponents.isEmpty()){
    //         lastComponent = crntComponents.lastElement();
    //         lastComponent.hover = true;
    //     }

    //     return lastComponent;
    // }
    
    @Override
    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        // if(!clickComponents(mx, my)){
        //     mousePressed(e.getButton(), mx, my);
        // }
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
        // hoverComponents(mx, my);
        mouseMoved(mx, my);
    }
}
