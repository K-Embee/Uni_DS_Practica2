package DSP2;

public interface Filtro {
	public double update(double rpm, EstadoPedales pedales, EstadoSCAV scav);
}
