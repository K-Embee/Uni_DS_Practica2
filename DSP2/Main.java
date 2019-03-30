package DSP2;

public class Main {
	public static void main(String[]args) { //TODO -- Borrar y añadir un controlador en condiciones
		SCAV s = new SCAV();
		Mantenimiento ma = new Mantenimiento();
		Monitor mo = new Monitor();
		Interfaz i = new Interfaz(s,ma,mo);
		Coche c = new Coche(s,ma,mo);
		while(true) {}
	}
}
