package DSP2;

public class Monitor {
	double velocidad_rpm;
	
	void update(double velocidad_rpm) {
		this.velocidad_rpm = velocidad_rpm;
	}
	
	double getVelocidad() {
		return velocidad_rpm;
	}
}
