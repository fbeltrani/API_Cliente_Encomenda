package br.com.clienteEncomenda.controller;


import br.com.clienteEncomenda.model.EncomendaModel;
import br.com.clienteEncomenda.service.EncomendaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "This service manipulates the Encomenda resource", tags = {"encomenda, api, service,"})
@RestController
@RequestMapping("/encomenda/v1")
public class EncomendaController {
    @Autowired
    private EncomendaService service;

    @ApiOperation(value = "Get all registered encomenda.")
    @GetMapping(produces = {"application/json", "application/xml", "application/x-yaml"})
    public List<EncomendaModel> findAll(){
        return service.findAll();
    }

    @ApiOperation(value = "Find a encomenda by id.", response = EncomendaModel.class)
    @GetMapping(value = "/{id}", produces = {"application/json", "application/xml", "application/x-yaml"})
    public EncomendaModel findById(@PathVariable("id") long Id) throws Exception{
        return service.findById(Id);
    }

    @ApiOperation(value = "Store a newly Encomenda", consumes = "application/json, application/xml", produces = "application/json, application/xml")
    @PostMapping(consumes = {"application/json", "application/xml", "application/x-yaml"}, produces = {"application/json", "application/xml", "application/x-yaml"})
    public EncomendaModel save(@RequestBody EncomendaModel encomenda){
        return service.save(encomenda);
    }

    @ApiOperation(value = "Update a encomenda by ID")
    @PutMapping(consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
    public EncomendaModel update(@RequestBody EncomendaModel encomenda) throws Exception{
        return service.update(encomenda);
    }

    @ApiOperation(value = "Delete a encomenda by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")long Id) throws Exception{
        service.delete(Id);
        return ResponseEntity.ok().build();
    }
}
