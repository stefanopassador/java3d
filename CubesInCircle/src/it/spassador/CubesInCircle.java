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
public class CubesInCircle extends Applet {
    private int cubesCount = 5;
    public CubesInCircle() {
        setLayout(new BorderLayout());
        GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
        Canvas3D canvas3D = new Canvas3D(config);
        add("Center", canvas3D);
        BranchGroup scene = createSceneGraph();
        scene.compile();

        SimpleUniverse simpleU = new SimpleUniverse(canvas3D);
        simpleU.getViewingPlatform().setNominalViewingTransform();
        simpleU.addBranchGraph(scene);
    }

    //funzione che crea sottografo
    private BranchGroup createSceneGraph() {
        BranchGroup node = new BranchGroup();
        for (int i = 0; i < cubesCount; i++) {
            TransformGroup TG = createSubGraph(i);
            node.addChild(TG); //aggiunge TG come figlio del BranchGroup
        }
        return node;
    }

    private TransformGroup createSubGraph(int index) {
        TransformGroup transform = new TransformGroup(); // crea oggetto TG
        Transform3D translation = new Transform3D(); // crea oggetto per la trasformazione
        double theta = (2*Math.PI) / cubesCount;
        theta = index * theta;
        translation.setTranslation(new Vector3d(Math.sin(theta) / 2, Math.cos(theta) / 2, 0)); // x + cos(t), y + sen(t), 0

        transform.setTransform(translation); // Assegno a transform la trasformazione
        transform.addChild(new ColorCube(0.1));
        return transform;
    }

    public static void main(String[] args) {
        new MainFrame(new CubesInCircle(), 1024, 768);
    }

}
