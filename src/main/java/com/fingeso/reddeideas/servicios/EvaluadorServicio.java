package com.fingeso.reddeideas.servicios;


import com.fingeso.reddeideas.modelos.Idea;
import com.fingeso.reddeideas.modelos.Usuario;
import com.fingeso.reddeideas.modelos.Evaluador;
import com.fingeso.reddeideas.repositorios.UsuarioRepository;
import com.fingeso.reddeideas.repositorios.EvaluadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8080",maxAge = 3600)
@RestController
@RequestMapping(value = "/evaluador")
public class EvaluadorServicio
{
    @Autowired
    private EvaluadorRepository evaluadorRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;


    //Entrega todos los evaluadores de la base de datos
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Evaluador> getEvaluadores()
    {
        return this.evaluadorRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Usuario getEvaluador(@PathVariable String id){
        return this.evaluadorRepository.findEvaluadorById(id);
    }

    //Crea un evaluador nuevo
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public  Evaluador createEvaluador(@RequestBody Evaluador newevaluador){
        Evaluador evaluador = new Evaluador();
        evaluador.setRut(newevaluador.getRut());
        evaluador.setPassword(newevaluador.getPassword());
        evaluador.setRol("Evaluador");
        evaluador.setCargo(newevaluador.getCargo());
        evaluador.setCorreo(newevaluador.getCorreo());
        evaluador.setTelefono(newevaluador.getTelefono());
        evaluador.setNombre(newevaluador.getNombre());

        List <Idea> listaIdeas = new ArrayList<>();
        List <Idea> listaIdeasSeleccionadas = new ArrayList<>();
        listaIdeas.add(null);
        listaIdeasSeleccionadas.add(null);

        evaluador.setIdeas(listaIdeas);
        evaluador.setIdeasSeleccionadas(listaIdeasSeleccionadas);

        return this.evaluadorRepository.save(evaluador);
    }

    //Cambia los valores de un administrador
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public Evaluador updateEvaluador(@RequestBody Evaluador newevaluador){

        Evaluador evaluador = this.evaluadorRepository.findEvaluadorById(newevaluador.getId());
        evaluador.setRut(newevaluador.getRut());
        evaluador.setPassword(newevaluador.getPassword());
        evaluador.setCargo(newevaluador.getCargo());
        evaluador.setCorreo(newevaluador.getCorreo());
        evaluador.setTelefono(newevaluador.getTelefono());
        evaluador.setNombre(newevaluador.getNombre());
        return this.evaluadorRepository.save(evaluador);
    }

    //Elimina un admin de la BD
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteEvaluador(@PathVariable String id){

        Evaluador evaluador = this.evaluadorRepository.findEvaluadorById(id);
        this.evaluadorRepository.delete(evaluador);
    }



}
