package dao;

import java.util.List;

import modelo.Motorista;
import excecao.ObjetoNaoEncontradoException;

public interface MotoristaDAO {
    long inclui(Motorista umMotorista);

    void altera(Motorista umMotorista) throws ObjetoNaoEncontradoException;

    void exclui(long id) throws ObjetoNaoEncontradoException;

    Motorista recuperaUmMotorista(long numero) throws ObjetoNaoEncontradoException;

    Motorista recuperaUmMotoristaComLock(long numero) throws ObjetoNaoEncontradoException;

    Motorista recuperaUmMotoristaECarros(long numero) throws ObjetoNaoEncontradoException;

    List<Motorista> recuperaMotoristasECarros();
}