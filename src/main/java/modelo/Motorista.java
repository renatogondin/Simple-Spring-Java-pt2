package modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name = "motorista")

public class Motorista {
    private Long id;
    private String nome;
    private String sobrenome;
    private String email;
    

    // Um personagem se refere a um único jogador

    private Cliente cliente;
    
    // Um personagem tem vários Equipamentos
    private List<Carro> carros = new ArrayList<Carro>();

    // ********* Construtores *********

    public Motorista() {
    }

    public Motorista(String nome, String sobrenome, String email, Cliente cliente) {
	this.nome = nome;
	this.sobrenome = sobrenome;
	this.email = email;
	this.cliente = cliente;
    }

    // ********* Métodos do Tipo Get *********

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    public Long getId() {
	return id;
    }
    
    
    @Column(nullable = false, name = "nome")
    public String getNome() {
	return nome;
    }

    @Column(name = "sobrenome")
    public String getSobrenome() {
	return sobrenome;
    }

    @Column(name = "email")
    public String getEmail() {
	return email;
    }

    // ********* Métodos do Tipo Set *********

    @SuppressWarnings("unused")
    private void setId(Long id) {
	this.id = id;
    }
    
    public void setNome(String nome) {
		this.nome = nome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public void setEmail(String email) {
		this.email = email;
	}

    
    // ********* Métodos para Associações *********

    
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CLIENTE_ID")
    public Cliente getCliente() {
	return this.cliente;
    }

    public void setCliente(Cliente cliente) {
	this.cliente = cliente;
    }
    
    @OneToMany(mappedBy = "motorista")
    @OrderBy
    public List<Carro> getCarros() {
	return this.carros;
    }

    public void setCarros(List<Carro> carros) {
	this.carros = carros;
    }
}