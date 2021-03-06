package DSP2;

import java.util.ArrayList;

public class CadenaFiltros {
	private ArrayList<Filtro> cadena = new ArrayList<Filtro>();
	
	public void addFiltro(Filtro f) { cadena.add(f); }
	public void removeFiltro(Filtro f) { cadena.remove(f); }
	
	public double update(double rpm, ReguladorPID regulador, EstadoPedales pedales, EstadoSCAV scav) {
		for(Filtro f : cadena){
			rpm = f.update(rpm, regulador, pedales, scav);
		}
		return rpm;
	}
}
