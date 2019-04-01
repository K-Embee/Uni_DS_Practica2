package DSP2;

public class FiltroRozamiento implements Filtro {
	@Override
	public double update(double rpm, EstadoPedales pedales, EstadoSCAV scav) {
		double UPS = Coche.UPDATES_PER_SECOND;
		return (rpm > 0.5/UPS || rpm < -0.5/UPS) ? ( rpm * (1 - 0.1/UPS)) : ( 0 );
	}

}