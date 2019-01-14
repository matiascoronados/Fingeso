package com.fingeso.reddeideas.servicios;

import com.fingeso.reddeideas.modelos.Usuario;
import com.fingeso.reddeideas.modelos.Admin;
import com.fingeso.reddeideas.modelos.Idea;
import com.fingeso.reddeideas.modelos.Reto;
import com.fingeso.reddeideas.repositorios.AdminRepository;
import com.fingeso.reddeideas.repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8080",maxAge = 3600)
@RestController
@RequestMapping(value = "/admin")
public class AdminServicio
{
    @Autowired
    private AdminRepository adminRepository;


    //Entrega todos los administradores de la base de datos
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Admin> getAdmins()
    {
        return this.adminRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Usuario getAdmin(@PathVariable String id){
        return this.adminRepository.findAdminById(id);
    }

    //Crea un administrador nuevo
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public  Admin createAdministrador(@RequestBody Admin newadmin){
        Admin admin = new Admin();
        admin.setRut(newadmin.getRut());
        admin.setPassword(newadmin.getPassword());
        admin.setRol("Admin");
        admin.setCargo(newadmin.getCargo());
        admin.setCorreo(newadmin.getCorreo());
        admin.setTelefono(newadmin.getTelefono());
        admin.setNombre(newadmin.getNombre());

        List <Idea> listaIdeas = new ArrayList<>();
        List <Reto> listaRetos = new ArrayList<>();
        listaIdeas.add(null);
        listaRetos.add(null);

        admin.setIdeas(listaIdeas);
        admin.setRetos(listaRetos);

        return this.adminRepository.save(admin);
    }

    //Cambia los valores de un administrador
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public Admin updateAdmin(@RequestBody Admin admin){

        Admin oldadmin = this.adminRepository.findAdminById(admin.getId());
        oldadmin.setRut(admin.getRut());
        oldadmin.setPassword(admin.getPassword());
        oldadmin.setCargo(admin.getCargo());
        oldadmin.setCorreo(admin.getCorreo());
        oldadmin.setTelefono(admin.getTelefono());
        oldadmin.setNombre(admin.getNombre());
        return this.adminRepository.save(oldadmin);
    }

    //Elimina un admin de la BD
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteAdmin(@PathVariable String id){

        Admin admin = this.adminRepository.findAdminById(id);
        this.adminRepository.delete(admin);
    }

    //banear usuario

    //guardar reto en arreglo interno de admin.

}
