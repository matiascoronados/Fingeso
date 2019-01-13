package com.fingeso.reddeideas.modelos;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.List;
import java.util.Date;

public class Idea {

	@Id
	private String id;
	private String numeroVotos;
	private String descripcion;
	private String titulo;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private Date fechaPublicacion;

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

	public Date getfechaPublicacion() { return fechaPublicacion; }

	public void setfechaPublicacion(Date fechaPublicacion) { this.fechaPublicacion = fechaPublicacion; }

	public int getNumeroComentarios()
	{
		if(listaComentarios == null)
		{
			return 0;
		}
		else {
			return this.listaComentarios.size();
		}
	}

}