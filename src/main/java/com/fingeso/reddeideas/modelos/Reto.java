package com.fingeso.reddeideas.modelos;

import org.springframework.data.annotation.Id;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.mongodb.core.mapping.DBRef;
import java.util.List; 

public class Reto{

	@Id
	private String id;
	private String nombre;
	private String descripcion;
	private String tema;
	private int tiempo;

	@JsonIgnore
	@DBRef
	private List<Idea> ideas;

	public String getId(){
		return this.id;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getNombre(){
		return this.nombre;
	}

	public void setNombre(String nombre){
		this.nombre = nombre;
	}

	public String getDescripcion(){
		return this.descripcion;
	}

	public void setDescripcion(String descripcion){
		this.descripcion = descripcion;
	}

	public String getTema(){
		return this.tema;
	}

	public void setTema(String tema){
		this.tema = tema;
	}

	public int getTiempo(){
		return this.tiempo;
	}

	public void setTiempo(int tiempo){
		this.tiempo = tiempo;
	}

	public List<Idea> getIdeas() { return ideas; }

	public void setIdeas(List<Idea> ideas) { this.ideas = ideas; }

}
