import javax.media.j3d.Geometry;
import javax.media.j3d.GeometryArray;
import javax.media.j3d.Shape3D;
import javax.media.j3d.TriangleStripArray;
import javax.vecmath.Point3d;
import javax.vecmath.TexCoord2f;
import javax.vecmath.Vector3f;


public class Flat extends VisualObject {
    //	Vertici .
    private static final Point3d P1 = new Point3d(-2.0,	1.0,	0.0);
    private static final Point3d P2 = new Point3d(-2.0, -1.0, 0.0);
    private static final Point3d P3 = new Point3d( 2.0,	1.0,	0.0);
    private static final Point3d P4 = new Point3d( 2.0, -1.0,	0.0);

    //	Coordinate della	Texture	corrispondenti	ad	ogni	vertice .
    private static final TexCoord2f T1 = new TexCoord2f(0.0f , 1.0f);
    private static final TexCoord2f T2 = new TexCoord2f(0.0f , 0.0f);
    private static final TexCoord2f T3 = new TexCoord2f(1.0f , 1.0f);
    private static final TexCoord2f T4 = new TexCoord2f(1.0f , 0.0f);

    //	Normale .
    private static final Vector3f N1 = new Vector3f(0.0f , 0.0f , 1.0f );

    private static final Point3d[]	P = { P1, P2, P3, P4 };
    private static final Vector3f[]	N =  { N1, N1, N1, N1 };
    private static final TexCoord2f[] T =  { T1, T2, T3, T4 };
    private static final int[]	L =  { 4 };

    protected Geometry createGeometry (){
        TriangleStripArray triangleStrip = new TriangleStripArray(
                L[0],
                GeometryArray.COORDINATES |
                        GeometryArray.NORMALS	|
                        GeometryArray.TEXTURE_COORDINATE_2,
                L);

        triangleStrip.setCoordinates(0,P);
        triangleStrip.setNormals(0, N);
        triangleStrip.setTextureCoordinates(0,0,T);

        return triangleStrip ;
    }

    public float getWidth() {
        return 4.0f;
    }

    public float getHeight() {
        return 2.0f;
    }
}