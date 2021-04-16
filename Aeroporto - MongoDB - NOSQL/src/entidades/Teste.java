package entidades;

import org.bson.Document;


public class Teste {
	
	private Integer numero_anac;
	private String nome;
	private Integer pontuacao_maxima;
	
	public Teste() {};
	
    public Teste(int numero_anac, String nome, int pontuacao_maxima) {
    	
        this.numero_anac       = numero_anac;
        this.nome 		       = nome;
        this.pontuacao_maxima  = pontuacao_maxima;
        
    }
    
    public Teste (Document doc) {
    	
        numero_anac  	   = doc.getInteger("numero_anac");
        nome   		       = doc.getString("nome");
        pontuacao_maxima   = doc.getInteger("pontuacao_maxima");
    }
    
	public Integer getNumero_anac() {
		return numero_anac;
	}
	
	public void setNumero_anac(Integer numero_anac) {
		this.numero_anac = numero_anac;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Integer getPontuacao_maxima() {
		return pontuacao_maxima;
	}
	
	public void setPontuacao_maxima(Integer pontuacao_maxima) {
		this.pontuacao_maxima = pontuacao_maxima;
	}
	
    public String testeString() {
        return "Teste{" + "numero_anac=" + numero_anac + ", nome=" + nome + ", pontuacao_maxima=" + pontuacao_maxima + '}';
    }
    
    
    public Document testeDocument() {
        return new Document("numero_anac" , numero_anac)
                .append("nome", nome)
                .append("pontuacao_maxima", pontuacao_maxima);
    } 
    
}