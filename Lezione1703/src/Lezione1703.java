import java.applet.Applet;
import java.awt.*;

import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.universe.SimpleUniverse;

import javax.media.j3d.*;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

/**
 * Created by stefanopassador on 16/03/16.
 */
public class Lezione1703 extends Applet {
    public Lezione1703() {
        setLayout(new BorderLayout()); // Layout manager del container
        // Trova la miglior configurazione grafica per il sistema
        GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
        // Canvas3D: si occupa del rendering 3D on-screen e off-screen
        Canvas3D canvas3D = new Canvas3D(config);
        add("Center", canvas3D);
        BranchGroup scene = createSceneGraph(); // creazione del sottografo principale
        scene.compile();

        //Creazione del SimpleUniverse
        SimpleUniverse simpleU = new SimpleUniverse(canvas3D);
        simpleU.getViewingPlatform().setNominalViewingTransform();
        // accedo all'oggetto view del SimpleUniverse
        View myView = simpleU.getViewer().getView();
        //Impostazione della distanza dal piano sullo sfondo
        myView.setBackClipDistance(10.0);
        //Impostazione del clip dal piano frontale
        myView.setFrontClipDistance(0.1);
        //Impostazione del campo visivo
        myView.setFieldOfView(Math.PI / 4);
        //Impostazione del tipo di proiezione
        // myView.setProjectionPolicy(View.PERSPECTIVE_PROJECTION);
        myView.setProjectionPolicy(View.PARALLEL_PROJECTION);
        simpleU.addBranchGraph(scene);
    }

    /**
     * Funzione che crea il sottografo
     *
     * @return il BranchGroup da aggiungere al SimpleUniverse
     */
    public BranchGroup createSceneGraph() {
        BranchGroup branchGroup = new BranchGroup(); // Creo un oggetto di tipo BranchGroup
        TransformGroup transform = new TransformGroup(); // Creo un oggetto di tipo TransformGroup

        Transform3D transformation, transformation2;
        transformation = new Transform3D();
        transformation2 = new Transform3D();
        transformation.rotX(Math.PI/4);
        transformation2.rotY(Math.PI/4);
        transformation.mul(transformation2);
        transform.setTransform(transformation);
        transform.addChild(new Tetrahedron()); // Aggiungo al transformGroup un figlio Cubo
        branchGroup.addChild(transform); // Aggiunge l'oggetto transform come figlo al BranchGroup
        return branchGroup; //
    }

    public static void main(String[] args) {
        // write your code here
        new MainFrame(new Lezione1703(), 500, 500);
    }
}
