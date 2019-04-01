package DSP2;

public class Monitor {
	double velocidad_rpm;
	double distancia;
	
	void update(double velocidad_rpm) {
		this.velocidad_rpm = velocidad_rpm;
	}
	
	double getVelocidad() {
		return velocidad_rpm;
	}
	
	double getVelocidadKm() {
		return velocidad_rpm*(Math.PI*Coche.RADIO_RUEDA*2)*60*0.001;
	}
	
	
}
