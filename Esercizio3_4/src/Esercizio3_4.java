import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.universe.ViewingPlatform;

import javax.media.j3d.*;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import java.applet.Applet;
import java.awt.*;

public class Esercizio3_4 extends Applet {
    public Esercizio3_4() {
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
        // Abilitazione del compatibility mode che ci permette di modificare la matrice di proiezione
        myView.setCompatibilityModeEnable(true) ;
        // Creazione di un Transform3D
        Transform3D proj=new Transform3D() ;
        // Impostazione della matrice di proiezione ortografica
        double ratio = 500.0/500.0;
        proj.perspective(0.25*Math.PI, ratio, 0.1, 2.8);
        // Imposta il frustrum visibile in base alla proiezione passata
        myView.setLeftProjection(proj) ;

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
        new MainFrame(new Esercizio3_4(), 500, 500);
    }
}
