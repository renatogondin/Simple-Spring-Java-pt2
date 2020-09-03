package modelo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import util.Util;

@Entity
@Table(name = "produto")

public class Produto {
    private Long id;
    private String nome;
    private String descricao;
    private double lanceMinimo;
    private Calendar dataCadastro;
    private Calendar dataVenda;

    // Um produto possui lances

    private List<Lance> lances = new ArrayList<Lance>();

    // ********* Construtores *********

    public Produto() {
    }

    public Produto(String nome, String descricao, double lanceMinimo, Calendar dataCadastro) {
	this.nome = nome;
	this.descricao = descricao;
	this.lanceMinimo = lanceMinimo;
	this.dataCadastro = dataCadastro;
    }

    // ********* Métodos do Tipo Get *********

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    public Long getId() {
	return id;
    }

    public String getNome() {
	return nome;
    }

    public String getDescricao() {
	return descricao;
    }

    @Column(name = "LANCE_MINIMO")
    public double getLanceMinimo() {
	return lanceMinimo;
    }

    @Transient
    public String getLanceMinimoMasc() {
	return Util.doubleToStr(lanceMinimo);
    }

    @Column(name = "DATA_CADASTRO")
    public Calendar getDataCadastro() {
	return dataCadastro;
    }

    @Transient
    public String getDataCadastroMasc() {
	return Util.calendarToStr(dataCadastro);
    }

    @Column(name = "DATA_VENDA")
    public Calendar getDataVenda() {
	return dataVenda;
    }

    @Transient
    public String getDataVendaMasc() {
	return Util.calendarToStr(dataVenda);
    }

    // ********* Métodos do Tipo Set *********

    public void setId(Long id) {
	this.id = id;
    }

    public void setNome(String nome) {
	this.nome = nome;
    }

    public void setDescricao(String descricao) {
	this.descricao = descricao;
    }

    public void setLanceMinimo(double lanceMinimo) {
	this.lanceMinimo = lanceMinimo;
    }

    public void setDataCadastro(Calendar dataCadastro) {
	this.dataCadastro = dataCadastro;
    }

    public void setDataVenda(Calendar dataVenda) {
	this.dataVenda = dataVenda;
    }

    // ********* Métodos para Associações *********

    /*
     * Com o atributo mappedBy. Sem ele a JPA irá procurar pela tabela
     * PRODUTO_LANCE. Com ele, ao se tentar recuperar um produto e todos os seus
     * lances, o join de PRODUTO e LANCE irá acontecer através da chave estrangeira
     * existente em LANCE. Sem ele a JPA irá procurar pela tabela PRODUTO_LANCE.
     */
    @OneToMany(mappedBy = "produto")
    @OrderBy
    public List<Lance> getLances() {
	return lances;
    }

    public void setLances(List<Lance> lances) {
	this.lances = lances;
    }
}
