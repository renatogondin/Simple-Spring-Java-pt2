package dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import dao.ViagemDAO;
import excecao.ObjetoNaoEncontradoException;
import modelo.Viagem;

@Repository
public class ViagemDAOImpl implements ViagemDAO {
	@PersistenceContext
	protected EntityManager em;

	public long inclui(Viagem umaViagem) {
		em.persist(umaViagem);

		return umaViagem.getId();
	}

	public void exclui(long id) throws ObjetoNaoEncontradoException {
		Viagem viagem = em.find(Viagem.class, id, LockModeType.PESSIMISTIC_WRITE);

		if (viagem == null) {
			throw new ObjetoNaoEncontradoException();
		}

		em.remove(viagem);
	}

	public void altera(Viagem umaViagem) throws ObjetoNaoEncontradoException {

		Viagem viagem = em.find(Viagem.class, umaViagem.getId(), LockModeType.PESSIMISTIC_WRITE);
		if (viagem == null) {
			throw new ObjetoNaoEncontradoException();
		}

		em.merge(umaViagem);

	}

	public Viagem recuperaUmaViagem(long id) throws ObjetoNaoEncontradoException {

		
		Viagem umaViagem = (Viagem) em.find(Viagem.class, new Long(id));

		if (umaViagem == null) {
			throw new ObjetoNaoEncontradoException();
		}

		return umaViagem;

	}

	@SuppressWarnings("unchecked")
	public List<Viagem> recuperaViagens() {
		return em.createQuery("select v from Viagem v order by v.id").getResultList();
	}

}
