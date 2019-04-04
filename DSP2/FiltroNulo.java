package DSP2;

public class FiltroNulo implements Filtro {
	@Override
	public double update(double rpm, EstadoPedales pedales, EstadoSCAV scav) {
		if(rpm < 0) {
			rpm = 0;
		}
		return rpm;
	}

}