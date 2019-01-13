package com.fingeso.reddeideas.servicios;

import com.fingeso.reddeideas.modelos.Reto;
import com.fingeso.reddeideas.modelos.Idea;
import com.fingeso.reddeideas.repositorios.RetoRepository;
import com.fingeso.reddeideas.repositorios.IdeaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8080",maxAge = 3600)
@RestController
@RequestMapping(value = "/retos")
public class RetoServicio {

    @Autowired
    private RetoRepository retoRepository;

    @Autowired
    private IdeaRepository ideaRepository;

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
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public  Reto createRetos(@RequestBody Reto retos){
        Reto challenge = new Reto();
        challenge.setNombre(retos.getNombre());
        challenge.setDescripcion(retos.getDescripcion());
        challenge.setTiempo(retos.getTiempo());
        challenge.setTema(retos.getTema());

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
        return this.retoRepository.save(reto);
    }

}