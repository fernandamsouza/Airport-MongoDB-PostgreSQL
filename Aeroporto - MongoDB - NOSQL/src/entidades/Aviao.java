package entidades;

import org.bson.Document;


public class Aviao {
	
	private Integer numero_registro;
	private Integer codigo_modelo;
	
	public Aviao() {};
	
	
    public Aviao(int numero_registro, int codigo_modelo) {
        this.numero_registro = numero_registro;
        this.codigo_modelo = codigo_modelo;
    }
    
    public Aviao (Document doc) {
        numero_registro = doc.getInteger("numero_registro");
        codigo_modelo = doc.getInteger("codigo_modelo");
      
    } 
    
	public Integer getNumero_registro() {
		return numero_registro;
	}
	
	public void setNumero_registro(Integer numero_registro) {
		this.numero_registro = numero_registro;
	}
	
	public Integer getCodigo_modelo() {
		return codigo_modelo;
	}
	
	public void setCodigo_modelo(Integer codigo_modelo) {
		this.codigo_modelo = codigo_modelo;
	}
	
	
	public String aviaoString() {
		return "Aviao{" + "numero_registro=" + numero_registro + ", codigo_modelo=" + codigo_modelo + '}';
	}
	
    public Document aviaoDocument (){
        return new Document("numero_registro" , numero_registro)
                .append("codigo_modelo", codigo_modelo);
    }           
         
}