package it.spassador;

import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.universe.ViewingPlatform;

import javax.media.j3d.*;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import java.applet.Applet;
import java.awt.*;

public class BaseView extends Applet {
    public BaseView() {
        setLayout(new BorderLayout()); // Layout manager del container
        // Trova la miglior configurazione grafica per il sistema
        GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
        // Canvas3D: si occupa del rendering 3D on-screen e off-screen
        Canvas3D canvas3D = new Canvas3D(config);
        add("Center", canvas3D);
        BranchGroup scene = createSceneGraph();
        scene.compile();
        // Creazione del SimpleUniverse
        SimpleUniverse simpleUniverse = new SimpleUniverse(canvas3D);

        // Posizionamento standard usato fino ad ora
        // simpleUniverse.getViewingPlatform().setNominalViewingTransform();

        // Corrispettivo di questo posizionamento:
        // View view = simpleUniverse.getViewer().getView(); // Ampiezza del campo visivo attuale
        // double fieldOfView = view.getFieldOfView(); // 0.25*Math.PI

        // Trasformazione per traslare l'osservatore lungo l'asse z in modo da poter inquadrare un quadrato 2x2 sul
        // piano xy e centrato sull'origine
        // Transform3D viewTransform = new Transform3D();
        // double distance = 1.0/Math.tan(fieldOfView/2.0);
        // viewTransform.setTranslation(new Vector3d(0.0, 0.0, distance));
        // Attivazione della trasformazione appena ricavata
        // ViewingPlatform vp = simpleUniverse.getViewingPlatform();
        // TransformGroup vtg = vp.getViewPlatformTransform();
        // vtg.setTransform(viewTransform);

        // View view = simpleUniverse.getViewer().getView(); // Ampiezza del campo visivo attuale
        // Transform3D t3d = new Transform3D();
        // t3d.setTranslation(new Vector3d(0, 0, 3));
        // Transform3D t3d2 = new Transform3D();
        // t3d2.rotY(Math.PI/4);
        // t3d2.invert();
        // t3d2.mul(t3d);
        // TransformGroup vtg = simpleUniverse.getViewingPlatform().getViewPlatformTransform();
        // vtg.setTransform(t3d2);

//        View view = simpleUniverse.getViewer().getView(); // Ampiezza del campo visivo attuale
//        double fieldOfView = view.getFieldOfView(); // 0.25*Math.PI
//        double distance = 1.0/Math.tan(fieldOfView/2.0);
        Transform3D transform = new Transform3D();

        /**
         * Codice che risolve l'esercizio 3.3
         * Posiziono l'osservatore sotto la scena, e punto il suo sguardo verso la scena
         */
        transform.lookAt(new Point3d(0.0, -5, 0),
                new Point3d(0.0, 0.0, 0.0),
                new Vector3d(0.0, 0.0, 1.0));
        transform.invert();
        TransformGroup vtg = simpleUniverse.getViewingPlatform().getViewPlatformTransform();
        vtg.setTransform(transform);
        // System.out.println(transform);
        simpleUniverse.addBranchGraph(scene);
    }

    /**
     * Funzione che crea il sottografo
     *
     * @return il BranchGroup da aggiungere al SimpleUniverse
     */
    public BranchGroup createSceneGraph() {
        BranchGroup branchGroup = new BranchGroup(); // Creo un oggetto di tipo BranchGroup
        TransformGroup transform = new TransformGroup(); // Creo un oggetto di tipo TransformGroup
        transform.addChild(new ColorCube(0.3)); // Aggiungo al transformGroup un figlio Cubo
        branchGroup.addChild(transform); // Aggiunge l'oggetto transform come figlo al BranchGroup
        return branchGroup; //
    }

    public static void main(String[] args) {
        // write your code here
        new MainFrame(new BaseView(), 500, 500);
    }
}
