import com.sun.j3d.utils.geometry.Cone;

import javax.media.j3d.*;
import javax.vecmath.Color3f;
import javax.vecmath.Matrix3d;
import javax.vecmath.Vector3d;
import java.awt.*;

/**
 * Created by stefanopassador on 31/03/16.
 */
public class Trottola extends Group {
    static final protected Appearance appeareanceUp = Trottola.createAppeareanceUp();
    static final protected Appearance appeareanceDown = Trottola.createAppeareanceDown();


    static final protected Transform3D upTransform = new Transform3D(
            new Matrix3d(1.0, 0.0, 0.0, 0.0, 0.5, 0.0, 0.0, 0.0, 1.0),
            new Vector3d(0.0, 0.5, 0.0),
            1.0
    );

    static final protected  Transform3D downTransform = new Transform3D(
            new Matrix3d(1.0, 0.0, 0.0, 0.0, -0.5, 0.0, 0.0, 0.0, 1.0),
            new Vector3d(0.0, -0.5, 0.0),
            1.0
    );

    protected TransformGroup upTG = new TransformGroup(upTransform);
    protected TransformGroup downTG = new TransformGroup(downTransform);
    protected Cone upCone = new Cone();
    protected Cone downCone = new Cone();

    public Trottola() {
        upCone.setAppearance(appeareanceUp);
        downCone.setAppearance(appeareanceDown);
        upTG.addChild(upCone);
        downTG.addChild(downCone);
        addChild(upTG);
        addChild(downTG);
    }

    private static Appearance createAppeareanceUp() {
        Appearance mAppearance = new Appearance();
        mAppearance.setColoringAttributes(new ColoringAttributes(255, 0, 0, ColoringAttributes.SHADE_FLAT));
        mAppearance.setTransparencyAttributes(new TransparencyAttributes(TransparencyAttributes.NICEST, 0.65f));
        mAppearance.setPolygonAttributes(new PolygonAttributes(PolygonAttributes.POLYGON_FILL, PolygonAttributes.CULL_NONE, 0));
        return mAppearance;
    }
    private static Appearance createAppeareanceDown() {
        Appearance mAppearance = new Appearance();
        mAppearance.setColoringAttributes(new ColoringAttributes(0, 0, 190, ColoringAttributes.SHADE_FLAT));
        mAppearance.setPolygonAttributes(new PolygonAttributes(PolygonAttributes.POLYGON_FILL, PolygonAttributes.CULL_NONE, 0));
        return mAppearance;
    }
}
