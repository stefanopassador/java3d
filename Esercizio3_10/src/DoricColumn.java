import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.geometry.Primitive;

import javax.media.j3d.*;
import javax.vecmath.Vector3f;
import java.awt.*;

/**
 * Created by stefanopassador on 10/04/16.
 */
public class DoricColumn extends Group {
    private float height;
    private TrunkedSquarePyramid trunkedSquarePyramid;
    private Component component;
    private Appearance appearance;
    /**
     * Constructor of a doric column
     *
     * @param height The height of the figure
     */
    public DoricColumn(float height, Appearance appearance) {
        this.appearance = appearance;
        this.component = component;
        this.height = height;
        addFusto();
        addEchino();
        addAbaco();
    }

    private void addFusto() {
        TransformGroup transformGroupCylinder = new TransformGroup();
        transformGroupCylinder.addChild(new Cylinder(
                height / 4.5f / 2,
                (height / 4.5f) * 3,
                Primitive.GENERATE_TEXTURE_COORDS |
                        Primitive.GENERATE_NORMALS,
                20,
                1,
                appearance));
        addChild(transformGroupCylinder);
    }

    private void addEchino() {
        TransformGroup transformGroupTrunkedPyramid = new TransformGroup();
        trunkedSquarePyramid = new TrunkedSquarePyramid(
                height / 4.5f / 2,
                height / 4.5f,
                height / 4.5f,
                appearance);
        transformGroupTrunkedPyramid.addChild(trunkedSquarePyramid);
        Transform3D transformationPyramid = new Transform3D();
        transformationPyramid.rotX((Math.PI / 2) * 3);
        Transform3D transformationPyramid3 = new Transform3D();
        transformationPyramid3.setTranslation(new Vector3f(
                0.0f,
                0.0f,
                ((height / 4.5f) * 3) / 2)
        );
        transformationPyramid.mul(transformationPyramid3);
        transformGroupTrunkedPyramid.setTransform(transformationPyramid);
        addChild(transformGroupTrunkedPyramid);
    }

    private void addAbaco() {
        TransformGroup transformGroupTrunkedPyramid = new TransformGroup();
        float size = height / 4.5f;
        Box box = new Box(
                size,
                height / 4.5f / 4,
                size,
                Primitive.GENERATE_TEXTURE_COORDS |
                        Primitive.GENERATE_NORMALS,
                appearance);

        transformGroupTrunkedPyramid.addChild(box);
        Transform3D transformation = new Transform3D();
        transformation.setTranslation(new Vector3f(
                0.0f,
                (((height / 4.5f) * 3) / 2) + height / 4.5f + height / 4.5f / 4,
                0.0f));
        Transform3D transformation2 = new Transform3D();
        transformation.mul(transformation2);
        transformGroupTrunkedPyramid.setTransform(transformation);
        addChild(transformGroupTrunkedPyramid);
    }
}
