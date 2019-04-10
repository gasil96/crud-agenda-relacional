package crud.entities;

public enum TipoSexoEnum {
	
	MASCULINO, FEMININO, NAO_BINARIO, NAO_DECLARADO;
	
	private String valor;

	public String getValor() {
		return valor;
	}
}
