package com.fingeso.reddeideas.repositorios;

import com.fingeso.reddeideas.modelos.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, Long >{

    public Usuario findUsuarioById(String id);
}
