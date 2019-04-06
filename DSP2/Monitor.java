package DSP2;

public class Monitor {
	double velocidad_rpm;
	double distancia;
	double revoluciones;
	
	void update(double velocidad_rpm) {
		this.velocidad_rpm = velocidad_rpm;
	}
	
	double getVelocidadRPM() {
		return velocidad_rpm;
	}
	
	double calcularDistancia() {
		double conversorHora = 3600*1000;
		double tiempo_actualizacion = 1000/Coche.UPDATES_PER_SECOND;
		double dist = getVelocidadKm()/(tiempo_actualizacion*conversorHora);
		return dist;
	}
	
	void actualizarDistancia() {
		distancia += calcularDistancia();
	}
	
	double calcularRevoluciones() {
		double conversorHora = 60*1000;
		double tiempo_actualizacion = 1000/Coche.UPDATES_PER_SECOND;
		double revs = getVelocidadRPM()/(tiempo_actualizacion*conversorHora);
		return revs;
	}
	
	void actualizarRevoluciones() {
		revoluciones += calcularRevoluciones();
	}
	
	double getVelocidadKm() {
		return velocidad_rpm*(Math.PI*Coche.RADIO_RUEDA*2)*60*0.001;
	}
	
	
}
