package servico.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import dao.ClienteDAO;
import excecao.ClienteJaTemMotoristasException;
import excecao.ClienteNaoEncontradoException;
import excecao.ObjetoNaoEncontradoException;
import modelo.Cliente;
import service.ClienteAppService;

public class ClienteAppServiceImpl implements ClienteAppService{
	@Autowired
	private ClienteDAO clienteDAO ;

	@Transactional
	public long inclui(Cliente umCliente) {

		// NENHUMA VALIDAÇÃO ESTÁ SENDO REALIZADA AQUI!!!
		long numero = clienteDAO.inclui(umCliente);
		return numero;

	}

	@Transactional
	public void altera(Cliente umCliente) throws ClienteNaoEncontradoException {
		try {

			clienteDAO.altera(umCliente);

		} catch (ObjetoNaoEncontradoException e) {

			throw new ClienteNaoEncontradoException("Cliente não encontrado");
		}
	}

	@Transactional
	public void exclui(long numero) throws ClienteNaoEncontradoException {
		try {
			Cliente cliente = clienteDAO.recuperaUmClienteEMotoristas(numero);
			
			if(cliente.getMotoristas().size() > 0)
			{	throw new ClienteJaTemMotoristasException("Este cliente possui motoristas e não pode ser removido!");
			}

			clienteDAO.exclui(numero);

		} catch (ObjetoNaoEncontradoException e) {

			throw new ClienteNaoEncontradoException("Cliente não encontrado");
		}
	}

	public Cliente recuperaUmCliente(long numero) throws ClienteNaoEncontradoException {
		try {
			Cliente umCliente = clienteDAO.recuperaUmCliente(numero);

			return umCliente;
		} catch (ObjetoNaoEncontradoException e) {
			throw new ClienteNaoEncontradoException("Cliente não encontrado");
		} 
	}

	public Cliente recuperaUmClienteEMotoristas(long numero) throws ClienteNaoEncontradoException {
		try {
			return clienteDAO.recuperaUmClienteEMotoristas(numero);
		} catch (ObjetoNaoEncontradoException e) {
			throw new ClienteNaoEncontradoException("Cliente não encontrado");
		} 
	}

	public List<Cliente> recuperaClientesEMotoristas() {
		
			return clienteDAO.recuperaClientesEMotoristas();
		
	}
}