package br.com.clienteEncomenda.model;


import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import javax.persistence.*;
import java.io.Serializable;

@Relation(collectionRelation = "encomendas")
@Entity
@Table(name="encomenda")
public class EncomendaModel extends RepresentationModel<EncomendaModel> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private ClienteModel cliente;

    @Column(name = "encomendaName", nullable = false, length = 50)
    private String encomendaName;

    @Column(name = "descricaoEncomenda", nullable = false, length = 50)
    private String descricaoEncomenda;

    @Column(name = "quantidade", nullable = false, length = 50)
    private String quantidade;

    public EncomendaModel() {
    }

    public EncomendaModel(long id, String encomendaName, String descricaoEncomenda, String quantidade) {
        this.id = id;
        this.encomendaName = encomendaName;
        this.descricaoEncomenda = descricaoEncomenda;
        this.quantidade = quantidade;
        this.cliente = cliente;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ClienteModel getCliente() {
        return cliente;
    }

    public void setCliente(ClienteModel cliente) {
        this.cliente = cliente;
    }

    public String getEncomendaName() {
        return encomendaName;
    }

    public void setEncomendaName(String encomendaName) {
        this.encomendaName = encomendaName;
    }

    public String getDescricaoEncomenda() {
        return descricaoEncomenda;
    }

    public void setDescricaoEncomenda(String descricaoEncomenda) {
        this.descricaoEncomenda = descricaoEncomenda;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    public ClienteModel getClienteName(){return cliente;}

    public void setClienteName(ClienteModel cliente){this.cliente = cliente;}

}
