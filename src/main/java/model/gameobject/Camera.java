package model.gameobject;

import model.geometric.Transform;

public abstract class Camera extends GameObject {

    private static final int DEFAULT_FIELD_OF_VIEW = 90;

    private int fieldOfView;

    protected Camera(Transform transform){
        super(transform);
        this.fieldOfView = DEFAULT_FIELD_OF_VIEW;
    }

    public int getFieldOfView() {
        return fieldOfView;
    }

    public void setFieldOfView(int fieldOfView) {
        this.fieldOfView = fieldOfView;
    }

}
