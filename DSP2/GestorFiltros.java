package DSP2;

public class GestorFiltros {
	private CadenaFiltros chain;
	
	public GestorFiltros() {
		chain = new CadenaFiltros();
		chain.addFiltro(new FiltroVelocidad());
		chain.addFiltro(new FiltroRozamiento());
	}
	
	public double update(double rpm, Object o) {
		return (chain.update(rpm, o));	
	}
}
