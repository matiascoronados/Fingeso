package com.fingeso.reddeideas.servicios;

import com.fingeso.reddeideas.modelos.Idea;
import com.fingeso.reddeideas.repositorios.IdeaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping(value = "/ideas")
public class IdeaServicio {

    @Autowired
    private IdeaRepository ideaRepository;


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
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public  Idea createIdea(@RequestBody Idea idea){
        System.out.println("\n\nENTREE\n\n");
        Idea think = new Idea();
        think.setNumeroVotos(idea.getNumeroVotos());
        think.setDescripcion(idea.getDescripcion());
        return this.ideaRepository.save(think);
    }

    //CAMBIA LOS VALORES DE LOS ATRIBUTOS DEL USUARIO
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public Idea updateIdea(@RequestBody Idea idea){

        Idea think = this.ideaRepository.findIdeaById(idea.getId());
        think.setNumeroVotos(idea.getNumeroVotos());
        think.setDescripcion(idea.getDescripcion());

        return this.ideaRepository.save(think);
    }

    //ELIMINA UN USUARIO EN BASE AL ID
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteIdea(@PathVariable String id){

        Idea think = this.ideaRepository.findIdeaById(id);
        this.ideaRepository.delete(think);
    }
}