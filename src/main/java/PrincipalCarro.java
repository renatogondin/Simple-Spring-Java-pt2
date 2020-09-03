import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import corejava.Console;
import excecao.CarroJaTemViagensException;
import excecao.CarroNaoEncontradoException;
import excecao.MotoristaNaoEncontradoException;
import modelo.Carro;
import modelo.Viagem;
import modelo.Motorista;
import service.CarroAppService;
import service.MotoristaAppService;

public class PrincipalCarro {
	public static void main(String[] args) {
		String marca;
		String modelo;
		String placa;

		Carro umCarro;
		Motorista umMotorista;
		
		@SuppressWarnings("resource")
		ApplicationContext fabrica = new ClassPathXmlApplicationContext("beans-jpa.xml");
		CarroAppService carroAppService = (CarroAppService)fabrica.getBean ("carroAppService");
		MotoristaAppService motoristaAppService = (MotoristaAppService)fabrica.getBean ("motoristaAppService");

		boolean continua = true;
		while (continua) {
			System.out.println('\n' + "O que você deseja fazer?");
			/* ==> */ System.out.println('\n' + "1. Cadastrar um carro de um motorista");
			System.out.println("2. Alterar um carro");
			/* ==> */ System.out.println("3. Remover um carro");
			
			System.out.println("4. Listar um carro e todas as suas viagens");
			System.out.println("5. Listar todos os carros e suas viagens");
			System.out.println("6. Sair");

			int opcao = Console.readInt('\n' + "Digite um número entre 1 e 6:");

			switch (opcao) {
			case 1: {
				long idMotorista = Console.readInt('\n' + "Informe o número do Motorista: ");

				try {
					umMotorista = motoristaAppService.recuperaUmMotorista(idMotorista);
				} catch (MotoristaNaoEncontradoException e) {
					System.out.println('\n' + e.getMessage());
					break;
				}

				marca = Console.readLine('\n' + "Informe a marca do carro: ");
				modelo = Console.readLine("Informe o modelo do carro: ");
				placa = Console.readLine("Informe a placa do carro: ");

				umCarro = new Carro(marca, modelo, placa, umMotorista);

				try
				/* ==> */ {
					carroAppService.inclui(umCarro);

					System.out.println('\n' + "Carro adicionado com sucesso");
				} catch (MotoristaNaoEncontradoException e) {
					System.out.println(e.getMessage());
				}

				break;
			}
			case 2: {
				int resposta = Console.readInt('\n' + "Digite o número do carro que você deseja alterar: ");

				try {
					umCarro = carroAppService.recuperaUmCarro(resposta);
					
				} catch (CarroNaoEncontradoException e) {
					System.out.println('\n' + e.getMessage());
					break;
				}

				System.out
						.println('\n' + "Número = " + umCarro.getId() + "    Marca = " + umCarro.getMarca()+"  Placa = "+ umCarro.getPlaca()+"   Modelo = "+umCarro.getModelo());

				System.out.println('\n' + "O que você deseja alterar?");
				System.out.println('\n' + "1. Marca");
				System.out.println("\n2. Modelo");
				System.out.println("\n3. Placa");

				int opcaoAlteracao = Console.readInt('\n' + "Digite um número de 1 a 3:");

				switch (opcaoAlteracao) {
				case 1:
					String marcaC = Console.readLine("Digite a nova marca: ");
					umCarro.setMarca(marcaC);

					try {
						carroAppService.altera(umCarro);

						System.out.println('\n' + "Alteração de carro efetuada com sucesso!");
					} catch (CarroNaoEncontradoException e) {
						System.out.println('\n' + e.getMessage());
					}

					break;

				case 2:
					String tipoC = Console.readLine("Digite o novo modelo: ");
					umCarro.setModelo(tipoC);

					try {
						carroAppService.altera(umCarro);

						System.out.println('\n' + "Alteração de tipo efetuada " + "com sucesso!");
					} catch (CarroNaoEncontradoException e) {
						System.out.println('\n' + e.getMessage());
					}

					break;
				case 3:
					String carroP = Console.readLine("Digite a nova placa: ");
					umCarro.setPlaca(carroP);

					try {
						carroAppService.altera(umCarro);

						System.out.println('\n' + "Alteração de placa efetuada " + "com sucesso!");
					} catch (CarroNaoEncontradoException e) {
						System.out.println('\n' + e.getMessage());
					}

					break;

				default:
					System.out.println('\n' + "Opção inválida!");
				}

				break;
			}


			case 3: {
				int resposta = Console.readInt('\n' + "Digite o número do carro que você deseja remover: ");

				try {
					umCarro = carroAppService.recuperaUmCarro(resposta);
				} catch (CarroNaoEncontradoException e) {
					System.out.println('\n' + e.getMessage());
					break;
				}

				System.out.println('\n' + "Número = " + umCarro.getId() + "    marca = " + umCarro.getMarca()
						+ "    modelo= " + umCarro.getModelo() + "  placa= " + umCarro.getPlaca());

				String resp = Console.readLine('\n' + "Confirma a remoção do Carro? s/n");

				if (resp.equals("s")) {
					try
					/* ==> */ {
						carroAppService.exclui(umCarro.getId());
						System.out.println('\n' + "carro removido com sucesso!");
					} catch (CarroNaoEncontradoException e) {
						System.out.println(e.getMessage());
					}catch (CarroJaTemViagensException e) {
						System.out.println('\n' + "Este carro possui viagens, logo não pode ser removido");
				    }
				} else {
					System.out.println('\n' + "Carro não removido.");
				}

				break;
			}

			case 4: {
				try {
					long resposta = Console.readInt('\n' + "Informe o número da Carro: ");
					umCarro = carroAppService.recuperaUmCarroEViagens(resposta);

				} catch (CarroNaoEncontradoException e) {
					System.out.println('\n' + e.getMessage());
					break;
				}

				System.out.println('\n' + "Número = " + umCarro.getId() + "    marca = " + umCarro.getMarca()
						+ "    modelo = " + umCarro.getModelo() + "  placa = " + umCarro.getPlaca());

				List<Viagem> viagens = umCarro.getViagens();

				for (Viagem viagem : viagens) {
					System.out.println('\n' + "      Viagem número = " + viagem.getId() + "  saída = "
							+ viagem.getSaida() + "  destino  = " + viagem.getDestino() + "  preço da gasolina = "
							+ viagem.getPreco());
				}

				break;
			}

			case 5: {
				List<Carro> carros = carroAppService.recuperaCarrosEViagens();

				if (carros.size() != 0) {

					for (Carro carro : carros) {

						System.out.println('\n' + "Número = " + carro.getId() + "    marca = "
								+ carro.getMarca() + "    modelo = " + carro.getModelo() + "  placa = "
								+ carro.getPlaca());

						List<Viagem> viagens = carro.getViagens();

						for (Viagem viagem : viagens) {
							System.out.println('\n' + "      Viagem número = " + viagem.getId() + "  saída = "
									+ viagem.getSaida() + "  destino  = " + viagem.getDestino() + "  preço da gasolina = "
									+ viagem.getPreco());
						}
					}
				} else {
					System.out.println('\n' + "Não há carros cadastrados.");
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
