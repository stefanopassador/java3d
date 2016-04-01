import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Cone;

import javax.media.j3d.*;
import javax.vecmath.Matrix3d;
import javax.vecmath.Matrix3f;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

/**
 * Created by stefanopassador on 31/03/16.
 */
public class MayaPyramid extends Group {
    private static final int BOXES_COUNT = 10;
    private static final float MAX_SIZE = 2.0f;
    private static final float BOXES_HEIGHT = 0.1f;
    private static final float TOP_SIZE = 0.4f;
    private static final float DELTA_BOXES = 0.2f;
    static final protected Appearance appeareance = MayaPyramid.createAppeareance();

    public MayaPyramid() {
        // Aggiungo i box sotto
        for (int i = 0; i < BOXES_COUNT; i++) {
            float size = MAX_SIZE - ((MAX_SIZE / (BOXES_COUNT + 5)) * i);
            Box box = new Box(size, size, BOXES_HEIGHT, appeareance);

            Transform3D transformation = new Transform3D();
            transformation.setTranslation(new Vector3d(0.0f, 0.0f, BOXES_HEIGHT * i * 2));
            TransformGroup upTG = new TransformGroup(transformation);
            upTG.addChild(box);
            addChild(upTG);
        }
        // Aggiungo il box sopra
        Box box = new Box(TOP_SIZE, TOP_SIZE, TOP_SIZE, appeareance);
        Transform3D transformation = new Transform3D();
        transformation.setTranslation(new Vector3d(0.0f, 0.0f, BOXES_HEIGHT * BOXES_COUNT * 2 - BOXES_HEIGHT + TOP_SIZE));
        TransformGroup upTG = new TransformGroup(transformation);
        upTG.addChild(box);
        addChild(upTG);
    }

    private static Appearance createAppeareance() {
        Appearance mAppearance = new Appearance();
        ColoringAttributes coloringAttributes = new ColoringAttributes(255, 0, 0, ColoringAttributes.SHADE_GOURAUD);
        mAppearance.setColoringAttributes(coloringAttributes);
        mAppearance.setLineAttributes(new LineAttributes());

        mAppearance.setTransparencyAttributes(new TransparencyAttributes(TransparencyAttributes.NICEST, 0.1f));
        return mAppearance;
    }
}
