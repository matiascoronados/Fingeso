package com.fingeso.reddeideas.servicios;

import com.fingeso.reddeideas.modelos.Usuario;
import com.fingeso.reddeideas.modelos.Comentario;
import com.fingeso.reddeideas.modelos.Idea;
import com.fingeso.reddeideas.modelos.Reto;
import com.fingeso.reddeideas.repositorios.UsuarioRepository;
import com.fingeso.reddeideas.repositorios.ComentarioRepository;
import com.fingeso.reddeideas.repositorios.IdeaRepository;
import com.fingeso.reddeideas.repositorios.RetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)
@RestController
@RequestMapping(value = "/ideas")
public class IdeaServicio {

    @Autowired
    private IdeaRepository ideaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private RetoRepository retoRepository;

    //ENTREGA TODOS LOS USUARIOS EN LA BD
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Idea> getIdeas(){
        return this.ideaRepository.findAll();
    }

    //ENTREGA UN USUARIO EN BASE AL ID
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Idea getIdea(@PathVariable String id){

        return this.ideaRepository.findIdeaById(id);
    }

    //CREA UN USUARIO NUEVO
    @RequestMapping(value = "/{id}",method = RequestMethod.POST)
    @ResponseBody
    public  Idea createIdea(@RequestBody Idea idea,@PathVariable String id){
        Idea think = new Idea();
        Calendar fechaActual = Calendar.getInstance();
        think.setNumeroVotos(0);
        think.setDescripcion(idea.getDescripcion());
        think.setTitulo(idea.getTitulo());
        think.setUsuario(this.usuarioRepository.findUsuarioById(id));
        think.setfechaPublicacion(fechaActual.getTime());

        List <Comentario> listaComentarios = new ArrayList<>();
        listaComentarios.add(null);
        think.setListaComentarios(listaComentarios);

        return this.ideaRepository.save(think);
    }

    //CREA UN USUARIO NUEVO
    @RequestMapping(value = "/{id}/{idreto}",method = RequestMethod.POST)
    @ResponseBody
    public  Idea createIdeaforReto(@RequestBody Idea idea,@PathVariable String id,@PathVariable String idreto){

        Idea think = new Idea();
        Calendar fechaActual = Calendar.getInstance();
        think.setNumeroVotos(0);
        think.setDescripcion(idea.getDescripcion());
        think.setTitulo(idea.getTitulo());
        think.setUsuario(this.usuarioRepository.findUsuarioById(id));

        think.setfechaPublicacion(fechaActual.getTime());

        List <Comentario> listaComentarios = new ArrayList<>();
        listaComentarios.add(null);
        think.setListaComentarios(listaComentarios);

        think.setReto(this.retoRepository.findRetoById(idreto));

        return this.ideaRepository.save(think);
    }

    //CAMBIA LOS VALORES DE LOS ATRIBUTOS DEL USUARIO
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public Idea updateIdea(@RequestBody Idea idea){

        Idea think = this.ideaRepository.findIdeaById(idea.getId());
        think.setNumeroVotos(idea.getNumeroVotos());
        think.setDescripcion(idea.getDescripcion());
        think.setTitulo(idea.getTitulo());
        return this.ideaRepository.save(think);
    }

    //ELIMINA UN USUARIO EN BASE AL ID
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteIdea(@PathVariable String id){

        Idea think = this.ideaRepository.findIdeaById(id);
        this.ideaRepository.delete(think);
    }

    //Agrega un comentario (ya creado) a la lista de comentarios de la idea indicada.
    @RequestMapping(value = "/{id}/addComentario", method = RequestMethod.POST)
    @ResponseBody
    public Idea addComentario(@PathVariable String id,@RequestBody Comentario comentario)
    {
        Idea idea = this.ideaRepository.findIdeaById(id);
        int numero = idea.getNumeroComentarios() + 1;

        idea.setNumeroComentarios(numero);

        return this.ideaRepository.save(idea);
    }


    @RequestMapping(value = "/{id}/addVoto", method = RequestMethod.POST)
    @ResponseBody
    public Idea addVoto(@PathVariable String id)
    {
        Idea idea = this.ideaRepository.findIdeaById(id);
        int numero = idea.getNumeroVotos()+1;
        idea.setNumeroVotos(numero);
        return this.ideaRepository.save(idea);
    }

    @RequestMapping(value = "/{id}/getUsuario", method = RequestMethod.GET)
    @ResponseBody
    public Usuario getUsuario(@PathVariable String id)
    {
        Idea idea = this.ideaRepository.findIdeaById(id);
        Usuario user = idea.getUsuario();
        return this.usuarioRepository.findUsuarioById(user.getId());
    }

    @RequestMapping(value = "/{id}/getIdeas", method = RequestMethod.GET)
    @ResponseBody
    public List<Idea> ideasUsuario(@PathVariable String id)
    {
        return this.ideaRepository.findIdeaByUsuario(id);
    }

    //Metodos de busqueda

    //Busqueda por titulo
    @RequestMapping(value = "/{titulo}/getIdeaByTitulo", method = RequestMethod.GET)
    @ResponseBody
    public List<Idea> getIdeaByTitulo(@PathVariable String titulo)
    {
        List<Idea> listaIdea = this.ideaRepository.findIdeaByTituloLike(titulo);
        return listaIdea;
    }

    //Busqueda por fecha
    @RequestMapping(value = "/getIdeaByFecha", method = RequestMethod.GET)
    @ResponseBody
    public List<Idea> getIdeaByFecha()
    {
        List<Idea> listaIdea = this.ideaRepository.findAll();
        Collections.reverse(listaIdea);
        return listaIdea;
    }

    //Busqueda por numero de comentarios
    @RequestMapping(value = "/getIdeaByComentarios", method = RequestMethod.GET)
    @ResponseBody
    public List<Idea> getIdeaByComentarios()
    {
        List<Idea> listaIdea = this.ideaRepository.findAll();
        listaIdea.sort(Comparator.comparing(Idea::getNumeroComentarios));
        Collections.reverse(listaIdea);
        return listaIdea;
    }

    //Busqueda por numero de votos
    @RequestMapping(value = "/getIdeaByVotos", method = RequestMethod.GET)
    @ResponseBody
    public List<Idea> getIdeaByVotos()
    {
        List<Idea> listaIdea = this.ideaRepository.findAll();
        listaIdea.sort(Comparator.comparing(Idea::getNumeroVotos));
        Collections.reverse(listaIdea);
        return listaIdea;
    }

    @RequestMapping(value = "/{id}/getIdeaByReto", method = RequestMethod.GET)
    @ResponseBody
    public List<Idea> getIdeaByReto(@PathVariable String id)
    {
        return this.ideaRepository.findIdeaByReto(id);
    }


}