package model.gameobject;

import model.Mesh;
import model.geometric.Transform;

public class Map extends Mesh {

    private static final String DEFAULT_MESH_FILE_NAME = "plane.obj";
    
    public Map(Transform transform) {
        super(transform, DEFAULT_MESH_FILE_NAME);
    }

    @Override
    public void update() {

    }

}
