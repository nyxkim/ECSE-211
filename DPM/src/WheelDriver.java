import lejos.nxt.NXTRegulatedMotor;

/**Controls the motors for the wheels
 * 
 * @author Daniel
 *
 */
public class WheelDriver {
	private NXTRegulatedMotor leftMotor,rightMotor;
	private static final int FORWARD_SPEED = 300;
	private static final int ROTATION_SPEED = 200;
	private static final int ACCELERATION_SPEED = 3000;
	
	public WheelDriver(NXTRegulatedMotor left, NXTRegulatedMotor right){
		this.leftMotor = left;
		this.rightMotor = right;
	}
	
	
}
