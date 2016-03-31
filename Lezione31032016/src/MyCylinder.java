import javax.media.j3d.*;
import javax.vecmath.Point3f;
import java.awt.*;

/**
 * Created by stefanopassador on 31/03/16.
 */
public class MyCylinder extends Shape3D {
    public static final float TOP = 1.0f;
    public static final float BOTTOM = -1.0f;

    protected Point3f v[] = null;
    protected TriangleStripArray triangleStrip = null;
    protected PolygonAttributes polygonAttributes = new PolygonAttributes();
    protected Appearance appearance = new Appearance();

    public MyCylinder(int steps) {
        v = new Point3f[(steps + 1) * 2];
        for (int i = 0; i < steps; i++) {
            double angle = 2.0 * Math.PI * (double) i / (double) steps;
            float x = (float) Math.sin(angle);
            float y = (float) Math.cos(angle);
            v[i * 2 + 0] = new Point3f(x, y, BOTTOM);
            v[i * 2 + 1] = new Point3f(x, y, TOP);
        }
        v[steps * 2 + 0] = new Point3f(0.0f, 1.0f, BOTTOM);
        v[steps * 2 + 1] = new Point3f(0.0f, 1.0f, TOP);

        int[] stripCounts = {(steps + 1) * 2};
        triangleStrip = new TriangleStripArray((steps + 1) * 2, GeometryArray.COORDINATES, stripCounts);

        triangleStrip.setCoordinates(0, v);
        setGeometry(triangleStrip);

        // Impostazione aspetto wireframe
        polygonAttributes.setPolygonMode(PolygonAttributes.POLYGON_LINE);
        polygonAttributes.setCullFace(PolygonAttributes.CULL_NONE);
        appearance.setPolygonAttributes(polygonAttributes);
        setAppearance(appearance);
    }
}
