package model;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

import model.geometric.Transform;
import model.geometric.Vector3;

public class FileTranslatorOBJ {

    private static final String SEPARATOR = File.separator;

    private static final String DEFAULT_OBJ_PATH = "res" + SEPARATOR + "model" + SEPARATOR;

    private final static String VERTICES = "v";

    private final static String TRIANGLE_INDICES = "f";

    private final static String INDICES_DELIMITER = "/";
    
    public static MeshData load(Transform transform, String fileName){
        File file = new File(DEFAULT_OBJ_PATH + fileName);
        
        if(file.exists()){
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                List<Triangle> triangles = new ArrayList<>();
                List<Vector3> vectors = new ArrayList<>();
                Stack<Integer[]> triangleIndices = new Stack<>();

                while(br.ready()){
                    String line = br.readLine();
                    
                    if(line.startsWith(VERTICES)){
                        String infoLine = line.substring(2, line.length());
                        vectors.add(getVertice(infoLine));
                    }else if(line.startsWith(TRIANGLE_INDICES)){
                        String infoLine = line.substring(2, line.length());
                        Integer[] indices = getTriangleWithIndices(infoLine);
                        triangleIndices.add(indices);
                    }
                }

                while(!triangleIndices.isEmpty()){
                    Integer[] indices = triangleIndices.remove(0);
                    Vector3 v1 = vectors.get(indices[0]);
                    Vector3 v2 = vectors.get(indices[1]);
                    Vector3 v3 = vectors.get(indices[2]);
                    Color color = new Color(
                        (int)(Math.random() * 255),
                        (int)(Math.random() * 255),
                        (int)(Math.random() * 255)
                    );
                    triangles.add(new Triangle(transform, v1, v2, v3, color));
                }
                
                Triangle[] arrayTriangles = triangles.toArray(new Triangle[triangles.size()]);
                return new MeshData(arrayTriangles);
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
        
        return null;
    }

    private static Vector3 getVertice(String line){
        Scanner sc = new Scanner(line);
        double x = 0, y = 0, z = 0;
        if(sc.hasNext()) x = Double.valueOf(sc.next());
        if(sc.hasNext()) y = Double.valueOf(sc.next());
        if(sc.hasNext()) z = Double.valueOf(sc.next());
        sc.close();
        return new Vector3(x, y, z);
    }

    private static Integer[] getTriangleWithIndices(String line){
        Scanner sc = new Scanner(line);
        Integer[] indices = null;
        if(sc.hasNext()) {
            Integer p1 = nextIndice(sc.next());

            if(sc.hasNext()) {
                Integer p2 = nextIndice(sc.next());

                if(sc.hasNext()) {
                    Integer p3 = nextIndice(sc.next());

                    indices = new Integer[]{ p1, p2, p3 };
                }
            }
        }
        sc.close();
        return indices;
    }

    private static Integer nextIndice(String line){
        return Integer.parseInt(line.split(INDICES_DELIMITER)[0]) - 1;
    }

}
