package data;

public class Evento extends Actividade{
	private String tipoEvento;

	public Evento(String tipo){
		this.tipoEvento = tipo;
	}

	public int getID() {
		return super.getIdActividade();
	}

	public void setID(int id) {
		super.setIdActividade(id);
	}

	//	@Override
	//	public String toString() {
	//		return "Evento [tipoEvento=" + tipoEvento + "]";
	//	}


}
