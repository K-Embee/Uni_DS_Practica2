package DSP2;

public class GestorFiltros {
	private CadenaFiltros cadena;
	
	public GestorFiltros() {
		cadena = new CadenaFiltros();
		cadena.addFiltro(new FiltroVelocidad());
		cadena.addFiltro(new FiltroRozamiento());
		cadena.addFiltro(new FiltroNulo());
	}
	
	public double update(double rpm, EstadoPedales pedales, EstadoSCAV scav) {
		return (cadena.update(rpm, pedales, scav));	
	}
}
