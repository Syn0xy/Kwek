package model;

import java.util.ArrayList;
import java.util.List;

import model.gameobject.Camera;
import model.gameobject.GameObject;
import model.gameobject.Map;
import model.gameobject.Player;
import model.gameobject.Wall;
import model.geometric.Transform;
import model.geometric.Vector3;
import model.input.Input;
import network.NetworkManager;
import view.util.Subject;

public class GameScene extends Subject {

    private static final boolean LIMIT_FRAMES_PER_SECOND = true;
    
    private static final int FRAMES_PER_SECOND = 60;

    private List<GameObject> gameObjects;

    private NetworkManager networkManager;

    private Camera camera;

    public GameScene(){
        this.gameObjects = new ArrayList<>();
        init();
    }

    public NetworkManager getNetworkManager() {
        return networkManager;
    }

    public Mesh[] getMeshs(){
        List<Mesh> meshs = new ArrayList<>();
        for(GameObject gameObject : gameObjects){
            if(gameObject instanceof Mesh){
                meshs.add((Mesh)gameObject);
            }
        }
        return meshs.toArray(new Mesh[meshs.size()]);
    }

    public List<GameObject> getGameObjects() {
        return gameObjects;
    }

    public Camera getCamera() {
        return camera;
    }

    private void init(){
        int width = 40;
        int height = 20;
        
        for(int i = 0; i < width; i++){
            for (int j = 0; j < height; j++) {
                gameObjects.add(new Map(new Transform(new Vector3(i, 0, j))));

                if(i == 0 || i == width - 1 || j == 0 || j == height - 1){
                    gameObjects.add(new Wall(new Transform(new Vector3(i + 0.5, 0.5, j + 0.5))));
                }
            }
        }

        GameObject player = new Player(new Transform(new Vector3(width / 2, 0.5, height / 2)));
        gameObjects.add(player);

        Vector3 cameraOffset = new Vector3(0, 20, -10);
        camera = new FollowingCamera(player.getTransform(), cameraOffset);
        camera.getTransform().rotation.x -= 65;
        gameObjects.add(camera);
    }

    public void start(){
        double prevTime = 0.0;
        double crntTime = 0.0;
        double maxTime = 1000.0 / FRAMES_PER_SECOND;
        while(true){
            if(LIMIT_FRAMES_PER_SECOND){
                crntTime = System.currentTimeMillis();
                if(crntTime - prevTime > maxTime){
                    prevTime = crntTime;
                    update();
                }
            }else{
                update();
            }
        }
    }

    private void update(){
        for (GameObject gameObject : gameObjects) {
            gameObject.update();
        }
        Input.update();
        notifyObservers();
    }

}
