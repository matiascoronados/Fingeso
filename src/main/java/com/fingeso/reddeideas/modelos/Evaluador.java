package com.fingeso.reddeideas.modelos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;

public class Evaluador extends Usuario
{
    @JsonIgnore
    @DBRef
    private List<Idea> ideasSeleccionadas;

    public Evaluador()
    {
        super();
    }

    public List<Idea> getIdeasSeleccionadas() { return ideasSeleccionadas;
    }
    public void setIdeasSeleccionadas(List<Idea> ideasSeleccionadas) { this.ideasSeleccionadas = ideasSeleccionadas; }

    public int getNumeroIdeasSeleccionadas()
    {
        if(ideasSeleccionadas == null)
        {
            return 0;
        }
        else {
            return this.ideasSeleccionadas.size();
        }
    }


}
