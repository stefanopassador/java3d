import javax.media.j3d.Appearance;
import javax.media.j3d.Geometry;
import javax.media.j3d.PolygonAttributes;
import javax.media.j3d.Shape3D;

/**
 * Created by stefanopassador on 17/03/16.
 */
abstract class VisualObject extends Shape3D {
    protected Geometry geometry;
    protected Appearance appearance;

    // Impostazione dei NodeComponent .
    public VisualObject() {
        geometry = createGeometry();
        appearance = createAppearance();
        setGeometry(geometry);
        setAppearance(appearance);
    }

    // Metodo per creare la geometria.
    protected abstract Geometry createGeometry();

    // Metodo per creare l’aspetto .
    private Appearance createAppearance() {
        Appearance app = new Appearance();
        //POLYGON_LINE - the polygon is rendered as lines drawn between consecutive vertices.
        //CULL_BACK - culls all front-facing polygons. The default.
        app.setPolygonAttributes(new PolygonAttributes(PolygonAttributes.POLYGON_LINE, PolygonAttributes.CULL_NONE, 0));
        return app;
    }
}