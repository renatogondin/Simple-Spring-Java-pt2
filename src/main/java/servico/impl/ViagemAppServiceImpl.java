package servico.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.CarroDAO;
import dao.ViagemDAO;
import excecao.CarroNaoEncontradoException;
import excecao.ViagemNaoEncontradaException;
import excecao.ObjetoNaoEncontradoException;
import modelo.Carro;
import modelo.Viagem;
import service.ViagemAppService;

//@Service
public class ViagemAppServiceImpl implements ViagemAppService {
	@Autowired
	private ViagemDAO viagemDAO;
	@Autowired
	private CarroDAO carroDAO;

	@Transactional
	public long inclui(Viagem umaViagem) throws CarroNaoEncontradoException {

		try {
			// NENHUMA VALIDAÇÃO ESTÁ SENDO REALIZADA AQUI!!!
			Carro umCarro = umaViagem.getCarro();

			try {
				carroDAO.recuperaUmCarroComLock(umCarro.getId());
			} catch (ObjetoNaoEncontradoException e) {
				throw new CarroNaoEncontradoException("Carro não encontado");
			}

			long numero = viagemDAO.inclui(umaViagem);
			return numero;
		} catch (CarroNaoEncontradoException e) {

			throw e;
		}
	}
	@Transactional
	public void exclui(long numero) throws ViagemNaoEncontradaException {
		try {

			viagemDAO.exclui(numero);

		} catch (ObjetoNaoEncontradoException e) {

			throw new ViagemNaoEncontradaException("Viagem não encontrada");
		}
	}

	@Transactional
	public void altera(Viagem umaViagem) throws ViagemNaoEncontradaException {
		try {
			viagemDAO.altera(umaViagem);
		} catch (ObjetoNaoEncontradoException e) {
			throw new ViagemNaoEncontradaException("Viagem não encontrada");
		}
	}

	public Viagem recuperaUmaViagem(long numero) throws ViagemNaoEncontradaException {
		try {
			Viagem umaViagem = viagemDAO.recuperaUmaViagem(numero);

			return umaViagem;
		} catch (ObjetoNaoEncontradoException e) {
			throw new ViagemNaoEncontradaException("Viagem não encontrada!");
		}
	}

	public List<Viagem> recuperaViagens() {

		return viagemDAO.recuperaViagens();

	}
}