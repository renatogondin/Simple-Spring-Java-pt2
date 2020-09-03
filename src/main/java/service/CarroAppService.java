package service;

import java.util.List;

import excecao.CarroNaoEncontradoException;
import excecao.MotoristaNaoEncontradoException;
import modelo.Carro;

public interface CarroAppService {
    long inclui(Carro umCarro) throws MotoristaNaoEncontradoException;

    void altera(Carro umCarro) throws CarroNaoEncontradoException;

    void exclui(long numero) throws CarroNaoEncontradoException;

    Carro recuperaUmCarroEViagens(long numero) throws CarroNaoEncontradoException;
    
    Carro recuperaUmCarro(long numero) throws CarroNaoEncontradoException;

    List<Carro> recuperaCarrosEViagens();
}