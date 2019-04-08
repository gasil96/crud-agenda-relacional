/* Classe criada  pelo Ulisses 
 * 
 */

package crud.entities;

public enum TipoComunicaEnum {
	
	EMAIL, 
	CELULAR, 
	TELEFONE_RESIDENCIAL, 
	TELEFONE_COMERCIAL;
	
	private  String tipo;
	
	public String getValor() {
		return tipo	;
	}
	
	
	

}
