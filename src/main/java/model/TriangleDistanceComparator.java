package model;

import java.util.Comparator;

import model.gameobject.Camera;
import model.geometric.Vector3;

public class TriangleDistanceComparator implements Comparator<Triangle> {

    private Vector3 position;

    public TriangleDistanceComparator(Camera camera){
        this.position = camera.getTransform().position;
    }

    @Override
    public int compare(Triangle t1, Triangle t2) {
        return Double.compare(
            t2.center.distance(position),
            t1.center.distance(position)
        );
    }

}
