package com.boster.proyectoAPI.services;

import com.boster.proyectoAPI.entidades.Pelicula;
import com.boster.proyectoAPI.repository.IPeliculaRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class PeliculaServiceImpl implements IPeliculaService {
    /*@Override
    public List<Pelicula> obtenerPeliculas() {
        List<Pelicula> peliculas = new ArrayList<>();
        Pelicula p1 = new Pelicula();
        p1.setNombre("rapidos y furiosos");
        p1.setDuracion(160);
        p1.setGenero("accion");
        peliculas.add(p1);
        return peliculas;
    }*/

    @Autowired
    IPeliculaRepositry iPeliculaRepositry;
    @Override
    public List<Pelicula> obtenerPeliculas() {
        return iPeliculaRepositry.findAll();
    }

    @Override
    public Pelicula agregarPelicula(Pelicula pelicula) {
        return iPeliculaRepositry.save(pelicula);
    }

    @Override
    public List<Pelicula> obtenerPeliculasDuracion() {
        return iPeliculaRepositry.peliculasDuracion();
    }

    @Override
    public Pelicula buscarById(Long id) {
        return iPeliculaRepositry.findById(id).orElse(null);
    }
}
