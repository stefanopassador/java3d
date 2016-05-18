import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.image.TextureLoader;

import javax.media.j3d.*;
import javax.vecmath.Vector3d;

/**
 * Created by stefanopassador on 17/05/16.
 */
public class Spaceship extends Group {
    private float dim;
    private Appearance appearance = createAppearance();
    public Spaceship(float dim) {
        this.dim = dim;
        // corpo centrale: sfera con texture
        createBody();
        // motore centrale: cilindro ed echino finale
        createTrunkedPyramid();
        // sezione di collegamento per i motori destro e sinistro: cilindro; - motore destro e sinistro: parallelepipedi
        createLink();
        // motore destro e sinistro: parallelepipedi
        createEngines();
    }

    private void createBody() {
        TransformGroup tg = new TransformGroup();
        Sphere sphere = new Sphere(dim/8, Primitive.GENERATE_NORMALS | Primitive.GENERATE_TEXTURE_COORDS,
                appearance);
        tg.addChild(sphere);
        addChild(tg);
    }

    private void createTrunkedPyramid() {
        TransformGroup tg = new TransformGroup();
        TrunkedSquarePyramid trunkedSquarePyramid = new TrunkedSquarePyramid(dim/16, dim/12, dim/4, appearance);
        tg.addChild(trunkedSquarePyramid);

        TransformGroup tg2 = new TransformGroup();
        tg2.addChild(new Cylinder(dim/12, 0.0f, Primitive.GENERATE_NORMALS | Primitive.GENERATE_TEXTURE_COORDS, appearance));
        Transform3D transform3D2 = new Transform3D();
        transform3D2.rotX(Math.PI/2);
        Transform3D transform3D3 = new Transform3D();
        transform3D3.setTranslation(new Vector3d(
                0.0,
                0.0,
                dim/4
        ));
        transform3D3.mul(transform3D2);
        tg2.setTransform(transform3D3);
        tg.addChild(tg2);
        TransformGroup transformGroup = new TransformGroup();
        transformGroup.addChild(tg);
        Transform3D transform3D = new Transform3D();
        transform3D.setTranslation(new Vector3d(
                0.0,
                0.0,
                dim/8 - dim/16
        ));
        transformGroup.setTransform(transform3D);

        addChild(transformGroup);
    }

    private void createLink() {
        Cylinder cylinder = new Cylinder(dim/30, dim/3, Primitive.GENERATE_NORMALS | Primitive.GENERATE_TEXTURE_COORDS,
                appearance);

        TransformGroup tg = new TransformGroup();
        tg.addChild(cylinder);

        Transform3D transform3D = new Transform3D();
        transform3D.setTranslation(new Vector3d(
                0.0,
                0.0,
                dim/5
        ));
        tg.setTransform(transform3D);
        addChild(tg);
    }

    private void createEngines() {
        Box box1 = new Box(dim/30, dim/30, dim/12, Primitive.GENERATE_NORMALS | Primitive.GENERATE_TEXTURE_COORDS, appearance);
        Transform3D transform3D = new Transform3D();
        transform3D.setTranslation(new Vector3d(
                0.0,
                dim/6,
                dim/5
        ));
        TransformGroup tg = new TransformGroup();
        tg.setTransform(transform3D);
        tg.addChild(box1);
        Box box2 = new Box(dim/30, dim/30, dim/12, Primitive.GENERATE_NORMALS | Primitive.GENERATE_TEXTURE_COORDS, appearance);
        Transform3D transform3D2 = new Transform3D();
        transform3D2.setTranslation(new Vector3d(
                0.0,
                -dim/6,
                dim/5
        ));
        TransformGroup tg2= new TransformGroup();
        tg2.setTransform(transform3D2);
        tg2.addChild(box2);

        addChild(tg);
        addChild(tg2);
    }


    private Appearance createAppearance() {
        Appearance appearance = new Appearance();
        Texture texture = new TextureLoader("hope.jpg", TextureLoader.GENERATE_MIPMAP, null).getTexture();
        TextureAttributes textureAttributes = new TextureAttributes();
        // Impostazioni per fondere il colore dell’oggetto con la texture.
        textureAttributes.setTextureMode(TextureAttributes.MODULATE);
        appearance.setTextureAttributes(textureAttributes);
        appearance.setTexture(texture);
        appearance.setMaterial(new Material());
        return appearance;
    }
}
