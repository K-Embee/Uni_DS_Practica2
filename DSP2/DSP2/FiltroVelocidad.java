package DSP2;

public class FiltroVelocidad implements Filtro {
	@Override
	public double update(double rpm, ReguladorPID regulador, EstadoPedales pedales, EstadoSCAV scav) {
		double UPS = Coche.UPDATES_PER_SECOND;
		
		if(pedales == EstadoPedales.FRENANDO)
			rpm -= 100/UPS;
		else if (pedales == EstadoPedales.ACELERANDO || scav == EstadoSCAV.ACELERAR)
			rpm += 100/UPS;
		else if(scav == EstadoSCAV.MANTENER || scav == EstadoSCAV.REINICIAR) {
			rpm += regulador.update(rpm);
		}
		
		return rpm;
	}

}
