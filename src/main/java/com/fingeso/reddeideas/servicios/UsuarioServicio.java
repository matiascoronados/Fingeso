package com.fingeso.reddeideas.servicios;

import com.fingeso.reddeideas.modelos.Usuario;
import com.fingeso.reddeideas.repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioServicio {

    @Autowired
    private UsuarioRepository usuarioRepository;


    //ENTREGA TODOS LOS USUARIOS EN LA BD
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Usuario> getUsuarios(){
        return this.usuarioRepository.findAll();
    }

    //ENTREGA UN USUARIO EN BASE AL ID
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Usuario getUsuario(@PathVariable String id){

        return this.usuarioRepository.findUsuarioById(id);
    }

    //CREA UN USUARIO NUEVO
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public  Usuario createUsuario(@RequestBody Usuario usuario){
        System.out.println("\n\nENTREE\n\n");
        Usuario user = new Usuario();
        user.setTelefono(usuario.getTelefono());
        user.setRut(usuario.getRut());
        user.setRol(usuario.getRol());
        user.setPassword(usuario.getPassword());
        user.setCorreo(usuario.getCorreo());
        user.setNombre(usuario.getNombre());
        return this.usuarioRepository.save(user);
    }

    //CAMBIA LOS VALORES DE LOS ATRIBUTOS DEL USUARIO
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public Usuario updateUsuario(@RequestBody Usuario usuario){

        Usuario user = this.usuarioRepository.findUsuarioById(usuario.getId());
        user.setNombre(usuario.getNombre());
        user.setCorreo(usuario.getCorreo());
        user.setPassword(usuario.getPassword());
        user.setRol(usuario.getRol());
        user.setRut(usuario.getRut());
        user.setTelefono(usuario.getTelefono());

        return this.usuarioRepository.save(user);
    }

    //ELIMINA UN USUARIO EN BASE AL ID
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteUsuario(@PathVariable String id){

        Usuario user = this.usuarioRepository.findUsuarioById(id);
        this.usuarioRepository.delete(user);
    }
}