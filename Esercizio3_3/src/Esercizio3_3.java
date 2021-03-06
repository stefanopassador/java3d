import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.universe.ViewingPlatform;

import javax.media.j3d.*;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import java.applet.Applet;
import java.awt.*;

public class Esercizio3_3 extends Applet {
    public Esercizio3_3() {
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

        Transform3D transform = new Transform3D();

        /**
         * Codice che risolve l'esercizio 3.3 - punto 1
         * Posiziono l'osservatore sotto la scena, e punto il suo sguardo verso la scena
         */
//        transform.lookAt(new Point3d(0.0, -5, 0),
//                new Point3d(0.0, 0.0, 0.0),
//                new Vector3d(0.0, 0.0, 1.0));

        /**
         * Codice che risolve l'esercizio 3.3 - punto 2
         * Posiziono l'osservatore sotto la scena, e punto il suo sguardo verso la scena
         */
        // Definisco la trasformazione da applicare alla ViewPlatform
        // che mi permette di spostare il punto di fissazione dell'osservatore
        // verso sinistra per ottenere un punto di fuga
//        transform.lookAt(new Point3d(0.0, 0.0, 3.0),
//                new Point3d(-0.5, 0.0, 0.0),
//                new Vector3d(0.0, 1.0, 0.0));

        // Definisco la trasformazione da applicare alla ViewPlatform
        // che mi permette di spostare la testa dell'osservatore verso destra
        // per ottenere due punti di fuga
//        transform.lookAt(new Point3d(1.5, 0.0, 2.0),
//				new Point3d(0.0, 0.0, 0.0),
//				new Vector3d(0.0, 1.0, 0.0));

        //Definisco la trasformazione da applicare alla ViewPlatform
        //che mi permette di spostare la testa dell'osservatore
        //verso un punto sinistro più alto per ottenere tre punti di fuga
        transform.lookAt(new Point3d(-1.5, 1.5, 2.0),
                new Point3d(0.0, 0.0, 0.0),
                new Vector3d(0.0, 1.0, 0.0));

        transform.invert();
        TransformGroup vtg = simpleUniverse.getViewingPlatform().getViewPlatformTransform();
        vtg.setTransform(transform);
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
        new MainFrame(new Esercizio3_3(), 500, 500);
    }
}
