package com.fingeso.reddeideas.modelos;

import org.bson.types.ObjectId;
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

	@JsonIgnore
	@DBRef
	private Usuario usuario;

	@JsonIgnore
	@DBRef
	private List<Comentario> listaComentarios;

	public String getId(){ return this.id; }

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

	public Usuario getUsuario() { return usuario;
	}
	public void setUsuario(Usuario usuario) { this.usuario = usuario; }

	public List<Comentario> getListaComentarios() { return listaComentarios;
	}
	public void setListaComentarios(List<Comentario> listaComentarios) { this.listaComentarios = listaComentarios; }

}