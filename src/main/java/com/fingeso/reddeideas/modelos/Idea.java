package com.fingeso.reddeideas.modelos;

import org.springframework.data.annotation.Id;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.mongodb.core.mapping.DBRef;
import java.util.List;

public class Idea {

	@Id
	public String id;
	public String numeroVotos;
	public String descripcion;

	public String getId(){
		return this.id;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getNumeroVotos(){
		return this.numeroVotos;
	}

	public void setNumeroVotos(String numeroVotos){
		this.numeroVotos = numeroVotos;
	}

	public String getDescripcion(){
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;		
	}
}