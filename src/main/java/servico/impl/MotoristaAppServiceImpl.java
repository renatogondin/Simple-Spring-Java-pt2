package servico.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import dao.ClienteDAO;
import dao.MotoristaDAO;
import excecao.ClienteNaoEncontradoException;
import excecao.MotoristaJaTemCarrosException;
import excecao.ObjetoNaoEncontradoException;
import excecao.MotoristaNaoEncontradoException;
import modelo.Cliente;
import modelo.Motorista;
import service.MotoristaAppService;

public class MotoristaAppServiceImpl implements MotoristaAppService {
	@Autowired
	private MotoristaDAO motoristaDAO;
	@Autowired
	private ClienteDAO clienteDAO ;

	@Transactional
	public long inclui(Motorista umMotorista) throws ClienteNaoEncontradoException {

		try {
			// NENHUMA VALIDAÇÃO ESTÁ SENDO REALIZADA AQUI!!!

			Cliente umCliente = umMotorista.getCliente();

			try {
				clienteDAO.recuperaUmClienteComLock(umCliente.getId());
			} catch (ObjetoNaoEncontradoException e) {
				throw new ClienteNaoEncontradoException("Cliente não encontado");
			}

			long numero = motoristaDAO.inclui(umMotorista);

			return numero;
		} catch (ClienteNaoEncontradoException e) {

			throw e;
		}
	}

	@Transactional
	public void altera(Motorista umMotorista) throws MotoristaNaoEncontradoException {
		try {

			motoristaDAO.altera(umMotorista);

		} catch (ObjetoNaoEncontradoException e) {

			throw new MotoristaNaoEncontradoException("Motorista não encontrado");
		}
	}

	@Transactional
	public void exclui(long numero) throws MotoristaNaoEncontradoException {
		try {
			Motorista motorista = motoristaDAO.recuperaUmMotoristaECarros(numero);
			
			if(motorista.getCarros().size() > 0)
			{	throw new MotoristaJaTemCarrosException("Este motorista possui carros e não pode ser removido!");
			}

			motoristaDAO.exclui(numero);

		} catch (ObjetoNaoEncontradoException e) {
			throw new MotoristaNaoEncontradoException("Motorista não encontrado");
		}
	}

	public Motorista recuperaUmMotorista(long numero) throws MotoristaNaoEncontradoException {
		try {
			Motorista umMotorista = motoristaDAO.recuperaUmMotorista(numero);

			return umMotorista;
		} catch (ObjetoNaoEncontradoException e) {
			throw new MotoristaNaoEncontradoException("Motorista não encontrado");
		} 
	}

	public Motorista recuperaUmMotoristaECarros(long numero) throws MotoristaNaoEncontradoException {
		try {
			return motoristaDAO.recuperaUmMotoristaECarros(numero);
		} catch (ObjetoNaoEncontradoException e) {
			throw new MotoristaNaoEncontradoException("Motorista não encontrado");
		} 
	}

	public List<Motorista> recuperaMotoristasECarros() {
		
			return motoristaDAO.recuperaMotoristasECarros();
		 
	}
}