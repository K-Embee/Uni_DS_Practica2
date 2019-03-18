package DSP2;

public class SimulacionCoche implements Runnable{

	public static double UPDATES_PER_SECOND = 60.0;
	private static double WHEEL_RADIUS = 0.4;
	
	private Cliente cliente;
	private double velocidad_rpm = 0;
	private double velocidad_kmh = 0;
	private double distancia = 0;
	
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(Math.round(1000/UPDATES_PER_SECOND));
			} catch (Exception e) {System.err.println("oof");};
			cliente.update();
		}
	}
	
	public void update(double rpm) {
		setRPM(rpm);
		setKMH( (Math.PI*WHEEL_RADIUS*2) * rpm * 60 * 0.001);
		updateDistancia(velocidad_kmh/UPDATES_PER_SECOND);
		
		cliente.setRPM(rpm);
		cliente.setKMH(velocidad_kmh);
		cliente.updateDisplay(distancia);
	}
	
	private void setRPM(double rpm) {
		velocidad_rpm = rpm;
	}
	
	private void setKMH(double kmh) {
		velocidad_kmh = kmh;
	}
	
	public void setCliente(Cliente c) {
		cliente = c;
	}
	
	private void updateDistancia(double rpm) {
		distancia += (velocidad_kmh/(3600*UPDATES_PER_SECOND));
	}
}
