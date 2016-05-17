import javax.media.j3d.*;
import javax.vecmath.Vector3d;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Enumeration;

/**
 * Created by stefanopassador on 17/05/16.
 */
public class CustomBehaviour extends Behavior {
    private TransformGroup targetTG;
    private Transform3D rotation = new Transform3D();
    private Transform3D rotation2 = new Transform3D();
    private Transform3D translation = new Transform3D();
    private double angle = 0.0;
    private double angle2 = 0.0;
    private double xTraslation = 0.0;
    private double yTraslation = 0.0;
    private double zTraslation = 0.0;
    private WakeupOnAWTEvent awt = new WakeupOnAWTEvent(KeyEvent.KEY_PRESSED);

    public CustomBehaviour(TransformGroup targetTG) {
        this.targetTG = targetTG;
    }

    public void initialize() {
        //Questo Behavior rispondera' ad eventi di tastiera sul key pressed
        this.wakeupOn(awt);
    }

    public void processStimulus(Enumeration criteria) {
        //Recupera gli stimoli che hanno attivato il behavior
        AWTEvent[] ev = null;
        while (criteria.hasMoreElements()) {
            Object obj = criteria.nextElement();
            if (obj instanceof WakeupOnAWTEvent)
                ev = ((WakeupOnAWTEvent) obj).getAWTEvent();
        }
        if (ev != null)
            for (int i = 0; i < ev.length; i++) //scorre tutti gli eventi AWT in cerca di un evento di tastiera
                if (ev[i] instanceof KeyEvent) {
                    //Recupera l'evento
                    KeyEvent key = (KeyEvent) ev[i];
                    //Recupera il codice dell'evento
                    int code = key.getKeyCode();
                    if (code == KeyEvent.VK_LEFT) { //Codice freccia a sinistra
                        //decrementa l'angolo
                        angle -= 0.1;
                        //Evita problemi di overflow
                        if (angle < 0) angle = 2 * Math.PI;
                    } else if (code == KeyEvent.VK_RIGHT) { //Codice freccia a destra
                        //Incrementa l'angolo
                        angle += 0.1;
                        //Evita problemi di overflow
                        if (angle > 2 * Math.PI) angle = 0;
                    } else if (code == KeyEvent.VK_DOWN) { //Codice freccia a giù
                        //Incrementa l'angolo
                        angle2 -= 0.1;
                        //Evita problemi di overflow
                        if (angle2 > 2 * Math.PI) angle2 = 0;
                    }  else if (code == KeyEvent.VK_UP) { //Codice freccia a destra
                        //Incrementa l'angolo
                        angle2 += 0.1;
                        //Evita problemi di overflow
                        if (angle2 > 2 * Math.PI) {
                            angle2 = 0;
                        }
                    } else if (code == KeyEvent.VK_Z) {
                        zTraslation = zTraslation + 0.1;
                    } else if (code == KeyEvent.VK_X) {
                        zTraslation = zTraslation - 0.1;
                    } else if (code == KeyEvent.VK_W) {
                        yTraslation = yTraslation + 0.1;
                    } else if (code == KeyEvent.VK_S) {
                        yTraslation = yTraslation - 0.1;
                    } else if (code == KeyEvent.VK_A) {
                        xTraslation = xTraslation + 0.1;
                    } else if (code == KeyEvent.VK_D) {
                        xTraslation = xTraslation - 0.1;
                    }
                    //imposta la rotazione dell'angolo
                    rotation2.rotX(angle2);
                    rotation.rotY(angle);
                    rotation.mul(rotation2);
                    translation.setTranslation(new Vector3d(
                            xTraslation,
                            yTraslation,
                            zTraslation
                    ));
                    rotation.mul(translation);
                    targetTG.setTransform(rotation);
                }
        //Resetta il Behavior per continuare a rispondere ad eventi di tastiera
        this.wakeupOn(awt);
    }
}
