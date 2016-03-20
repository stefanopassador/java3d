package it.spassador;

import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.universe.SimpleUniverse;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3d;
import java.applet.Applet;
import java.awt.*;

/**
 * Created by stefanopassador on 03/03/16.
 */
public class Esercizio3_2 extends Applet {
    private static final int CUBES_COUNT = 1;

    /**
     * Costruttore dell'Applet
     */
    public Esercizio3_2() {
        // Imposto il layout dell'Applet a un BorderLayout
        setLayout(new BorderLayout());
        // Ottengo la configurazione del SimpleUniverse
        GraphicsConfiguration config = SimpleUniverse
                .getPreferredConfiguration();
        Canvas3D canvas3D = new Canvas3D(config);
        add("Center", canvas3D);
        // Ottengo il BranchGroup creato con il metodo createSceneGraph();
        BranchGroup scene = createSceneGraph();
        scene.compile();

        SimpleUniverse simpleU = new SimpleUniverse(canvas3D);
        simpleU.getViewingPlatform().setNominalViewingTransform();
        simpleU.addBranchGraph(scene);
    }

    /**
     * Metodo che costruisce genera un BranchGroup contenente tanti ColorCube
     * quanto specificato nella variabile CUBES_COUNT.
     * @return BranchGroup contenente i vari TransformGroup.
     */
    private BranchGroup createSceneGraph() {
        BranchGroup node = new BranchGroup();
        // Ciclo sul numero di cubi totali
        // (il numero è specificato dalla costante CUBES_COUNT)
        for (int i = 0; i < CUBES_COUNT; i++) {
            // Genero l'i-esimo TransformGroup
            TransformGroup TG = createSubGraph(i);
            // Aggiungo il TransformGroup al BranchGroup
            node.addChild(TG); //aggiunge TG come figlio del BranchGroup
        }
        return node;
    }

    /**
     * Metodo che crea un TransformGroup e ci posiziona un cubo
     * @param i Indice del cubo corrente
     * @return TransformGroup contenente il cubo corrente
     */
    private TransformGroup createSubGraph(int i) {
        TransformGroup transform = new TransformGroup(); // crea oggetto TG
        // crea oggetto per la trasformazione
        Transform3D translation = new Transform3D();
        // Definisco l'angolo sulla base del numero di cubi totali.
        double theta = (2*Math.PI) / CUBES_COUNT;
        // Definisco l'angolo per l'i-esimo elemento
        theta = i * theta;
        // Definisco la traslazione
        translation.setTranslation(new Vector3d(
                Math.cos(theta + Math.PI/2) / 2,
                Math.sin(theta + Math.PI/2) / 2,
                0)
        );

        // Assegno al TransformGroup la traslazione
        transform.setTransform(translation);
        // Aggiungo un ColorCube al TransformGroup
        transform.addChild(new ColorCube(0.1));
        return transform;
    }

    public static void main(String[] args) {
        new MainFrame(new Esercizio3_2(), 500, 325);
    }
}
