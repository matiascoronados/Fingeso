package com.fingeso.reddeideas;

import org.springframework.data.annotation.Id;

public class Entidad{

	@Id
	public String _id;

	public String nombre;

	public Entidad(){}

	public Entidad(String nombre){
		this.nombre = nombre;
	}

	@Override
	public String toString(){
		return String.format("Entidad[_id=%s,nombre='%s']",this._id,this.nombre);
	}
}