package org.iplacex.proyectos.discografia.artistas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ArtistaController {
    @Autowired
    private IArtistasRepository artistasRepository;

    @PostMapping(
            value = "/artista",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Artista> HandleInsertArtistaRequest(@RequestBody Artista artista) {
        Artista temp = artistasRepository.insert(artista);
        return new ResponseEntity<>(temp, null, HttpStatus.CREATED);
    }

    @GetMapping(
            value = "/artista/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Artista> HandleGetArtistaRequest(@PathVariable("id") String id) {
        var artista = artistasRepository.findById(id);
        if (artista.isEmpty()) {
            return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(artista.get(), null, HttpStatus.OK);
    }

    @GetMapping(
            value = "/artistas",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Iterable<Artista>> HandleGetArtistasRequest() {
        var artistas = artistasRepository.findAll();
        return new ResponseEntity<>(artistas, null, HttpStatus.OK);
    }

    @PutMapping(
            value = "/artista/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Artista> HandleUpdateArtistaRequest(@PathVariable("id") String id, @RequestBody Artista artista) {
        if (!artistasRepository.existsById(id)) {
            return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
        }
        var updatedArtista = artistasRepository.save(artista);
        return new ResponseEntity<>(updatedArtista, null, HttpStatus.OK);
    }

    @DeleteMapping(
            value = "/artista/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Artista> HandleDeleteArtistaRequest(@PathVariable("id") String id) {
        var artista = artistasRepository.findById(id);
        if (artista.isEmpty()) {
            return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
        }
        artistasRepository.deleteById(id);
        return new ResponseEntity<>(artista.get(), null, HttpStatus.NO_CONTENT);
    }
}
