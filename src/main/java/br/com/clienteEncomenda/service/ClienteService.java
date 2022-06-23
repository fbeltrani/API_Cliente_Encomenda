package br.com.clienteEncomenda.service;

import br.com.clienteEncomenda.model.ClienteModel;
import br.com.clienteEncomenda.model.EncomendaModel;
import br.com.clienteEncomenda.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    @Autowired
    ClienteRepository repository;

    public ClienteModel findById(long Id) throws Exception{
        return repository.findById(Id).orElseThrow(() -> new Exception("Not Found"));
    }
    public Page<ClienteModel> findAll(Pageable pageable){

        return repository.findAll(pageable);
    }

    public ClienteModel save(ClienteModel cliente){
        return repository.save(cliente);
    }

    public ClienteModel update(ClienteModel hero) throws Exception{
        ClienteModel c = findById(hero.getId());
        c.setClienteName(hero.getClienteName());
        c.setClienteAge(hero.getClienteAge());
        return repository.save(c);
    }

    public void delete(long Id) throws Exception{
        ClienteModel cliente = findById(Id);
        repository.delete(cliente);
    }
}
