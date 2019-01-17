package com.fingeso.reddeideas.servicios;

import com.fingeso.reddeideas.modelos.Reto;
import com.fingeso.reddeideas.modelos.Idea;
import com.fingeso.reddeideas.modelos.Usuario;
import com.fingeso.reddeideas.repositorios.RetoRepository;
import com.fingeso.reddeideas.repositorios.IdeaRepository;
import com.fingeso.reddeideas.repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)
@RestController
@RequestMapping(value = "/retos")
public class RetoServicio {

    @Autowired
    private RetoRepository retoRepository;

    @Autowired
    private IdeaRepository ideaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    //ENTREGA TODOS LOS USUARIOS EN LA BD
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Reto> getRetos(){
        return this.retoRepository.findAll();
    }

    //ENTREGA UN USUARIO EN BASE AL ID
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Reto getRetos(@PathVariable String id){

        return this.retoRepository.findRetoById(id);
    }

    //CREA UN USUARIO NUEVO
    @RequestMapping(value = "/{id}",method = RequestMethod.POST)
    @ResponseBody
    public  Reto createRetos(@PathVariable String id, @RequestBody Reto retos){
        Reto challenge = new Reto();
        Usuario usuario = this.usuarioRepository.findUsuarioById(id);
        Calendar fechaActual = Calendar.getInstance();
        challenge.setNombre(retos.getNombre());
        challenge.setDescripcion(retos.getDescripcion());
        challenge.setTiempo(retos.getTiempo());
        challenge.setTema(retos.getTema());
        challenge.setfechaPublicacion(fechaActual.getTime());
        challenge.setUsuario(usuario);
        List <Idea> listaIdeas = new ArrayList<>();
        listaIdeas.add(null);
        challenge.setIdeas(listaIdeas);

        return this.retoRepository.save(challenge);
    }

    //CAMBIA LOS VALORES DE LOS ATRIBUTOS DEL USUARIO
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public Reto updateReto(@RequestBody Reto retos){

        Reto challenge = this.retoRepository.findRetoById(retos.getId());
        challenge.setNombre(retos.getNombre());
        challenge.setDescripcion(retos.getDescripcion());
        challenge.setTiempo(retos.getTiempo());
        challenge.setTema(retos.getTema());
        return this.retoRepository.save(challenge);
    }

    //ELIMINA UN USUARIO EN BASE AL ID
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteReto(@PathVariable String id){

        Reto challenge = this.retoRepository.findRetoById(id);
        this.retoRepository.delete(challenge);
    }

    //Agrega una idea al arreglo interno de reto.
    @RequestMapping(value = "/{id}/addIdea", method = RequestMethod.POST)
    @ResponseBody
    public Reto addIdea (@PathVariable String id,@RequestBody Idea idea){
        Idea ideaUsuario = this.ideaRepository.findIdeaById(idea.getId());
        Reto reto = this.retoRepository.findRetoById(id);
        List<Idea> listaIdeas = reto.getIdeas();
        listaIdeas.add(ideaUsuario);
        reto.setIdeas(listaIdeas);
        int cantidad = reto.getCantidadIdeas() + 1;
        reto.setCantidadIdeas(cantidad);
        return this.retoRepository.save(reto);
    }

    //Metodos de busqueda

    //Busqueda por titulo
    @RequestMapping(value = "/{nombre}/getRetoByNombre", method = RequestMethod.GET)
    @ResponseBody
    public List<Reto> getRetoByNombre(@PathVariable String nombre)
    {
        List<Reto> listaRetos = this.retoRepository.findRetoByNombreLike(nombre);
        return listaRetos;
    }

    //Busqueda por tema
    @RequestMapping(value = "/{nombre}/getRetoByTema", method = RequestMethod.GET)
    @ResponseBody
    public List<Reto> getRetoByTema(@PathVariable String nombre)
    {
        List<Reto> listaRetos = this.retoRepository.findRetoByTemaLike(nombre);
        return listaRetos;
    }

    //Busqueda por fecha
    @RequestMapping(value = "/getRetoByFecha", method = RequestMethod.GET)
    @ResponseBody
    public List<Reto> getRetoByFecha()
    {
        List<Reto> listaRetos = this.retoRepository.findAll();
        Collections.reverse(listaRetos);
        return listaRetos;
    }

    //Busqueda por numero de ideas
    @RequestMapping(value = "/getRetoByIdeas", method = RequestMethod.GET)
    @ResponseBody
    public List<Reto> getRetoByIdeas()
    {
        List<Reto> listaRetos = this.retoRepository.findAll();
        listaRetos.sort(Comparator.comparing(Reto::getCantidadIdeas));
        Collections.reverse(listaRetos);
        return listaRetos;
    }
}