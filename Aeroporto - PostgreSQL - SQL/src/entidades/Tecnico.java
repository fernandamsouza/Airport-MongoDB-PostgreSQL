package entidades;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexaoBD.ExecutarQuery;

public class Tecnico {
	private Integer numero_matricula;
	private Integer numero_membro;
	private Integer salario;
	private Long numero_telefone;
	private String endereco;
	
	public Integer getNumero_matricula() {
		return numero_matricula;
	}
	
	public void setNumero_matricula(Integer numero_matricula) {
		this.numero_matricula = numero_matricula;
	}
	
	public Integer getNumero_membro() {
		return numero_membro;
	}
	
	public void setNumero_membro(Integer numero_membro) {
		this.numero_membro = numero_membro;
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
	
	public StringBuilder tecnicoCadastro() {
		StringBuilder inserir_tecnico = new StringBuilder();
		inserir_tecnico.append("SELECT criar_tecnico("+
				numero_matricula+
				", "+
				numero_membro+
				", "+
				salario+
				", "+
				numero_telefone+
				", \'"+
				endereco+
				"\')");
		System.out.println(inserir_tecnico);
		return inserir_tecnico;
	}	
	
	public List<Tecnico> busca_tecnico(Connection conection) throws SQLException {
		
		StringBuilder buscar_tecnico = new StringBuilder();
		buscar_tecnico.append("select * from tecnico t");
		ResultSet rs_buscar_tecnicos = ExecutarQuery.busca(buscar_tecnico, conection);
		List<Tecnico> tecnicos = new ArrayList<>();

		try {
			System.out.println("Tecnicos cadastrados no Banco de Dados: \n");
			while(rs_buscar_tecnicos.next()) {
				
				int numero_matriculaAux = rs_buscar_tecnicos.getInt("numero_matricula");
				int numero_membroAux = rs_buscar_tecnicos.getInt("numero_membro");
				int salarioAux = rs_buscar_tecnicos.getInt("salario");	
				long numero_telefoneAux = rs_buscar_tecnicos.getLong("numero_telefone");
				String enderecoAux = rs_buscar_tecnicos.getString("endereco");

				Tecnico tecnico = new Tecnico();

				tecnico.setNumero_matricula(numero_matriculaAux);
				tecnico.setNumero_membro(numero_membroAux);
				tecnico.setSalario(salarioAux);
				tecnico.setNumero_telefone(numero_telefoneAux);
				tecnico.setEndereco(enderecoAux);
				
				tecnicos.add(tecnico);
				System.out.println(numero_matriculaAux + " - " + numero_membroAux + " - " + salarioAux + " - " + numero_telefoneAux + " - " + enderecoAux + "\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return tecnicos;
	}
	
}