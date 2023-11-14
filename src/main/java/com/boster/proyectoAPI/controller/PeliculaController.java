package com.boster.proyectoAPI.controller;


import com.boster.proyectoAPI.entidades.Pelicula;
import com.boster.proyectoAPI.excepciones.InvalidDataException;
import com.boster.proyectoAPI.excepciones.NotFoundExceptionPersonalizada;
import com.boster.proyectoAPI.excepciones.RequestException;
import com.boster.proyectoAPI.models.AuthenticationReq;
import com.boster.proyectoAPI.models.TokenInfo;
import com.boster.proyectoAPI.services.IPeliculaService;

import com.boster.proyectoAPI.services.JwtUtilService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.*;


@RestController
@RequestMapping("/pelicula/v0")
public class PeliculaController {

    @Autowired
    IPeliculaService iPeliculaService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    UserDetailsService usuarioDetailsService;

    @Autowired
    private JwtUtilService jwtUtilService;


    private static final Logger logger = LoggerFactory.getLogger(PeliculaController.class);

    @GetMapping("/pelicula")
    public ResponseEntity<List<Pelicula>> getPeliculas(){
        List<Pelicula> list =new ArrayList<>();
        list = iPeliculaService.obtenerPeliculas();
        if(list.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(list);
    }
    @GetMapping("/pelicula/{id}")
    public ResponseEntity<Object> getPeliculaById(@PathVariable Long id) throws Exception{
        Map<String, Object> responsePersonalizado = new HashMap<>();
        Pelicula pelicula = iPeliculaService.buscarById(id);
        if (pelicula==null){
            throw new NotFoundExceptionPersonalizada("Pelicula no encontrada", "err-p987", HttpStatus.NOT_FOUND);
        }
       /* if(pelicula == null){
            responsePersonalizado.put("Error:","No existe una pelicula con ese id");
            return new ResponseEntity<>(responsePersonalizado, HttpStatus.NOT_FOUND);
        }*/
        return ResponseEntity.ok(pelicula);
    }

    @GetMapping("/pelicula/duracion")
    public ResponseEntity<List<Pelicula>> getPeliculasDuracion(){
        List<Pelicula> list =new ArrayList<>();
        list = iPeliculaService.obtenerPeliculasDuracion();
        if(list.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(list);
    }

   /* @PostMapping("/pelicula")
    public ResponseEntity<?> addPelicula(@RequestBody Pelicula pelicula){
        Map<String,Object> responsePersonalizado = new HashMap<>();
        if(pelicula.getNombre().equals(null) || pelicula.getNombre().equals("")){
            throw new RequestException("El nombre no puede ir vacio","err-p345",HttpStatus.BAD_REQUEST);
        }

        if(pelicula.getDuracion()>120){
            throw new RequestException("Por regla de negocio las peliculas no pueden durar mas de 120 segundo","regla-234",HttpStatus.CONFLICT);
        }

        Pelicula pelicula1 = iPeliculaService.agregarPelicula(pelicula);

        return ResponseEntity.status(HttpStatus.CREATED).body(pelicula1);
    }*/
   @PostMapping("/pelicula")
   public ResponseEntity<?> addPelicula(@Valid @RequestBody Pelicula pelicula, BindingResult result){
       Map<String,Object> responsePersonalizado = new HashMap<>();
       if (result.hasErrors()){
           throw new InvalidDataException("errores de validacion","err-valid",HttpStatus.BAD_REQUEST, result);
       }

       Pelicula pelicula1 = iPeliculaService.agregarPelicula(pelicula);

       return ResponseEntity.status(HttpStatus.CREATED).body(pelicula1);
   }

    @PostMapping("/authenticate")
    public ResponseEntity<TokenInfo> authenticate(@RequestBody AuthenticationReq authenticationReq) {
       // logger.info("Autenticando al usuario {}", authenticationReq.getUsuario());


        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationReq.getUsuario(),
                        authenticationReq.getClave())); //autentica

        final UserDetails userDetails = usuarioDetailsService.loadUserByUsername(
                authenticationReq.getUsuario()); // crea userdatails con las credenciales

        final String jwt = jwtUtilService.generateToken(userDetails); // se crean el token

        TokenInfo tokenInfo = new TokenInfo(jwt);

        return ResponseEntity.ok(tokenInfo);
    }
}
