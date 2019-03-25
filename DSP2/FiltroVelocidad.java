package DSP2;

public class FiltroVelocidad implements Filtro {
	@Override
	public double update(double rpm, Object o) {
		double UPS = Coche.UPDATES_PER_SECOND;
		
		return rpm;
	}

}
