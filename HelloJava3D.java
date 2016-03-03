import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.scenegraph.io.state.javax.media.j3d.TransformGroupState;
import com.sun.j3d.utils.universe.SimpleUniverse;

import javax.media.j3d.*;
import java.applet.Applet;
import java.awt.*;

/**
 * Created by stefanopassador on 25/02/16.
 */
public class HelloJava3D extends Applet {
    public HelloJava3D() {
        setLayout(new BorderLayout());
        Transform3D t = new Transform3D();
        GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
        Canvas3D canvas3D = new Canvas3D(config);
        add("Center", canvas3D);
        BranchGroup scene = createSceneGraph();
        scene.compile();

        SimpleUniverse simpleU = new SimpleUniverse(canvas3D);
        simpleU.getViewingPlatform().setNominalViewingTransform();
        simpleU.addBranchGraph(scene);
    }

    public BranchGroup createSceneGraph() {
        BranchGroup node = new BranchGroup();
        TransformGroup TG = createSubGraph();
        node.addChild(TG);
        return node;
    }

    public TransformGroup createSubGraph() {
        Transform3D rotate = new Transform3D(); 
        rotate.rotX(Math.PI/4.0d);
        Transform3D rotateY = new Transform3D();
        rotateY.rotY(Math.PI/4.0d);
        rotate.mul(rotateY);
        TransformGroup transform = new TransformGroup(rotate);
        transform.addChild(new ColorCube(0.3));
        return transform;
    }

    public static void main(String[] args) {
        new MainFrame(new HelloJava3D(), 1024, 768);
    }
}
