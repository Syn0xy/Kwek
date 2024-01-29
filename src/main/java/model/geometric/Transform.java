package model.geometric;

public class Transform {
    
    public Vector3 position;
    
    public Vector3 rotation;
    
    public Vector3 scale;

    public Transform(Vector3 position, Vector3 rotation, Vector3 scale) {
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
    }
    
    public Transform(Vector3 position, Vector3 rotation){
        this(position, rotation, new Vector3(1, 1, 1));
    }
    
    public Transform(Vector3 position){
        this(position, new Vector3(0, 0, 0));
    }

    @Override
    public String toString() {
        return "Transform [position=" + position + ", rotation=" + rotation + ", scale=" + scale + "]";
    }

}
