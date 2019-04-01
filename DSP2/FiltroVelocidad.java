package DSP2;

public class FiltroVelocidad implements Filtro {
	@Override
	public double update(double rpm, EstadoPedales estado) {
		if(estado == EstadoPedales.FRENANDO)
			rpm -= 10;
		else if (estado == EstadoPedales.ACELERANDO)
			rpm += 10;
		
		return rpm;
	}

}
