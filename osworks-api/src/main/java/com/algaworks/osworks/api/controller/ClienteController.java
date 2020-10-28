package com.algaworks.osworks.api.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.osworks.domain.model.Cliente;

@RestController
public class ClienteController {
	
	
	@GetMapping("/clientes")
	public List<Cliente> listar() {
		var cliente1 = new Cliente() ;
		cliente1.setId(1L);
		cliente1.setNome("Paulo Previlato");
		cliente1.setTelefone("69 99302-8281");
		cliente1.setEmail("paulo.previlato@gmail.com");
		
		var cliente2 = new Cliente() ;
		cliente2.setId(2L);
		cliente2.setNome("Alberto Rocha");
		cliente2.setTelefone("69 9999-9999");
		cliente2.setEmail("alberto.rocha@gmail.com");
		return Arrays.asList(cliente1, cliente2);
	}
}
