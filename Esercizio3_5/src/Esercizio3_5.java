import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.universe.ViewingPlatform;

import javax.media.j3d.*;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import java.applet.Applet;
import java.awt.*;

public class Esercizio3_5 extends Applet {
    public Esercizio3_5() {
        setLayout(new BorderLayout()); // Layout manager del container
        // Trova la miglior configurazione grafica per il sistema
        GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
        // Canvas3D: si occupa del rendering 3D on-screen e off-screen
        Canvas3D canvas3D = new Canvas3D(config);
        add("Center", canvas3D);

        // Creazione del SimpleUniverse
        SimpleUniverse simpleUniverse = new SimpleUniverse(canvas3D);
        simpleUniverse.getViewingPlatform().setNominalViewingTransform();
        // accedo all'oggetto view del SimpleUniverse
        View myView = simpleUniverse.getViewer().getView();
        //Impostazione della distanza dal piano sullo sfondo
        myView.setBackClipDistance(10.0);
        //Impostazione del clip dal piano frontale
        myView.setFrontClipDistance(0.1);
        //Impostazione del campo visivo
        myView.setFieldOfView(Math.PI/2);
        //Impostazione del tipo di proiezione
        myView.setProjectionPolicy(View.PERSPECTIVE_PROJECTION);
//        myView.setProjectionPolicy(View.PARALLEL_PROJECTION);

        // Trasformazione che mi permette di vedere tre punti di fuga
        Transform3D transform = new Transform3D();
        transform.lookAt(new Point3d(-1.5, 1.5, 2.0),
                new Point3d(0.0, 0.0, 0.0),
                new Vector3d(0.0, 1.0, 0.0));
        transform.invert();
        TransformGroup vtg = simpleUniverse.getViewingPlatform().getViewPlatformTransform();
        vtg.setTransform(transform);

        // creazione e aggiunta del sottografo principale
        BranchGroup scene = createSceneGraph();
        scene.compile();
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
        new MainFrame(new Esercizio3_5(), 500, 500);
    }
}
