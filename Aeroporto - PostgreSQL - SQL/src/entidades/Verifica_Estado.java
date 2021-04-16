package entidades;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import conexaoBD.ExecutarQuery;

public class Verifica_Estado {
	private Integer numero_registro;
	private Integer numero_anac;
	private String data_realizacao;
	private Long numero_horas;
	private Integer pontuacao;
	
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
	
	public StringBuilder estadoCadastro() {
        StringBuilder inserir_estado = new StringBuilder();
        
		inserir_estado.append("SELECT criar_estado("+
				numero_registro+
				", "+
				numero_anac+
				", "+
				data_realizacao+
				", "+
				numero_horas+
				", "+
				pontuacao+
				")");
		
		System.out.println("Query executada: " + inserir_estado);
		 
		return inserir_estado;
	}
	
	public List<Verifica_Estado> busca_estado(Connection conection) throws SQLException {
		
		StringBuilder buscar_estado = new StringBuilder();
		buscar_estado.append("select * from Verifica_Estado t");
		ResultSet rs_buscar_estados = ExecutarQuery.busca(buscar_estado, conection);
		List<Verifica_Estado> verifica_estados = new ArrayList<>();

		try {
			System.out.println("Estados cadastrados no Banco de Dados: \n");
			while(rs_buscar_estados.next()) {
				
				int numero_registroAux = rs_buscar_estados.getInt("numero_registro");
				int numero_anacAux = rs_buscar_estados.getInt("numero_anac");
				String data_realizacaoAux = rs_buscar_estados.getString("data_realizacao");	
				long numero_horasAux = rs_buscar_estados.getLong("numero_horas");
				int pontuacaoAux = rs_buscar_estados.getInt("pontuacao");

				Verifica_Estado verifica_Estado = new Verifica_Estado();

				verifica_Estado.setNumero_registro(numero_registroAux);
				verifica_Estado.setNumero_anac(numero_anacAux);
				verifica_Estado.setData_realizacao(data_realizacaoAux);
				verifica_Estado.setNumero_horas(numero_horasAux);
				verifica_Estado.setPontuacao(pontuacaoAux);
				
				verifica_estados.add(verifica_Estado);
				System.out.println(numero_registroAux + " - " + numero_anacAux + " - " + data_realizacaoAux + " - " + numero_horasAux + " - " + pontuacaoAux + "\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return verifica_estados;
	}
	
}