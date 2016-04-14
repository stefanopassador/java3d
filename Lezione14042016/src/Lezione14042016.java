import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;
import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.universe.SimpleUniverse;

import javax.media.j3d.*;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.TexCoord2f;
import javax.vecmath.Vector3d;
import java.applet.Applet;
import java.awt.*;

/**
 * Created by stefanopassador on 14/04/16.
 */
public class Lezione14042016 extends Applet {
    public Lezione14042016() {
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
        transform.lookAt(new Point3d(0.0, 0.0, 8.0),
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

    /**
     * Funzione che crea il sottografo
     *
     * @return il BranchGroup da aggiungere al SimpleUniverse
     */
    public BranchGroup createSceneGraph() {
        BranchGroup branchGroup = new BranchGroup(); // Creo un oggetto di tipo BranchGroup
        TransformGroup transformGroup = new TransformGroup();
        Flat flat = new Flat();
//        Shape3D shape = new Shape3D(flat.createGeometry(), createAppearance());
        Sphere earth = new Sphere(1.0f, Primitive.GENERATE_TEXTURE_COORDS, createEarthAppearance());

        transformGroup.addChild(earth);


        branchGroup.addChild(transformGroup);

        TextureLoader myLoader = new TextureLoader("stars.jpg", this);
        ImageComponent2D myImage = myLoader.getImage();
        Background myBack = new Background();
        myBack.setImage(myImage);
        myBack.setImageScaleMode(Background.SCALE_FIT_MAX);
        BoundingSphere myBounds = new BoundingSphere(new Point3d(), 1000.0);
        myBack.setApplicationBounds(myBounds);
        branchGroup.addChild(myBack);

//        branchGroup.addChild(createDirectionalLight());
        return branchGroup;
    }

    private Appearance createAppearance() {
        //Crea l'aspetto del piano
        Appearance appear = new Appearance();
        //Carica la texture
        TextureLoader loader = new TextureLoader("stripe.gif", this);
        ImageComponent2D image = loader.getImage();

        Texture2D texture = new Texture2D(Texture.BASE_LEVEL,
                Texture.RGBA, image.getWidth(), image.getHeight());
        texture.setImage(0, image);

        //Imposta il comportamento ai bordi
        texture.setBoundaryModeS(Texture.WRAP);//orizzontale
        texture.setBoundaryModeT(Texture.WRAP); //verticale
        // Texture.WRAP --> Ripete
        // Texture.CLAMP --> Prolunga il bordo

        appear.setTexture(texture);
        return appear;
    }

    private Appearance createEarthAppearance() {
        Appearance appearance = new Appearance();
        TextureLoader textureLoader = new TextureLoader("earth.jpg", null);
        Texture texture = textureLoader.getTexture();
        appearance.setTexture(texture);
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


    public static void main(String[] args) {
        new MainFrame(new Lezione14042016(), 800, 800);
    }
}
