package crud.entities;

public class Comunica {

	private int id_comunica;
	private String telefone_celular;
	private String telefone_comercial;
	private String telefone_residencial;
	private String email;
	private int id_contato;
	
	public Comunica() {
		
	}

	public Comunica(int id_comunica, String telefone_celular, String telefone_comercial, String telefone_residencial,
			String email, int id_contato) {
		super();
		this.id_comunica = id_comunica;
		this.telefone_celular = telefone_celular;
		this.telefone_comercial = telefone_comercial;
		this.telefone_residencial = telefone_residencial;
		this.email = email;
		this.id_contato = id_contato;
	}

	public int getId_comunica() {
		return id_comunica;
	}

	public void setId_comunica(int id_comunica) {
		this.id_comunica = id_comunica;
	}

	public String getTelefone_celular() {
		return telefone_celular;
	}

	public void setTelefone_celular(String telefone_celular) {
		this.telefone_celular = telefone_celular;
	}

	public String getTelefone_comercial() {
		return telefone_comercial;
	}

	public void setTelefone_comercial(String telefone_comercial) {
		this.telefone_comercial = telefone_comercial;
	}

	public String getTelefone_residencial() {
		return telefone_residencial;
	}

	public void setTelefone_residencial(String telefone_residencial) {
		this.telefone_residencial = telefone_residencial;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getId_contato() {
		return id_contato;
	}

	public void setId_contato(int id_contato) {
		this.id_contato = id_contato;
	}

	@Override
	public String toString() {
		return "Comunica [id_comunica=" + id_comunica + ", telefone_celular=" + telefone_celular
				+ ", telefone_comercial=" + telefone_comercial + ", telefone_residencial=" + telefone_residencial
				+ ", email=" + email + ", id_contato=" + id_contato + "]";
	}







}
