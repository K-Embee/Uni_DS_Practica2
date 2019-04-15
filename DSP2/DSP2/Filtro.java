package DSP2;

public interface Filtro {
	public double update(double rpm, ReguladorPID regulador, EstadoPedales pedales, EstadoSCAV scav);
}
