package entidades;

import org.bson.Document;

public class Tecnico {
	private Integer numero_matricula;
	private Integer salario;
	private Long numero_telefone;
	private String endereco;
	
	
	public Tecnico() {};
	
    public Tecnico(int numero_matricula, int salario, long numero_telefone, String endereco) {
    	
        this.numero_matricula = numero_matricula;
        this.salario 		  = salario;
        this.numero_telefone  = numero_telefone;
        this.endereco 		  = endereco;
        
    }
    
    public Tecnico (Document doc) {
    	
        numero_matricula  = doc.getInteger("numero_matricula");
        salario   		  = doc.getInteger("salario");
        numero_telefone   = doc.getLong("numero_telefone");
        endereco 	      = doc.getString("endereco");
    }
    
	public Integer getNumero_matricula() {
		return numero_matricula;
	}
	
	public void setNumero_matricula(Integer numero_matricula) {
		this.numero_matricula = numero_matricula;
	}
	
	public Integer getSalario() {
		return salario;
	}
	
	public void setSalario(Integer salario) {
		this.salario = salario;
	}
	
	public Long getNumero_telefone() {
		return numero_telefone;
	}
	
	public void setNumero_telefone(Long numero_telefone) {
		this.numero_telefone = numero_telefone;
	}
	
	public String getEndereco() {
		return endereco;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
    public String tecnicoString() {
        return "Tecnico{" + "numero_matricula=" + numero_matricula + ", salario=" + salario + ", numero_telefone=" + numero_telefone + ", endereco=" + endereco + '}';
    }
    
    
    public Document tecnicoDocument (){
        return new Document("numero_matricula" , numero_matricula)
                .append("salario", salario)
                .append("numero_telefone", numero_telefone)
                .append("endereco", endereco);
    } 
	
	
}