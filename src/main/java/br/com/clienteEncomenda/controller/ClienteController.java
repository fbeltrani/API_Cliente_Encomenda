package br.com.clienteEncomenda.controller;


import br.com.clienteEncomenda.model.ClienteModel;
import br.com.clienteEncomenda.model.EncomendaModel;
import br.com.clienteEncomenda.service.ClienteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.*;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "This service manipulates the Cliente resource", tags = {"cliente, api, service,"})
@RestController
@RequestMapping("/cliente/v1")
public class ClienteController {

    private void buildEntityLink(ClienteModel category) throws Exception {
        category.add(
                WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(
                                ClienteController.class).findById(category.getId())
                ).withSelfRel());
    }

    private void buildEntityLink (EncomendaModel encomenda) throws Exception {
        //..add a self link
        encomenda.add(WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(
                        EncomendaController.class).findById(encomenda.getId())
        ).withSelfRel());//.. add the link of relatioships
        if(!encomenda.getClienteName().hasLinks()) {
            Link encomendaLink= WebMvcLinkBuilder.linkTo(
                    WebMvcLinkBuilder.methodOn(
                            EncomendaController.class).findById(encomenda.getClienteName().getId())).withSelfRel();
            encomenda.getClienteName().add(encomendaLink);}
    }

        @Autowired
        private ClienteService service;

        @ApiOperation(value = "Get all registered clientes.")
        @GetMapping(value = "/", produces = {"application/json", "application/xml", "application/x-yaml"})
        public ResponseEntity<PagedModel<ClienteModel>> findAll (@RequestParam(value = "page", defaultValue = "0") int page,
                                                                 @RequestParam(value = "size", defaultValue = "10") int size,
                                                                 @RequestParam(value = "direction", defaultValue = "asc") String direction,
                                                                 PagedResourcesAssembler<ClienteModel> assembler) throws Exception {


            var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC: Sort.Direction.ASC;

            Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "clienteName"));

            Page<ClienteModel> clientes = service.findAll(pageable);
            for (ClienteModel clienteModel : clientes) {
                buildEntityLink(clienteModel);
            }
            return new ResponseEntity(assembler.toModel(clientes), HttpStatus.OK);
        }

        @ApiOperation(value = "Find a cliente by id.", response = ClienteModel.class)
        @GetMapping(value = "/{id}", produces = {"application/json", "application/xml", "application/x-yaml"})
        public ClienteModel findById (@PathVariable("id") long Id) throws Exception {
            ClienteModel clienteModel = service.findById(Id);
            buildEntityLink(clienteModel);
            return clienteModel;
        }

        @ApiOperation(value = "Store a newly Cliente", consumes = "application/json, application/xml", produces = "application/json, application/xml")
        @PostMapping(consumes = {"application/json", "application/xml", "application/x-yaml"}, produces = {"application/json", "application/xml", "application/x-yaml"})
        public ClienteModel save (@RequestBody ClienteModel clienteModel){
            return service.save(clienteModel);
        }

        @ApiOperation(value = "Update a Cliente by ID")
        @PutMapping(consumes = {"application/json", "application/xml", "application/x-yaml"}, produces = {"application/json", "application/xml", "application/x-yaml"})
        public ClienteModel update (@RequestBody ClienteModel clienteModel) throws Exception {
            return service.update(clienteModel);
        }

        @ApiOperation(value = "Delete a cliente by ID")
        @DeleteMapping("/{id}")
        public ResponseEntity<?> delete ( @PathVariable("id") long Id) throws Exception {
            service.delete(Id);
            return ResponseEntity.ok().build();
        }
    }