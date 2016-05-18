import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;
import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.universe.SimpleUniverse;

import javax.media.j3d.*;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import java.applet.Applet;
import java.awt.*;

/**
 * Created by stefanopassador on 14/04/16.
 */
public class Esercizio4_3 extends Applet {
    public Esercizio4_3() {
        setLayout(new BorderLayout()); //layout manager del container
        //trova la miglior configurazione grafica per il sistema
        GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
        // Canvas3: si occupa del rendering 3D on-screen e off-screen
        Canvas3D canvas3D = new Canvas3D(config);
        add("Center", canvas3D);
        BranchGroup scene = createSceneGraph();
        scene.compile();

        // Creazione del SimpleUniverse
        SimpleUniverse simpleUniverse = new SimpleUniverse(canvas3D);
        simpleUniverse.getViewingPlatform()
                .setNominalViewingTransform();
        View myView = simpleUniverse.getViewer().getView();

        myView.setFieldOfView(Math.PI / 4);
        Transform3D transform = new Transform3D();
        transform.lookAt(new Point3d(0.0, 0.0, 8.0),
                new Point3d(0.0, 0.0, 0.0),
                new Vector3d(0.0, 1.0, 0.0));
        transform.invert();
        TransformGroup vtg = simpleUniverse
                .getViewingPlatform().getViewPlatformTransform();
        vtg.setTransform(transform);
        // creazione e aggiunta del sottografo principale

        simpleUniverse.addBranchGraph(scene);

        OrbitBehavior orbit = new OrbitBehavior(canvas3D, OrbitBehavior.REVERSE_ROTATE);
        orbit.setSchedulingBounds(new BoundingSphere());
        simpleUniverse.getViewingPlatform().setViewPlatformBehavior(orbit);
    }

    /**
     * Funzione che crea il sottografo
     *
     * @return il BranchGroup da aggiungere al SimpleUniverse
     */
    public BranchGroup createSceneGraph() {
        BranchGroup branchGroup = new BranchGroup();
        TransformGroup transformGroup = new TransformGroup();
        Sphere earth = new Sphere(1.0f,
                Primitive.GENERATE_TEXTURE_COORDS | Primitive.GENERATE_NORMALS,
                createEarthAppearance());
        transformGroup.addChild(earth);
        branchGroup.addChild(transformGroup);
        TransformGroup transformGroup2 = new TransformGroup();
        transformGroup2.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

        Sphere moon = new Sphere(0.3f,
                Primitive.GENERATE_TEXTURE_COORDS | Primitive.GENERATE_NORMALS,
                createMoonAppearance());
        transformGroup2.addChild(moon);

        RotationBehaviour rotator = new RotationBehaviour(transformGroup2);
        // Imposta un raggio d'azione del behavior
        BoundingSphere bounds = new BoundingSphere();
        rotator.setSchedulingBounds(bounds);
        // Aggiungo l'interpolatore al gruppo di trasformazione
        branchGroup.addChild(rotator);

        branchGroup.addChild(transformGroup2);
        branchGroup.addChild(createBackground());
        branchGroup.addChild(createDirectionalLight());
        return branchGroup;
    }

    private Appearance createEarthAppearance() {
        Appearance appearance = new Appearance();
        Texture texture = new TextureLoader("earth.jpg",
                TextureLoader.GENERATE_MIPMAP, this).getTexture();
        TextureAttributes textureAttributes = new TextureAttributes () ;
        // Impostazioni per fondere il colore dell’oggetto con la texture.
        textureAttributes.setTextureMode(TextureAttributes.MODULATE) ;
        appearance.setTextureAttributes(textureAttributes);
        appearance.setTexture(texture);
        appearance.setMaterial(new Material());
        return appearance;
    }

    private Appearance createMoonAppearance() {
        Appearance appearance = new Appearance();
        Texture texture = new TextureLoader("moon.jpg",
                TextureLoader.GENERATE_MIPMAP, this).getTexture();
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
        // impostazione del bound
        lightD1.setInfluencingBounds(bounds);
        return lightD1;
    }

    private Background createBackground() {
        TextureLoader myLoader = new TextureLoader("stars.jpg", this);
        ImageComponent2D myImage = myLoader.getImage();
        Background myBack = new Background();
        myBack.setImage(myImage);
        myBack.setImageScaleMode(Background.SCALE_FIT_MAX);
        BoundingSphere myBounds = new BoundingSphere(new Point3d(), 1000.0);
        myBack.setApplicationBounds(myBounds);
        return myBack;
    }

    public static void main(String[] args) {
        new MainFrame(new Esercizio4_3(), 800, 800);
    }
}
