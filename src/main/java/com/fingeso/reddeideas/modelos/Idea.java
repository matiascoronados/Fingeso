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
	public String titulo;

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

	public String getTitulo(){
		return this.titulo;
	}

	public void setTitulo(String titulo){
		this.titulo = titulo;
	}
}