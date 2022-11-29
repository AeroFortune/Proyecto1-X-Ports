package com.example.sportsbe.Controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sportsbe.Models.Articulos;
import com.example.sportsbe.Models.Usuarios;
import com.example.sportsbe.Services.RushevoDb;

@RestController
@CrossOrigin
@Controller
public class RushEvoController {

    @GetMapping("/rushevo_db/articulos/all")    
    public List<Articulos> ObtenerArticulos(){
        return new RushevoDb().ObtenerArticulos();
    }

    @GetMapping("/rushevo_db/usuarios/all")
    public List<Usuarios> ObtenerUsuarios(){
        return new RushevoDb().ObtenerUsuarios();
    }

    @PostMapping("/rushevo_db/usuarios/")
    public int InsertarUsuario(@RequestBody Usuarios usuario){
        return new RushevoDb().GuardarUsuarios(usuario); 
    }

    @PutMapping("/rushevo_db/usuarios/")
    public int ActualizarUsuario(@RequestBody Usuarios usuario){
        return new RushevoDb().ActualizarUsuario(usuario); 
    }

    @DeleteMapping("/rushevo_db/usuarios/{id_user}")
    public int Delete(@PathVariable("id_user") int pid){
        return new RushevoDb().EliminarUsuario(pid); 
    }

    

}
