package DSP2;

public class FiltroRozamiento implements Filtro {
	protected SimulacionCoche objetivo;
	
	@Override
	public double update(double rpm, EstadoMotor est) {
		double UPS = SimulacionCoche.UPDATES_PER_SECOND;
		return (rpm > 0.5/UPS || rpm < -0.5/UPS) ? ( rpm * (1 - 0.1/UPS)) : ( 0 );
	}

}