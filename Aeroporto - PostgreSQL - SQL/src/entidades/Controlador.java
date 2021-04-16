package entidades;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import conexaoBD.ExecutarQuery;

public class Controlador {
	private Integer numero_matricula;
	private Integer numero_membro;
	private String data_exame;
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
	
	public StringBuilder controladorCadastro() {
		StringBuilder inserir_controlador = new StringBuilder();
		inserir_controlador.append("SELECT criar_controlador("+
				numero_matricula+
				", "+
				numero_membro+
				", "+
				data_exame+
				", "+
				salario+
				", "+
				numero_telefone+
				", \'"+
				endereco+
				"\')");
		System.out.println(inserir_controlador);
		return inserir_controlador;
	}
	
	public List<Controlador> busca_controlador(Connection conection) throws SQLException {
		
		StringBuilder buscar_controlador = new StringBuilder();
		buscar_controlador.append("select * from controlador t");
		ResultSet rs_buscar_controladores = ExecutarQuery.busca(buscar_controlador, conection);
		List<Controlador> controladores = new ArrayList<>();

		try {
			System.out.println("Controladores cadastrados no Banco de Dados: \n");
			while(rs_buscar_controladores.next()) {
				
				int numero_matriculaAux = rs_buscar_controladores.getInt("numero_matricula");
				int numero_membroAux = rs_buscar_controladores.getInt("numero_membro");
				String data_exameAux = rs_buscar_controladores.getString("data_exame");
				int salarioAux = rs_buscar_controladores.getInt("salario");	
				long numero_telefoneAux = rs_buscar_controladores.getLong("numero_telefone");
				String enderecoAux = rs_buscar_controladores.getString("endereco");

			
				Controlador controlador = new Controlador();

				controlador.setNumero_matricula(numero_matriculaAux);
				controlador.setNumero_membro(numero_membroAux);
				controlador.setData_exame(data_exameAux);
				controlador.setSalario(salarioAux);
				controlador.setNumero_telefone(numero_telefoneAux);
				controlador.setEndereco(enderecoAux);
				
				controladores.add(controlador);
				System.out.println(numero_matriculaAux + " - " + numero_membroAux + " - " + data_exameAux + " - " + salarioAux + " - " + numero_telefoneAux + " - " + enderecoAux + "\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return controladores;
	}

	
}

