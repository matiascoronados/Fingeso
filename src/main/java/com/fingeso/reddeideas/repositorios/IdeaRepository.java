package com.fingeso.reddeideas.repositorios;

import com.fingeso.reddeideas.modelos.Idea;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IdeaRepository extends MongoRepository<Idea, String>{

    public Idea findIdeaById(String id);
    public List<Idea> findIdeaByUsuario(String id);
    public List<Idea> findIdeaByTituloLike(String nombre);

}
