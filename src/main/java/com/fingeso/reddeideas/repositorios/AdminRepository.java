package com.fingeso.reddeideas.repositorios;

import com.fingeso.reddeideas.modelos.Admin;
import com.fingeso.reddeideas.modelos.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRepository extends MongoRepository<Admin, Long >{

    public Admin findAdminById(String id);
    public List<Admin> findAdminByRol(String rol);
}