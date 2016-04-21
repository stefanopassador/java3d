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
 * Created by stefanopassador on 21/04/16.
 */
public class Lezione21042016 extends Applet {
    public Lezione21042016() {
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
        transform.lookAt(new Point3d(0.0, 0.0, 30.0),
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
        Appearance appearance = createAppearance();
        int dim = 10;
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                TransformGroup transformGroup = new TransformGroup();
                Flat flat = new Flat();
                flat.setAppearance(appearance);

                Transform3D transform3D = new Transform3D();
                transform3D.setTranslation(new Vector3d(
                        -(flat.getWidth() * (dim / 2)) + (flat.getWidth() * i) + flat.getWidth() / 2,
                        -(flat.getHeight() * (dim / 2)) + (flat.getHeight() * j) + flat.getHeight() / 2,
                        0.0f
                ));

                transformGroup.addChild(flat);
                transformGroup.setTransform(transform3D);

                branchGroup.addChild(transformGroup);
            }
        }

        return branchGroup;
    }

    private Appearance createAppearance() {
        //Crea l'aspetto del piano
        Appearance appear = new Appearance();
        //Carica la texture
        Texture texture = new TextureLoader("mattone.jpg", TextureLoader.GENERATE_MIPMAP, this).getTexture();
        TextureAttributes textureAttributes = new TextureAttributes () ;
        // Impostazioni per fondere il colore dell’oggetto con la texture.
        textureAttributes.setTextureMode(TextureAttributes.MODULATE);
        appear.setTextureAttributes(textureAttributes);
        appear.setTexture(texture);
        return appear;
    }

    public static void main(String[] args) {
        new MainFrame(new Lezione21042016(), 800, 800);
    }
}
