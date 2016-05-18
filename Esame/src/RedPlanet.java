import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.image.TextureLoader;

import javax.media.j3d.*;
import javax.vecmath.Vector3d;

/**
 * Created by stefanopassador on 17/05/16.
 */
public class RedPlanet extends Group {
    public RedPlanet(float dim) {
        TransformGroup tg = new TransformGroup();
        tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

        Sphere sphere = new Sphere(dim, Primitive.GENERATE_NORMALS | Primitive.GENERATE_TEXTURE_COORDS,
                100, createRedPlanetAppearance());
        tg.addChild(sphere);

        Transform3D transform3D = new Transform3D();
        transform3D.setTranslation(new Vector3d(
                -2.0,
                -2.0,
                3.0
        ));
        tg.setTransform(transform3D);
        addChild(tg);

        //Crea un behavior
        RotationBehavior rotator = new RotationBehavior(tg, transform3D);
        //Imposta un raggio d'azione del behavior
        BoundingSphere bounds = new BoundingSphere();
        rotator.setSchedulingBounds(bounds);
        //aggiunge l'interpolatore alla gruppo di trasformazione
        addChild(rotator);
    }

    private Appearance createRedPlanetAppearance() {
        Appearance appearance = new Appearance();
        Texture texture = new TextureLoader("mars.jpg", TextureLoader.GENERATE_MIPMAP, null).getTexture();
        TextureAttributes textureAttributes = new TextureAttributes () ;
        // Impostazioni per fondere il colore dell’oggetto con la texture.
        textureAttributes.setTextureMode(TextureAttributes.MODULATE) ;
        appearance.setTextureAttributes(textureAttributes);
        appearance.setTexture(texture);
        appearance.setMaterial(new Material());
        return appearance;
    }
}
