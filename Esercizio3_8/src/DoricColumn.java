import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.geometry.Primitive;

import javax.media.j3d.*;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;
import java.awt.*;

/**
 * Created by stefanopassador on 10/04/16.
 */
public class DoricColumn extends Group {
    private float HEIGHT;
    private TrunkedSquarePyramid trunkedSquarePyramid;
    /**
     * Constructor of a doric column
     *
     * @param height The height of the figure
     */
    public DoricColumn(float height) {
        this.HEIGHT = height;
        addFusto();
        addEchino();
        addAbaco();
    }

    private void addFusto() {
        // The cylinder
        TransformGroup transformGroupCylinder = new TransformGroup();
        transformGroupCylinder.addChild(new Cylinder(HEIGHT / 10, (HEIGHT / 5) * 4, Primitive.GENERATE_NORMALS, 20, 1, createAppeareance()));
        addChild(transformGroupCylinder);
    }

    private void addEchino() {
        TransformGroup transformGroupTrunkedPyramid = new TransformGroup();
        trunkedSquarePyramid = new TrunkedSquarePyramid(HEIGHT / 10, HEIGHT / 5, createAppeareance());
        transformGroupTrunkedPyramid.addChild(trunkedSquarePyramid);
        Transform3D transformationPyramid = new Transform3D();
        transformationPyramid.rotX((Math.PI / 2) * 3);
        Transform3D transformationPyramid3 = new Transform3D();
        transformationPyramid3.setTranslation(new Vector3f(0.0f, 0.0f, (HEIGHT / 2) - trunkedSquarePyramid.getHeight()));
        transformationPyramid.mul(transformationPyramid3);
        transformGroupTrunkedPyramid.setTransform(transformationPyramid);
        addChild(transformGroupTrunkedPyramid);
    }

    private void addAbaco() {
        TransformGroup transformGroupTrunkedPyramid = new TransformGroup();
        float size = HEIGHT / 5; //(HEIGHT / 5) / (float) Math.sqrt(2.0);
        Box box = new Box(size, HEIGHT / 20, size, Primitive.GENERATE_NORMALS, createAppeareance());

        transformGroupTrunkedPyramid.addChild(box);
        Transform3D transformation = new Transform3D();
        transformation.setTranslation(new Vector3f(0.0f, (HEIGHT / 2) + trunkedSquarePyramid.getHeight() - box.getYdimension(), 0.0f));
        Transform3D transformation2 = new Transform3D();
        transformation2.rotY(Math.PI / 4);
        transformation.mul(transformation2);
        transformGroupTrunkedPyramid.setTransform(transformation);
        addChild(transformGroupTrunkedPyramid);
    }
    /**
     * Metodo che genera l'Appearance dei vari box
     *
     * @return L'istanza di Appearance generata
     */
    private static Appearance createAppeareance() {
//        // Creo un oggetto Appearance
        Appearance mAppearance = new Appearance();
        // Definisco il colore
        mAppearance.setColoringAttributes(new ColoringAttributes(Color.DARK_GRAY.getRed(), Color.DARK_GRAY.getGreen(), Color.DARK_GRAY.getBlue(), ColoringAttributes.SHADE_GOURAUD));

        mAppearance.setPolygonAttributes(new PolygonAttributes(PolygonAttributes.POLYGON_FILL, PolygonAttributes.CULL_NONE, 0));

        // Set up a basic material
        Material mat = new Material( );
        mat.setAmbientColor( 0.2f, 0.2f, 0.2f );
        mat.setDiffuseColor( 1.0f, 1.0f, 1.0f );
        mat.setSpecularColor( 0.0f, 0.0f, 0.0f );
        mat.setLightingEnable( true );

        // Set up the texturing attributes with an initial
        // texture mode, texture transform, and blend color
        TextureAttributes texatt = new TextureAttributes( );
        texatt.setPerspectiveCorrectionMode( TextureAttributes.NICEST );
        texatt.setTextureTransform( new Transform3D( ) );  // Identity
        Color blendColor = Color.GRAY;
        texatt.setTextureBlendColor( blendColor.getRed(), blendColor.getBlue(), blendColor.getGreen(), 0.5f );

        // Enable changing these while the node component is live
        texatt.setCapability( TextureAttributes.ALLOW_MODE_WRITE );
        texatt.setCapability( TextureAttributes.ALLOW_BLEND_COLOR_WRITE );
        texatt.setCapability( TextureAttributes.ALLOW_TRANSFORM_WRITE );

        // Create an appearance using these attributes
        mAppearance.setMaterial(mat);
        mAppearance.setTextureAttributes(texatt);
        return mAppearance;
    }
}
