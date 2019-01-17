package com.fingeso.reddeideas.repositorios;

import com.fingeso.reddeideas.modelos.Reto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RetoRepository extends MongoRepository<Reto, String>{

    public Reto findRetoById(String id);
    public List<Reto> findRetoByNombreLike(String nombre);
    public List<Reto> findRetoByTemaLike(String tema);

}