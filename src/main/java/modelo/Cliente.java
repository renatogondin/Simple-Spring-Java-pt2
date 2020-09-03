package modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;


@Entity
@Table(name = "cliente")

public class Cliente {
    private Long id;
    private String nome;
    private String sobrenome;
    private String email;

    // Um jogador possui muitos personagens

    private List<Motorista> motoristas = new ArrayList<Motorista>();

    // ********* Construtores *********

    public Cliente() {
    }

    public Cliente(String nome, String sobrenome, String email) {
	this.nome=nome;
	this.sobrenome=sobrenome;
	this.email=email;

    }

    // ********* Métodos do Tipo Get *********

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    public Long getId() {
	return id;
    }

    @Column(name= "nome")
    public String getNome() {
	return nome;
    }

    @Column(name= "sobrenome")
    public String getSobrenome() {
	return sobrenome;
    }
    
    @Column(name= "email")
    public String getEmail() {
		return email;
	}

    // ********* Métodos do Tipo Set *********

    @SuppressWarnings("unused")
	public void setId(Long id) {
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
	/*
     * Com o atributo mappedBy. Sem ele a JPA irá procurar pela tabela
     * PRODUTO_LANCE. Com ele, ao se tentar recuperar um produto e todos os seus
     * lances, o join de PRODUTO e LANCE irá acontecer através da chave estrangeira
     * existente em LANCE. Sem ele a JPA irá procurar pela tabela PRODUTO_LANCE.
     */
    @OneToMany(mappedBy = "cliente")
    @OrderBy
    public List<Motorista> getMotoristas() {
	return this.motoristas;
    }

    public void setMotoristas(List<Motorista> motoristas) {
	this.motoristas = motoristas;
    }
}
