package com.fingeso.reddeideas.servicios;

import com.fingeso.reddeideas.modelos.Comentario;
import com.fingeso.reddeideas.modelos.Idea;
import com.fingeso.reddeideas.modelos.Usuario;
import com.fingeso.reddeideas.repositorios.ComentarioRepository;
import com.fingeso.reddeideas.repositorios.IdeaRepository;
import com.fingeso.reddeideas.repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8080",maxAge = 3600)
@RestController
@RequestMapping(value = "/comentarios")
public class ComentarioServicio {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private IdeaRepository ideaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    //Entrega todos los comentarios realizados en la paguina
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Comentario> getComentarios(){
        return this.comentarioRepository.findAll();
    }

    //Entrega los comentarios de una idea.
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public List<Comentario> getComentario(@PathVariable String id){

        return this.comentarioRepository.findComentarioByIdea(id);
    }

    //Crea comentario dentro de idea
    @RequestMapping(value = "/{id_idea}/{id_usuario}",method = RequestMethod.POST)
    @ResponseBody
    public  Comentario createComentario(@RequestBody Comentario comentario,@PathVariable String id_idea,@PathVariable String id_usuario){

        Comentario comentary = new Comentario();
        Calendar fechaActual = Calendar.getInstance();
        Idea idea = this.ideaRepository.findIdeaById(id_idea);
        comentary.setfechaPublicacion(fechaActual.getTime());
        comentary.setTexto(comentario.getTexto());
        comentary.setIdea(this.ideaRepository.findIdeaById(id_idea));
        comentary.setUsuario(this.usuarioRepository.findUsuarioById(id_usuario));

        return this.comentarioRepository.save(comentary);
    }

    //CAMBIA LOS VALORES DE LOS ATRIBUTOS DEL USUARIO
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public Comentario updateComentario(@RequestBody Comentario comentario){

        Comentario comentary = this.comentarioRepository.findComentarioById(comentario.getId());
        comentary.setTexto(comentario.getTexto());
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