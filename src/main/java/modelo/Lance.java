package modelo;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import util.Util;

@Entity
@Table(name = "lance")

public class Lance {
    private Long id;
    private double valor;
    private Calendar dataCriacao;

    // Um lance se refere a um único produto

    private Produto produto;

    // ********* Construtores *********

    public Lance() {
    }

    public Lance(double valor, Calendar dataCriacao, Produto produto) {
	this.valor = valor;
	this.dataCriacao = dataCriacao;
	this.produto = produto;
    }

    // ********* Métodos do Tipo Get *********

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    public Long getId() {
	return id;
    }

    @Column(nullable = false)
    public double getValor() {
	return valor;
    }

    @Column(name = "DATA_CRIACAO")
    @Temporal(value = TemporalType.DATE)
    public Calendar getDataCriacao() {
	return dataCriacao;
    }

    @Transient
    public String getDataCriacaoMasc() {
	return Util.calendarToStr(dataCriacao);
    }

    // ********* Métodos do Tipo Set *********

    @SuppressWarnings("unused")
    private void setId(Long id) {
	this.id = id;
    }

    public void setValor(double valor) {
	this.valor = valor;
    }

    public void setDataCriacao(Calendar dataCriacao) {
	this.dataCriacao = dataCriacao;
    }

    // ********* Métodos para Associações *********

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUTO_ID")
    public Produto getProduto() {
	return produto;
    }

    public void setProduto(Produto produto) {
	this.produto = produto;
    }
}