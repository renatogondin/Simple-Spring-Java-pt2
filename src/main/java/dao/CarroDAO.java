package dao;

import java.util.List;

import modelo.Carro;
import excecao.ObjetoNaoEncontradoException;

public interface CarroDAO {
    long inclui(Carro umCarro);

    void altera(Carro umCarro) throws ObjetoNaoEncontradoException;

    void exclui(long id) throws ObjetoNaoEncontradoException;

    Carro recuperaUmCarro(long numero) throws ObjetoNaoEncontradoException;

    Carro recuperaUmCarroComLock(long numero) throws ObjetoNaoEncontradoException;

    Carro recuperaUmCarroEViagens(long numero) throws ObjetoNaoEncontradoException;

    List<Carro> recuperaCarrosEViagens();
}