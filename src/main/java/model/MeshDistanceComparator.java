package model;

import java.util.Comparator;

import model.gameobject.Camera;
import model.geometric.Vector3;

public class MeshDistanceComparator implements Comparator<Mesh> {

    private Vector3 position;

    public MeshDistanceComparator(Camera camera){
        this.position = camera.getTransform().position;
    }

    @Override
    public int compare(Mesh m1, Mesh m2) {
        return Double.compare(
            m2.getCenter().distance(position),
            m1.getCenter().distance(position)
        );
    }

}
