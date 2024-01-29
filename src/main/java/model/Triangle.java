package model;

import java.awt.Color;
import java.awt.Polygon;

import model.gameobject.Camera;
import model.geometric.Transform;
import model.geometric.Vector3;
import model.util.MutableDouble;

public class Triangle extends Polygon{

    private static final int SIDES_TRIANGLE = 3;
    
    private static final boolean VISIBLE = false;
    
    private Transform transform;
    private Vector3[] points;
    private Color color;

    private boolean visible;
    private Vector3[] spacePoints;
    private boolean[] visiblePoints;
    protected Vector3 center;

    public Triangle(Transform transform, Vector3 point1, Vector3 point2, Vector3 point3, Color color){
        super(new int[SIDES_TRIANGLE], new int[SIDES_TRIANGLE], SIDES_TRIANGLE);
        this.transform = transform;
        this.points = new Vector3[]{
            point1,
            point2,
            point3
        };
        this.color = color;
        this.visible = VISIBLE;
        this.spacePoints = new Vector3[points.length];
        this.visiblePoints = new boolean[points.length];
        this.center = new Vector3();
        init();
    }

    private void init(){
        for(int i = 0; i < spacePoints.length; i++){
            spacePoints[i] = new Vector3();
        }
    }
    
    public boolean isVisible() {
        return visible;
    }

    public Vector3 getCenter() {
        return center;
    }

    public Color getColor() {
        return color;
    }

    public void reload(int width, int height, Camera c){
        for(int i = 0; i < spacePoints.length; i++){
            reloadPositionSpace(i);
            reloadLocationInScreen(width, height, c, i);
        }
        calculateVisibility();
        calculateCenter();
    }
    
    private void reloadPositionSpace(int index){
        Vector3 v = new Vector3(
            points[index].x * transform.scale.x,
            points[index].y * transform.scale.y,
            points[index].z * transform.scale.z
        );
        rotate(v, transform.rotation);
        v.x += transform.position.x;
        v.y += transform.position.y;
        v.z += transform.position.z;
        spacePoints[index] = v;
    }

    private void reloadLocationInScreen(int width, int height, Camera c, int index){
        Vector3 v = spacePoints[index];
        Vector3 cp = c.getTransform().position;
        Vector3 cr = c.getTransform().rotation;
        
        MutableDouble px = new MutableDouble(v.x - cp.x);
        MutableDouble py = new MutableDouble(cp.y - v.y);
        MutableDouble pz = new MutableDouble(v.z - cp.z);
        
        rotation(px, pz, - cr.y);
        rotation(py, px, cr.z);
        rotation(py, pz, cr.x);

        visiblePoints[index] = pz.getValue() >= 0;
        if(visiblePoints[index]){
            double screenProportion = width / (2 * StrictMath.tan(Math.toRadians(c.getFieldOfView() / 2)));
            double proportion = screenProportion / pz.getValue();
            xpoints[index] = (int)(px.getValue() * proportion + width / 2);
            ypoints[index] = (int)(py.getValue() * proportion + height / 2);
        }
    }

    private void calculateVisibility(){
        visible = visiblePoints[0] && visiblePoints[1] && visiblePoints[2];
    }

    private void calculateCenter(){
        center.x = (spacePoints[0].x + spacePoints[1].x + spacePoints[2].x) / 3;
        center.y = (spacePoints[0].y + spacePoints[1].y + spacePoints[2].y) / 3;
        center.z = (spacePoints[0].z + spacePoints[1].z + spacePoints[2].z) / 3;
    }
    
    private static void rotation(MutableDouble a, MutableDouble b, double rotation){
        double rotRadian = StrictMath.atan2(a.getValue(), b.getValue()) + Math.toRadians(rotation);
        double distance = Math.sqrt(Math.pow(a.getValue(), 2) + Math.pow(b.getValue(), 2));

        a.setValue(distance * StrictMath.sin(rotRadian));
        b.setValue(distance * StrictMath.cos(rotRadian));
    }

    private static void rotate(Vector3 position, Vector3 rotation){
        MutableDouble verticeX = new MutableDouble(position.x);
        MutableDouble verticeY = new MutableDouble(position.y);
        MutableDouble verticeZ = new MutableDouble(position.z);
        
        rotation(verticeY, verticeZ, rotation.x);
        rotation(verticeY, verticeX, rotation.z);
        rotation(verticeX, verticeZ, rotation.y);

        position.x = verticeX.getValue();
        position.y = verticeY.getValue();
        position.z = verticeZ.getValue();
    }
}