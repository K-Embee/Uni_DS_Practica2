package DSP2;

public class FiltroVelocidad implements Filtro {
	@Override
	public double update(double rpm, EstadoPedales pedales, EstadoSCAV scav) {
		if(pedales.FRENANDO)
			rpm -= 10;
		else if (pedales.ACELERANDO)
			rpm += 10;
		
		return rpm;
	}

}
