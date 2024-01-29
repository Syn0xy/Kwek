package model;

import java.util.Arrays;

import model.gameobject.Camera;
import model.geometric.Vector3;

public class MeshData {

    private Triangle[] triangles;

    protected Vector3 center;
    
    protected MeshData(Triangle[] triangles){
        this.triangles = triangles;
        center();
    }

    private void center(){
        Triangle crntTriangle;
        center = new Vector3(0, 0, 0);
        for(int i = 0; i < triangles.length; i++){
            crntTriangle = triangles[i];
            center.x += crntTriangle.center.x;
            center.y += crntTriangle.center.y;
            center.z += crntTriangle.center.z;
        }
        center.x /= triangles.length;
        center.y /= triangles.length;
        center.z /= triangles.length;
    }

    public Triangle[] getTriangles() {
        return triangles;
    }

    public void update(int width, int height, Camera camera){
        for (int i = 0; i < triangles.length; i++) {
            triangles[i].reload(width, height, camera);
        }
        Arrays.sort(triangles, new TriangleDistanceComparator(camera));
    }
}
