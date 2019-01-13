package com.fingeso.reddeideas.servicios;

import com.fingeso.reddeideas.modelos.Reto;
import com.fingeso.reddeideas.repositorios.RetoRepository;
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
}