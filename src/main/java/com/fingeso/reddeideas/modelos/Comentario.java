package com.fingeso.reddeideas.modelos;

import org.springframework.data.annotation.Id;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

//import java.util.Date; ->para utilizar tipos de Date 

public class Comentario{

	@Id
	private String id;
	private String texto;

	@JsonIgnore
	@DBRef
	private Usuario usuario;

	@JsonIgnore
	@DBRef
	private Idea idea;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private Date fechaPublicacion;

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

	public Usuario getUsuario(){
		return this.usuario;
	}

	public void setUsuario(Usuario usuario){
		this.usuario  = usuario;
	}

	public Idea getIdea(){
		return this.idea;
	}

	public void setIdea(Idea idea){ this.idea  = idea; }

	public Date getfechaPublicacion() { return fechaPublicacion; }

	public void setfechaPublicacion(Date fechaPublicacion) { this.fechaPublicacion = fechaPublicacion; }

}
