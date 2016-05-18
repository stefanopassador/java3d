import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.behaviors.keyboard.KeyNavigatorBehavior;
import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.universe.SimpleUniverse;

import javax.media.j3d.*;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;
import java.applet.Applet;
import java.awt.*;

/**
 * Created by stefanopassador on 15/04/16.
 */
public class Esercizio4_4 extends Applet {
    public Esercizio4_4() {
        setLayout(new BorderLayout());
        GraphicsConfiguration config = SimpleUniverse
                .getPreferredConfiguration();
        Canvas3D canvas3D = new Canvas3D(config);
        add("Center", canvas3D);
        BranchGroup scene = createSceneGraph();


        // Creazione del SimpleUniverse
        SimpleUniverse simpleUniverse = new SimpleUniverse(canvas3D);
        simpleUniverse.getViewingPlatform().setNominalViewingTransform();
        View myView = simpleUniverse.getViewer().getView();
        TransformGroup vtg = simpleUniverse.getViewingPlatform()
                .getViewPlatformTransform();

        myView.setFieldOfView(Math.PI / 4);
        Transform3D transform = new Transform3D();
        transform.lookAt(new Point3d(0.0, 0.0, 10.0),
                new Point3d(0.0, 0.0, 0.0),
                new Vector3d(0.0, 1.0, 0.0));
        transform.invert();
        vtg.setTransform(transform);

        // Imposto il comportamento basato sui tasti
        KeyNavigatorBehavior keyNavBeh = new
                KeyNavigatorBehavior(vtg);
        //Imposto il bound del behavior
        keyNavBeh.setSchedulingBounds(new BoundingSphere(
                new Point3d(), 10000.0));
        scene.addChild(keyNavBeh);
        scene.compile();
        simpleUniverse.addBranchGraph(scene);
    }

    public BranchGroup createSceneGraph() {
        BranchGroup branchGroup = new BranchGroup();
        TransformGroup transformGroup = new TransformGroup();

        transformGroup.addChild(new PoseidoneTemple(createAppearance()));

        branchGroup.addChild(transformGroup);
        branchGroup.addChild(createDirectionalLight());
        return branchGroup;
    }

    private Appearance createAppearance() {
        Appearance appearance = new Appearance();
        Texture texture = new TextureLoader("PietraColonna.gif", TextureLoader.GENERATE_MIPMAP, this).getTexture();
        TextureAttributes textureAttributes = new TextureAttributes () ;
        // Impostazioni per fondere il colore dell’oggetto con la texture.
        textureAttributes.setTextureMode(TextureAttributes.MODULATE) ;
        appearance.setTextureAttributes(textureAttributes);
        appearance.setTexture(texture);
        appearance.setMaterial(new Material());
        return appearance;
    }

    private DirectionalLight createDirectionalLight() {
        // Creazione del bound
        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0d, 0.0d, 0.0d), 40.0d);
        // Creazione di una luce direzionale
        DirectionalLight lightD1 = new DirectionalLight();
        lightD1.setDirection(new Vector3f(
                0.0f,
                -2.0f,
                -10.0f
        ));
        // impostazione del bound
        lightD1.setInfluencingBounds(bounds);
        return lightD1;
    }

    public static void main(String[] args) {
        new MainFrame(new Esercizio4_4(), 800, 800);
    }
}
