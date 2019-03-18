package DSP2;

import java.util.ArrayList;

public class CadenaFiltros {
	private ArrayList<Filtro> cadena = new ArrayList<Filtro>();
	
	public void addFiltro(Filtro f) { cadena.add(f); }
	public void removeFiltro(Filtro f) { cadena.remove(f); }
	
	public double update(double rpm, EstadoMotor est) {
		for(Filtro f : cadena){
			rpm = f.update(rpm, est);
		}
		return rpm;
	}
}
