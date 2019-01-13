package com.fingeso.reddeideas.repositorios;

import com.fingeso.reddeideas.modelos.Comentario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComentarioRepository extends MongoRepository<Comentario, String>{

    public Comentario findComentarioById(String id);
}