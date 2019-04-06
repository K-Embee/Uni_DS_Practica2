package DSP2;

public class Monitor {
	double velocidad_rpm;
	double distancia;
	double revoluciones;
	double velocidad_scav;
	
	void update(double velocidad_rpm, double velocidad_scav) {
		this.velocidad_rpm = velocidad_rpm;
		this.velocidad_scav = velocidad_scav;
	}
	
	double getVelocidadRPM() {
		return velocidad_rpm;
	}
	
	double getVelocidadSCAV() {
		return velocidad_scav*(Math.PI*Coche.RADIO_RUEDA*2)*60*0.001;
	}
	
	double calcularDistancia() {
		double conversorHora = 3600*1000;
		double tiempo_actualizacion = 1000/Coche.UPDATES_PER_SECOND;
		double dist = getVelocidadKMH()/(tiempo_actualizacion*conversorHora);
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
	
	double getVelocidadKMH() {
		return velocidad_rpm*(Math.PI*Coche.RADIO_RUEDA*2)*60*0.001;
	}
	
	
}
