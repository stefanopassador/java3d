import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;
import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.universe.SimpleUniverse;

import javax.media.j3d.*;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;
import java.applet.Applet;
import java.awt.*;

/**
 * Created by stefanopassador on 15/04/16.
 */
public class Esercizio3_10 extends Applet {
    public Esercizio3_10() {
        setLayout(new BorderLayout()); //layout manager del container
        //trova la miglior configurazione grafica per il sistema
        GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
        // Canvas3: si occupa del rendering 3D on-screen e off-screen
        Canvas3D canvas3D = new Canvas3D(config);
        add("Center", canvas3D);
        BranchGroup scene = createSceneGraph(); // creazione del sottografo principale
        scene.compile();


        // Creazione del SimpleUniverse
        SimpleUniverse simpleUniverse = new SimpleUniverse(canvas3D);
        simpleUniverse.getViewingPlatform().setNominalViewingTransform(); // accedo all'oggetto view del SimpleUniverse
        View myView = simpleUniverse.getViewer().getView(); //Impostazione della distanza dal piano sullo sfondo myView.setBackClipDistance(10.0);

        myView.setFieldOfView(Math.PI / 4);
        Transform3D transform = new Transform3D();
        transform.lookAt(new Point3d(0.0, 0.0, 10.0),
                new Point3d(0.0, 0.0, 0.0),
                new Vector3d(0.0, 1.0, 0.0));
        transform.invert();
        TransformGroup vtg = simpleUniverse.getViewingPlatform().getViewPlatformTransform();
        vtg.setTransform(transform);
        // creazione e aggiunta del sottografo principale

        simpleUniverse.addBranchGraph(scene);

        OrbitBehavior orbit = new OrbitBehavior(canvas3D, OrbitBehavior.REVERSE_ROTATE);
        orbit.setSchedulingBounds(new BoundingSphere());
        simpleUniverse.getViewingPlatform().setViewPlatformBehavior(orbit);
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
        new MainFrame(new Esercizio3_10(), 800, 800);
    }
}
