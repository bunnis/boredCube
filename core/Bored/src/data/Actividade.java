package data;

public class Actividade {

	private int idActividade;
	private String nomeActividade;
	private String moradaActividade;
	private String GPSACtividade;
	private String fotoActividade;
	private Pais paisActividade;
	
	
	public void setNomeActividade(String nomeActividade) {
		this.nomeActividade = nomeActividade;
	}
	public void setMoradaActividade(String moradaActividade) {
		this.moradaActividade = moradaActividade;
	}
	public void setGPSACtividade(String gPSACtividade) {
		GPSACtividade = gPSACtividade;
	}
	public void setFotoActividade(String fotoActividade) {
		this.fotoActividade = fotoActividade;
	}
	public void setPaisActividade(Pais paisActividade) {
		this.paisActividade = paisActividade;
	}
	@Override
	public String toString() {
		return "Actividade [idActividade=" + idActividade + ", nomeActividade=" + nomeActividade + ", moradaActividade="
				+ moradaActividade + ", GPSACtividade=" + GPSACtividade + ", fotoActividade=" + fotoActividade
				+ ", paisActividade=" + paisActividade + "]";
	}
	public int getIdActividade() {
		return idActividade;
	}
	public void setIdActividade(int idActividade) {
		this.idActividade = idActividade;
	}
	public String getNomeActividade() {
		return nomeActividade;
	}
	public String getMoradaActividade() {
		return moradaActividade;
	}
	public String getGPSACtividade() {
		return GPSACtividade;
	}
	public String getFotoActividade() {
		return fotoActividade;
	}
	public Pais getPaisActividade() {
		return paisActividade;
	}
	
	
}
