import javax.media.j3d.*;
import javax.vecmath.Vector3d;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Enumeration;

/**
 * Created by stefanopassador on 28/04/16.
 */
class RotationBehaviour extends Behavior {
    private TransformGroup targetTG;
    private Transform3D rotation = new Transform3D();
    private double angle = 0.0;
    private double angle2 = 0.0;
    private WakeupOnElapsedTime awt = new WakeupOnElapsedTime(100);

    public RotationBehaviour(TransformGroup targetTG) {
        this.targetTG = targetTG;
    }

    public void initialize() {
        //Questo Behavior rispondera' ad eventi di tastiera sul key pressed
        this.wakeupOn(awt);
    }

    public void processStimulus(Enumeration criteria) {
        //Recupera gli stimoli che hanno attivato il behavior
        //Incrementa l'angolo
        angle += 0.1;
        //Evita problemi di overflow
        if (angle > 2 * Math.PI) angle = 0;
        //imposta la rotazione dell'angolo
        rotation.rotY(angle);
        Transform3D transform3D = new Transform3D();
        transform3D.setTranslation(new Vector3d(
                2.0f,
                0.5f,
                -3.0f
        ));
//        rotation.mul(transform3D);
        targetTG.setTransform(rotation);
        //Resetta il Behavior per continuare a rispondere ad eventi di tastiera
        this.wakeupOn(awt);
    }
}