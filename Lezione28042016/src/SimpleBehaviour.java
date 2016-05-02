import javax.media.j3d.Behavior;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.WakeupOnAWTEvent;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Enumeration;

/**
 * Created by stefanopassador on 28/04/16.
 */
class SimpleBehaviour extends Behavior {
    private TransformGroup targetTG;
    private Transform3D rotation = new Transform3D();
    private double angle = 0.0;
    private WakeupOnAWTEvent awt = new WakeupOnAWTEvent(KeyEvent.KEY_PRESSED);

    public SimpleBehaviour(TransformGroup targetTG) {
        this.targetTG = targetTG;
    }

    public void initialize() {
        //Questo Behavior rispondera' ad eventi di tastiera sul key pressed
        this.wakeupOn(new WakeupOnAWTEvent(KeyEvent.KEY_PRESSED));
    }

    public void processStimulus(Enumeration criteria) {
        //Incrementa l'angolo
        angle += 0.1;
        //Evita problemi di overflow
        if (angle > 2 * Math.PI) angle = 0;
        //imposta la rotazione dell'angolo
        rotation.rotY(angle);
        targetTG.setTransform(rotation);
        //Resetta il Behavior per continuare a rispondere ad eventi di tastiera
        this.wakeupOn(new WakeupOnAWTEvent(KeyEvent.KEY_PRESSED));
    }
}