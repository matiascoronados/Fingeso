package com.fingeso.reddeideas.modelos;

import org.springframework.data.annotation.Id;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.mongodb.core.mapping.DBRef;
import java.util.List;

public class Comentario{

	@Id
	public String id;
	public String texto;
	public String fecha;

	public String getId(){
		return this.id;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getTexto(){
		return this.texto;
	}

	public void setTexto(String texto){
		this.texto = texto;
	}

	public String getFecha(){
		return this.fecha;
	}

	public void setFecha(String fecha){
		this.fecha  = fecha;
	}
}
