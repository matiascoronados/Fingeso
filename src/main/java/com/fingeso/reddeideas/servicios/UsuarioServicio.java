package com.fingeso.reddeideas.servicios;


import com.fingeso.reddeideas.modelos.Idea;
import com.fingeso.reddeideas.modelos.Usuario;
import com.fingeso.reddeideas.modelos.Comentario;
import com.fingeso.reddeideas.repositorios.UsuarioRepository;
import com.fingeso.reddeideas.repositorios.IdeaRepository;
import com.fingeso.reddeideas.repositorios.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)
@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioServicio {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private IdeaRepository ideaRepository;

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
        Usuario user = new Usuario();
        user.setRut(usuario.getRut());
        user.setPassword(usuario.getPassword());
        user.setRol("Participante");
        user.setCargo(usuario.getCargo());
        user.setCorreo(usuario.getCorreo());
        user.setTelefono(usuario.getTelefono());
        user.setNombre(usuario.getNombre());

        List <Idea> lista = new ArrayList<>();
        lista.add(null);
        user.setIdeas(lista);

        return this.usuarioRepository.save(user);
    }

    //CAMBIA LOS VALORES DE LOS ATRIBUTOS DEL USUARIO
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public Usuario updateUsuario(@RequestBody Usuario usuario){

        Usuario user = this.usuarioRepository.findUsuarioById(usuario.getId());
        user.setRut(usuario.getRut());
        user.setPassword(usuario.getPassword());
        user.setRol(usuario.getRol());
        user.setCargo(usuario.getCargo());
        user.setCorreo(usuario.getCorreo());
        user.setTelefono(usuario.getTelefono());
        user.setNombre(usuario.getNombre());
        return this.usuarioRepository.save(user);
    }

    //ELIMINA UN USUARIO EN BASE AL ID
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteUsuario(@PathVariable String id){

        Usuario user = this.usuarioRepository.findUsuarioById(id);
        this.usuarioRepository.delete(user);
    }

    //ElIMINAR IDEA
    @RequestMapping(value = "/{id}/deleteIdea", method = RequestMethod.DELETE)
    @ResponseBody
    public Usuario deleteIdea(@PathVariable String id,@RequestBody Idea oldIdea)
    {
        Usuario user = this.usuarioRepository.findUsuarioById(id);
        List<Idea> userIdeas= user.getIdeas();
        Idea repoIdea = this.ideaRepository.findIdeaById(oldIdea.getId());
        userIdeas.remove(repoIdea);
        user.setIdeas(userIdeas);
        return this.usuarioRepository.save(user);
    }


    //Agrega una idea (ya creada) a la lista de ideas del usuario
    @RequestMapping(value = "/{id}/addIdea", method = RequestMethod.POST)
    @ResponseBody
    public Usuario addIdea(@PathVariable String id,@RequestBody Idea oldIdea)
    {
        Usuario usuario = this.usuarioRepository.findUsuarioById(id);
        List<Idea> ideasUsuario = usuario.getIdeas();
        Idea idea = this.ideaRepository.findIdeaById(oldIdea.getId());
        ideasUsuario.add(idea);
        usuario.setIdeas(ideasUsuario);
        int numero = usuario.getNumeroIdeas() + 1;
        usuario.setNumeroIdeas(numero);
        return this.usuarioRepository.save(usuario);
    }





}