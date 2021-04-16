package entidades;

import org.bson.Document;

public class Perito {
	
	private Integer codigo_modelo;
	private Integer numero_matricula;
	
	public Perito() {};
	
    public Perito(int codigo_modelo, int numero_matricula) {
    	
    	this.codigo_modelo    = codigo_modelo;
        this.numero_matricula = numero_matricula;
        
    }
    
    public Perito (Document doc) {
    	
        codigo_modelo 	  = doc.getInteger("codigo_modelo");
        numero_matricula  = doc.getInteger("numero_matricula");
      
    } 
	
	public Integer getCodigo_modelo() {
		return codigo_modelo;
	}
	
	public void setCodigo_modelo(Integer codigo_modelo) {
		this.codigo_modelo = codigo_modelo;
	}
	
	public Integer getNumero_matricula() {
		return numero_matricula;
	}
	
	public void setNumero_matricula(Integer numero_matricula) {
		this.numero_matricula = numero_matricula;
	}
	
    public String peritoString() {
        return "perito{" + "codigo_modelo=" + codigo_modelo + ", numero_matricula=" + numero_matricula + '}';
    }
    
    public Document peritoDocument (){
        return new Document("codigo_modelo" , codigo_modelo)
                .append("numero_matricula", numero_matricula);
    } 
	
}