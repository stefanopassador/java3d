
import com.sun.j3d.utils.geometry.GeometryInfo;
import com.sun.j3d.utils.geometry.NormalGenerator;
import com.sun.j3d.utils.geometry.Primitive;

import javax.media.j3d.*;
import javax.vecmath.Point3f;
import javax.vecmath.TexCoord2f;

/**
 * Questa figura 3D mostra il tronco di una piramide quadrata.
 * Created by stefanopassador on 31/03/16.
 */
public class TrunkedSquarePyramid extends Shape3D {
    public static final float TOP = 0.1f;
    public static final float BOTTOM = 0.0f;

    protected Point3f v[] = null;
    protected TriangleStripArray triangleStrip = null;
    //CV:  mi servono le TEXTURE COORDS
    protected TexCoord2f t[] = null; 

    /**
     * Costruttore
     */
    public TrunkedSquarePyramid(float bottomWidth, float topWidth, float height, Appearance appearance) {
        // Numero delle facce
        int faces = 20;
        // Creo un array di Point3f per generare i triangoli che costruiscono il tronco di piramide
        v = new Point3f[(faces + 1) * 2];
        //CV
        t = new TexCoord2f[(faces+1)*2];
        
        for (int i = 0; i < faces; i++) {
            // Calcolo la posizione di ogni punto per le coordinate x e y (x = sin(angle) e y = cos(angle))
            double angle = 2.0 * Math.PI * (double) i / (double) faces;
            float x = (float) Math.sin(angle);
            float y = (float) Math.cos(angle);
            // Imposto il valore del punto in basso, che ha come posizione su z BOTTOM
            v[i * 2 + 0] = new Point3f(x * bottomWidth, y * bottomWidth, BOTTOM);
            // Imposto il valore del punto in alto, che ha come posizione su z TOP
            // Le coordinate x e y sono divise per 3 per definire la differenza di grandezza dalla base
            v[i * 2 + 1] = new Point3f(x * topWidth, y * topWidth, BOTTOM + height);
            
            //CV
            t[i*2] = new TexCoord2f((float)((float)i/(float)faces),0.0f);
			t[i*2+1] = new TexCoord2f((float)((float)i/(float)faces),1.0f);
            
        }
        // Aggiungo gli ultimi angoli che completano la figura
        v[faces * 2 + 0] = new Point3f(0.0f, 1.0f  * bottomWidth, BOTTOM);
        v[faces * 2 + 1] = new Point3f(0.0f, 1.0f * topWidth, BOTTOM + height);
         //CV
        t[faces*2] = new TexCoord2f(1.0f,0.0f);
		t[faces*2+1] = new TexCoord2f(1.0f,1.0f);

        int[] stripCounts = {(faces + 1) * 2};
        // Creo un TriangleStripArray
        triangleStrip = new TriangleStripArray((faces + 1) * 2, GeometryArray.COORDINATES | GeometryArray.NORMALS | GeometryArray.TEXTURE_COORDINATE_2, stripCounts);
        // Definisco quali coordinate userà il TriangleStripArray
        triangleStrip.setCoordinates(0, v);
        
        //CV:setto le coordinate
        triangleStrip.setTextureCoordinates(0, 0, t);

        // Genero e imposto le normali
        GeometryInfo gi = new GeometryInfo ( triangleStrip ) ;
        NormalGenerator normalGenerator = new NormalGenerator ( );
        normalGenerator.generateNormals(gi) ;
//        triangleStrip.setNormals(0, gi.getGeometryArray().getNormalRefFloat());
        // Imposto al mio Shape3D quale GeometryArray userò
        setGeometry(gi.getGeometryArray());

        // Imposto l'appearance
        setAppearance(appearance);
    }


    public float getHeight() {
        return TOP - BOTTOM;
    }
}
