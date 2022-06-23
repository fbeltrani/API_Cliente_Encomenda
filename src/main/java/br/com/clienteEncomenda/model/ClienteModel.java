package br.com.clienteEncomenda.model;


import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import javax.persistence.*;
import java.io.Serializable;

@Relation(collectionRelation = "clientes")
@Entity
@Table(name="cliente")
public class ClienteModel extends RepresentationModel<ClienteModel> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "clienteName", nullable = false, length = 50)
    private String clienteName;

    @Column(name = "clienteAge", nullable = false, length = 50)
    private String clienteAge;

    public ClienteModel() {
    }

    public ClienteModel(long id, String clienteName, String clienteAge) {
        this.id = id;
        this.clienteName = clienteName;
        this.clienteAge = clienteAge;
    }

    public long getId() {return id;}

    public void setId(long id) {this.id = id;}

    public String getClienteName() {return clienteName;}

    public void setClienteName(String clienteName) {this.clienteName = clienteName;}

    public String getClienteAge() {return clienteAge;}

    public void setClienteAge(String clienteAge) {this.clienteAge = clienteAge;}
}
