import javax.media.j3d.*;
import javax.vecmath.Point3f;

/**
 * Questa figura 3D mostra il tronco di una piramide quadrata.
 * Created by stefanopassador on 31/03/16.
 */
public class TrunkedSquarePyramid extends Shape3D {
    public static final float TOP = 1.f;
    public static final float BOTTOM = -1.0f;

    protected Point3f v[] = null;
    protected TriangleStripArray triangleStrip = null;
    protected PolygonAttributes polygonAttributes = new PolygonAttributes();
    protected Appearance appearance = new Appearance();

    /**
     * Costruttore
     */
    public TrunkedSquarePyramid() {
        // Numero delle facce
        int faces = 4;
        // Creo un array di Point3f per generare i triangoli che costruiscono il tronco di piramide
        v = new Point3f[(faces + 1) * 2];
        for (int i = 0; i < faces; i++) {
            // Calcolo la posizione di ogni punto per le coordinate x e y (x = sin(angle) e y = cos(angle))
            double angle = 2.0 * Math.PI * (double) i / (double) faces;
            float x = (float) Math.sin(angle);
            float y = (float) Math.cos(angle);
            // Imposto il valore del punto in basso, che ha come posizione su z BOTTOM
            v[i * 2 + 0] = new Point3f(x, y, BOTTOM);
            // Imposto il valore del punto in alto, che ha come posizione su z TOP
            // Le coordinate x e y sono divise per 3 per definire la differenza di grandezza dalla base
            v[i * 2 + 1] = new Point3f(x / 3, y / 3, TOP);
        }
        // Aggiungo gli ultimi angoli che completano la figura
        v[faces * 2 + 0] = new Point3f(0.0f, 1.0f, BOTTOM);
        v[faces * 2 + 1] = new Point3f(0.0f, 1.0f / 3, TOP);

        int[] stripCounts = {(faces + 1) * 2};
        // Creo un TriangleStripArray
        triangleStrip = new TriangleStripArray((faces + 1) * 2, GeometryArray.COORDINATES, stripCounts);

        // Definisco quali coordinate userà il TriangleStripArray
        triangleStrip.setCoordinates(0, v);
        // Imposto al mio Shape3D quale GeometryArray userò
        setGeometry(triangleStrip);

        // Impostazione aspetto wireframe
        polygonAttributes.setPolygonMode(PolygonAttributes.POLYGON_LINE);
        polygonAttributes.setCullFace(PolygonAttributes.CULL_NONE);
        appearance.setPolygonAttributes(polygonAttributes);
        setAppearance(appearance);
    }
}
