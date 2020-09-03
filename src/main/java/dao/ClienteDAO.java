package dao;

import java.util.List;

import modelo.Cliente;
import excecao.ObjetoNaoEncontradoException;

public interface ClienteDAO {
    long inclui(Cliente umCliente);

    void altera(Cliente umCliente) throws ObjetoNaoEncontradoException;

    void exclui(long id) throws ObjetoNaoEncontradoException;

    Cliente recuperaUmCliente(long numero) throws ObjetoNaoEncontradoException;

    Cliente recuperaUmClienteComLock(long numero) throws ObjetoNaoEncontradoException;

    Cliente recuperaUmClienteEMotoristas(long numero) throws ObjetoNaoEncontradoException;

    List<Cliente> recuperaClientesEMotoristas();
}