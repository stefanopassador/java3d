import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.geometry.ColorCube;
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
        scene.compile();


        // Creazione del SimpleUniverse
        SimpleUniverse simpleUniverse = new SimpleUniverse(canvas3D);
        simpleUniverse.getViewingPlatform().setNominalViewingTransform();
        View myView = simpleUniverse.getViewer().getView();

        myView.setFieldOfView(Math.PI / 4);
        Transform3D transform = new Transform3D();
        transform.lookAt(new Point3d(0.0, 0.0, 10.0),
                new Point3d(0.0, 0.0, 0.0),
                new Vector3d(0.0, 1.0, 0.0));
        transform.invert();
        TransformGroup vtg = simpleUniverse.getViewingPlatform().getViewPlatformTransform();
        vtg.setTransform(transform);

        simpleUniverse.addBranchGraph(scene);

//        OrbitBehavior orbit = new OrbitBehavior(canvas3D, OrbitBehavior.REVERSE_ROTATE);
//        orbit.setSchedulingBounds(new BoundingSphere());
//        simpleUniverse.getViewingPlatform().setViewPlatformBehavior(orbit);
    }

    public BranchGroup createSceneGraph() {
        BranchGroup branchGroup = new BranchGroup();
        TransformGroup transformGroup = new TransformGroup();

//        transformGroup.addChild(new Box(1.0f, 1.0f, 1.0f,
//                Primitive.GENERATE_NORMALS | Primitive.GENERATE_TEXTURE_COORDS,
//                createAppearance()));
        transformGroup.addChild(new ColorCube(1.0f));

        //Imposta la capacita' di scrivere la trasformazione
        transformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        //Aggiunge al gruppo un cubo colorato
        transformGroup.addChild(new ColorCube(0.4));
        //Crea un behavior
        CustomBehaviour rotator = new CustomBehaviour(transformGroup);
        //Imposta un raggio d'azione del behavior
        BoundingSphere bounds = new BoundingSphere();
        rotator.setSchedulingBounds(bounds);
        //aggiunge l'interpolatore alla gruppo di trasformazione
        transformGroup.addChild(rotator);

        branchGroup.addChild(transformGroup);
        branchGroup.addChild(createDirectionalLight());
        return branchGroup;
    }

    private Appearance createAppearance() {
        Appearance appearance = new Appearance();
        Texture texture = new TextureLoader("filepath", TextureLoader.GENERATE_MIPMAP, this).getTexture();
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

    private Background createBackground() {
        TextureLoader myLoader = new TextureLoader("filepath", this);
        ImageComponent2D myImage = myLoader.getImage();
        Background myBack = new Background();
        myBack.setImage(myImage);
        myBack.setImageScaleMode(Background.SCALE_FIT_MAX);
        BoundingSphere myBounds = new BoundingSphere(new Point3d(), 1000.0);
        myBack.setApplicationBounds(myBounds);
        return myBack;
    }

    public static void main(String [] args){
        new MainFrame(new Esame(), 800, 800);
    }
}
