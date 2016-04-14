import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;
import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.universe.SimpleUniverse;

import javax.media.j3d.*;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;
import java.applet.Applet;
import java.awt.*;

/**
 * Created by stefanopassador on 08/04/16.
 */
public class Esercizio3_7 extends Applet {
    private static final int MATRIX_DIM = 5;

    public Esercizio3_7() {
        setLayout(new BorderLayout()); //layout manager del container
        //trova la miglior configurazione grafica per il sistema
        GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
        // Canvas3: si occupa del rendering 3D on-screen e off-screen
        Canvas3D canvas3D = new Canvas3D(config);
        add("Center", canvas3D);
        BranchGroup scene = createSceneGraph(); // creazione del sottografo principale
        scene.compile();

        add("Center", canvas3D);
        SimpleUniverse simpleU = new SimpleUniverse(canvas3D);
        simpleU.getViewingPlatform().setNominalViewingTransform();

        simpleU.addBranchGraph(scene);

        OrbitBehavior orbit = new OrbitBehavior(canvas3D, OrbitBehavior.REVERSE_ROTATE);
        orbit.setSchedulingBounds(new BoundingSphere());
        simpleU.getViewingPlatform().setViewPlatformBehavior(orbit);
    }

    /**
     * Funzione che crea il sottografo
     *
     * @return il BranchGroup da aggiungere al SimpleUniverse
     */
    public BranchGroup createSceneGraph() {
        BranchGroup branchGroup = new BranchGroup(); // Creo un oggetto di tipo BranchGroup
        Appearance appearance = createAppeareance();
        Appearance noMaterialAppearance = createAppeareanceWithNoMaterial();
        float single_translation = 0.2f;
        Transform3D transformation;
        // Creo una matrice di sfere
        for (int i = 0; i < MATRIX_DIM; i++) {
            for (int j = 0; j < MATRIX_DIM; j++) {
                TransformGroup transform = new TransformGroup(); // Creo un oggetto di tipo TransformGroup
                transformation = new Transform3D();
                transformation.setTranslation(new Vector3f((i - MATRIX_DIM/2) * single_translation, (j - MATRIX_DIM/2) * single_translation, 0.0f));
                transform.setTransform(transformation);
                Sphere sphere = new Sphere(0.1f);
                sphere.setAppearance(noMaterialAppearance);
                if (i > 0 && j > 0 && i < (MATRIX_DIM - 1) && (j < MATRIX_DIM - 1)) {
                    sphere = new Sphere(0.1f, Primitive.GEOMETRY_NOT_SHARED | Primitive.GENERATE_NORMALS, appearance);
                }
                transform.addChild(sphere);

                branchGroup.addChild(transform); // Aggiunge l'oggetto transform come figlo al BranchGroup
            }
        }
        branchGroup.addChild(createDirectionalLight()); // aggiunta della light al BranchGroup
        return branchGroup;
    }

    private DirectionalLight createDirectionalLight() {
        // Creazione del bound
        BoundingSphere bounds = new BoundingSphere(new Point3d(1.0d, 0.0d, 0.0d), 40.0d);
        // Creazione di una luce direzionale
        DirectionalLight lightD1 = new DirectionalLight();

        // impostazione del bound
        lightD1.setInfluencingBounds(bounds);
        return lightD1;
    }

    private Appearance createAppeareance() {
        Appearance mAppearance = new Appearance();
        Material material = new Material();
        mAppearance.setMaterial(material);
        return mAppearance;
    }

    private Appearance createAppeareanceWithNoMaterial() {
        Appearance mAppearance = new Appearance();
        ColoringAttributes mColoringAttributes = new ColoringAttributes();
        mColoringAttributes.setColor(new Color3f(Color.GRAY));
        mAppearance.setColoringAttributes(mColoringAttributes);
        mAppearance.setPolygonAttributes(new PolygonAttributes(PolygonAttributes.POLYGON_FILL, PolygonAttributes.CULL_FRONT, 0));
        return mAppearance;
    }



    public static void main(String[] args) {
        new MainFrame(new Esercizio3_7(), 800, 800);
    }
}
