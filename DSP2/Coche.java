package DSP2;

public class Coche implements Runnable{
	public static final double UPDATES_PER_SECOND = 60.0;
	public static final double RADIO_RUEDA = 0.40; //Metros
	private SCAV scav;
	private Monitor monitor;
	private Mantenimiento mantenimiento;

	//General
	private GestorFiltros filtros;
	private double velocidad_rpm;
	
	public Coche(SCAV s, Mantenimiento ma, Monitor mo) {
		scav = s;
		mantenimiento = ma;
		monitor = mo;
		filtros = new GestorFiltros();
		velocidad_rpm = 0;
		new Thread(this).start();
	}

	@Override
	public void run() {
		while(true) {
			
			//TODO -- Obtener información del mantenimiento si no se ha arrancado
			
			//TODO -- Llamar al gestor de filtros con la velocidad actual y el estado del SCAV
			velocidad_rpm = filtros.update(velocidad_rpm, scav.getPedales(), scav.getSCAV());
			
			//TODO -- Actualizar al monitor con la información relevante
			monitor.update(velocidad_rpm);
			
			mantenimiento.update(velocidad_rpm);

			try {
				Thread.sleep(Math.round((1000/UPDATES_PER_SECOND)));
			} catch (InterruptedException e) { e.printStackTrace(); }
		}
	}
}
