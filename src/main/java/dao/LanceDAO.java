package dao;

import java.util.List;

import modelo.Lance;
import modelo.Produto;
import excecao.ObjetoNaoEncontradoException;

public interface LanceDAO {
    long inclui(Lance umLance);

    void exclui(long id) throws ObjetoNaoEncontradoException;

    Lance recuperaUmLance(long numero) throws ObjetoNaoEncontradoException;

    List<Lance> recuperaLances();

    Lance recuperaUltimoLance(Produto produto) throws ObjetoNaoEncontradoException;
}