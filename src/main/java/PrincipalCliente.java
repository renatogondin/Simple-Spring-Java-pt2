
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import corejava.Console;
import excecao.ClienteJaTemMotoristasException;
import excecao.ClienteNaoEncontradoException;
import modelo.Cliente;
import modelo.Motorista;
import service.ClienteAppService;

public class PrincipalCliente {
	public static void main(String[] args) {
		String nome;
		String sobrenome;
		String email;
		Cliente umCliente;

		@SuppressWarnings("resource")
		ApplicationContext fabrica = new ClassPathXmlApplicationContext("beans-jpa.xml");
		ClienteAppService clienteAppService = (ClienteAppService)fabrica.getBean ("clienteAppService");

		boolean continua = true;
		while (continua) {
			System.out.println('\n' + "O que você deseja fazer?");
			System.out.println('\n' + "1. Cadastrar um cliente");
			System.out.println("2. Alterar um cliente");
			System.out.println("3. Remover um cliente");
			System.out.println("4. Listar um cliente e seus motoristas");
			System.out.println("5. Listar todos os clientes e seus motoristas");
			System.out.println("6. Sair");

			int opcao = Console.readInt('\n' + "Digite um número entre 1 e 6:");

			switch (opcao) {
			case 1: {
				nome = Console.readLine('\n' + "Informe o nome do cliente: ");
				sobrenome = Console.readLine("Informe o sobrenome do cliente: ");
				email = Console.readLine("Informe o email do cliente: ");

				Cliente cliente = new Cliente(nome, sobrenome, email);

				long numero = clienteAppService.inclui(cliente);

				System.out.println('\n' + "Cliente número " + numero + " incluído com sucesso!");

				break;
			}

			case 2: {
				int resposta = Console.readInt('\n' + "Digite o número do cliente que você deseja alterar: ");

				try {
					umCliente = clienteAppService.recuperaUmCliente(resposta);
					
				} catch (ClienteNaoEncontradoException e) {
					System.out.println('\n' + e.getMessage());
					break;
				}

				System.out
						.println('\n' + "Número = " + umCliente.getId() + "    Nome = " + umCliente.getNome());

				System.out.println('\n' + "O que você deseja alterar?");
				System.out.println('\n' + "1. Email");
				System.out.println("\n2. Sobrenome");

				int opcaoAlteracao = Console.readInt('\n' + "Digite um número de 1 a 2:");

				switch (opcaoAlteracao) {
				case 1:
					String ez = Console.readLine("Digite o novo email: ");
					umCliente.setEmail(ez);

					try {
						clienteAppService.altera(umCliente);

						System.out.println('\n' + "Alteração de cliente efetuada com sucesso!");
					} catch (ClienteNaoEncontradoException e) {
						System.out.println('\n' + e.getMessage());
					}

					break;

				case 2:
					String s = Console.readLine("Digite o novo sobrenome: ");
					umCliente.setSobrenome(s);

					try {
						clienteAppService.altera(umCliente);

						System.out.println('\n' + "Alteração de sobrenome efetuada " + "com sucesso!");
					} catch (ClienteNaoEncontradoException e) {
						System.out.println('\n' + e.getMessage());
					}

					break;

				default:
					System.out.println('\n' + "Opção inválida!");
				}

				break;
			}

			case 3: {
				int resposta = Console.readInt('\n' + "Digite o número do cliente que você deseja remover: ");

				try {
					umCliente = clienteAppService.recuperaUmCliente(resposta);
				} catch (ClienteNaoEncontradoException e) {
					System.out.println('\n' + e.getMessage());
					break;
				}

				System.out.println('\n' + "Número = " + umCliente.getId() + "    Nome = " + umCliente.getNome()
						+ "   E-mail= " + umCliente.getEmail());

				String resp = Console.readLine('\n' + "Confirma a remoção do cliente?");

				if (resp.equals("s")) {
					try {
						clienteAppService.exclui(umCliente.getId());
						System.out.println('\n' + "Cliente removido com sucesso!");
					} catch (ClienteNaoEncontradoException e) {
						System.out.println('\n' + e.getMessage());
					}catch (ClienteJaTemMotoristasException e) {
						System.out.println('\n' + "Este cliente possui motristas, logo não pode ser removido");
				    }
				} else {
					System.out.println('\n' + "Cliente não removido.");
				}

				break;
			}

			case 4: {
				long numero = Console.readInt('\n' + "Informe o número do cliente: ");

				try {
					umCliente = clienteAppService.recuperaUmClienteEMotoristas(numero);
				} catch (ClienteNaoEncontradoException e) {
					System.out.println('\n' + e.getMessage());
					break;
				}

				System.out.println('\n' + "Número = " + umCliente.getId() + "    Nome = " + umCliente.getNome()
						+ "   E-mail= " + umCliente.getEmail());

				List<Motorista> motoristas = umCliente.getMotoristas();

				for (Motorista motorista : motoristas) {
					System.out.println('\n' + "      Motorista número = " + motorista.getId() + "  Nome = "
							+ motorista.getNome() + "  Sobrenome  = " + motorista.getSobrenome() + " Email= "
							+ motorista.getEmail());
				}

				break;
			}

			case 5: {
				List<Cliente> clientes= clienteAppService.recuperaClientesEMotoristas();

				if (clientes.size() != 0) {
					System.out.println("");

					for (Cliente cliente : clientes) {
						System.out.println('\n' + " Cliente Número = " + cliente.getId() + "    Nome = "
								+ cliente.getNome() + "   E-mail= " + cliente.getEmail());

						List<Motorista> motoristas = cliente.getMotoristas();

						for (Motorista motorista : motoristas) {
							System.out.println(
									'\n' + "      Motorista número = " + motorista.getId() + "  Nome = "
											+ motorista.getNome() + "  Sobrenome  = " + motorista.getSobrenome());
						}
					}
				} else {
					System.out.println('\n' + "Não há clientes cadastrados de acordo como foi solicitado.");
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
