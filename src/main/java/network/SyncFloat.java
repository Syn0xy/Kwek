package network;

public class SyncFloat extends SyncValue<Float> {

    private static final float DEFAULT_FLOAT_REFRESHING = 1;

    public SyncFloat(Float value){
        super(value, DEFAULT_FLOAT_REFRESHING);
    }

}
