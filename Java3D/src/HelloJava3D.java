import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.universe.SimpleUniverse;

public class HelloJava3D extends Applet {
    public HelloJava3D() {
        setLayout(new BorderLayout());
        Transform3D t = new Transform3D();
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
        public BranchGroup createSceneGraph() {
            BranchGroup node = new BranchGroup();
            TransformGroup TG = createSubGraph();
            node.addChild(TG); //aggiunge TG come figlio del BranchGroup
            return node;
        }

        public TransformGroup createSubGraph(){
            TransformGroup transform = new TransformGroup();
            transform.addChild(new ColorCube(0.3));
            return transform;
        }


    public static void main(String[] args){
        new MainFrame(new HelloJava3D(), 1024,768);
    }
}

