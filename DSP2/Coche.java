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
	private double velocidad_guardada;
	private boolean actualizar_velocidad_guardada;
	private ReguladorPID regulador;
	
	public Coche(SCAV s, Mantenimiento ma, Monitor mo) {
		scav = s;
		mantenimiento = ma;
		monitor = mo;
		filtros = new GestorFiltros();
		velocidad_rpm = 0;
		velocidad_guardada = 0;
		actualizar_velocidad_guardada = true;
		regulador = new ReguladorPID(0);
		new Thread(this).start();
	}

	@Override
	public void run() {
		while(true) {
			
			//TODO -- Obtener información del mantenimiento si no se ha arrancado
			
			
			//
			EstadoSCAV estado_scav = scav.getSCAV();
			
			if(estado_scav == EstadoSCAV.MANTENER && actualizar_velocidad_guardada) {
				actualizar_velocidad_guardada = false;
				velocidad_guardada = velocidad_rpm;
				regulador.setObjetivo(velocidad_guardada);
			}
			else if(estado_scav != EstadoSCAV.MANTENER && !actualizar_velocidad_guardada) {
				actualizar_velocidad_guardada = true;
			}
			
			//TODO -- Llamar al gestor de filtros con la velocidad actual y el estado del SCAV
			velocidad_rpm = filtros.update(velocidad_rpm, regulador, scav.getPedales(), estado_scav);
			
			//TODO -- Actualizar al monitor con la información relevante
			monitor.update(velocidad_rpm, velocidad_guardada);
			
			mantenimiento.update(velocidad_rpm);

			try {
				Thread.sleep(Math.round((1000/UPDATES_PER_SECOND)));
			} catch (InterruptedException e) { e.printStackTrace(); }
		}
	}
}
