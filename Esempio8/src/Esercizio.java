/**
 * Created by Luca on 13/04/2016.
 */

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;
import javax.media.j3d.*;
import javax.vecmath.Color3f;
import javax.vecmath.Matrix3d;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.universe.ViewingPlatform;

public class Esercizio extends Applet {
    public Esercizio() {
        setLayout(new BorderLayout()); //layout manager del container //trova la miglior configurazione grafica per il sistema
        GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration(); // Canvas3: si occupa del rendering 3D on-screen e off-screen
        Canvas3D canvas3D = new Canvas3D(config);
        add("Center", canvas3D);
        BranchGroup scene = createSceneGraph(); // creazione del sottografo principale
        scene.compile(); //Creazione del SimpleUniverse
        SimpleUniverse simpleU = new SimpleUniverse(canvas3D);
        simpleU.getViewingPlatform().setNominalViewingTransform();
        simpleU.addBranchGraph(scene);
/*
        //impostazione della visuale
        Transform3D viewTransform = new Transform3D();
        viewTransform.lookAt(new Point3d(0.0, 0.0, 1.0), new Point3d(0.0, 0.0, 0.0), new Vector3d(0.0, 1.0, 0.0));
        viewTransform.invert();
        ViewingPlatform vp = simpleU.getViewingPlatform();
        TransformGroup vtg = vp.getViewPlatformTransform();
        vtg.setTransform(viewTransform);
*/
    }

    //funzione che crea sottografo
    public BranchGroup createSceneGraph() {
        BranchGroup node = new BranchGroup();
        Appearance appearance = new Appearance();
        Material material = new Material();
        appearance.setMaterial(material);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                TransformGroup transform = new TransformGroup();
                Sphere sphere = new Sphere(0.3f, Primitive.GEOMETRY_NOT_SHARED | Primitive.GENERATE_NORMALS, appearance);
                transform.addChild(sphere);

                //aggiunge TG come figlio del BranchGroup
                node.addChild(transform);
            }
        }

        return node;
    }

    private AmbientLight createAmbientLight() {
        /*reazione del bound definisce lo spazio dell'illuminazione mi
        dice quali sono gli
        oggetti che posso illuminare
        */
        BoundingSphere bounds = new BoundingSphere(new Point3d(0.d, 0.d, 0.d), 10.d);  // creazione di una sorgente di luce
        AmbientLight lightP1 = new AmbientLight();
        Color3f green = new Color3f(0.0f, 1.0f, 0.0f);
        lightP1.setColor(green);
        lightP1.setInfluencingBounds(bounds);
        return lightP1;
    }

    private DirectionalLight createDirectionalLight() {
        // creazione del bound
        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0d, 0.0d, 0.0d), 50.0d);
        // creazione di una luce direzionale
        DirectionalLight lightD1 = new DirectionalLight();
        Color3f red = new Color3f(1.0f, 0.0f, 0.0f);
        lightD1.setColor(red);
        // impostazione del bound
        lightD1.setInfluencingBounds(bounds);
        // aggiunta al BranchGroup
        return lightD1;
    }


    public static void main(String[] args) {
        new MainFrame(new Esercizio(), 1024, 768);
    }
}




