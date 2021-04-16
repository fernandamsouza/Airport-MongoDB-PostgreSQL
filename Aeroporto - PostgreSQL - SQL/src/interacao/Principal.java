package interacao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import entidades.Modelo;
import entidades.Controlador;
import entidades.*;
import conexaoBD.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Principal {
	
	private static Scanner s = new Scanner(System.in);
	private static Connection conection;
	
	public static void main(String[] args) throws SQLException{
		boolean t = true;
		
		while(t) {
			System.out.println("Digite o que deseja fazer: \n"+"1- Conectar ao BD \n"+
					"2- Cadastrar modelo \n"+"3- Cadastrar controlador \n"+"4- Cadastrar tecnico \n"+"5- Cadastrar teste \n"+"6- Cadastrar aviao \n"+"7- Cadastrar estado \n"
					+"8- Cadastrar perito \n"+ "9- Buscar todos os modelos\n"+ "10- Buscar todos os tecnicos\n"+  "11- Buscar todos os avioes\n"+  "12- Buscar todos os controladores\n"
									+ "13- Buscar todos os testes\n" + "14- Buscar todos os estados\n" + "15- Buscar todos os peritos" + "\n");
			short opc = s.nextShort();
			switch(opc) {
				case 1:
					conectarBD();
					break;
				case 2:
					cadastrarModelo();
					break;
				case 3:
					cadastrarControlador();
					break;
				case 4:
					cadastrarTecnico();
					break;
				case 5:
					cadastrarTeste();
					break;
				case 6:
					cadastrarAviao();
					break;
				case 7:
					cadastrarEstado();
					break;
				case 8:
					cadastrarPerito();
					break;
				case 9:
					Modelo modelo = new Modelo();
					modelo.busca_modelo(conection);
					break;
				case 10:
					Tecnico tecnico = new Tecnico();
					tecnico.busca_tecnico(conection);
					break;
				case 11:
					Aviao aviao = new Aviao();
					aviao.busca_aviao(conection);
					break;
				case 12:
					Controlador controlador = new Controlador();
					controlador.busca_controlador(conection);
					break;
				case 13:
					Teste teste = new Teste();
					teste.busca_teste(conection);
					break;
				case 14:
					Verifica_Estado verifica_estado = new Verifica_Estado();
					verifica_estado.busca_estado(conection);
					break;
				case 15:
					Perito perito = new Perito();
					perito.busca_perito(conection);
					break;
				default:
					System.out.println("Digite outro comando");
					break;
			}
		}
	}

	
	public static void conectarBD() throws SQLException {
		try {
			s.nextLine();
			
			System.out.println("Digite o endereco do servidor:");
			String endereco = s.nextLine();
			
			System.out.println("Digite a porta do servidor:");
			String porta = s.nextLine();
			
			System.out.println("Digite a senha do servidor:");
			String senha = s.nextLine();
			
			conection = ConectarBanco.getConection(endereco, porta, senha);
			
			System.out.println("SUCESSO - Conexão com o BD estabelecida.");
			
		} catch(SQLException e) {
			throw e;
		}
		
	}

	
	public static void cadastrarModelo() throws SQLException {
		
		Modelo m = new Modelo();
		
		System.out.println("Digite o codigo do Modelo: \n");
		Integer codigo_modelo = s.nextInt();
		
		System.out.println("Digite a capacidade do Modelo: \n");
		Integer capacidade = s.nextInt();
		
		System.out.println("Digite o peso do Modelo: \n");
		Integer peso = s.nextInt();
		
		s.nextLine();
		
		System.out.println("Digite o nome do Modelo: \n");
		String nome = s.nextLine();
		
		m.setCodigo_modelo(codigo_modelo);
		m.setCapacidade(capacidade);
		m.setPeso(peso);
		m.setNome_modelo(nome);
		
		if (ExecutarQuery.cadastra(m.modeloCadastro(), conection) == false) {
			System.out.println("Erro ao cadastrar novo modelo.\n");
		} else {
			System.out.println("Sucesso! ID do modelo: " + codigo_modelo + "\n");
		}
	}
	
	public static void cadastrarControlador() throws SQLException {
		
		Controlador c = new Controlador();
		
		System.out.println("Digite o numero da matricula: \n");
		Integer numero_matricula = s.nextInt();
		
		System.out.println("Digite o numero de membro: \n");
		Integer numero_membro = s.nextInt();
		
		s.nextLine();
		
		System.out.println("Digite a data do exame: \n");
		String data_exame = s.nextLine();
		
		System.out.println("Digite o salario: \n");
		Integer salario = s.nextInt();
		
		System.out.println("Digite o numero de telefone: \n");
		Long numero_telefone = s.nextLong();
		
		s.nextLine();
		
		System.out.println("Digite o endereco: \n");
		String endereco = s.nextLine();
		
		c.setNumero_matricula(numero_matricula);
		c.setNumero_membro(numero_membro);
		c.setData_exame(data_exame);
		c.setSalario(salario);
		c.setNumero_telefone(numero_telefone);
		c.setEndereco(endereco);
		
		if (ExecutarQuery.cadastra(c.controladorCadastro(), conection) == false) {
			System.out.println("Erro ao cadastrar novo controlador.\n");
		} else {
			System.out.println("Sucesso! Numero de matricula do controlador: " + numero_matricula + "\n");
		}
		
	}
	
	public static void cadastrarTecnico() throws SQLException {
		
		Tecnico t = new Tecnico();
		
		System.out.println("Digite o numero da matricula: \n");
		Integer numero_matricula = s.nextInt();
		
		System.out.println("Digite o numero de membro: \n");
		Integer numero_membro = s.nextInt();
		
		System.out.println("Digite o salario: \n");
		Integer salario = s.nextInt();
		
		System.out.println("Digite o numero de telefone: \n");
		Long numero_telefone = s.nextLong();
		
		s.nextLine();
		
		System.out.println("Digite o endereco: \n");
		String endereco = s.nextLine();
		
		t.setNumero_matricula(numero_matricula);
		t.setNumero_membro(numero_membro);
		t.setSalario(salario);
		t.setNumero_telefone(numero_telefone);
		t.setEndereco(endereco);
		
		if (ExecutarQuery.cadastra(t.tecnicoCadastro(), conection) == false) {
			System.out.println("Erro ao cadastrar novo tecnico.\n");
		} else {
			System.out.println("Sucesso! Numero de matricula do tecnicor: " + numero_matricula + "\n");
		}
		
	}
	
	public static void cadastrarTeste() throws SQLException {
		
		Teste t = new Teste();
		
		System.out.println("Digite o numero anac: \n");
		Integer numero_anac = s.nextInt();
		
		s.nextLine();
		
		System.out.println("Digite o nome do teste: \n");
		String nome = s.nextLine();
		
		System.out.println("Digite a pontuacao maxima: \n");
		Integer pontuacao_max = s.nextInt();
		
		t.setNumero_anac(numero_anac);
		t.setNome(nome);
		t.setPontuacao_maxima(pontuacao_max);
		

		if (ExecutarQuery.cadastra(t.testeCadastro(), conection) == false) {
			System.out.println("Erro ao cadastrar novo tecnico.\n");
		} else {
			System.out.println("Sucesso! Numero anac do teste: " + numero_anac+ "\n");
		}
		
	}
	
	public static void cadastrarAviao() throws SQLException {
		
		Aviao a = new Aviao();
		
		System.out.println("Digite o numero registro: \n");
		Integer numero_registro = s.nextInt();
		
		System.out.println("Digite o codigo do modelo: \n");
		Integer codigo_modelo = s.nextInt();
		
		System.out.println("Digite o numero matricula: \n");
		Integer numero_matricula = s.nextInt();
		
		System.out.println("Digite o numero membro: \n");
		Integer numero_membro = s.nextInt();
		
		a.setNumero_registro(numero_registro);
		a.setCodigo_modelo(codigo_modelo);
		a.setNumero_matricula(numero_matricula);
		a.setNumero_membro(numero_membro);
		

		if (ExecutarQuery.cadastra(a.aviaoCadastro(), conection) == false) {
			System.out.println("Erro ao cadastrar novo aviao.\n");
		} else {
			System.out.println("Sucesso! Numero aviao: " + numero_registro + "\n");
		}
		
	}
	
	public static void cadastrarEstado() throws SQLException {
		
		Verifica_Estado ve = new Verifica_Estado();
		
		System.out.println("Digite o numero registro: \n");
		Integer numero_registro = s.nextInt();
		
		System.out.println("Digite o numero anac: \n");
		Integer numero_anac = s.nextInt();
		
		s.nextLine();
		
		System.out.println("Digite a data de realizacao: \n");
		String data = s.nextLine();
		
		System.out.println("Digite o numero horas: \n");
		Long numero_horas = s.nextLong();
		
		System.out.println("Digite a pontuacao: \n");
		Integer pontuacao = s.nextInt();
		
		ve.setNumero_registro(numero_registro);
		ve.setNumero_anac(numero_anac);
		ve.setData_realizacao(data);
		ve.setNumero_horas(numero_horas);
		ve.setPontuacao(pontuacao);
		
		
		if (ExecutarQuery.cadastra(ve.estadoCadastro(), conection) == false) {
			System.out.println("Erro ao cadastrar novo estado.\n");
		} else {
			System.out.println("Sucesso! Pontuacao: " + pontuacao + " para Aviao: " + numero_registro + "\n");
		}
		
	}
	
	public static void cadastrarPerito() throws SQLException {
		
		Perito p = new Perito();
		
		System.out.println("Digite o codigo do modelo: \n");
		Integer codigo_modelo = s.nextInt();
		
		System.out.println("Digite o numero matricula: \n");
		Integer numero_matricula = s.nextInt();
		
		System.out.println("Digite o numero membro: \n");
		Integer numero_membro = s.nextInt();
		
		p.setCodigo_modelo(codigo_modelo);
		p.setNumero_matricula(numero_matricula);
		p.setNumero_membro(numero_membro);
		

		if (ExecutarQuery.cadastra(p.peritoCadastro(), conection) == false) {
			System.out.println("Erro ao cadastrar novo perito.\n");
		} else {
			System.out.println("Sucesso! Codigo modelo: " + codigo_modelo + " Numero matricula: " + numero_matricula + "\n");
		}
		
	}

	
}


