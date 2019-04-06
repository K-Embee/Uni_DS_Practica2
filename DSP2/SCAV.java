package DSP2;

import javafx.util.Pair;
import java.util.ArrayList;

public class SCAV {
	private EstadoSCAV scav;
	private EstadoPedales pedales;
	private EstadoArranque arranque;
	
	static private ArrayList<Pair<EstadoSCAV,EstadoSCAV>> automata; //Contiene las transiciones validos entre estados
	
	public SCAV() {
		scav = EstadoSCAV.PARAR;
		pedales = EstadoPedales.NINGUNO;
		arranque = EstadoArranque.NO_ENCENDIDO;
		
		inicializarAutomata();
	}
	
	boolean setSCAV(EstadoSCAV state) {
		if(arranque == EstadoArranque.NO_ENCENDIDO) {
			return false;
		}
		if(automata.contains(new Pair<EstadoSCAV,EstadoSCAV>(scav, state))) {
			scav = state;
			return true;
		}
		else {
			return false;
		}
	}
	
	void setPedales(EstadoPedales state) {
		pedales = state;
	}
	
	void setArranque(EstadoArranque state) {
		arranque = state;
	}
	
	void pararSCAV() {
		scav = EstadoSCAV.PARAR;
	}
	
	void frenar() {
		setPedales(EstadoPedales.FRENANDO);
		pararSCAV();
	}
	
	void apagarMotor() {
		setPedales(EstadoPedales.NINGUNO);
		pararSCAV();
		setArranque(EstadoArranque.NO_ENCENDIDO);
	}
	
	public EstadoSCAV getSCAV() {
		return scav;
	}
	
	public EstadoPedales getPedales() {
		return pedales;
	}
	
	public EstadoArranque getArranque() {
		return arranque;
	}
	
	private void inicializarAutomata() {
		automata = new ArrayList<Pair<EstadoSCAV,EstadoSCAV>>();
		automata.add(new Pair<EstadoSCAV,EstadoSCAV>(EstadoSCAV.PARAR, EstadoSCAV.ACELERAR));
		automata.add(new Pair<EstadoSCAV,EstadoSCAV>(EstadoSCAV.PARAR, EstadoSCAV.REINICIAR));
		automata.add(new Pair<EstadoSCAV,EstadoSCAV>(EstadoSCAV.ACELERAR, EstadoSCAV.MANTENER));
		automata.add(new Pair<EstadoSCAV,EstadoSCAV>(EstadoSCAV.MANTENER, EstadoSCAV.ACELERAR));
		automata.add(new Pair<EstadoSCAV,EstadoSCAV>(EstadoSCAV.MANTENER, EstadoSCAV.PARAR));
		automata.add(new Pair<EstadoSCAV,EstadoSCAV>(EstadoSCAV.REINICIAR, EstadoSCAV.PARAR));
		automata.add(new Pair<EstadoSCAV,EstadoSCAV>(EstadoSCAV.REINICIAR, EstadoSCAV.ACELERAR));
	}
}
