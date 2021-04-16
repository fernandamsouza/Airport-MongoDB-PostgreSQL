package entidades;

import org.bson.Document;


public class Modelo {
	
	private Integer codigo_modelo;
	private Integer capacidade;
	private Integer peso;
	private String nome_modelo;
	
    public Modelo() {};
    
    public Modelo(int codigo_modelo, int capacidade, int peso, String nome_modelo) {
    	this.codigo_modelo = codigo_modelo;
    	this.capacidade    = capacidade;
    	this.peso          = peso;
    	this.nome_modelo   = nome_modelo;
    }
	
    public Modelo (Document doc) {
    	
        codigo_modelo     = doc.getInteger("codigo_modelo");
        capacidade        = doc.getInteger("capacidade");
        peso			  = doc.getInteger("capacidade");
        nome_modelo 	  = doc.getString("nome_modelo");
      
    } 
    
	public Integer getCodigo_modelo() {
		return codigo_modelo;
	}
	
	public void setCodigo_modelo(Integer codigo_modelo) {
		this.codigo_modelo = codigo_modelo;
	}
	
	public Integer getCapacidade() {
		return capacidade;
	}
	
	public void setCapacidade(Integer capacidade) {
		this.capacidade = capacidade;
	}
	
	public Integer getPeso() {
		return peso;
	}
	
	public void setPeso(Integer peso) {
		this.peso = peso;
	}
	
	public String getNome_modelo() {
		return nome_modelo;
	}
	
	public void setNome_modelo(String nome_modelo) {
		this.nome_modelo = nome_modelo;
	}
	
    public String modeloString() {
        return "Modelo{" + "codigo_modelo=" + codigo_modelo + ", capacidade=" + capacidade + ", peso=" + peso + ", nome_modelo=" + nome_modelo + '}';
    }
    
    
    public Document modeloDocument (){
        return new Document("codigo_modelo" , codigo_modelo)
                .append("capacidade", capacidade)
                .append("peso", peso)
                .append("nome_modelo", nome_modelo);
    }  
	
}