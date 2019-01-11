package com.fingeso.reddeideas;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EntidadRepository extends MongoRepository<Entidad, String>{

	public Entidad findBy_id(String id);
	public List<Entidad> findByNombre(String nombre);
}