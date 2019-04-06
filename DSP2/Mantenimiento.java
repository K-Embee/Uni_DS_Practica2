package DSP2;

public class Mantenimiento {
	private final static double MAXGASOLINA = 50; //en litros
	private final static double MAXACEITE = 5; //en litros
	private double nivel_gasolina;
	double nivel_aceite;
	double rotaciones_totales;
	double rotaciones_aceite;
	double rotaciones_pastillas;
	double rotaciones_revision;
	
	Mantenimiento() {
		nivel_gasolina = MAXGASOLINA;
		rotaciones_totales = 0;
		nivel_aceite = MAXACEITE;
		rotaciones_totales = 0;
		rotaciones_aceite = 0;
		rotaciones_pastillas = 0;
		rotaciones_revision = 0;
	}
	
	double getRotaciones() {
		return rotaciones_totales;
	}
	
	double getDistancia() {
		return rotaciones_totales*(Math.PI*Coche.RADIO_RUEDA*2)*0.001;
	}
	
	void update(double rpm) {
		double gasolina_gastada = gastoGasolina(rpm);
		nivel_gasolina -= gasolina_gastada;
	}

	double gastoGasolina(double rpm) {
		double rot = rpm/(60*Coche.UPDATES_PER_SECOND);
		rotaciones_totales += rot;
		return rot*rot*5*Math.pow(10, -5) ;//Math.pow(10, 10) = 10 ^(-10) //Cambiado a 10^(-5) para que se note por ahora
	}
	
	double gastoGasolinaMedio() {
		return (MAXGASOLINA - nivel_gasolina)/Coche.UPDATES_PER_SECOND;
	}
	
	double getGasolina() {
		return nivel_gasolina;
	}
	
	void repostarGasolina(){
		nivel_gasolina = MAXGASOLINA;
	}
	
	void realizarEngrase() {
		rotaciones_aceite = rotaciones_totales;
		nivel_aceite = MAXACEITE;
	}
	
	void realizarCambioPastillas() {
		rotaciones_pastillas = rotaciones_totales;
	}
	
	void realizarRevision() {
		rotaciones_revision = rotaciones_totales;
	}
	
	boolean necesitoEngrase() {
		if( rotaciones_totales - rotaciones_aceite >= 5*Math.pow(10, 2) ) //6 -> 2 para comprobar
			return true;
		else 
			return false;
	}
	
	boolean necesitoCambioPastillas() {
		if( rotaciones_totales - rotaciones_pastillas >= Math.pow(10, 3) ) //8 -> 3 para comprobar
			return true;
		else 
			return false;
	}
	
	boolean necesitoRevision() {
		if( rotaciones_totales - rotaciones_revision >= Math.pow(10, 4) ) //9 -> 4 para comprobar
			return true;
		else 
			return false;
	}
}
