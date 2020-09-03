import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import corejava.Console;
import excecao.CarroNaoEncontradoException;
import excecao.ViagemNaoEncontradaException;
import modelo.Carro;
import modelo.Viagem;
import service.CarroAppService;
import service.ViagemAppService;

public class PrincipalViagem {
	public static void main(String[] args) {
		String saida;
		String destino;
		double preco;
		Carro umCarro;
		Viagem umaViagem;
		
		
		@SuppressWarnings("resource")
		ApplicationContext fabrica = new ClassPathXmlApplicationContext("beans-jpa.xml");
		CarroAppService carroAppService = (CarroAppService)fabrica.getBean ("carroAppService");
		ViagemAppService viagemAppService = (ViagemAppService)fabrica.getBean("viagemAppService");
	

		boolean continua = true;
		while (continua) {
			System.out.println('\n' + "O que você deseja fazer?");
			/* ==> */ System.out.println('\n' + "1. Cadastrar uma viagem de um carro");
			System.out.println("2. Alterar uma viagem");
			/* ==> */ System.out.println("3. Remover uma viagem");
			System.out.println("4. Listar todas as viagens");
			System.out.println("5. Sair");

			int opcao = Console.readInt('\n' + "Digite um número entre 1 e 5:");

			switch (opcao) {
			case 1: {
				long idCarro = Console.readInt('\n' + "Informe o número do carro: ");

				try {
					umCarro = carroAppService.recuperaUmCarro(idCarro);
				} catch (CarroNaoEncontradoException e) {
					System.out.println('\n' + e.getMessage());
					break;
				}

				preco = Console.readDouble('\n' + "Informe o preço da gasolina da viagem: ");
				saida = Console.readLine("Informe o local de saída da viagem: ");
				destino = Console.readLine("Informe o destino da viagem: ");

				umaViagem = new Viagem(preco, saida, destino, umCarro);
				

				try
				/* ==> */ {
					viagemAppService.inclui(umaViagem);

					System.out.println('\n' + "Viagem adicionada com sucesso");
				} catch (CarroNaoEncontradoException e) {
					System.out.println(e.getMessage());
				}

				break;
			}
			case 2: {
				int resposta = Console.readInt('\n' + "Digite o número da viagem que você deseja alterar: ");

				try {
					umaViagem = viagemAppService.recuperaUmaViagem(resposta);

				} catch (ViagemNaoEncontradaException e) {
					System.out.println('\n' + e.getMessage());
					break;
				}

				System.out.println('\n' + "Número = " + umaViagem.getId() + "    saida = " + umaViagem.getSaida()
						+ "  Preço = " + umaViagem.getPreco() + "   Destino= " + umaViagem.getDestino());

				System.out.println('\n' + "O que você deseja alterar?");
				System.out.println('\n' + "1. Saida");
				System.out.println("\n2. Preço da gasolina");
				System.out.println("\n3. Destino");

				int opcaoAlteracao = Console.readInt('\n' + "Digite um número de 1 a 3:");

				switch (opcaoAlteracao) {
				case 1:
					String nomeS = Console.readLine("Digite o novo local de saída: ");
					umaViagem.setSaida(nomeS);

					try {
						viagemAppService.altera(umaViagem);

						System.out.println('\n' + "Alteração de viagem efetuada com sucesso!");
					} catch (ViagemNaoEncontradaException e) {
						System.out.println('\n' + e.getMessage());
					}

					break;

				case 2:
					double novoP = Console.readDouble("Digite o novo Preço da gasolina da viagem: ");
					umaViagem.setPreco(novoP);

					try {
						viagemAppService.altera(umaViagem);

						System.out.println('\n' + "Alteração de viagem efetuada com sucesso!");
					} catch (ViagemNaoEncontradaException e) {
						System.out.println('\n' + e.getMessage());
					}

					break;
				case 3:
					String d = Console.readLine("Digite o novo Destino: ");
					umaViagem.setDestino(d);

					try {
						viagemAppService.altera(umaViagem);

						System.out.println('\n' + "Alteração de viagem efetuada com sucesso!");
					} catch (ViagemNaoEncontradaException e) {
						System.out.println('\n' + e.getMessage());
					}

					break;
				default:
					System.out.println('\n' + "Opção inválida!");
				}

				break;
			}

			case 3: {
				int resposta = Console.readInt('\n' + "Digite o número da viagem que você deseja remover: ");

				try {
					umaViagem = viagemAppService.recuperaUmaViagem(resposta);
				} catch (ViagemNaoEncontradaException e) {
					System.out.println('\n' + e.getMessage());
					break;
				}

				System.out.println(
						'\n' + "Número = " + umaViagem.getId() + "    Preço = " + umaViagem.getPreco()
								+ "    Saída: = " + umaViagem.getSaida() + "  Destino: " + umaViagem.getDestino());

				String resp = Console.readLine('\n' + "Confirma a remoção da viagem?");

				if (resp.equals("s")) {
					try
					/* ==> */ {
						viagemAppService.exclui(umaViagem.getId());
						System.out.println('\n' + "Viagem removida com sucesso!");
					} catch (ViagemNaoEncontradaException e) {
						System.out.println(e.getMessage());
					}
				} else {
					System.out.println('\n' + "Viagem não removida.");
				}

				break;
			}

			case 4: {
				List<Viagem> arrayViagens = viagemAppService.recuperaViagens();

				if (arrayViagens.size() == 0) {
					System.out.println('\n' + "Nao há viagens cadastradas.");
					break;
				}

				System.out.println("");
				for (Viagem viagem : arrayViagens) {
					System.out.println(
							'\n' + "Número = " + viagem.getId() + "    Preço = " + viagem.getPreco()
									+ "    Saída: = " + viagem.getSaida() + "  Destino: " + viagem.getDestino());
				}

				break;
			}

			case 5: {
				continua = false;
				break;
			}

			default:
				System.out.println('\n' + "Opção inválida!");
			}
		}
	}
}
