package DSP2;

public class Mantenimiento {
	private final static double MAXCAPACIDAD = 50; //en litros
	private double nivel_gasolina;
	
	void Mantenimiento() {
		nivel_gasolina = MAXCAPACIDAD;	
	}
	
	void update(double rpm) {
		double rot = rpm/(60*Coche.UPDATES_PER_SECOND);
		double gasolina_gastada = rot*rot*5*Math.pow(10, -10) ;//Math.pow(10, 10) = 10 ^(-10)
		nivel_gasolina -= gasolina_gastada;
	}
	
	double getGasolina() {
		return nivel_gasolina;
	}
}
