package entidades;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexaoBD.ExecutarQuery;

public class Aviao {
	private Integer numero_registro;
	private Integer codigo_modelo;
	private Integer numero_matricula;
	private Integer numero_membro;
	
	public Integer getNumero_registro() {
		return numero_registro;
	}
	
	public void setNumero_registro(Integer numero_registro) {
		this.numero_registro = numero_registro;
	}
	
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
	
	public StringBuilder aviaoCadastro() {
        StringBuilder inserir_aviao = new StringBuilder();
        
		inserir_aviao.append("SELECT criar_aviao("+
				numero_registro+
				", "+
				codigo_modelo+
				", "+
				numero_matricula+
				", "+
				numero_membro+
				")");
		
		System.out.println(inserir_aviao);
		
		return inserir_aviao;
	}
	
	public List<Aviao> busca_aviao(Connection conection) throws SQLException {
		
		StringBuilder buscar_aviao = new StringBuilder();
		buscar_aviao.append("select * from aviao a");
		ResultSet rs_buscar_avioes = ExecutarQuery.busca(buscar_aviao, conection);
		List<Aviao> avioes = new ArrayList<>();

		try {
			System.out.println("Avioes cadastrados no Banco de Dados: \n");
			while(rs_buscar_avioes.next()) {
				
				int numero_registroAux = rs_buscar_avioes.getInt("numero_registro");
				int codigo_modeloAux = rs_buscar_avioes.getInt("codigo_modelo");	
				int numero_matriculaAux = rs_buscar_avioes.getInt("numero_matricula");
				int numero_membroAux = rs_buscar_avioes.getInt("numero_membro");

				Aviao aviao = new Aviao();

				aviao.setNumero_registro(numero_registroAux);
				aviao.setCodigo_modelo(codigo_modeloAux);
				aviao.setNumero_matricula(numero_matriculaAux);
				aviao.setNumero_membro(numero_membroAux);
				
				avioes.add(aviao);
				System.out.println(numero_registroAux + " - " + codigo_modeloAux + " - " + numero_matriculaAux + " - " + numero_membroAux + "\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return avioes;
	}
}