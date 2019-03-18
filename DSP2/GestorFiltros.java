package DSP2;

public class GestorFiltros {
	private SimulacionCoche coche;
	private CadenaFiltros chain;
	
	public GestorFiltros() {
		chain = new CadenaFiltros();
		chain.addFiltro(new FiltroVelocidad());
		chain.addFiltro(new FiltroRozamiento());
	}
	
	public void update(double rpm, EstadoMotor est) {
		coche.update(chain.update(rpm, est));	
	}
	
	public void setSimulacion(SimulacionCoche s) {
		coche = s;
	}
}
