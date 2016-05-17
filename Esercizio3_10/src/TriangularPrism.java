
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

    TexCoord2f frontTopT = new TexCoord2f(0.0f, 1.5f);  //top front
    TexCoord2f backTopT = new TexCoord2f(0.0f, 1.5f);  //top back
    TexCoord2f frontLT = new TexCoord2f(-1.0f, 0.0f); //front left
    TexCoord2f frontRT = new TexCoord2f(1.0f, 0.0f); //front right
    TexCoord2f backRT = new TexCoord2f(1.0f, 0.0f);  //back right
    TexCoord2f backLT = new TexCoord2f(-1.0f, 0.0f);  //back left

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
            frontRT, frontTopT, frontLT,
            backRT, frontTopT, frontRT,
            frontTopT, backRT, backTopT,
            backLT, backTopT, backRT,
            frontLT, backTopT, backLT,
            backTopT, frontLT, frontTopT,
            frontLT, backRT, frontRT,
            backRT, frontLT, backLT,
    };


    public TriangularPrism(Appearance appearance) {
        setGeometry(createGeometry());
        setAppearance(appearance);
    }

    protected Geometry createGeometry() {
        TriangleArray triangles;
        triangles = new TriangleArray(faces.length,
                GeometryArray.COORDINATES |
                        GeometryArray.NORMALS |
                        GeometryArray.TEXTURE_COORDINATE_2);
        triangles.setCoordinates(0, faces);
        triangles.setTextureCoordinates(0,0, t);

        // Genero e imposto le normali
        GeometryInfo gi = new GeometryInfo (triangles);
        NormalGenerator normalGenerator = new NormalGenerator ( );
        normalGenerator.generateNormals(gi) ;
        // Imposto al mio Shape3D quale GeometryArray userò
        return gi.getGeometryArray();
    }
}
