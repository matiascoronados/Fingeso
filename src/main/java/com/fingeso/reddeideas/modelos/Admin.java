package com.fingeso.reddeideas.modelos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;

public class Admin extends Usuario
{
    @JsonIgnore
    @DBRef
    private List<Reto> retos;

    public Admin()
    {
        super();
    }

    public List<Reto> getRetos() { return retos;
    }
    public void setRetos(List<Reto> retos) { this.retos = retos; }

    public int getNumeroRetos()
    {
        if(retos == null)
        {
            return 0;
        }
        else {
            return this.retos.size();
        }
    }

}
