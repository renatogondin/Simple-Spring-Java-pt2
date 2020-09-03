package service;

import java.util.List;

import excecao.CarroNaoEncontradoException;
import excecao.ViagemNaoEncontradaException;
import modelo.Viagem;

public interface ViagemAppService {
    long inclui(Viagem umaViagem) throws CarroNaoEncontradoException;

    void altera(Viagem umaViagem) throws ViagemNaoEncontradaException;

    void exclui(long numero) throws ViagemNaoEncontradaException;

    
    Viagem recuperaUmaViagem(long numaero) throws ViagemNaoEncontradaException;

    List<Viagem> recuperaViagens();
}