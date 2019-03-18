package DSP2;

public class Controlador {
	public static void main(String args[]) {
		Cliente c = new Cliente();
		c.setVisible(true);
		
		SimulacionCoche s = new SimulacionCoche();
		s.setCliente(c);
		
		GestorFiltros g = new GestorFiltros();
		g.setSimulacion(s);
		c.setFiltro(g);

		Thread t = new Thread(s);
		t.start();
	}
}
