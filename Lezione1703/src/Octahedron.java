import javax.media.j3d.Geometry;
import javax.media.j3d.TriangleArray;
import javax.vecmath.Point3d;

/**
 * Created by stefanopassador on 17/03/16.
 */
public class Octahedron extends VisualObject {
    private static final Point3d A = new Point3d(0.0, 0.0, 1.0);
    private static final Point3d B = new Point3d(0.0, 0.0, -1.0);
    private static final Point3d C = new Point3d(1.0, 1.0, 0.0);
    private static final Point3d D = new Point3d(1.0, -1.0, 0.0);
    private static final Point3d E = new Point3d(-1.0, -1.0, 0.0);
    private static final Point3d F = new Point3d(-1.0, 1.0, 0.0);

    private static
    final Point3d[] faces = {
            A, C, D,
            A, D, E,
            A, E, F,
            A, F, C,
            B, C, D,
            B, D, E,
            B, E, F,
            B, F, C
    };

    protected Geometry createGeometry() {
        TriangleArray octahedron;
        //TriangleArray.COORDINATES mi dice che i triangoli sono formati da un ARRAY DI COORDINATE
        octahedron = new TriangleArray(faces.length,
                TriangleArray.COORDINATES);
        octahedron.setCoordinates(0, faces);
        return octahedron;
    }
}
