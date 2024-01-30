package view.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public abstract class GUIComponent extends GUIContainer {

    private static final Color DEFAULT_COLOR = Color.LIGHT_GRAY;

    private static final VerticalAlignment VERTICAL_ALIGNMENT = VerticalAlignment.MIDDLE;
    
    private static final HorizontalAlignment HORIZONTAL_ALIGNMENT = HorizontalAlignment.CENTER;

    protected List<GUIComponent> components;

    protected VerticalAlignment verticalAlignment;

    protected HorizontalAlignment horizontalAlignment;

    protected Color color;
    
    protected boolean hover;

    protected GUIComponent(){
        this.components = new ArrayList<>();
        this.verticalAlignment = VERTICAL_ALIGNMENT;
        this.horizontalAlignment = HORIZONTAL_ALIGNMENT;
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

    protected boolean clickComponents(int mx, int my){
        GUIComponent hover = hoverComponents(mx, my);
        boolean isNotNull = hover != null;
        
        if(isNotNull && hover instanceof Button){
            return ((Button)hover).click(mx, my);
        }

        return isNotNull;
    }
    
    protected GUIComponent hoverComponents(int mx, int my){
        Stack<GUIComponent> crntComponents = new Stack<>();
        GUIComponent lastComponent = null;
        
        for(GUIComponent component : components){
            component.hover = false;
            if(component.isHover(mx, my)){
                crntComponents.add(component);
            }
        }
        
        if(!crntComponents.isEmpty()){
            lastComponent = crntComponents.lastElement();
            lastComponent.hover = true;
        }else if(isHover(mx, my)){
            lastComponent = this;
        }

        return lastComponent;
    }

}
