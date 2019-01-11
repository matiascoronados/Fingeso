package com.fingeso.reddeideas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/entidades")
public class EntidadController {
  @Autowired
  private EntidadRepository repository;

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public List<Entidad> getAllEntidades(){
	return repository.findAll();
	}

}