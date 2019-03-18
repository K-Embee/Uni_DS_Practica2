package DSP2;

public class FiltroVelocidad implements Filtro {
	protected SimulacionCoche objetivo;

	@Override
	public double update(double rpm, EstadoMotor est) {
		double UPS = SimulacionCoche.UPDATES_PER_SECOND;
		if(est == EstadoMotor.ACCEL) { rpm += 100/UPS; }
		if(est == EstadoMotor.DECEL) { rpm -= 100/UPS; }
		return rpm;
	}

}
