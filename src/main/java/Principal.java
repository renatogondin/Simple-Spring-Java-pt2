
import corejava.Console;
//import excecao.NaoAutorizadoException;



public class Principal {
	
	
	
	public static void main(String[] args) throws Exception {

		

			boolean continua = true;
			while (continua) {
				System.out.println('\n' + "Selecione uma opção:");
				System.out.println('\n' + "1. Clientes");
				System.out.println("2. Motoristas");
				System.out.println("3. Carros");
				System.out.println("4. Viagens");
				System.out.println("5. Sair");

				int opcao = Console.readInt('\n' + "Digite um número entre 1 e 5:");

				switch (opcao) {
				case 1: {
					PrincipalCliente.main(null);
					break;
				}
				case 2: {
					PrincipalMotorista.main(null);
					break;
				}
				case 3: {
					PrincipalCarro.main(null);
					break;
				}
				case 4: {
					PrincipalViagem.main(null);
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


			
	
			
			
		
	





