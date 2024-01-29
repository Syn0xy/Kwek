package model.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Map;

public class Input implements KeyListener, MouseListener, MouseMotionListener {

    private static Input singleton;
    
    private static Map<KeyCode, InputKeyCode> keysCode;
    
    protected Input(){
        keysCode = InputKeyCode.getInputsKeyCode();
    }
    
    public static Input getInstance(){
        if(singleton == null) singleton = new Input();
        return singleton;
    }

    public static void update(){
        for(InputKeyCode input : keysCode.values()){
            input.update();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        KeyCode key = KeyCode.getKey(e.getKeyCode());
        if(key == null){
            System.err.println(KeyEvent.getKeyText(e.getKeyCode()));
            return;
        }
        keysCode.get(key).keyDown();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        KeyCode key = KeyCode.getKey(e.getKeyCode());
        if(key == null){
            System.err.println(KeyEvent.getKeyText(e.getKeyCode()));
            return;
        }
        keysCode.get(key).keyUp();
        
    }

    @Override
    public void keyTyped(KeyEvent e) {}
    

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        KeyCode key = KeyCode.getKey(e.getButton());
        if(key == null){
            System.err.println("Mouse " + e.getButton());
            return;
        }
        keysCode.get(key).keyDown();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        KeyCode key = KeyCode.getKey(e.getButton());
        if(key == null){
            System.err.println("Mouse " + e.getButton());
            return;
        }
        keysCode.get(key).keyUp();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    public static boolean getKey(KeyCode key){
        InputKeyCode input;
        return ((input = keysCode.get(key)) != null) ? input.isStay() : false;
    }

    public static boolean getKeyDown(KeyCode key){
        InputKeyCode input;
        return ((input = keysCode.get(key)) != null) ? input.isEnter() : false;
    }

    public static boolean getKeyUp(KeyCode key){
        InputKeyCode input;
        return ((input = keysCode.get(key)) != null) ? input.isExit() : false;
    }

}
