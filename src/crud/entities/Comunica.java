package crud.entities;

public class Comunica {

	private int id_comunica;
	private TipoComunicaEnum tipo;
	private String registro;
	private int id_contato;

	public Comunica() {

	}

	public Comunica(int id_comunica, TipoComunicaEnum tipo, String registro, int id_contato) {
		super();
		this.id_comunica = id_comunica;
		this.tipo = tipo;
		this.registro = registro;
		this.id_contato = id_contato;
	}
	
	public int getId_comunica() {
		return id_comunica;
	}

	public void setId_comunica(int id_comunica) {
		this.id_comunica = id_comunica;
	}



	public TipoComunicaEnum getTipo() {
		return tipo;
	}

	public void setTipo(TipoComunicaEnum tipo) {
		this.tipo = tipo;
	}

	public String getRegistro() {
		return registro;
	}

	public void setRegistro(String registro) {
		this.registro = registro;
	}

	public int getId_contato() {
		return id_contato;
	}

	public void setId_contato(int id_contato) {
		 this.id_contato = id_contato;
	}
	
	public String toString() {
		return "Comunica [id_comunica=" + id_comunica + ", tipo=" + tipo + ", registro=" + registro + ", id_contato="
				+ id_contato + "]";
	}
	
	

	
	
}
