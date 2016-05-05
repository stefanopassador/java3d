
import javax.media.j3d.*;
import javax.vecmath.Point3f;

/**
 * Created by stefanopassador on 20/04/16.
 */
public class TriangularPrism extends Shape3D {
    Point3f frontTop = new Point3f(0.0f, 1.5f, 1.0f);  //top front
    Point3f backTop = new Point3f(0.0f, 1.5f, -1.0f);  //top back
    Point3f frontL = new Point3f(-1.0f,0.0f, 1.0f); //front left
    Point3f frontR = new Point3f( 1.0f,0.0f, 1.0f); //front right
    Point3f backR = new Point3f( 1.0f,0.0f, -1.0f);  //back right
    Point3f backL = new Point3f(-1.0f,0.0f, -1.0f);  //back left

    Point3f[] faces = {
            frontL, frontR, frontTop,
            frontTop, frontL, backL,
            frontTop, backL, backTop,
            frontTop, frontR, backTop,
            frontR, backTop, backR,
            backR, backL, backTop
    };

    public TriangularPrism(Appearance appearance) {
        setGeometry(createGeometry());
        setAppearance(appearance);

    }

    protected Geometry createGeometry() {
        TriangleArray triangles;
        //TriangleArray.COORDINATES mi dice che i triangoli sono formati da un ARRAY DI COORDINATE
        triangles = new TriangleArray(faces.length, TriangleArray.COORDINATES);
        triangles.setCoordinates(0, faces);
        return triangles;
    }
}
