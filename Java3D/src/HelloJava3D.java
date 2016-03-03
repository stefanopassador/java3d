import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3d;

import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.universe.SimpleUniverse;

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

    //funzione che crea sottografo
    public BranchGroup createSceneGraph() {
        BranchGroup node = new BranchGroup();
        TransformGroup TG = createSubGraphDoubleCube();
        //TransformGroup TG1 = createSubGraphTraslationX();
        node.addChild(TG); //aggiunge TG come figlio del BranchGroup
        return node;
    }

    public TransformGroup createSubGraph1() {
        TransformGroup transform = new TransformGroup(); // crea oggetto TG
        Transform3D td3Vector = new Transform3D(); // crea oggetto per la trasformazione
        Transform3D td3Rotation = new Transform3D();
        td3Rotation.rotX(Math.PI * 0.4d); // def. rotazione su x
        td3Rotation.setScale(new Vector3d(1.0d, 1.5d, 1.5d)); //def. scaling
        td3Vector.setTranslation(new Vector3d(-0.3d, 0.4d, 0.2d)); // definizione traslazione
        transform.setTransform(td3Rotation); // Assegno a transform la trasformazione
        transform.addChild(new ColorCube(0.2));
        return transform;
    }

    public TransformGroup createSubGraph2() {
        TransformGroup transformGroup = new TransformGroup();
        Transform3D transformation1 = new Transform3D();
        Transform3D transformation2 = new Transform3D();
        Transform3D transformation3 = new Transform3D();

        transformation1.rotX(Math.PI/4);
        transformation2.setTranslation(new Vector3d(0.4d, 0.0d, 0.0d));
        transformation3.setScale(new Vector3d(0.5d, 1.5d, 1.5d));
        transformation1.mul(transformation2);
        transformation1.mul(transformation3);

        transformGroup.setTransform(transformation1);
        transformGroup.addChild(new ColorCube(0.3d));
        return transformGroup;
    }

    public TransformGroup createSubGraph3() {
        // Con trasformazioni diverse la moltiplicazione tra matrici non è commutativa.
        // Con trasformazioni dello stesso tipo la moltiplicazione tra matrici è commutativa.
        TransformGroup transformGroup = new TransformGroup();
        Transform3D transformation1 = new Transform3D();
        Transform3D transformation2 = new Transform3D();

        transformation1.setTranslation(new Vector3d(1d, 0.0d, 0.0d));
        transformation2.setTranslation(new Vector3d(-0.5d, 0.0d, 0.0d));
        transformation1.mul(transformation2);

        transformGroup.setTransform(transformation1);
        transformGroup.addChild(new ColorCube(0.3d));
        return transformGroup;
    }

    public TransformGroup createSubGraphRotationX45() {
        TransformGroup transformGroup = new TransformGroup();
        Transform3D transformation = new Transform3D();

        transformation.rotX(Math.PI/4);

        transformGroup.setTransform(transformation);
        transformGroup.addChild(new ColorCube(0.3d));
        return transformGroup;
    }
    public TransformGroup createSubGraphTraslationX() {
        TransformGroup transformGroup = new TransformGroup();
        Transform3D transformation = new Transform3D();

        transformation.setTranslation(new Vector3d(-0.5d, 0.0d, 0.0d));

        transformGroup.setTransform(transformation);
        transformGroup.addChild(new ColorCube(0.3d));
        return transformGroup;
    }

    public TransformGroup createSubGraphDoubleCube() {
        TransformGroup transformGroup = new TransformGroup();
        Transform3D transformation = new Transform3D();
        transformation.setTranslation(new Vector3d(-0.5d, 0.0d, 0.0d));
        transformGroup.setTransform(transformation);
        transformGroup.addChild(new ColorCube(0.3d));

        TransformGroup transformGroup1 = new TransformGroup();
        Transform3D transformation1 = new Transform3D();
        transformation1.rotX(Math.PI/4);
        transformGroup1.setTransform(transformation1);
        transformGroup1.addChild(new ColorCube(0.3d));

        transformGroup.addChild(transformGroup1);
        return transformGroup;
    }
    public static void main(String[] args) {
        new MainFrame(new HelloJava3D(), 1024, 768);
    }
}

