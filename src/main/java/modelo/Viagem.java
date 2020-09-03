package modelo;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "viagem")

public class Viagem {
	long id;
   String saida;
   String destino;
   double preco;

    // Uma habilidade se refere a um unico equipamento

    private Carro carro;

    // ********* Construtores *********

    public Viagem() {
    }

    public Viagem(double preco, String saida,String destino, Carro carro) {
	this.preco = preco;
	this.destino = destino;
	this.saida = saida;
	this.carro = carro;
    }

    // ********* Métodos do Tipo Get *********

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    public Long getId() {
	return id;
    }

    @Column(nullable = false, name="preco")
    public double getPreco() {
	return preco;
    }

    @Column(name = "destino")
    public String getDestino() {
	return destino;
    }
    @Column(name = "saida")
    public String getSaida() {
	return saida;
    }

    

    // ********* Métodos do Tipo Set *********

    @SuppressWarnings("unused")
    private void setId(Long id) {
	this.id = id;
    }

    public void setPreco(double preco) {
	this.preco = preco;
    }

    public void setDestino(String destino) {
	this.destino = destino;
    }
	public void setSaida(String saida) {
		this.saida = saida;
	}

    // ********* Métodos para Associações *********


	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CARRO_ID")
    public Carro getCarro() {
	return carro;
    }

    public void setCarro(Carro carro) {
	this.carro = carro;
    }
}