package view.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public abstract class GUIComponent extends GUIContainer {

    private static final Color DEFAULT_COLOR = Color.LIGHT_GRAY;

    protected List<GUIComponent> components;

    protected Color color;
    
    protected boolean hover;

    protected GUIComponent(){
        this.components = new ArrayList<>();
        this.color = DEFAULT_COLOR;
    }

    protected void paint(Graphics graphics, GUIContainer container){
        for(GUIComponent component : components){
            component.paint(graphics, this);
        }
    }

    public boolean add(GUIComponent component){
        return components.add(component);
    }

    public boolean remove(GUIComponent component){
        return components.remove(component);
    }

    public void removeAll(){
        components.clear();
    }

    public void setColor(Color color){
        this.color = color;
    }
    
    protected boolean isHover(int mx, int my){
        return mx >= positionx && mx <= positionx + width && my >= positiony && my <= positiony + height;
    }
}
