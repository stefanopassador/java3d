import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Primitive;

import javax.media.j3d.*;
import javax.vecmath.Color3f;
import javax.vecmath.Vector3d;
import java.awt.*;

/**
 * Created by stefanopassador on 15/04/16.
 */
public class PoseidoneTemple extends Group {
    private static final int STAIRS_COUNT = 3;
    private static final float STAIRS_DIFFERENCE = 0.1f;
    private static final float STAIRS_HEIGHT = 0.03f;
    private static final int COLUMN_WIDTH = 6;
    private static final int COLUMN_DEPTH = 14;
    private static final float COLUMN_HEIGHT = 1.0f;
    private static final float COLUMN_WIDTH_SIZE = COLUMN_HEIGHT / 4.5f * 3;
    private static final float ROOF_1_HEIGHT = COLUMN_HEIGHT / 4.5f / 2;
    private Appearance appearance;
    /**
     * Costruttore di un tempio di Poseidone
     */
    public PoseidoneTemple(Appearance appearance) {
        this.appearance = appearance;
        // Creazione degli scalini sotto
        createStairs();

        // Creazione delle colonne
        createColumns();

        // Creazione del tetto
        createRoof1();
        createRoof2();
    }

    private void createStairs() {
        for (int i = 0; i < STAIRS_COUNT; i++) {
            float xDim = (COLUMN_WIDTH_SIZE * COLUMN_WIDTH / 2) +
                    (STAIRS_DIFFERENCE * (i + 1));
            float yDim = STAIRS_HEIGHT;
            float zDim = (COLUMN_WIDTH_SIZE * COLUMN_DEPTH / 2) +
                    (STAIRS_DIFFERENCE * (i + 1));

            Box box = new Box(
                    xDim,
                    yDim,
                    zDim,
                    Primitive.GENERATE_TEXTURE_COORDS |
                            Primitive.GENERATE_NORMALS,
                    appearance);

            Transform3D transform3D = new Transform3D();
            transform3D.setTranslation(new Vector3d(
                    0.0,
                    -((COLUMN_HEIGHT / 4.5 * 1.5f) + (STAIRS_HEIGHT) + (i * STAIRS_HEIGHT * 2)),
                    -COLUMN_WIDTH_SIZE / 2
            ));

            TransformGroup tg = new TransformGroup();
            tg.addChild(box);
            tg.setTransform(transform3D);

            addChild(tg);
        }
    }

    private void createColumns() {
        for (int i = 0; i < COLUMN_WIDTH; i++) {
            for (int j = 0; j < COLUMN_DEPTH; j++) {
                if (i == 0 || i == COLUMN_WIDTH - 1 ||
                        j == 0 || j == COLUMN_DEPTH - 1) {
                    TransformGroup tg = new TransformGroup();
                    DoricColumn doricColumn = new DoricColumn(COLUMN_HEIGHT, appearance);
                    tg.addChild(doricColumn);
                    Transform3D transformation = new Transform3D();
                    float xTranslation =
                            (-(COLUMN_WIDTH_SIZE * COLUMN_WIDTH) / 2) + i * COLUMN_WIDTH_SIZE + COLUMN_WIDTH_SIZE / 2;
                    float zTranslation =
                            (-(COLUMN_WIDTH_SIZE * COLUMN_DEPTH) / 2) + j * COLUMN_WIDTH_SIZE;
                    transformation.setTranslation(new Vector3d(xTranslation, 0.0f, zTranslation));

                    tg.setTransform(transformation);
                    addChild(tg);
                }
            }
        }
    }

    private void createRoof1() {
        Box frontBox = new Box(COLUMN_WIDTH_SIZE * COLUMN_WIDTH / 2, ROOF_1_HEIGHT, COLUMN_WIDTH_SIZE / 2,
                Primitive.GENERATE_TEXTURE_COORDS | Primitive.GENERATE_NORMALS, appearance);
        Box backBox = new Box(COLUMN_WIDTH_SIZE * COLUMN_WIDTH / 2, ROOF_1_HEIGHT, COLUMN_WIDTH_SIZE / 2,
                Primitive.GENERATE_TEXTURE_COORDS | Primitive.GENERATE_NORMALS, appearance);

        Box leftBox = new Box(COLUMN_WIDTH_SIZE / 2, ROOF_1_HEIGHT, COLUMN_WIDTH_SIZE * (COLUMN_DEPTH - 2) / 2 ,
                Primitive.GENERATE_TEXTURE_COORDS | Primitive.GENERATE_NORMALS, appearance);
        Box rightBox = new Box(COLUMN_WIDTH_SIZE / 2, ROOF_1_HEIGHT, COLUMN_WIDTH_SIZE * (COLUMN_DEPTH - 2) / 2,
                Primitive.GENERATE_TEXTURE_COORDS | Primitive.GENERATE_NORMALS, appearance);

        TransformGroup tg;
        Transform3D transform3D;

        float yTranslation = COLUMN_HEIGHT / 4.5f * 3 + ROOF_1_HEIGHT;
        // Frontal box
        tg = new TransformGroup();
        transform3D = new Transform3D();
        transform3D.setTranslation(new Vector3d(0.0, yTranslation, (COLUMN_WIDTH_SIZE * COLUMN_DEPTH / 2) - COLUMN_WIDTH_SIZE));
        tg.setTransform(transform3D);
        tg.addChild(frontBox);
        addChild(tg);

        // Back box
        tg = new TransformGroup();
        transform3D = new Transform3D();
        transform3D.setTranslation(new Vector3d(0.0, yTranslation, -((COLUMN_WIDTH_SIZE * COLUMN_DEPTH / 2) )));
        tg.setTransform(transform3D);
        tg.addChild(backBox);
        addChild(tg);

        // Left box
        tg = new TransformGroup();
        transform3D = new Transform3D();
        transform3D.setTranslation(new Vector3d(-((COLUMN_WIDTH_SIZE * COLUMN_WIDTH / 2) - COLUMN_WIDTH_SIZE/2), yTranslation, -COLUMN_WIDTH_SIZE/2));
        tg.setTransform(transform3D);
        tg.addChild(leftBox);
        addChild(tg);

        // Right box
        tg = new TransformGroup();
        transform3D = new Transform3D();
        transform3D.setTranslation(new Vector3d(((
                COLUMN_WIDTH_SIZE * COLUMN_WIDTH / 2) - COLUMN_WIDTH_SIZE/2),
                yTranslation,
                -COLUMN_WIDTH_SIZE/2));
        tg.setTransform(transform3D);
        tg.addChild(rightBox);
        addChild(tg);
    }

    private void createRoof2() {
        // Front
        TransformGroup tg = new TransformGroup();
        TriangularPrism triangularPrism = new TriangularPrism(appearance);

        Transform3D scale = new Transform3D();
        scale.setScale(new Vector3d(
                COLUMN_WIDTH_SIZE * COLUMN_WIDTH / 2,
                0.5,
                COLUMN_WIDTH_SIZE / 2
        ));
        Transform3D translation = new Transform3D();
        float yTranslation = COLUMN_HEIGHT / 4.5f * 3 + ROOF_1_HEIGHT + ROOF_1_HEIGHT;
        translation.setTranslation(new Vector3d(
                0.0,
                yTranslation,
                (COLUMN_WIDTH_SIZE * COLUMN_DEPTH) / 2 - COLUMN_WIDTH_SIZE
        ));
        translation.mul(scale);
        tg.setTransform(translation);
        tg.addChild(triangularPrism);
        addChild(tg);

        // Back
        tg = new TransformGroup();
        triangularPrism = new TriangularPrism(appearance);

        scale = new Transform3D();
        scale.setScale(new Vector3d(
                COLUMN_WIDTH_SIZE * COLUMN_WIDTH / 2,
                0.5,
                COLUMN_WIDTH_SIZE / 2
        ));
        translation = new Transform3D();
        translation.setTranslation(new Vector3d(
                0.0,
                yTranslation,
                (-(COLUMN_WIDTH_SIZE * COLUMN_DEPTH) / 2)
        ));
        translation.mul(scale);
        tg.setTransform(translation);
        tg.addChild(triangularPrism);
        addChild(tg);
    }
}
