package model.geometric;

public class Vector3 {
    
    public double x;

    public double y;

    public double z;

    public Vector3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3(){
        this(0, 0, 0);
    }

    public double distance(Vector3 vec3){
        return Math.sqrt(
            Math.pow(vec3.x - x, 2) +
            Math.pow(vec3.y - y, 2) +
            Math.pow(vec3.z - z, 2)
        );
    }

    @Override
    public String toString() {
        return "Vector3 [x=" + x + ", y=" + y + ", z=" + z + "]";
    }

}
