package dao;

import java.util.List;

import excecao.ObjetoNaoEncontradoException;
import modelo.Viagem;

public interface ViagemDAO {
    long inclui(Viagem umaViagem);

    void exclui(long id) throws ObjetoNaoEncontradoException;

    Viagem recuperaUmaViagem(long numero) throws ObjetoNaoEncontradoException;

    List<Viagem> recuperaViagens();
    
    void altera(Viagem umaViagem) throws ObjetoNaoEncontradoException;

}