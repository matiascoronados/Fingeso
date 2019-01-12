package com.fingeso.reddeideas.repositorios;

import com.fingeso.reddeideas.modelos.Reto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

public interface RetoRepository extends MongoRepository<Reto, String>{

    public Reto findRetoById(String id);
}