package service;

import java.util.List;

import excecao.ClienteNaoEncontradoException;
import excecao.MotoristaNaoEncontradoException;
import modelo.Motorista;

public interface MotoristaAppService {
    long inclui(Motorista umMotorista) throws ClienteNaoEncontradoException;

    void altera(Motorista umMotorista) throws MotoristaNaoEncontradoException;

    void exclui(long numero) throws MotoristaNaoEncontradoException;

    Motorista recuperaUmMotoristaECarros(long numero) throws MotoristaNaoEncontradoException;
    
    Motorista recuperaUmMotorista(long numero) throws MotoristaNaoEncontradoException;

    List<Motorista> recuperaMotoristasECarros();
}