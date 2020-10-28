package com.algaworks.osworks.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.osworks.domain.model.Cliente;
//anotação do spring, que define como repositorio similar ao DAO do JSF. através dele eu posso herdar inumeras dependencias.
@Repository 
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	List<Cliente> findByNome(String nome);
	
	//busca nome com letras e expressoes similares. igual ao like%nome%
	List<Cliente>  findByNomeContaining	(String nome);
	
}
