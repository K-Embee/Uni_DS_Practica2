package DSP2;

public class GestorFiltros {
	private CadenaFiltros cadena;
	
	public GestorFiltros() {
		cadena = new CadenaFiltros();
		cadena.addFiltro(new FiltroVelocidad());
		cadena.addFiltro(new FiltroRozamiento());
	}
	
	public double update(double rpm, EstadoPedales estado) {
		return (cadena.update(rpm, estado));	
	}
}
