package model;

import model.gameobject.Camera;
import model.geometric.Transform;
import model.geometric.Vector3;

public class FollowingCamera extends Camera {

    private Transform follower;

    private Vector3 offset;

    public FollowingCamera(Transform follower, Vector3 offset){
        super(new Transform(new Vector3(follower.position.x, follower.position.y, follower.position.z)));
        this.follower = follower;
        this.offset = offset;
    }
    
    @Override
    public void update() {
        transform.position.x = follower.position.x + offset.x;
        transform.position.y = follower.position.y + offset.y;
        transform.position.z = follower.position.z + offset.z;
    }
    
}
