package model;

import model.gameobject.Camera;
import model.gameobject.GameObject;
import model.geometric.Transform;
import model.geometric.Vector3;

public abstract class Mesh extends GameObject {

    private MeshData data;

    protected Mesh(Transform transform, String fileName){
        super(transform);
        this.data = load(transform, fileName);
    }

    public Triangle[] getTriangles() {
        return data.getTriangles();
    }

    public Vector3 getCenter(){
        return new Vector3(
            transform.position.x + data.center.x,
            transform.position.y + data.center.y,
            transform.position.z + data.center.z
        );
    }

    public void update(int width, int height, Camera camera){
        data.update(width, height, camera);
    }

    public static MeshData load(Transform transform, String fileName) {
        return FileTranslatorOBJ.load(transform, fileName);
    }
}
