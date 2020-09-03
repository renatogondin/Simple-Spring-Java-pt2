import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import corejava.Console;
import excecao.ClienteNaoEncontradoException;
import excecao.MotoristaJaTemCarrosException;
import excecao.MotoristaNaoEncontradoException;
import modelo.Carro;
import modelo.Cliente;
import modelo.Motorista;
import service.ClienteAppService;
import service.MotoristaAppService;

public class PrincipalMotorista {
	public static void main(String[] args) {
		String nome;
		String sobrenome;
		String email;

		Cliente umCliente;
		Motorista umMotorista;

		@SuppressWarnings("resource")
		ApplicationContext fabrica = new ClassPathXmlApplicationContext("beans-jpa.xml");
		ClienteAppService clienteAppService = (ClienteAppService)fabrica.getBean ("clienteAppService");
		MotoristaAppService motoristaAppService = (MotoristaAppService)fabrica.getBean ("motoristaAppService");
		

		boolean continua = true;
		while (continua) {
			System.out.println('\n' + "O que você deseja fazer?");
			/* ==> */ System.out.println('\n' + "1. Cadastrar um motorista de um cliente");
			System.out.println("\n2. Alterar um motorista");
			/* ==> */ System.out.println("3. Remover um motorista");
			System.out.println("4. Listar um motorista e todos os seus carros");
			System.out.println("5. Listar Todos os motoristas e seus carros");
			System.out.println("6. Sair");

			int opcao = Console.readInt('\n' + "Digite um número entre 1 e 6:");

			switch (opcao) {
			case 1: {
				long idCliente = Console.readInt('\n' + "Informe o número do cliente: ");

				try {
					umCliente = clienteAppService.recuperaUmCliente(idCliente);
				} catch (ClienteNaoEncontradoException e) {
					System.out.println('\n' + e.getMessage());
					break;
				}

				nome = Console.readLine('\n' + "Informe o nome do Motorista: ");
				sobrenome = Console.readLine("Informe o sobrenome do Motorista: ");
				email = Console.readLine("Informe o email do Motorista: ");

				umMotorista = new Motorista(nome, sobrenome, email, umCliente);

				try
				/* ==> */ {
					motoristaAppService.inclui(umMotorista);

					System.out.println('\n' + "Motorista adicionado com sucesso");
				} catch (ClienteNaoEncontradoException e) {
					System.out.println(e.getMessage());
				}

				break;
			}
			case 2: {
				int resposta = Console.readInt('\n' + "Digite o número do motorista que você deseja alterar: ");

				try {
					umMotorista = motoristaAppService.recuperaUmMotorista(resposta);
					
				} catch (MotoristaNaoEncontradoException e) {
					System.out.println('\n' + e.getMessage());
					break;
				}

				System.out
						.println('\n' + "Número = " + umMotorista.getId() + "    nome = " + umMotorista.getNome()+"  sobrenome = "+ umMotorista.getSobrenome()+"   email= "+umMotorista.getEmail());

				System.out.println('\n' + "O que você deseja alterar?");
				System.out.println('\n' + "1. Nome");
				System.out.println("\n2. Email");

				int opcaoAlteracao = Console.readInt('\n' + "Digite um número de 1 a 2:");

				switch (opcaoAlteracao) {
				case 1:
					String nomeM = Console.readLine("Digite o novo Nome: ");
					umMotorista.setNome(nomeM);

					try {
						motoristaAppService.altera(umMotorista);

						System.out.println('\n' + "Alteração de motorista efetuada com sucesso!");
					} catch (MotoristaNaoEncontradoException e) {
						System.out.println('\n' + e.getMessage());
					}

					break;

				case 2:
					String em = Console.readLine("Digite o novo email: ");
					umMotorista.setEmail(em);

					try {
						motoristaAppService.altera(umMotorista);

						System.out.println('\n' + "Alteração de email efetuada " + "com sucesso!");
					} catch (MotoristaNaoEncontradoException e) {
						System.out.println('\n' + e.getMessage());
					}

					break;

				default:
					System.out.println('\n' + "Opção inválida!");
				}

				break;
			}


			case 3: {
				int resposta = Console.readInt('\n' + "Digite o número do motorista que você deseja remover: ");

				try {
					umMotorista = motoristaAppService.recuperaUmMotorista(resposta);
				} catch (MotoristaNaoEncontradoException e) {
					System.out.println('\n' + e.getMessage());
					break;
				}

				System.out.println('\n' + "Número = " + umMotorista.getId() + "    Nome = " + umMotorista.getNome()
						+ "    Sobrenome = " + umMotorista.getSobrenome() + "    email=" + umMotorista.getEmail());

				String resp = Console.readLine('\n' + "Confirma a remoção do motorista? S/N");

				if (resp.equals("s")) {
					try
					/* ==> */ {
						motoristaAppService.exclui(umMotorista.getId());
						System.out.println('\n' + "Motorista removido com sucesso!");
					} catch (MotoristaNaoEncontradoException e) {
						System.out.println(e.getMessage());
					}catch (MotoristaJaTemCarrosException e) {
						System.out.println('\n' + "Este motorista possui carros, logo não pode ser removido");
				    }
				} else {
					System.out.println('\n' + "Motorista não removido.");
				}

				break;
			}

			case 4: {
				try {
					long resposta = Console.readInt('\n' + "Informe o número do Motorista: ");
					umMotorista = motoristaAppService.recuperaUmMotoristaECarros(resposta);

				} catch (MotoristaNaoEncontradoException e) {
					System.out.println('\n' + e.getMessage());
					break;
				}

				System.out.println('\n' + "Número = " + umMotorista.getId() + "    Nome = " + umMotorista.getNome()
						+ "    Sobrenome = " + umMotorista.getSobrenome() + "    Email=" + umMotorista.getEmail());

				List<Carro> carros = umMotorista.getCarros();

				for (Carro carro : carros) {
					System.out.println('\n' + "      Carro número = " + carro.getId() + "  marca = "
							+ carro.getMarca() + "  modelo  = " + carro.getModelo() + "  placa = "
							+ carro.getPlaca());
				}

				break;
			}

			case 5: {
				List<Motorista> motoristas = motoristaAppService.recuperaMotoristasECarros();

				if (motoristas.size() != 0) {
					System.out.println("");

					for (Motorista motorista : motoristas) {

						System.out.println('\n' + "Número = " + motorista.getId() + "    Nome = "
								+ motorista.getNome() + "    sobrenome=" + motorista.getSobrenome() + "     email=" + motorista.getEmail());

						List<Carro> carros = motorista.getCarros();

						for (Carro carro : carros) {
							System.out.println('\n' + "      Carro número = " + carro.getId() + "  marca = "
									+ carro.getMarca() + "  modelo  = " + carro.getModelo() + "  placa = "
									+ carro.getPlaca());
						}
					}
				} else {
					System.out.println('\n' + "Não há motoristas cadastrados.");
				}

				break;
			}

			case 6: {
				continua = false;
				break;
			}

			default:
				System.out.println('\n' + "Opção inválida!");
			}
		}
	}
}
