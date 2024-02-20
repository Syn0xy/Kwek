package network;

public class SyncInteger extends SyncValue<Integer> {

    private static final float DEFAULT_INTEGER_REFRESHING = 1;

    public SyncInteger(Integer integer){
        super(integer, DEFAULT_INTEGER_REFRESHING);
    }

}
