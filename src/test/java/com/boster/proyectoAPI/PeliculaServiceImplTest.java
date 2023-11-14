package com.boster.proyectoAPI;

import com.boster.proyectoAPI.entidades.Pelicula;
import com.boster.proyectoAPI.repository.IPeliculaRepositry;
import com.boster.proyectoAPI.services.PeliculaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;


public class PeliculaServiceImplTest {

    @Mock
    private IPeliculaRepositry iPeliculaRepositryMock;

    @InjectMocks
    private PeliculaServiceImpl peliculaServiceMock;

    private Pelicula peliculaEsperada;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
        peliculaEsperada = new Pelicula();
        peliculaEsperada.setId(1L);
        peliculaEsperada.setNombre("it");
        peliculaEsperada.setDuracion(120);
        peliculaEsperada.setGenero("terror");
    }


    @Test
    void getPeliculaById(){
        when(iPeliculaRepositryMock.findById(1L)).thenReturn(Optional.ofNullable(peliculaEsperada));
        Pelicula peliculaResultante = peliculaServiceMock.buscarById(1L);
        assertEquals(peliculaEsperada,peliculaResultante);
    }

    @Test
    void getPeliculas(){
        List<Pelicula> peliculas = new ArrayList<>();
        peliculas.add(peliculaEsperada);
        when(peliculaServiceMock.obtenerPeliculas()).thenReturn(peliculas);
        assertNotNull(peliculaServiceMock.obtenerPeliculas());
    }
}
