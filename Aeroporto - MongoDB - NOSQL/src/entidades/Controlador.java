package entidades;

import org.bson.Document;

public class Controlador {
	private Integer numero_matricula;
	private String data_exame;
	private Integer salario;
	private Long numero_telefone;
	private String endereco;
	
	public Controlador() {};
	
    public Controlador(int numero_matricula, String data_exame, int salario, long numero_telefone, String endereco) {
    	
        this.numero_matricula = numero_matricula;
        this.data_exame 	  = data_exame;
        this.salario 		  = salario;
        this.numero_telefone  = numero_telefone;
        this.endereco 		  = endereco;
        
    }
    
    public Controlador (Document doc) {
    	
        numero_matricula  = doc.getInteger("numero_matricula");
        data_exame 		  = doc.getString("data_exame");
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
	
	public String getData_exame() {
		return data_exame;
	}
	
	public void setData_exame(String data_exame) {
		this.data_exame = data_exame;
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
	
    public String controladorString() {
        return "Controlador{" + "numero_matricula=" + numero_matricula + ", data_exame=" + data_exame + ", salario=" + salario + ", numero_telefone=" + numero_telefone + ", endereco=" + endereco + '}';
    }
    
    
    public Document controladorDocument (){
        return new Document("numero_matricula" , numero_matricula)
                .append("data_exame", data_exame)
                .append("salario", salario)
                .append("numero_telefone", numero_telefone)
                .append("endereco", endereco);
    }   
	
}

