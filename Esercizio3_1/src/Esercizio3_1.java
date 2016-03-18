import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;
import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.universe.SimpleUniverse;
import sun.java2d.pipe.SpanShapeRenderer;

import javax.media.j3d.*;
import javax.vecmath.Vector3d;
import java.applet.Applet;
import java.awt.*;

/**
 * Created by stefanopassador on 17/03/16.
 */
public class Esercizio3_1 extends Applet {
    public Esercizio3_1() {
        setLayout(new BorderLayout());
        GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
        Canvas3D canvas3D = new Canvas3D(config);
        add("Center", canvas3D);
        BranchGroup scene = createSceneGraph();
        scene.compile();

        SimpleUniverse simpleUniverse = new SimpleUniverse(canvas3D);
        simpleUniverse.getViewingPlatform().setNominalViewingTransform();

        OrbitBehavior orbit = new OrbitBehavior(canvas3D, OrbitBehavior.REVERSE_ROTATE);
        orbit.setSchedulingBounds(new BoundingSphere());
        simpleUniverse.getViewingPlatform().setViewPlatformBehavior(orbit);

        orbit.setRotXFactor(1);//or any other value
        orbit.setRotYFactor(1);

        simpleUniverse.addBranchGraph(scene);
    }

    public BranchGroup createSceneGraph() {
        BranchGroup node = new BranchGroup();
        TransformGroup transformGroup = createSubGraph();
        node.addChild(transformGroup);
        return node;
    }

    public TransformGroup createSubGraph() {
        TransformGroup transformGroup = new TransformGroup();
        Transform3D transformation = new Transform3D();

        transformation.setScale(new Vector3d(1.0, 1.0, -1.5));

        transformGroup.setTransform(transformation);
        transformGroup.addChild(new ColorCube(0.4));
        return transformGroup;
    }

    public static void main(String[] args) {
        new MainFrame(new Esercizio3_1(), 500, 500);
    }
}
