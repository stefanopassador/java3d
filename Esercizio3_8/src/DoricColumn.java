import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.image.TextureLoader;

import javax.media.j3d.*;
import javax.vecmath.Vector3f;
import java.awt.*;

/**
 * Class that create a DoricColumn
 * Created by stefanopassador on 10/04/16.
 */
public class DoricColumn extends Group {
    private float height;
    private TrunkedSquarePyramid trunkedSquarePyramid;
    private Component component;
    private Appearance appearance = createAppeareance();
    /**
     * Constructor of a doric column
     *
     * @param height The height of the figure
     * @param component Needed for the texture
     */
    public DoricColumn(float height, Component component) {
        this.component = component;
        this.height = height;
        addFusto();
        addEchino();
        addAbaco();
    }

    private void addFusto() {
        // The cylinder
        TransformGroup transformGroupCylinder = new TransformGroup();
        transformGroupCylinder.addChild(new Cylinder(height / 4.5f / 2, (height / 4.5f) * 3,
                Primitive.GENERATE_NORMALS | Primitive.GENERATE_TEXTURE_COORDS, 20, 1, appearance));
        addChild(transformGroupCylinder);
    }

    private void addEchino() {
        TransformGroup transformGroupTrunkedPyramid = new TransformGroup();
        trunkedSquarePyramid = new TrunkedSquarePyramid(height / 4.5f / 2, height / 4.5f, height / 4.5f,
                appearance);
        transformGroupTrunkedPyramid.addChild(trunkedSquarePyramid);
        Transform3D transformationPyramid = new Transform3D();
        transformationPyramid.rotX((Math.PI / 2) * 3);
        Transform3D transformationPyramid3 = new Transform3D();
        transformationPyramid3.setTranslation(new Vector3f(0.0f, 0.0f, ((height / 4.5f) * 3) / 2));
        transformationPyramid.mul(transformationPyramid3);
        transformGroupTrunkedPyramid.setTransform(transformationPyramid);
        addChild(transformGroupTrunkedPyramid);
    }

    private void addAbaco() {
        TransformGroup transformGroupTrunkedPyramid = new TransformGroup();
        float size = height / 4.5f; //(height / 5) / (float) Math.sqrt(2.0);
        Box box = new Box(size, height / 4.5f / 4, size,
                Primitive.GENERATE_NORMALS | Primitive.GENERATE_TEXTURE_COORDS, appearance);

        transformGroupTrunkedPyramid.addChild(box);
        Transform3D transformation = new Transform3D();
        transformation.setTranslation(new Vector3f(0.0f,
                (((height / 4.5f) * 3) / 2) + height / 4.5f + height / 4.5f / 4,
                0.0f));
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
    private Appearance createAppeareance() {
        Appearance appearance = new Appearance();
        Texture texture = new TextureLoader("PietraColonna.gif", TextureLoader.GENERATE_MIPMAP, component).getTexture();
        TextureAttributes textureAttributes = new TextureAttributes () ;
        // Impostazioni per fondere il colore dell’oggetto con la texture.
        textureAttributes.setTextureMode(TextureAttributes.MODULATE) ;
        appearance.setTextureAttributes(textureAttributes);
        appearance.setTexture(texture);
        appearance.setMaterial(new Material());
        return appearance;
    }
}
