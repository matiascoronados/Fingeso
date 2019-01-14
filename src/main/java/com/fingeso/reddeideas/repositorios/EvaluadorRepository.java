package com.fingeso.reddeideas.repositorios;

import com.fingeso.reddeideas.modelos.Evaluador;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvaluadorRepository extends MongoRepository<Evaluador, Long >{

    public Evaluador findEvaluadorById(String id);
    public List<Evaluador> findEvaluadorByRol(String rol);
}