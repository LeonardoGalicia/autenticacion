package com.boster.proyectoAPI.services;

import com.boster.proyectoAPI.entidades.Pelicula;

import java.util.List;

public interface IPeliculaService {
    List<Pelicula> obtenerPeliculas();
    Pelicula agregarPelicula(Pelicula pelicula);
    List<Pelicula> obtenerPeliculasDuracion();

    Pelicula buscarById(Long id);


}
