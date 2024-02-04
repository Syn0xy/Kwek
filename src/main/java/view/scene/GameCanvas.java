package view.scene;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Arrays;

import model.GameScene;
import model.Mesh;
import model.MeshDistanceComparator;
import model.Triangle;
import model.gameobject.Camera;
import view.gui.Button;
import view.gui.GUIContainer;
import view.gui.Panel;
import view.gui.Scene;
import view.gui.HorizontalAlignment;
import view.gui.VerticalAlignment;

public class GameCanvas extends Panel {

    private static final Color BACKGROUND_COLOR = Color.BLACK;

    private Scene parentScene;
    
    private Mesh[] meshs;
    
    private Camera currentCamera;

    private int width;

    private int height;

    public GameCanvas(Scene parentScene, GameScene gameScene){
        this.parentScene = parentScene;
        this.meshs = gameScene.getMeshs();
        this.currentCamera = gameScene.getCamera();
        init();
    }

    private void init(){
        setSize(100, 100);
        Button exitButton = new Button("Back", () -> {
            parentScene.setScene(0);
        });

        exitButton.setHorizontalAlignment(HorizontalAlignment.CENTER);
        exitButton.setVerticalAlignment(VerticalAlignment.MIDDLE);
        exitButton.setBackgroundColor(Color.WHITE);
        exitButton.setColor(Color.BLACK);
        exitButton.setFill(true);

        add(exitButton);
    }
    
    @Override
    public void paint(Graphics g, GUIContainer container) {
        width = (getWidth() * container.getWidth()) / 100;
        height = (getHeight() * container.getHeight()) / 100;
        clearScreen(g);
        draw(g);
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
