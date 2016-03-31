import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;
import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.scenegraph.io.state.javax.media.j3d.ColoringAttributesState;
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
public class Lezione31032016 extends Applet {
    public Lezione31032016() {
        setLayout(new BorderLayout()); //layout manager del container
        //trova la miglior configurazione grafica per il sistema
        GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
        // Canvas3: si occupa del rendering 3D on-screen e off-screen
        Canvas3D canvas3D = new Canvas3D(config);
        add("Center", canvas3D);
        BranchGroup scene = createSceneGraph(); // creazione del sottografo principale
        scene.compile();

        add("Center", canvas3D);
        SimpleUniverse simpleU = new SimpleUniverse(canvas3D);
        simpleU.getViewingPlatform().setNominalViewingTransform();

        Transform3D transform = new Transform3D();
        transform.lookAt(new Point3d(3.0, 4.0, 2.0),
                new Point3d(0.0, 0.0, 0.0),
                new Vector3d(1.0, 0.5, 0.0));

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

        Transform3D transformation = new Transform3D();
        transformation.rotX(Math.PI / 4);
        transform.setTransform(transformation);

//        transform.addChild(new MyCylinder(100)); // Aggiungo al transformGroup un figlio Cubo
//        Float size = 2.0f;
//        transform.addChild(new Sphere(size, 100, 100, createAppeareance()));
        transform.addChild(new Trottola());
        branchGroup.addChild(transform); // Aggiunge l'oggetto transform come figlo al BranchGroup
        return branchGroup; //
    }

    private Appearance createAppeareance() {
        Appearance mAppearance = new Appearance();
        ColoringAttributes mColoringAttributes = new ColoringAttributes();
        mColoringAttributes.setColor(new Color3f(Color.CYAN));
        mAppearance.setColoringAttributes(mColoringAttributes);
        mAppearance.setPolygonAttributes(new PolygonAttributes(PolygonAttributes.POLYGON_LINE, PolygonAttributes.CULL_BACK, 0));
        return mAppearance;
    }

    public static void main(String[] args) {
        new MainFrame(new Lezione31032016(), 800, 800);
    }
}
