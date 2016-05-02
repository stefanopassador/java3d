import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;
import com.sun.j3d.utils.geometry.ColorCube;
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
public class Lezione28042016 extends Applet {
    public Lezione28042016() {
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
        transform.lookAt(new Point3d(0.0, 0.0, 2.0),
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
        //Crea la radice del branch graph
        BranchGroup objRoot = new BranchGroup();

        //Crea un gruppo per le trasformazioni affini
        TransformGroup objSpin = new TransformGroup();
        //Imposta la capacita' di scrivere la trasformazione
        objSpin.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        //Aggiunge al gruppo un cubo colorato
        objSpin.addChild(new ColorCube(0.4));
        //Crea un behavior
        SimpleBehaviour3 rotator = new SimpleBehaviour3(objSpin);
        //Imposta un raggio d'azione del behavior
        BoundingSphere bounds = new BoundingSphere();
        rotator.setSchedulingBounds(bounds);
        //aggiunge l'interpolatore alla gruppo di trasformazione
        objSpin.addChild(rotator);
        //Aggiunge alla radice il gruppo
        objRoot.addChild(objSpin);
        return objRoot;
    }

    private Appearance createAppeareance() {
        Appearance mAppearance = new Appearance();
        ColoringAttributes mColoringAttributes = new ColoringAttributes();
        mColoringAttributes.setColor(new Color3f(Color.CYAN));
        mAppearance.setColoringAttributes(mColoringAttributes);
        mAppearance.setPolygonAttributes(new PolygonAttributes(PolygonAttributes.POLYGON_LINE, PolygonAttributes.CULL_BACK, 0));
        return mAppearance;
    }

    //L'implementazione del Behavior personalizzato


    public static void main(String[] args) {
        new MainFrame(new Lezione28042016(), 800, 800);
    }
}
