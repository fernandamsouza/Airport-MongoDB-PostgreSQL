package interacao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dao.*;
import entidades.*;
import conexaoBD.*;
import java.sql.*;

import com.mongodb.client.MongoCollection;



public class Principal {
	
	private static Scanner s = new Scanner(System.in);
	
	public static void main(String[] args) throws SQLException{
		boolean t = true;
		
		while(t) {
			System.out.println("Digite o que deseja fazer:\n" + "1- Conectar ao BD \n" + "2- Cadastrar um documento \n" + "3- Buscar uma coleção\n" + "4- Atualizar um documento\n" + "5- Deletar um documento\n" + "6- Buscar por codigo\n");
			short opc = s.nextShort();
			switch(opc) {
				case 1:
					conectarBD();
					break;
				case 2:
					System.out.println("Digite o que deseja fazer: \n"+
							"1- Cadastrar modelo \n"+"2- Cadastrar controlador \n"+"3- Cadastrar tecnico \n"+"4- Cadastrar teste \n"+"5- Cadastrar aviao \n"+"6- Cadastrar estado \n"
							+"7- Cadastrar perito \n");
					opc = s.nextShort();
					switch(opc) {
					case 1:
						cadastrarModelo();
						break;
					case 2:
						cadastrarControlador();
						break;
					case 3:
						cadastrarTecnico();
						break;
					case 4:
						cadastrarTeste();
						break;
					case 5:
						cadastrarAviao();
						break;
					case 6:
						cadastrarEstado();
						break;
					case 7:
						cadastrarPerito();
						break;
					default:
						System.out.println("Digite outro comando");
						break;
					}
					break;
				case 3:
					System.out.println("1- Buscar todos os modelos\n"+ "2- Buscar todos os tecnicos\n"+  "3- Buscar todos os avioes\n"+  "4- Buscar todos os controladores\n"
									+ "5- Buscar todos os testes\n" + "6- Buscar todos os estados\n" + "7- Buscar todos os peritos" + "\n");
					opc = s.nextShort();
					switch(opc) {
					case 1:
						ModeloDAO mDAO = new ModeloDAO();
						
						List<Modelo> listar = mDAO.listar();
						
						System.out.println("\n");
			        	System.out.println("codigo" + "\t" + "capacidade" + "\t" + "peso" + "\t" + "nome");
				        for(int i = 0; i<listar.size(); i++) {
				            System.out.println(listar.get(i).getCodigo_modelo() + "\t" + listar.get(i).getCapacidade() + "\t\t" + listar.get(i).getPeso() + "\t" + listar.get(i).getNome_modelo());
				        }
						System.out.println("\n");
				        
						break;
					case 2:
						TecnicoDAO tDAO = new TecnicoDAO();

						List<Tecnico> listarT = tDAO.listar();
						
						System.out.println("\n");
			        	System.out.println("matricula" + "\t" + "salario" + "\t\t" + "telefone" + "\t" + "endereco");
				        for(int i = 0; i<listarT.size(); i++) {
				            System.out.println(listarT.get(i).getNumero_matricula() + "\t\t" + listarT.get(i).getSalario() + "\t\t" + listarT.get(i).getNumero_telefone() + "\t" + listarT.get(i).getEndereco());
				        }
						System.out.println("\n");
				        
						break;
					case 3:
						AviaoDAO aDAO = new AviaoDAO();
						
						List<Aviao> listarA = aDAO.listar();
						
						System.out.println("\n");
			        	System.out.println("numero_registro" + "\t" + "codigo_modelo");
				        for(int i = 0; i<listarA.size(); i++) {
				            System.out.println(listarA.get(i).getNumero_registro() + "\t\t" + listarA.get(i).getCodigo_modelo());
				        }
						System.out.println("\n");
				        
						break;
					case 4:
						ControladorDAO cDAO = new ControladorDAO();
						
						List<Controlador> listarC = cDAO.listar();
						
						System.out.println("\n");
			        	System.out.println("matricula" + "\t" + "data_exame" + "\t" + "salario" + "\t\t" + "telefone" + "\t" + "endereco");
				        for(int i = 0; i<listarC.size(); i++) {
				        	 System.out.println(listarC.get(i).getNumero_matricula() + "\t\t" + listarC.get(i).getData_exame() + "\t" + listarC.get(i).getSalario() + "\t\t" + listarC.get(i).getNumero_telefone() + "\t" + listarC.get(i).getEndereco());
				        }
						System.out.println("\n");
				        
						break;				
					case 5:
						TesteDAO tesDAO = new TesteDAO();
						List<Teste> listarTES = tesDAO.listar();
						
						System.out.println("\n");
			        	System.out.println("numero_anac" + "\t" + "nome" + "\t\t" + "pontuacao_maxima");
				        for(int i = 0; i<listarTES.size(); i++) {
				        	 System.out.println(listarTES.get(i).getNumero_anac() + "\t\t" + listarTES.get(i).getNome() + "\t\t" + listarTES.get(i).getPontuacao_maxima());
				        }
						System.out.println("\n");
				        
						break;	
					case 6:
						Verifica_EstadoDAO estadoDAO = new Verifica_EstadoDAO();
						List<Verifica_Estado> listarEstado = estadoDAO.listar();
						
						System.out.println("\n");
			        	System.out.println("numero_registro" + "\t\t" + "numero_anac" + "\t\t" + "numero_matricula" + "\t" + "data_realizacao" + "\t\t" + "numero_horas" + "\t\t" + "pontuacao");
				        for(int i = 0; i<listarEstado.size(); i++) {
				        	 System.out.println(listarEstado.get(i).getNumero_registro() + "\t\t\t" + listarEstado.get(i).getNumero_anac() + "\t\t\t" + listarEstado.get(i).getNumero_matricula() + "\t\t\t" + listarEstado.get(i).getData_realizacao() + "\t\t" + listarEstado.get(i).getNumero_horas() + "\t\t\t" + listarEstado.get(i).getPontuacao());
				        }
						System.out.println("\n");
				        
						break;	
					case 7:
						PeritoDAO pDAO = new PeritoDAO();
						List<Perito> listarP = pDAO.listar();

						System.out.println("\n");
			        	System.out.println("codigo_modelo" + "\t" + "numero_matricula");
				        for(int i = 0; i<listarP.size(); i++) {
				        	 System.out.println(listarP.get(i).getCodigo_modelo() + "\t\t" + listarP.get(i).getNumero_matricula());
				        }
						System.out.println("\n");
				        
						break;	
					default:
						System.out.println("Digite outro comando");
						break;
					}
					break;
				case 4:
					System.out.println("1- Atualizar um Aviao\n" + "2- Atualizar um Teste\n");
					opc = s.nextShort();
					switch(opc) {
						case 1:
							AviaoDAO aviaoDAO = new AviaoDAO();
							
							System.out.println("Digite o numero de registro para atualizar: \n");
							Integer numero_registro = s.nextInt();
							
							System.out.println("Digite o codigo do modelo: \n");
							Integer codigo_modelo = s.nextInt();
						
							
							Aviao a = new Aviao(numero_registro, codigo_modelo);
							
							boolean returnS = aviaoDAO.update(a);
							
							if (returnS) {
								System.out.println("Atualizado com sucesso!\n");
							}
							
							break;
						case 2:
							TesteDAO testeDAO = new TesteDAO();
							
							System.out.println("Digite o numero anac para atualizar: \n");
							Integer numero_anac = s.nextInt();
							
							System.out.println("Digite o nome para atualizar: \n");
							String nome = s.next();
							
							
							System.out.println("Digite a nova pontuacao maxima: \n");
							Integer pontuacao_maxima = s.nextInt();
						
							Teste t2 = new Teste(numero_anac, nome, pontuacao_maxima);
							
							boolean returnSE = testeDAO.update(t2);
							
							if (returnSE) {
								System.out.println("Atualizado com sucesso!\n");
							}
							
							break;
						default:
							System.out.println("Digite outro comando");
							break;
					}
					break;
				case 5:
					System.out.println("1- Deletar um Aviao\n" + "2- Deletar um Teste\n");
					opc = s.nextShort();
					switch(opc) {
						case 1:
							AviaoDAO aviaoDAO = new AviaoDAO();
							
							System.out.println("Digite o numero de registro para deletar: \n");
							Integer numero_registro = s.nextInt();

							
							boolean returnD = aviaoDAO.deletarNumeroRegistro(numero_registro);
							
							if (returnD) {
								System.out.println("Deletado com sucesso!\n");
							}
							
							break;
						case 2:
							TesteDAO testeDAO = new TesteDAO();
							
							System.out.println("Digite o numero anac para deletar: \n");
							Integer numero_anac = s.nextInt();
							
							
							boolean returnNA = testeDAO.deletarNumeroAnac(numero_anac);
							
							if (returnNA) {
								System.out.println("Deletado com sucesso!\n");
							}
							
							break;
						default:
							System.out.println("Digite outro comando");
							break;
					}
					break;
				case 6:
					System.out.println("1- Buscar um Aviao\n" + "2- Buscar um Teste\n");
					opc = s.nextShort();
					switch(opc) {
						case 1:

							AviaoDAO aviaoDAO = new AviaoDAO();
							
							System.out.println("Digite o numero de registro para buscar: \n");
							Integer numero_registro = s.nextInt();
							
							Aviao avi = aviaoDAO.buscarNumeroRegistro(numero_registro);
							
							System.out.println("O avião " + numero_registro + " possui codigo de modelo " + avi.getCodigo_modelo());
							
							break;
						case 2:
							TesteDAO testeDAO = new TesteDAO();
							
							System.out.println("Digite o numero anac para buscar: \n");
							Integer numero_anac = s.nextInt();
							
							
							Teste test = testeDAO.buscarNumeroAnac(numero_anac);
							
							System.out.println("O teste " + numero_anac + " possui nome " + test.getNome() + " e pontuacao_maxima de " + test.getPontuacao_maxima());	
							
							break;
						default:
							System.out.println("Digite outro comando");
							break;
					}
					break;
			}
		}
	}

	
	public static void conectarBD() {
			
			MongoCollection collection = new ConectarBanco().getCollection("Tecnico");
			System.out.println(collection);

			System.out.println("SUCESSO - Conexão com o MongoDB estabelecida.");
			
	}

	
	public static void cadastrarModelo() throws SQLException {
		
		System.out.println("Digite o codigo do Modelo: \n");
		Integer codigo_modelo = s.nextInt();
		
		System.out.println("Digite a capacidade do Modelo: \n");
		Integer capacidade = s.nextInt();
		
		System.out.println("Digite o peso do Modelo: \n");
		Integer peso = s.nextInt();
		
		s.nextLine();
		
		System.out.println("Digite o nome do Modelo: \n");
		String nome = s.nextLine();
		
		Modelo m = new Modelo(codigo_modelo, capacidade, peso, nome);
		ModeloDAO dao = new ModeloDAO();
		
		dao.salvar(m);
		
	}
	
	public static void cadastrarControlador() throws SQLException {
		
		System.out.println("Digite o numero da matricula: \n");
		Integer numero_matricula = s.nextInt();
		
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

		Controlador c = new Controlador(numero_matricula, data_exame, salario, numero_telefone, endereco);
		ControladorDAO dao = new ControladorDAO();
		
		dao.salvar(c);
		
	}
	
	public static void cadastrarTecnico() throws SQLException {
		
		System.out.println("Digite o numero da matricula: \n");
		Integer numero_matricula = s.nextInt();
		
		System.out.println("Digite o salario: \n");
		Integer salario = s.nextInt();
		
		System.out.println("Digite o numero de telefone: \n");
		Long numero_telefone = s.nextLong();
		
		s.nextLine();
		
		System.out.println("Digite o endereco: \n");
		String endereco = s.nextLine();
		
		Tecnico t = new Tecnico(numero_matricula, salario, numero_telefone, endereco);
		TecnicoDAO dao = new TecnicoDAO();
		
		dao.salvar(t);
		
	}
	
	public static void cadastrarTeste() throws SQLException {
		
		System.out.println("Digite o numero anac: \n");
		Integer numero_anac = s.nextInt();
		
		s.nextLine();
		
		System.out.println("Digite o nome do teste: \n");
		String nome = s.nextLine();
		
		System.out.println("Digite a pontuacao maxima: \n");
		Integer pontuacao_max = s.nextInt();
		
		Teste t = new Teste(numero_anac, nome, pontuacao_max);
		TesteDAO dao = new TesteDAO();
		
		dao.salvar(t);
		
	}
	
	public static void cadastrarAviao() throws SQLException {
		
		System.out.println("Digite o numero registro: \n");
		Integer numero_registro = s.nextInt();
		
		System.out.println("Digite o codigo do modelo: \n");
		Integer codigo_modelo = s.nextInt();
			
		Aviao a = new Aviao(numero_registro, codigo_modelo);
		AviaoDAO dao = new AviaoDAO();
		
		dao.salvar(a);
	}
	
	public static void cadastrarEstado() throws SQLException {
		
		System.out.println("Digite o numero registro: \n");
		Integer numero_registro = s.nextInt();
		
		System.out.println("Digite o numero anac: \n");
		Integer numero_anac = s.nextInt();
		
		System.out.println("Digite o numero de matricula: \n");
		Integer numero_matricula = s.nextInt();
		
		s.nextLine();
		
		System.out.println("Digite a data de realizacao: \n");
		String data = s.nextLine();
		
		System.out.println("Digite o numero horas: \n");
		Long numero_horas = s.nextLong();
		
		System.out.println("Digite a pontuacao: \n");
		Integer pontuacao = s.nextInt();
		
		Verifica_Estado ve = new Verifica_Estado(numero_registro, numero_anac, numero_matricula, data, numero_horas, pontuacao);
		Verifica_EstadoDAO dao = new Verifica_EstadoDAO();
		
		dao.salvar(ve);
		
	}
	
	public static void cadastrarPerito() throws SQLException {

		
		System.out.println("Digite o codigo do modelo: \n");
		Integer codigo_modelo = s.nextInt();
		
		System.out.println("Digite o numero matricula: \n");
		Integer numero_matricula = s.nextInt();
		
		
		Perito p = new Perito(codigo_modelo, numero_matricula);
		PeritoDAO dao = new PeritoDAO();
		
		dao.salvar(p);
		
	}

	
}


