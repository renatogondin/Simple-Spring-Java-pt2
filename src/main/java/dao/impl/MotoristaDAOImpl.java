package dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import dao.MotoristaDAO;
import excecao.ObjetoNaoEncontradoException;
import modelo.Motorista;

@Repository
public class MotoristaDAOImpl implements MotoristaDAO {
	@PersistenceContext
	protected EntityManager em;

	public long inclui(Motorista umMotorista) {

		em.persist(umMotorista);

		return umMotorista.getId();
	}

	public void altera(Motorista umMotorista) throws ObjetoNaoEncontradoException {
			Motorista motorista = em.find(Motorista.class, umMotorista.getId(), LockModeType.PESSIMISTIC_WRITE);

			if (motorista == null) {
				throw new ObjetoNaoEncontradoException();
			}
			em.merge(umMotorista);
		
	}

	public void exclui(long id) throws ObjetoNaoEncontradoException {
		
			Motorista motorista = em.find(Motorista.class, id, LockModeType.PESSIMISTIC_WRITE);

			if (motorista == null) {
				throw new ObjetoNaoEncontradoException();
			}

			em.remove(motorista);
		
	}

	public Motorista recuperaUmMotorista(long id) throws ObjetoNaoEncontradoException {
		
			Motorista umMotorista = (Motorista) em.find(Motorista.class, new Long(id));

			if (umMotorista == null) {
				throw new ObjetoNaoEncontradoException();
			}

			return umMotorista;
		
	}

	public Motorista recuperaUmMotoristaComLock(long id) throws ObjetoNaoEncontradoException {
		
			Motorista um = em.find(Motorista.class, id, LockModeType.PESSIMISTIC_WRITE);

			if (um == null) {
				throw new ObjetoNaoEncontradoException();
			}

			return um;
		
	}

	public Motorista recuperaUmMotoristaECarros(long numero) throws ObjetoNaoEncontradoException {
		try {
			String busca = "select m from Motorista m left outer join fetch m.carros where m.id = :id";

			Motorista umMotorista = (Motorista) em.createQuery(busca).setParameter("id", numero).getSingleResult();

			return umMotorista;
		} catch (NoResultException e) {
			throw new ObjetoNaoEncontradoException();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Motorista> recuperaMotoristasECarros() {
		List<Motorista> motoristas = em
				.createQuery("select distinct m from Motorista m left outer join fetch m.carros")
				.getResultList();

		return motoristas;
	}

	@SuppressWarnings("unchecked")
	public List<Motorista> recuperaMotorista() {
		return em.createQuery("select m from Motorista m order by m.id").getResultList();
	}

}
