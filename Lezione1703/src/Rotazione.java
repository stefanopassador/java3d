import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.universe.SimpleUniverse;

import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Point3d;
import java.applet.Applet;
import java.awt.*;

/**
 * Created by stefanopassador on 17/03/16.
 */
public class Rotazione extends Applet {
    public Rotazione() {
        setLayout(new BorderLayout()); //layout manager del container
        //trova la miglior configurazione grafica per il sistema
        GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
        // Canvas3: si occupa del rendering 3D on-screen e off-screen
        Canvas3D canvas3D = new Canvas3D(config);
        add("Center", canvas3D);
        SimpleUniverse simpleU = new SimpleUniverse(canvas3D);
        simpleU.getViewingPlatform().setNominalViewingTransform();
        TransformGroup viewTransformGroup =
                simpleU.getViewingPlatform().getViewPlatformTransform();
        //Comportamento predefinito di rotazione legata agli eventi del mouse
        MouseRotate rotateBehavior = new MouseRotate( ) ;
        // Legame fa il comportamento e il TranformGroup.
        rotateBehavior.setTransformGroup(viewTransformGroup) ;
        //Zona in cui tenere conto degli eventi. Sfera di raggio 1 con centro nello 0
        rotateBehavior.setSchedulingBounds(new BoundingSphere(new Point3d(0.0,0.0,0.0), 1.0));
        //TranformGroup da legare alla rotazione interattiva.
        TransformGroup mainTG = new TransformGroup() ;
        Tetrahedron cube = new Tetrahedron();
        mainTG.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE) ;
        mainTG.addChild(cube);
        BranchGroup objRoot = new BranchGroup( ) ;
        objRoot.addChild(mainTG);
        objRoot.addChild(rotateBehavior);
        simpleU.addBranchGraph(objRoot);
    }

    public static void main(String[] args) {
        new MainFrame(new Rotazione(), 500, 500);
    }
}
