package crud.entities;

public class Contato {

	private int id_contato;
	private String nome;
	private String cpf;
	private int idade;
	private String sexo;
	private int rfp;

	public int getRfp() {
		return rfp;
	}

	public void setRfp(int rfp) {
		this.rfp = rfp;
	}

	public Contato() {

	}

	public Contato(int id_contato, String nome, String cpf, int idade, String sexo) {
		super();
		this.id_contato = id_contato;
		this.nome = nome;
		this.cpf = cpf;
		this.idade = idade;
		this.sexo = sexo;
	}

	public int getId_contato() {
		return id_contato;
	}

	public void setId_contato(int id_contato) {
		this.id_contato = id_contato;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	@Override
	public String toString() {
		return "Contato [id_contato=" + id_contato + ", nome=" + nome + ", cpf=" + cpf + ", idade=" + idade + ", sexo="
				+ sexo + "]";
	}
}