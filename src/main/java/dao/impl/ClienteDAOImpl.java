package dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import dao.ClienteDAO;
import excecao.ObjetoNaoEncontradoException;
import modelo.Cliente;

@Repository
public class ClienteDAOImpl implements ClienteDAO {
	@PersistenceContext
	protected EntityManager em;

	public long inclui(Cliente umCliente) {

		em.persist(umCliente);

		return umCliente.getId();

	}

	public void altera(Cliente umCliente) throws ObjetoNaoEncontradoException {

		Cliente cliente = em.find(Cliente.class, umCliente.getId(), LockModeType.PESSIMISTIC_WRITE);

		if (cliente == null) {
			throw new ObjetoNaoEncontradoException();
		}

		em.merge(umCliente);

	}

	public void exclui(long id) throws ObjetoNaoEncontradoException {

		Cliente cliente = em.find(Cliente.class, id, LockModeType.PESSIMISTIC_WRITE);

		if (cliente == null) {
			throw new ObjetoNaoEncontradoException();
		}

		em.remove(cliente);

	}

	public Cliente recuperaUmCliente(long id) throws ObjetoNaoEncontradoException {

		Cliente umCliente = (Cliente) em.find(Cliente.class, new Long(id));

		if (umCliente == null) {
			throw new ObjetoNaoEncontradoException();
		}

		return umCliente;

	}

	public Cliente recuperaUmClienteComLock(long id) throws ObjetoNaoEncontradoException {

		Cliente umCliente = em.find(Cliente.class, id, LockModeType.PESSIMISTIC_WRITE);

		if (umCliente == null) {
			throw new ObjetoNaoEncontradoException();
		}

		return umCliente;

	}

	@SuppressWarnings("unchecked")
	public List<Cliente> recuperaClientes() {

		List<Cliente> clientes = em.createQuery("select c from banco.cliente c " + "order by c.id asc")
				.getResultList();

		return clientes;

	}

	public Cliente recuperaUmClienteEMotoristas(long numero) throws ObjetoNaoEncontradoException {

		String busca = "select c from Cliente c left outer join fetch c.motoristas where c.id = :id";

		Cliente umCliente = (Cliente) em.createQuery(busca).setParameter("id", numero).getSingleResult();

		// A busca retorna um único Jogador (SingleResult()).

		/*
		 * Em função do método getSingleResult() será propagada a exceção
		 * NoResultException caso nenhum Jogador seja encontrado.
		 */

		return umCliente;

	}

	@SuppressWarnings("unchecked")
	public List<Cliente> recuperaClientesEMotoristas() {

		List<Cliente> clientes= em.createQuery("select distinct c from Cliente c left outer join fetch c.motoristas")
				.getResultList();

		return clientes;
	}
}