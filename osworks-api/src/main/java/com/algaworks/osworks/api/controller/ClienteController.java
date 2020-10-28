package com.algaworks.osworks.api.controller;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.osworks.domain.model.Cliente;
import com.algaworks.osworks.repository.ClienteRepository;

@RestController
public class ClienteController {
	
	
//	@GetMapping("/clientes")
//	public List<Cliente> listar() {
//		var cliente1 = new Cliente() ;
//		cliente1.setId(1L);
//		cliente1.setNome("Paulo Previlato");
//		cliente1.setTelefone("69 99302-8281");
//		cliente1.setEmail("paulo.previlato@gmail.com");
//		
//		var cliente2 = new Cliente() ;
//		cliente2.setId(2L);
//		cliente2.setNome("Alberto Rocha");
//		cliente2.setTelefone("69 9999-9999");
//		cliente2.setEmail("alberto.rocha@gmail.com");
//		return Arrays.asList(cliente1, cliente2);
//	}
	
	
	
	//interface do JPA que faz as operaçoes nas entidades do banco como ler, gravar e apagar.
		@PersistenceContext
		 private EntityManager manager;
		//similar ao DAO
		@Autowired
		private ClienteRepository clienteDAO;

		//o metodo listar é um Endpoint no Spring, ele precisa do Getmapping para ser acessado pela requisição.
		@GetMapping("/clientes")	
		public List<Cliente> listar() {
//			return manager.createQuery("from Cliente", Cliente.class).getResultList(); usar somente para teste.
			return clienteDAO.findAll(); // busca toda lista de cliente
			
//	    	Cliente cliente1 = new Cliente();
//	    	cliente1.setId(1L);
//	    	cliente1.setNome("Alex gonçalves");
//	    	cliente1.setEmail("alexnascimento1515@gmail.com");
//	    	cliente1.setTelefone("69-993716500");
//	    	Cliente cliente2 = new Cliente();
//	    	cliente2.setId(2L);
//	    	cliente2.setNome("Ana");
//	    	cliente2.setEmail("ana1515@gmail.com");
//	    	cliente2.setTelefone("69-999990000");
//			return Arrays.asList(cliente1, cliente2);
	    }
		
		//o metodo buscar cliente por Idcliente especifico é tbm um Endpoint
		@GetMapping("/clientes/{clienteId}")
		public ResponseEntity<Cliente>  buscar(@PathVariable Long clienteId) {
			Optional<Cliente> cliente= clienteDAO.findById(clienteId);
			
			if(cliente.isPresent()) {
				//se encontra cliente vai retornar o cliente com codigo 200 de sucesso
				return ResponseEntity.ok(cliente.get());
			}
			//se não encontrar cliente vai retornar o codigo 404 de não encontrado
			return ResponseEntity.notFound().build();
			//caso não haja nada dentro de cliente, retorna null. Utilizar quando eu fazer uma busca simples sem tratamento de dados, buscando diretamente pela classe Cliente.
//			return cliente.orElse(null);
			
		}

		//O postMapping é anotacao necessaria para enviar um conjunto de dados para servidor. O requestBody transforma os dados Json que vem do cliente em um Objeto Cliente
		@PostMapping 
		@ResponseStatus(HttpStatus.CREATED)
		public Cliente adicionar(@RequestBody Cliente cliente) {
			return clienteDAO.save(cliente);
		}
		
		//esta anotacao é similar ao do postMapping.
		@PutMapping("/{clienteId}")
		public ResponseEntity<Cliente> atualizar(@PathVariable Long clienteId, @RequestBody Cliente cliente){
			 if(!clienteDAO.existsById(clienteId)) {
				 return ResponseEntity.notFound().build();
			 }
			 cliente.setId(clienteId);
			 cliente = clienteDAO.save(cliente);
			 
			 return ResponseEntity.ok(cliente);
		}
		
		@DeleteMapping("/{cienteId}")
		public ResponseEntity<Void> excluir(@PathVariable Long clienteId){
			if(!clienteDAO.existsById(clienteId)) {
				return ResponseEntity.notFound().build();
			}
			clienteDAO.deleteById(clienteId);
			return ResponseEntity.noContent().build();	
		}
	
	
	
}
