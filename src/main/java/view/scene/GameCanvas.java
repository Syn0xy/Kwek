package view.scene;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Arrays;

import model.GameScene;
import model.Mesh;
import model.MeshDistanceComparator;
import model.Triangle;
import model.gameobject.Camera;
import view.gui.GUIContainer;
import view.gui.Panel;

public class GameCanvas extends Panel {

    private static final Color BACKGROUND_COLOR = Color.BLACK;
    
    private Mesh[] meshs;
    
    private Camera currentCamera;

    public GameCanvas(GameScene gameScene){
        this.meshs = gameScene.getMeshs();
        this.currentCamera = gameScene.getCamera();
    }
    
    @Override
    public void paint(Graphics g, GUIContainer container) {
        clearScreen(g);
        draw(g);
    }

    private void clearScreen(Graphics g){
        g.setColor(BACKGROUND_COLOR);
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    private void draw(Graphics g){
        updateMeshs();
        sortMeshs();
        drawMeshs(g);
    }

    private void updateMeshs(){
        int width = getWidth();
        int height = getHeight();
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
