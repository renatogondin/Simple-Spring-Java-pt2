package dao;

import java.util.List;

import modelo.Produto;
import excecao.ObjetoNaoEncontradoException;

public interface ProdutoDAO {
    long inclui(Produto umProduto);

    void altera(Produto umProduto) throws ObjetoNaoEncontradoException;

    void exclui(long id) throws ObjetoNaoEncontradoException;

    Produto recuperaUmProduto(long numero) throws ObjetoNaoEncontradoException;

    Produto recuperaUmProdutoComLock(long numero) throws ObjetoNaoEncontradoException;

    Produto recuperaUmProdutoELances(long numero) throws ObjetoNaoEncontradoException;

    List<Produto> recuperaProdutosELances();
}