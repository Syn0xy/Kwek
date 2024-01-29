package model.gameobject;

import model.Mesh;
import model.geometric.Transform;

public class Wall extends Mesh {

    private static final String DEFAULT_MESH_FILE_NAME = "cube.obj";
    
    public Wall(Transform transform) {
        super(transform, DEFAULT_MESH_FILE_NAME);
    }

    @Override
    public void update() {
        
    }

}
