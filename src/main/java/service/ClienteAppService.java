package service;

import java.util.List;

import excecao.ClienteNaoEncontradoException;
import modelo.Cliente;

public interface ClienteAppService {
    long inclui(Cliente umCliente);

    void altera(Cliente umCliente) throws ClienteNaoEncontradoException;

    void exclui(long numero) throws ClienteNaoEncontradoException;

    Cliente recuperaUmClienteEMotoristas(long numero) throws ClienteNaoEncontradoException;
    
    Cliente recuperaUmCliente(long numero) throws ClienteNaoEncontradoException;

    List<Cliente> recuperaClientesEMotoristas();
}