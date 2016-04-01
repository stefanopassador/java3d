import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;
import com.sun.j3d.utils.universe.SimpleUniverse;

import javax.media.j3d.*;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import java.applet.Applet;
import java.awt.*;

/**
 * Created by stefanopassador on 31/03/16.
 */
public class Esercizio3_6 extends Applet {
    public Esercizio3_6() {
        setLayout(new BorderLayout()); //layout manager del container
        //trova la miglior configurazione grafica per il sistema
        GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
        // Canvas3: si occupa del rendering 3D on-screen e off-screen
        Canvas3D canvas3D = new Canvas3D(config);
        add("Center", canvas3D);
        BranchGroup scene = createSceneGraph(); // creazione del sottografo principale
        scene.compile();

        SimpleUniverse simpleU = new SimpleUniverse(canvas3D);
        simpleU.getViewingPlatform().setNominalViewingTransform();

        Transform3D transform = new Transform3D();
        transform.lookAt(new Point3d(0.0, 0.0, 5.0),
                new Point3d(0.0, 0.5, 0.0),
                new Vector3d(0.0, 0.1, 0.0));

        transform.invert();
        TransformGroup vtg = simpleU.getViewingPlatform().getViewPlatformTransform();
        vtg.setTransform(transform);
        simpleU.addBranchGraph(scene);

        OrbitBehavior orbit = new OrbitBehavior(canvas3D, OrbitBehavior.REVERSE_ROTATE);
        orbit.setSchedulingBounds(new BoundingSphere());
        simpleU.getViewingPlatform().setViewPlatformBehavior(orbit);
    }

    /**
     * Funzione che crea il sottografo
     *
     * @return il BranchGroup da aggiungere al SimpleUniverse
     */
    public BranchGroup createSceneGraph() {
        BranchGroup branchGroup = new BranchGroup(); // Creo un oggetto di tipo BranchGroup
        TransformGroup transform = new TransformGroup(); // Creo un oggetto di tipo TransformGroup

//        Transform3D transformation = new Transform3D();
//        Transform3D transformation2 = new Transform3D();
//        transformation.setTranslation(
//                new Vector3d(0.0, 0.0, -5.0)
//        );
//        transformation2.rotZ(Math.PI/5);
//        transformation2.rotX(-Math.PI/2);
//
//        transformation.mul(transformation2);
//        transform.setTransform(transformation);

        Transform3D transform3D = new Transform3D();
        transform3D.rotX(-Math.PI/2);
        transform.setTransform(transform3D);

        transform.addChild(new TrunkedSquarePyramid());
//        transform.addChild(new MayaPyramid());
        branchGroup.addChild(transform); // Aggiunge l'oggetto transform come figlo al BranchGroup
        return branchGroup; //
    }

    public static void main(String[] args) {
        new MainFrame(new Esercizio3_6(), 800, 800);
    }
}
