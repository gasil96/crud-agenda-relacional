package crud.entities;

public class Comunica {

	public enum TIPO {
		EMAIL, CELULAR, TELEFONE_RESIDENCIAL, TELEFONE_COMERCIAL
	};

	private int id_comunica;
	private TIPO tipo;
	private String registro;
	private int id_contato;

	public Comunica() {

	}

	public int getId_comunica() {
		return id_comunica;
	}

	public void setId_comunica(int id_comunica) {
		this.id_comunica = id_comunica;
	}

	public int getId_contato() {
		return id_contato;
	}

	public void setId_contato(int id_contato) {
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

	public Comunica(int id_comunica, TIPO tipo, String registro, int id_contato) {
		super();
		this.id_comunica = id_comunica;
		this.tipo = tipo;
		this.registro = registro;
		this.id_contato = id_contato;
	}

	
	
}
