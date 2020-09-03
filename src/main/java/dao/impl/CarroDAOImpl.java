package dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import dao.CarroDAO;
import excecao.ObjetoNaoEncontradoException;
import modelo.Carro;

@Repository
public class CarroDAOImpl implements CarroDAO {
	@PersistenceContext
	protected EntityManager em;

	public long inclui(Carro umCarro) {
		em.persist(umCarro);

		return umCarro.getId();

	}

	public void altera(Carro umCarro) throws ObjetoNaoEncontradoException {
		Carro carro = em.find(Carro.class, umCarro.getId(), LockModeType.PESSIMISTIC_WRITE);

		if (carro== null) {
			throw new ObjetoNaoEncontradoException();
		}

		em.merge(umCarro);

	}

	public void exclui(long id) throws ObjetoNaoEncontradoException {
		Carro carro = em.find(Carro.class, id, LockModeType.PESSIMISTIC_WRITE);

		if (carro == null) {
			throw new ObjetoNaoEncontradoException();
		}

		em.remove(carro);
	}

	public Carro recuperaUmCarro(long id) throws ObjetoNaoEncontradoException {
		Carro umCarro = (Carro) em.find(Carro.class, new Long(id));

		if (umCarro == null) {
			throw new ObjetoNaoEncontradoException();
		}

		return umCarro;

	}

	public Carro recuperaUmCarroComLock(long id) throws ObjetoNaoEncontradoException {
		Carro um = em.find(Carro.class, id, LockModeType.PESSIMISTIC_WRITE);

		if (um == null) {
			throw new ObjetoNaoEncontradoException();
		}

		return um;
	}

	public Carro recuperaUmCarroEViagens(long numero) throws ObjetoNaoEncontradoException {
		try {
			String busca = "select c from Carro c left outer join fetch c.viagens where c.id = :id";

			Carro umCarro = (Carro) em.createQuery(busca).setParameter("id", numero)
					.getSingleResult();

			return umCarro;
		} catch (NoResultException e) {
			throw new ObjetoNaoEncontradoException();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Carro> recuperaCarrosEViagens() {
		List<Carro> carros = em
				.createQuery("select distinct c from Carro c left outer join fetch c.viagens")
				.getResultList();

		return carros;
	}

	@SuppressWarnings("unchecked")
	public List<Carro> recuperaCarro() {
		return em.createQuery("select c from Carro c order by c.id").getResultList();
	}

}
