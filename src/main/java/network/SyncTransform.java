package network;

import model.geometric.Transform;

public class SyncTransform extends SyncValue<Transform> {

    private static final float DEFAULT_INTEGER_REFRESHING = 0.75f;

    public SyncTransform(Transform transform){
        super(transform, DEFAULT_INTEGER_REFRESHING);
    }

}
