package com.fingeso.reddeideas.servicios;

import com.fingeso.reddeideas.modelos.Comentario;
import com.fingeso.reddeideas.repositorios.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping(value = "/comentarios")
public class ComentarioServicio {

    @Autowired
    private ComentarioRepository comentarioRepository;


    //ENTREGA TODOS LOS USUARIOS EN LA BD
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Comentario> getComentarios(){
        return this.comentarioRepository.findAll();
    }

    //ENTREGA UN USUARIO EN BASE AL ID
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Comentario getComentario(@PathVariable String id){

        return this.comentarioRepository.findComentarioById(id);
    }

    //CREA UN USUARIO NUEVO
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public  Comentario createComentario(@RequestBody Comentario comentario){
        System.out.println("\n\nENTREE\n\n");
        Comentario comentary = new Comentario();
        comentary.setTexto(comentario.getTexto());
        comentary.setFecha(comentario.getFecha());
        return this.comentarioRepository.save(comentary);
    }

    //CAMBIA LOS VALORES DE LOS ATRIBUTOS DEL USUARIO
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public Comentario updateComentario(@RequestBody Comentario comentario){

        Comentario comentary = this.comentarioRepository.findComentarioById(comentario.getId());
        comentary.setTexto(comentario.getTexto());
        comentary.setFecha(comentario.getFecha());
        return this.comentarioRepository.save(comentary);
    }

    //ELIMINA UN USUARIO EN BASE AL ID
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteComentario(@PathVariable String id){

        Comentario comentary = this.comentarioRepository.findComentarioById(id);
        this.comentarioRepository.delete(comentary);
    }
}