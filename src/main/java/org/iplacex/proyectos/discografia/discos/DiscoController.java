package org.iplacex.proyectos.discografia.discos;

import org.iplacex.proyectos.discografia.artistas.Artista;
import org.iplacex.proyectos.discografia.artistas.IArtistasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class DiscoController {
    @Autowired
    private IArtistasRepository artistasRepository;
    @Autowired
    private IDiscosRepository discosRepository;

    @PostMapping(
            value = "/disco",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Disco> HandlePostDiscoRequest(@RequestBody Disco disco) {
        var artista = artistasRepository.findById(disco.idArtista);
        if (artista.isEmpty()) {
            return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
        }
        Disco temp = discosRepository.insert(disco);
        return new ResponseEntity<>(temp, null, HttpStatus.CREATED);
    }

    @GetMapping(
            value = "/disco/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Disco> HandleGetDiscoRequest(@PathVariable("id") String id) {
        var disco = discosRepository.findById(id);
        if (disco.isEmpty()) {
            return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(disco.get(), null, HttpStatus.OK);
    }

    @GetMapping(
            value = "/discos",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Iterable<Disco>> HandleGetDiscosRequest() {
        var discos = discosRepository.findAll();
        return new ResponseEntity<>(discos, null, HttpStatus.OK);
    }

    @GetMapping(
            value = "/artista/{id}/discos",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    List<Disco> HandleGetDiscosByArtista(
            @PathVariable("id") String idArtista
    ) {
        return discosRepository.findDiscosByIdArtista(idArtista);
    }
}
