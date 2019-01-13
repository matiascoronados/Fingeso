package com.fingeso.reddeideas.modelos;

import org.springframework.data.annotation.Id;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.mongodb.core.mapping.DBRef;
import java.util.List; 

public class Reto{

	@Id
	public String id;
	public String nombre;
	public String descripcion;
	public String tema;
	public int tiempo;

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

	public void setTema(String Tema){
		this.tema = tema;
	}

	public int getTiempo(){
		return this.tiempo;
	}

	public void setTiempo(int tiempo){
		this.tiempo = tiempo;
	}

}
