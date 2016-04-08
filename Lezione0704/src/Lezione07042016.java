import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;
import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.universe.SimpleUniverse;

import javax.media.j3d.*;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import java.applet.Applet;
import java.awt.*;

/**
 * Created by stefanopassador on 07/04/16.
 */
public class Lezione07042016 extends Applet {
    public Lezione07042016() {
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

        // Creazione del SimpleUniverse
        simpleU.getViewingPlatform().setNominalViewingTransform();
        // accedo all'oggetto view del SimpleUniverse
        View myView = simpleU.getViewer().getView();
        // Abilitazione del compatibility mode che ci permette di modificare la matrice di proiezione
        myView.setCompatibilityModeEnable(true) ;
        // Creazione di un Transform3D
        Transform3D proj=new Transform3D() ;
        // Impostazione della matrice di proiezione ortografica
        double ratio = 500.0/500.0;
        proj.perspective(0.25*Math.PI, ratio, 0.1, 5.0);
        // Imposta il frustrum visibile in base alla proiezione passata
        myView.setLeftProjection(proj) ;

        Transform3D transform = new Transform3D();
        transform.lookAt(new Point3d(-1.5, 1.5, 2.0),
                new Point3d(0.0, 0.0, 0.0),
                new Vector3d(0.0, 1.0, 0.0));

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
        Sphere sphere = new Sphere(0.5f, Primitive.GEOMETRY_NOT_SHARED | Primitive.GENERATE_NORMALS, createAppeareance());
        transform.addChild(sphere);
        transform.addChild(createDirectionalLight()); // aggiunta della light al BranchGroup
        branchGroup.addChild(transform); // Aggiunge l'oggetto transform come figlo al BranchGroup

        return branchGroup; //
    }

    private AmbientLight createGreenLight() {
        /* Reazione del bound definisce lo spazio dell'illuminazione
           mi dice quali sono gli oggetti che posso illuminare.
           */
        BoundingSphere bounds = new BoundingSphere(
                new Point3d(0.d,0.d,0.d),10.d);
        // creazione di una sorgente di luce
        AmbientLight lightP1 = new AmbientLight();
        Color3f green = new Color3f(0.0f, 1.0f, 0.0f);
        lightP1.setColor(green);
        lightP1.setInfluencingBounds(bounds);
        return lightP1;
    }

    private DirectionalLight createDirectionalLight() {
        // Creazione del bound
        BoundingSphere bounds = new BoundingSphere(new Point3d(1.0d, 0.0d, 0.0d), 40.0d);
        // Creazione di una luce direzionale
        DirectionalLight lightD1 = new DirectionalLight();

        // impostazione del bound
        lightD1.setInfluencingBounds(bounds);
        return lightD1;
    }

    private Appearance createAppeareance() {
        Appearance mAppearance = new Appearance();
        ColoringAttributes mColoringAttributes = new ColoringAttributes();
//        mColoringAttributes.setColor(new Color3f(Color.RED));
//        mAppearance.setColoringAttributes(mColoringAttributes);
//        mAppearance.setPolygonAttributes(new PolygonAttributes(PolygonAttributes.POLYGON_LINE, PolygonAttributes.CULL_NONE, 0));
        Material material = new Material();
        mAppearance.setMaterial(material);
        return mAppearance;
    }


    public static void main(String[] args) {
        new MainFrame(new Lezione07042016(), 800, 800);
    }
}
