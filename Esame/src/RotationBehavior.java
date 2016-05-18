import javax.media.j3d.Behavior;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.WakeupOnElapsedTime;
import javax.vecmath.Vector3d;
import java.util.Enumeration;

/**
 * Created by stefanopassador on 17/05/16.
 */
class RotationBehavior extends Behavior {
    private TransformGroup targetTG;
    private Transform3D rotation = new Transform3D();
    private Vector3d translation = new Vector3d();
    private double angle = 0.0;
    private double angle2 = 0.0;
    private WakeupOnElapsedTime awt = new WakeupOnElapsedTime(100);

    public RotationBehavior(TransformGroup targetTG, Transform3D transform) {
        this.targetTG = targetTG;
        transform.get(translation);
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
        rotation.rotZ(angle);
        Transform3D transform3D = new Transform3D();
        transform3D.setTranslation(new Vector3d(
                translation
        ));
        transform3D.mul(rotation);
        targetTG.setTransform(transform3D);
        this.wakeupOn(awt);
    }
}