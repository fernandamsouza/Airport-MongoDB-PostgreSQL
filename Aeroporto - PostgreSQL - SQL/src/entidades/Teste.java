package entidades;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexaoBD.ExecutarQuery;

public class Teste {
	private Integer numero_anac;
	private String nome;
	private Integer pontuacao_maxima;
	
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
	
	public StringBuilder testeCadastro() {
        StringBuilder inserir_teste = new StringBuilder();
        
		inserir_teste.append("SELECT criar_teste("+
				numero_anac+
				", \'"+
				nome+
				"\', "+
				pontuacao_maxima+
				")");
		
		System.out.println(inserir_teste);
		
		return inserir_teste;
	}
	
	public List<Teste> busca_teste(Connection conection) throws SQLException {
		
		StringBuilder buscar_teste = new StringBuilder();
		buscar_teste.append("select * from teste t");
		ResultSet rs_buscar_testes = ExecutarQuery.busca(buscar_teste, conection);
		List<Teste> testes = new ArrayList<>();

		try {
			System.out.println("Controladores cadastrados no Banco de Dados: \n");
			while(rs_buscar_testes.next()) {
				
				int numero_anacAux = rs_buscar_testes.getInt("numero_anac");
				String nomeAux = rs_buscar_testes.getString("nome");
				int pontuacaoMaximaAux = rs_buscar_testes.getInt("pontuacao_maxima");	
			
				Teste teste = new Teste();

				teste.setNumero_anac(numero_anacAux);
				teste.setNome(nomeAux);
				teste.setPontuacao_maxima(pontuacaoMaximaAux);
				
				testes.add(teste);
				System.out.println(numero_anacAux + " - " + nomeAux + " - " + pontuacaoMaximaAux + "\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return testes;
	}
}