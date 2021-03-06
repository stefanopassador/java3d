import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.behaviors.keyboard.KeyNavigatorBehavior;
import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;
import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.universe.SimpleUniverse;

import javax.media.j3d.*;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;
import java.applet.Applet;
import java.awt.*;

/**
 * Created by stefanopassador on 17/05/16.
 */
public class Esame extends Applet {
    public Esame() {
        setLayout(new BorderLayout());
        GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
        Canvas3D canvas3D = new Canvas3D(config);
        add("Center", canvas3D);
        BranchGroup scene = createSceneGraph();

        // Creazione del SimpleUniverse
        SimpleUniverse simpleUniverse = new SimpleUniverse(canvas3D);
        simpleUniverse.getViewingPlatform().setNominalViewingTransform();
        TransformGroup vtg = simpleUniverse.getViewingPlatform().getViewPlatformTransform();

        View myView = simpleUniverse.getViewer().getView();

        myView.setFieldOfView(Math.PI / 4);
        Transform3D transform = new Transform3D();
        transform.lookAt(new Point3d(0.0, 0.0, 10.0),
                new Point3d(0.0, 0.0, 0.0),
                new Vector3d(0.0, 1.0, 0.0));
        transform.invert();
        vtg.setTransform(transform);

        KeyNavigatorBehavior keyNavBeh = new KeyNavigatorBehavior(vtg);
        //Imposto il bound del behavior
        keyNavBeh.setSchedulingBounds(new BoundingSphere(new
                Point3d(), 10000.0));
        scene.addChild(keyNavBeh);
        scene.compile();
        simpleUniverse.addBranchGraph(scene);


        OrbitBehavior orbit = new OrbitBehavior(canvas3D, OrbitBehavior.REVERSE_ROTATE);
        orbit.setSchedulingBounds(new BoundingSphere());
        simpleUniverse.getViewingPlatform().setViewPlatformBehavior(orbit);
    }

    public BranchGroup createSceneGraph() {
        BranchGroup branchGroup = new BranchGroup();
        TransformGroup transformGroup = new TransformGroup();

        //Imposta la capacita' di scrivere la trasformazione
        transformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        //Aggiunge al gruppo un cubo colorato
        transformGroup.addChild(new CubeSpaceship(0.5f));
        // Aggiungo al gruppo il pianeta rosso
        transformGroup.addChild(new RedPlanet(1.0f));
        // Aggiungo al gruppo l'astronave
        transformGroup.addChild(new Spaceship(1.5f));

        branchGroup.addChild(transformGroup);
        branchGroup.addChild(createDirectionalLight());
        branchGroup.addChild(createBackground());
        return branchGroup;
    }

    private DirectionalLight createDirectionalLight() {
        // Creazione del bound
        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0d, 0.0d, 0.0d), 40.0d);
        // Creazione di una luce direzionale
        DirectionalLight lightD1 = new DirectionalLight();
        lightD1.setDirection(new Vector3f(
                10.0f,
                -2.0f,
                -10.0f
        ));
        // impostazione del bound
        lightD1.setInfluencingBounds(bounds);
        return lightD1;
    }

    private Background createBackground() {
        TextureLoader myLoader = new TextureLoader("space.jpg", this);
        ImageComponent2D myImage = myLoader.getImage();
        Background myBack = new Background();
        myBack.setImage(myImage);
        myBack.setImageScaleMode(Background.SCALE_FIT_MAX);
        BoundingSphere myBounds = new BoundingSphere(new Point3d(), 1000.0);
        myBack.setApplicationBounds(myBounds);
        return myBack;
    }

    public static void main(String [] args){
        new MainFrame(new Esame(), 1024, 800);
    }
}
