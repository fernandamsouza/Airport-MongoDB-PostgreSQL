package entidades;

import java.util.ArrayList;
import java.util.List;

import conexaoBD.ExecutarQuery;
import java.sql.*;

public class Modelo {
	private Integer codigo_modelo;
	private Integer capacidade;
	private Integer peso;
	private String nome_modelo;
	
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
	
	public StringBuilder modeloCadastro() {
        StringBuilder inserir_modelo = new StringBuilder();
        
		inserir_modelo.append("SELECT criar_modelo("+
				codigo_modelo+
				", "+
				peso+
				", "+
				capacidade+
				", \'"+
				nome_modelo+
				"\')");
		
		System.out.println("Query executada: " + inserir_modelo);
		
		return inserir_modelo;
	}
	
	public List<Modelo> busca_modelo(Connection conection) throws SQLException {
		
		StringBuilder buscar_modelo = new StringBuilder();
		buscar_modelo.append("select * from modelo m");
		ResultSet rs_buscar_modelos = ExecutarQuery.busca(buscar_modelo, conection);
		List<Modelo> modelos = new ArrayList<>();

		try {
			System.out.println("Modelos de aviões cadastrados no Banco de Dados: \n");
			while(rs_buscar_modelos.next()) {
				
				int codigoModeloAux = rs_buscar_modelos.getInt("codigo_modelo");
				int capacidadeAux = rs_buscar_modelos.getInt("capacidade");
				int pesoAux = rs_buscar_modelos.getInt("peso");	
				String nome_modeloAux = rs_buscar_modelos.getString("nome_modelo");

				Modelo modelo = new Modelo();
				
				modelo.setCodigo_modelo(codigoModeloAux);
				modelo.setCapacidade(capacidadeAux);
				modelo.setPeso(pesoAux);
				modelo.setNome_modelo(nome_modeloAux);
				
				modelos.add(modelo);
				System.out.println(codigoModeloAux + " - " + capacidadeAux + " - " + pesoAux + " - " + nome_modeloAux + "\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return modelos;
	}
	
	
}