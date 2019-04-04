package crud.entities;

public class Comunica {

	public enum TIPO {EMAIL, CELULAR, TELEFONE_RESIDENCIAL, TELEFONE_COMERCIAL};

	private int id_comunica;
	private Contato id_contato;
	private TIPO tipo;
	private String registro;
	
	
	public Comunica() {
		
	}


	public int getId_comunica() {
		return id_comunica;
	}


	public void setId_comunica(int id_comunica) {
		this.id_comunica = id_comunica;
	}


	public Contato getId_contato() {
		return id_contato;
	}


	public void setId_contato(Contato id_contato) {
		this.id_contato = id_contato;
	}


	public TIPO getTipo() {
		return tipo;
	}


	public void setTipo(TIPO tipo) {
		this.tipo = tipo;
	}


	public String getRegistro() {
		return registro;
	}


	public void setRegistro(String registro) {
		this.registro = registro;
	}


	public Comunica(int id_comunica, Contato id_contato, TIPO tipo, String registro) {
		super();
		this.id_comunica = id_comunica;
		this.id_contato = id_contato;
		this.tipo = tipo;
		this.registro = registro;
	}


	@Override
	public String toString() {
		return "Comunica [id_comunica=" + id_comunica + ", id_contato=" + id_contato + ", tipo=" + tipo + ", registro="
				+ registro + "]";
	}
	
	

	






}
