package model.gameobject;

import model.Mesh;
import model.geometric.Transform;
import model.input.Input;
import model.input.KeyCode;

public class Player extends Mesh {

    private static final String DEFAULT_MESH_FILE_NAME = "player.obj";
    
    public Player(Transform transform) {
        super(transform, DEFAULT_MESH_FILE_NAME);
    }

    @Override
    public void update() {
        if(Input.getKey(KeyCode.D)){
            transform.position.x += 0.1;
        }
        if(Input.getKey(KeyCode.Q)){
            transform.position.x -= 0.1;
        }
        if(Input.getKey(KeyCode.Z)){
            transform.position.z += 0.1;
        }
        if(Input.getKey(KeyCode.S)){
            transform.position.z -= 0.1;
        }
    }

}
