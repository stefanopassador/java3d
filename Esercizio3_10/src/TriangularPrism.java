
import com.sun.j3d.utils.geometry.GeometryInfo;
import com.sun.j3d.utils.geometry.NormalGenerator;

import javax.media.j3d.*;
import javax.vecmath.Point3f;
import javax.vecmath.TexCoord2f;

/**
 * Created by stefanopassador on 20/04/16.
 */
public class TriangularPrism extends Shape3D {
    Point3f frontTop = new Point3f(0.0f, 1.5f, 1.0f);  //top front
    Point3f backTop = new Point3f(0.0f, 1.5f, -1.0f);  //top back
    Point3f frontL = new Point3f(-1.0f, 0.0f, 1.0f); //front left
    Point3f frontR = new Point3f(1.0f, 0.0f, 1.0f); //front right
    Point3f backR = new Point3f(1.0f, 0.0f, -1.0f);  //back right
    Point3f backL = new Point3f(-1.0f, 0.0f, -1.0f);  //back left

    Point3f[] faces = {
            frontR, frontTop, frontL,
            backR, frontTop, frontR,
            frontTop, backR, backTop,
            backL, backTop, backR,
            frontL, backTop, backL,
            backTop, frontL, frontTop,
            frontL, backR, frontR,
            backR, frontL, backL,
    };

    TexCoord2f t[] = {
            new TexCoord2f(0.0f, 1.0f),
            new TexCoord2f(0.0f, 0.0f),
            new TexCoord2f(1.0f, 1.0f),
            new TexCoord2f(1.0f, 0.0f),
    };


    public TriangularPrism(Appearance appearance) {
        setGeometry(createGeometry());
        setAppearance(appearance);
    }

    protected Geometry createGeometry() {
        TriangleArray triangles;
        //TriangleArray.COORDINATES mi dice che i triangoli sono formati da un ARRAY DI COORDINATE
        triangles = new TriangleArray(faces.length, GeometryArray.COORDINATES | GeometryArray.NORMALS |
                GeometryArray.TEXTURE_COORDINATE_2);
        triangles.setCoordinates(0, faces);
        //CV:setto le coordinate
        triangles.setTextureCoordinates(0,0, t);

        // Genero e imposto le normali
        GeometryInfo gi = new GeometryInfo (triangles);
        NormalGenerator normalGenerator = new NormalGenerator ( );
        normalGenerator.generateNormals(gi) ;
//        triangleStrip.setNormals(0, gi.getGeometryArray().getNormalRefFloat());
        // Imposto al mio Shape3D quale GeometryArray userò
        return gi.getGeometryArray();
    }
}
