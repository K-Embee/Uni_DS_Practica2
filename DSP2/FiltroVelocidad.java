package DSP2;

public class FiltroVelocidad implements Filtro {
	@Override
	public double update(double rpm, EstadoPedales pedales, EstadoSCAV scav) {
		double UPS = Coche.UPDATES_PER_SECOND;
		
		if(pedales == EstadoPedales.FRENANDO)
			rpm -= 100/UPS;
		else if (pedales == EstadoPedales.ACELERANDO || scav == EstadoSCAV.ACELERAR)
			rpm += 100/UPS;
		else if(scav == EstadoSCAV.MANTENER) {
			return (rpm > 0.5/UPS) ? ( rpm / (1 - 0.1/UPS)) : ( 0 ); //Modificar luego a un modelo mas realista
		}
		
		return rpm;
	}

}
