package view.scene;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Arrays;

import model.GameScene;
import model.Mesh;
import model.MeshDistanceComparator;
import model.Triangle;
import model.gameobject.Camera;
import network.SyncInteger;
import view.gui.AxisAlignment;
import view.gui.Button;
import view.gui.GUIContainer;
import view.gui.Panel;
import view.gui.Scene;
import view.gui.Text;
import view.gui.HorizontalAlignment;
import view.gui.VerticalAlignment;

public class GameCanvas extends Panel {

    private static final Color BACKGROUND_COLOR = Color.BLACK;

    private Scene parentScene;

    private int width;

    private int height;
    
    private Mesh[] meshs;
    
    private Camera currentCamera;

    private SyncInteger teamScore1;

    private SyncInteger teamScore2;

    public GameCanvas(Scene parentScene, GameScene gameScene){
        this.parentScene = parentScene;
        this.meshs = gameScene.getMeshs();
        this.currentCamera = gameScene.getCamera();
        this.teamScore1 = gameScene.getTeamScore1();
        this.teamScore2 = gameScene.getTeamScore2();
        init();
    }

    private void init(){
        setColor(new Color(0, 0, 0, 0));
        setAxisAlignment(AxisAlignment.X_AXIS);
        setHorizontalAlignment(HorizontalAlignment.RIGHT);
        setVerticalAlignment(VerticalAlignment.LOWER);
        
        Button exitButton = new Button("Back", () -> {
            parentScene.setScene(0);
        });

        Text text = new Text("Score: " + teamScore1.getValue() + " / " + teamScore2.getValue());
        text.setHorizontalAlignment(HorizontalAlignment.LEFT);
        text.setSize(100, 50);

        exitButton.setTextHorizontalAlignment(HorizontalAlignment.CENTER);
        exitButton.setTextVerticalAlignment(VerticalAlignment.MIDDLE);
        exitButton.setBackgroundColor(Color.WHITE);
        exitButton.setColor(Color.BLACK);
        exitButton.setFill(true);

        add(text);
        add(exitButton);
    }
    
    @Override
    public void paint(Graphics g, GUIContainer container) {
        width = getWidth();
        height = getHeight();
        clearScreen(g);
        draw(g);
        super.paint(g, this);
    }

    private void clearScreen(Graphics g){
        g.setColor(BACKGROUND_COLOR);
        g.fillRect(0, 0, width, height);
    }

    private void draw(Graphics g){
        updateMeshs();
        sortMeshs();
        drawMeshs(g);
    }
    
    private void updateMeshs(){
        for(int i = 0; i < meshs.length; i++){
            meshs[i].update(width, height, currentCamera);
        }
    }

    private void sortMeshs(){
        Arrays.sort(meshs, new MeshDistanceComparator(currentCamera));
    }

    private void drawMeshs(Graphics g){
        Triangle[] triangles;
        Triangle crntTriangle;
        for(int i = 0; i < meshs.length; i++){
            triangles = meshs[i].getTriangles();
            for(int j = 0; j < triangles.length; j++){
                crntTriangle = triangles[j];
                if(crntTriangle.isVisible()){
                    g.setColor(crntTriangle.getColor());
                    g.fillPolygon(crntTriangle);
                }
            }
        }
    }

}
