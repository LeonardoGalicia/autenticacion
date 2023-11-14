package com.boster.proyectoAPI.repository;

import com.boster.proyectoAPI.entidades.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IPeliculaRepositry extends JpaRepository<Pelicula, Long> {

    @Query(value = "select * from peliculas where duracion>=80", nativeQuery = true)
    List<Pelicula> peliculasDuracion();
}
