import javax.media.j3d.*;
import javax.vecmath.Vector3d;
import java.util.Enumeration;

/**
 * Classe che definisce il comportamento di rotazione temporizzato.
 *
 * Created by stefanopassador on 28/04/16.
 */
class RotationBehaviour extends Behavior {
    private TransformGroup targetTG;
    private Transform3D rotation = new Transform3D();
    Transform3D translation = new Transform3D();
    private double angle = 0.0;
    // Definisco il tipo di comportamento -> Temporizzato
    private WakeupOnElapsedTime awt = new WakeupOnElapsedTime(20);

    public RotationBehaviour(TransformGroup targetTG) {
        this.targetTG = targetTG;
    }

    public void initialize() {
        //Questo Behavior rispondera' ad eventi temporizzati
        this.wakeupOn(awt);
    }

    public void processStimulus(Enumeration criteria) {
        // Recupera gli stimoli che hanno attivato il behavior
        // Incremento l'angolo
        angle = (angle + 0.01) % (Math.PI * 2);
        // Imposto la rotazione
        rotation.rotY(angle);
        // Imposto la traslazione
        translation.setTranslation(new Vector3d(
                2.0f,
                0.0f,
                -3.0f
        ));
        // Moltiplico i due transform3d facendo in modo che
        // // la rotazione sia applicata dopo la traslazione
        rotation.mul(translation);
        targetTG.setTransform(rotation);
        // Resetto il behavior
        this.wakeupOn(awt);
    }
}