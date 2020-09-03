package servico.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import dao.CarroDAO;
import dao.MotoristaDAO;
import excecao.CarroJaTemViagensException;
import excecao.CarroNaoEncontradoException;
import excecao.ObjetoNaoEncontradoException;
import excecao.MotoristaNaoEncontradoException;
import modelo.Carro;
import modelo.Motorista;
import service.CarroAppService;

public class CarroAppServiceImpl implements CarroAppService {
	@Autowired
	private CarroDAO carroDAO;
	@Autowired
	private MotoristaDAO motoristaDAO;

	@Transactional
	public long inclui(Carro umCarro) throws MotoristaNaoEncontradoException {

		try {
			// NENHUMA VALIDAÇÃO ESTÁ SENDO REALIZADA AQUI!!!

			Motorista umMotorista = umCarro.getMotorista();

			try {
				motoristaDAO.recuperaUmMotoristaComLock(umMotorista.getId());
			} catch (ObjetoNaoEncontradoException e) {
				throw new MotoristaNaoEncontradoException("Personagem não encontado");
			}

			long numero = carroDAO.inclui(umCarro);

			return numero;
		} catch (MotoristaNaoEncontradoException e) {

			throw e;
		}
	}

	@Transactional
	public void altera(Carro umCarro) throws CarroNaoEncontradoException {
		try {
			carroDAO.altera(umCarro);

		} catch (ObjetoNaoEncontradoException e) {

			throw new CarroNaoEncontradoException("Carro não encontrado");
		}
	}

	@Transactional
	public void exclui(long numero) throws CarroNaoEncontradoException {
		try {
			Carro carro = carroDAO.recuperaUmCarroEViagens(numero);
			
			if(carro.getViagens().size() > 0)
			{	throw new CarroJaTemViagensException("Este carro possui viagens e não pode ser removido!");
			}
			carroDAO.exclui(numero);
		} catch (ObjetoNaoEncontradoException e) {
			throw new CarroNaoEncontradoException("Carro não encontrado");
		}
	}

	public Carro recuperaUmCarro(long numero) throws CarroNaoEncontradoException {
		try {
			Carro umCarro = carroDAO.recuperaUmCarro(numero);

			return umCarro;
		} catch (ObjetoNaoEncontradoException e) {
			throw new CarroNaoEncontradoException("Carro não encontrado");
		}
	}

	public Carro recuperaUmCarroEViagens(long numero) throws CarroNaoEncontradoException {
		try {
			return carroDAO.recuperaUmCarroEViagens(numero);
		} catch (ObjetoNaoEncontradoException e) {
			throw new CarroNaoEncontradoException("Carro não encontrado");
		}
	}

	public List<Carro> recuperaCarrosEViagens() {

		return carroDAO.recuperaCarrosEViagens();
	}
}