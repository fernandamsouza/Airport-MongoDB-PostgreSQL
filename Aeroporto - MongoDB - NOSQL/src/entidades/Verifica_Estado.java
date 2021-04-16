package entidades;

import org.bson.Document;


public class Verifica_Estado {
	
	private Integer numero_registro;
	private Integer numero_anac;
	private Integer numero_matricula;
	private String data_realizacao;
	private Long numero_horas;
	private Integer pontuacao;
	
	public Verifica_Estado() {};
	
    public Verifica_Estado(int numero_registro, int numero_anac, int numero_matricula, String data_realizacao, Long numero_horas, int pontuacao) {
    	
    	this.numero_registro   = numero_registro;
        this.numero_anac       = numero_anac;
        this.numero_matricula  = numero_matricula;
        this.data_realizacao   = data_realizacao;
        this.numero_horas 	   = numero_horas;
        this.pontuacao 		   = pontuacao;
 
    }
    
    public Verifica_Estado (Document doc) {
    	
    	numero_registro    = doc.getInteger("numero_registro");
        numero_anac  	   = doc.getInteger("numero_anac");
        numero_matricula   = doc.getInteger("numero_matricula");
        data_realizacao    = doc.getString("data_realizacao");
        numero_horas       = doc.getLong("numero_horas");
        pontuacao          = doc.getInteger("pontuacao");
        
    }
	
	public Integer getNumero_registro() {
		return numero_registro;
	}
	
	public void setNumero_registro(Integer numero_registro) {
		this.numero_registro = numero_registro;
	}
	
	public Integer getNumero_anac() {
		return numero_anac;
	}
	
	public void setNumero_anac(Integer numero_anac) {
		this.numero_anac = numero_anac;
	}
	
	public String getData_realizacao() {
		return data_realizacao;
	}
	
	public void setData_realizacao(String data_realizacao) {
		this.data_realizacao = data_realizacao;
	}
	
	public Long getNumero_horas() {
		return numero_horas;
	}
	
	public void setNumero_horas(Long numero_horas) {
		this.numero_horas = numero_horas;
	}
	
	public Integer getPontuacao() {
		return pontuacao;
	}
	
	public void setPontuacao(Integer pontuacao) {
		this.pontuacao = pontuacao;
	}

	public Integer getNumero_matricula() {
		return numero_matricula;
	}

	public void setNumero_matricula(Integer numero_matricula) {
		this.numero_matricula = numero_matricula;
	}
	
    public String verificaEstadoString() {
        return "verifica_estado{" + "numero_registro=" + numero_registro + "numero_anac=" + numero_anac + "numero_matricula=" + numero_matricula + ", data_realizacao=" + data_realizacao + ", numero_horas=" + numero_horas + ", pontuacao=" + pontuacao + '}';
    }
    
    
    public Document verificaEstadoDocument() {
        return new Document("numero_registro" , numero_registro)
                .append("numero_anac", numero_anac)
                .append("numero_matricula", numero_matricula)
                .append("data_realizacao", data_realizacao)
                .append("numero_horas", numero_horas)
                .append("pontuacao", pontuacao);
    } 
	
}