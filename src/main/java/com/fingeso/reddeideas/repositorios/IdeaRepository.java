package com.fingeso.reddeideas.repositorios;

import com.fingeso.reddeideas.modelos.Idea;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

public interface IdeaRepository extends MongoRepository<Idea, String>{

    public Idea findIdeaById(String id);
}
