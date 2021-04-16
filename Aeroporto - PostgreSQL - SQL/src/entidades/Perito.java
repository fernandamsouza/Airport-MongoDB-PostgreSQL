package entidades;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexaoBD.ExecutarQuery;

public class Perito {
	private Integer codigo_modelo;
	private Integer numero_matricula;
	private Integer numero_membro;
	
	
	public Integer getCodigo_modelo() {
		return codigo_modelo;
	}
	
	public void setCodigo_modelo(Integer codigo_modelo) {
		this.codigo_modelo = codigo_modelo;
	}
	
	public Integer getNumero_membro() {
		return numero_membro;
	}
	
	public void setNumero_membro(Integer numero_membro) {
		this.numero_membro = numero_membro;
	}
	
	public Integer getNumero_matricula() {
		return numero_matricula;
	}
	
	public void setNumero_matricula(Integer numero_matricula) {
		this.numero_matricula = numero_matricula;
	}
	
	public StringBuilder peritoCadastro() {
        StringBuilder inserir_perito = new StringBuilder();
        
		inserir_perito.append("SELECT criar_perito("+
				codigo_modelo+
				", "+
				numero_matricula+
				", "+
				numero_membro+
				")");
		
		System.out.println(inserir_perito);
		
		return inserir_perito;
	}
	
	public List<Perito> busca_perito(Connection conection) throws SQLException {
		
		StringBuilder buscar_perito = new StringBuilder();
		buscar_perito.append("select * from perito p");
		ResultSet rs_buscar_peritos = ExecutarQuery.busca(buscar_perito, conection);
		List<Perito> peritos = new ArrayList<>();

		try {
			System.out.println("Peritos cadastrados no Banco de Dados: \n");
			while(rs_buscar_peritos.next()) {
				
				int codigo_modeloAux = rs_buscar_peritos.getInt("codigo_modelo");	
				int numero_matriculaAux = rs_buscar_peritos.getInt("numero_matricula");
				int numero_membroAux = rs_buscar_peritos.getInt("numero_membro");

				Perito perito = new Perito();

				perito.setCodigo_modelo(codigo_modeloAux);
				perito.setNumero_matricula(numero_matriculaAux);
				perito.setNumero_membro(numero_membroAux);
				
				peritos.add(perito);
				System.out.println(codigo_modeloAux + " - " + numero_matriculaAux + " - " + numero_membroAux + "\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return peritos;
	}
}