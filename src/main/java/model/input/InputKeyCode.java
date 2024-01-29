package model.input;

import java.util.HashMap;
import java.util.Map;

public class InputKeyCode {
    private boolean enter;
    private boolean stay;
    private boolean exit;
    private boolean nothing;

    public InputKeyCode(){
        this.enter = false;
        this.stay = false;
        this.exit = false;
        this.nothing = true;
    }
    
    public boolean isEnter(){ return enter; }
    public boolean isStay(){ return stay; }
    public boolean isExit(){ return exit; }
    public boolean isNothing(){ return nothing; }

    protected void update(){
        if(nothing) return;
        if(enter) enter = false;
        if(exit) reset();
    }
    
    protected void keyDown(){
        if(nothing) enter = true;
        stay = true;
        exit = false;
        nothing = false;
    }
    
    protected void keyUp(){
        exit = true;
    }

    private void reset(){
        this.enter = false;
        this.stay = false;
        this.exit = false;
        this.nothing = true;
    }

    public static Map<KeyCode, InputKeyCode> getInputsKeyCode(){
        Map<KeyCode, InputKeyCode> inputs = new HashMap<>();
        for(KeyCode key : KeyCode.values()) inputs.put(key, new InputKeyCode());
        return inputs;
    }

    @Override
    public String toString() {
        return "InputKeyCode [enter=" + enter + ", stay=" + stay + ", exit=" + exit + ", nothing=" + nothing + "]";
    }

}
