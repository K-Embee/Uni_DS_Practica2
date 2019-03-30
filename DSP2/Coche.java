package DSP2;

public class Coche implements Runnable{
	public static final double UPDATES_PER_SECOND = 60.0;
	private SCAV scav;
	private Monitor monitor;
	private Mantenimiento mantenimiento;

	//General
	private GestorFiltros filtros;
	private double velocidad_rpm;
	private double gasolina = 50.0;
	private final double max_gasolina = 50.0;
	
	//SCAV
	private EstadoArranque state_arranque;
	private EstadoPedales state_pedales;
	private EstadoSCAV state_scav;
	
	//Mantenimiento
	//TODO
	
	//Monitor
	//TODO
	
	public Coche(SCAV s, Mantenimiento ma, Monitor mo) {
		scav = s;
		mantenimiento = ma;
		monitor = mo;
		filtros = new GestorFiltros();
		
		new Thread(this);
	}

	@Override
	public void run() {
		while(true) {
			state_arranque = scav.getArranque();
			state_pedales = scav.getPedales();
			state_scav = scav.getSCAV();
			
			//TODO -- Obtener información del mantenimiento si no se ha arrancado
			
			//TODO -- Llamar al gestor de filtros con la velocidad actual y el estado del SCAV
			
			//TODO -- Actualizar al monitor con la información relevante

			try {
				Thread.sleep(Math.round((1000/UPDATES_PER_SECOND)));
			} catch (InterruptedException e) { e.printStackTrace(); }
		}
	}
}
