import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Cone;

import javax.media.j3d.*;
import javax.vecmath.Matrix3d;
import javax.vecmath.Matrix3f;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

/**
 * Classe che crea una piramide maya rossa con una trasparenza di 0.1f
 * Created by stefanopassador on 31/03/16.
 */
public class MayaPyramid extends Group {
    private static final int BOXES_COUNT = 10;
    private static final float MAX_SIZE = 2.0f;
    private static final float BOXES_HEIGHT = 0.1f;
    private static final float DELTA_BOXES = 0.2f;
    static final protected Appearance appeareance = MayaPyramid.createAppeareance();

    /**
     * Costruttore della piramide maya
     */
    public MayaPyramid() {
        float size = MAX_SIZE;
        // Aggiungo i box che sono sotto
        for (int i = 0; i < BOXES_COUNT; i++) {
            // La dimensione di ogni box si rimpicciolisce a ogni iterazione
            // Divido MAX_SIZE  per BOXES_COUNT + 5 perché mi permette di avere l'ultimo box più grande
            size = MAX_SIZE - ((MAX_SIZE / (BOXES_COUNT + 5)) * i);
            Box box = new Box(size, size, BOXES_HEIGHT, appeareance);

            // Creo la trasformazione che definisce la traslazione di ogni box
            Transform3D transformation = new Transform3D();
            transformation.setTranslation(new Vector3d(0.0f, 0.0f, BOXES_HEIGHT * i * 2));
            TransformGroup upTG = new TransformGroup(transformation);
            upTG.addChild(box);
            addChild(upTG);
        }
        // Aggiungo il box sopra
        // La grandezza del box in alto è calcolata dinamicamente
        float top_size = size / 2;
        Box box = new Box(top_size, top_size, top_size, appeareance);
        Transform3D transformation = new Transform3D();
        // Definisco la traslazione per il box in alto
        transformation.setTranslation(new Vector3d(0.0f, 0.0f, BOXES_HEIGHT * BOXES_COUNT * 2 - BOXES_HEIGHT + top_size));
        TransformGroup upTG = new TransformGroup(transformation);
        upTG.addChild(box);
        addChild(upTG);
    }

    /**
     * Metodo che genera l'Appearance dei vari box
     * @return L'istanza di Appearance generata
     */
    private static Appearance createAppeareance() {
        // Creo un oggetto Appearance
        Appearance mAppearance = new Appearance();
        // Definisco il colore
        mAppearance.setColoringAttributes(new ColoringAttributes(255, 0, 0, ColoringAttributes.SHADE_GOURAUD));
        // Definisco la trasparenza
        mAppearance.setTransparencyAttributes(new TransparencyAttributes(TransparencyAttributes.NICEST, 0.1f));

        mAppearance.setPolygonAttributes(new PolygonAttributes(PolygonAttributes.POLYGON_LINE, PolygonAttributes.CULL_NONE, 0));
        return mAppearance;
    }
}
