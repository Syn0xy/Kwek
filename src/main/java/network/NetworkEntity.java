package network;

public class NetworkEntity {

    private String identity;

    private float refreshTime;

    private long currentTime;

    private long previousTime;

    protected NetworkEntity(int refreshTimeInSeconds, String identity){
        this.identity = identity;
        this.refreshTime = refreshTimeInSeconds * 1000;
    }

    protected void update(){
        currentTime = System.currentTimeMillis();
        if(currentTime - previousTime > refreshTime){
            socket();
            previousTime = currentTime;
        }
    }

    private void socket(){
        System.out.println(identity);
    }

}
