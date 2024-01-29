package model.gameobject;

import model.geometric.Transform;

public abstract class GameObject {

    protected Transform transform;

    protected GameObject(Transform transform){
        this.transform = transform;
    }

    public abstract void update();

    public Transform getTransform() {
        return transform;
    }

}
