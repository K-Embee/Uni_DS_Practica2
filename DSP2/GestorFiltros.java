package DSP2;

public class GestorFiltros {
	private CadenaFiltros cadena;
	
	public GestorFiltros() {
		cadena = new CadenaFiltros();
		cadena.addFiltro(new FiltroVelocidad());
		cadena.addFiltro(new FiltroRozamiento());
		cadena.addFiltro(new FiltroNulo());
	}
	
	public double update(double rpm, ReguladorPID regulador, EstadoPedales pedales, EstadoSCAV scav) {
		return (cadena.update(rpm, regulador, pedales, scav));	
	}
}
