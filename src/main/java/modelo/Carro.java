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
@Table(name = "carro")

public class Carro {
    private Long id;
    private String marca;
    private String modelo;
    private String placa;
    

    // Um Carro se refere a um só motorista

    private Motorista motorista;
    
    // Um Carro faz várias viagens
    private List<Viagem> viagens = new ArrayList<Viagem>();

    // ********* Construtores *********

    public Carro() {
    }

    public Carro(String marca, String modelo, String placa, Motorista motorista) {
	this.marca = marca;
	this.modelo = modelo;
	this.placa= placa;
	this.motorista = motorista;
    }

    // ********* Métodos do Tipo Get *********

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    public Long getId() {
	return id;
    }
    
    
    @Column(nullable = false, name = "marca")
    public String getMarca() {
	return marca;
    }

    @Column(name = "modelo")
    public String getModelo() {
	return modelo;
    }

    @Column(name = "placa")
    public String getPlaca() {
	return placa;
    }

    // ********* Métodos do Tipo Set *********

    @SuppressWarnings("unused")
    private void setId(Long id) {
	this.id = id;
    }
    
    public void setMarca(String marca) {
		this.marca = marca;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

    
    // ********* Métodos para Associações *********

    
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MOTORISTA_ID")
    public Motorista getMotorista() {
	return this.motorista;
    }

    public void setMotorista(Motorista motorista) {
	this.motorista = motorista;
    }
    
    @OneToMany(mappedBy = "carro")
    @OrderBy
    public List<Viagem> getViagens() {
	return this.viagens;
    }

    public void setViagens(List<Viagem> viagens) {
	this.viagens = viagens;
    }
}