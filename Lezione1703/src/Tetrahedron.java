import javax.media.j3d.Geometry;
import javax.media.j3d.TriangleArray;
import javax.vecmath.Point3d;

/**
 * Created by stefanopassador on 17/03/16.
 */
public class Tetrahedron extends VisualObject {
    private static final Point3d P1 = new Point3d(1.0, 1.0, 1.0);
    private static final Point3d P2 = new Point3d(-1.0, -1.0, 1.0);
    private static final Point3d P3 = new Point3d(-1.0, 1.0, -1.0);
    private static final Point3d P4 = new Point3d(1.0, -1.0, -1.0);
    private static
    final Point3d[] faces = {
            P1, P3, P2,
            P1, P2, P4,
            P2, P3, P4,
            P1, P4, P3
    };

    protected Geometry createGeometry() {
        TriangleArray triangles;
        //TriangleArray.COORDINATES mi dice che i triangoli sono formati da un ARRAY DI COORDINATE
        triangles = new TriangleArray(faces.length,
                TriangleArray.COORDINATES);
        triangles.setCoordinates(0, faces);
        return triangles;
    }
}