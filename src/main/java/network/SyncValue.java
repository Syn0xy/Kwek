package network;

import java.util.ArrayList;
import java.util.List;

import network.util.Subject;

public class SyncValue<E> extends Subject implements Sync {

    private static final List<Sync> syncList = new ArrayList<>();

    private static int counterID = 0;

    private int id;

    private E value;

    private float refreshTime;

    private long currentTime;

    private long previousTime;

    protected SyncValue(E value, float refreshTime){
        this.id = counterID++;
        this.value = value;
        this.refreshTime = refreshTime;
        
    }

    public E getValue() {
        return value;
    }

    @Override
    public void update(){
        currentTime = System.currentTimeMillis();
        if(currentTime - previousTime > refreshTime){
            socket();
            previousTime = currentTime;
        }
    }

    private void socket(){
        System.out.println((int)(refreshTime / 1000) + " secondes !");
        notifyObservers();
    }

    public static void refresh(){
        for(Sync sync : syncList){
            sync.update();
        }
    }

}
