package br.com.clienteEncomenda.service;

import br.com.clienteEncomenda.model.EncomendaModel;
import br.com.clienteEncomenda.repository.EncomendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EncomendaService {
    @Autowired
    EncomendaRepository repository;

    public EncomendaModel findById(long Id) throws Exception{
        return repository.findById(Id).orElseThrow(() -> new Exception("Not Found"));
    }

    public List<EncomendaModel> findAll(){
        return repository.findAll();
    }

    public EncomendaModel save(EncomendaModel encomenda){
        return repository.save(encomenda);
    }

    public EncomendaModel update(EncomendaModel encomenda) throws Exception{
        EncomendaModel e = findById(encomenda.getId());
        e.setEncomendaName(encomenda.getEncomendaName());
        e.setDescricaoEncomenda(encomenda.getDescricaoEncomenda());
        e.setQuantidade(encomenda.getQuantidade());
        return repository.save(e);
    }

    public void delete(long Id) throws Exception{
        EncomendaModel encomenda = findById(Id);
        repository.delete(encomenda);
    }
}
