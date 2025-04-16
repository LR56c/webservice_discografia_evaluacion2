package org.iplacex.proyectos.discografia.discos;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.util.List;

@Document("discos")
public class Disco {
    @Id
    public String id;
    public String idArtista;
    public String nombre;
    @Field(name = "anioLanzamiento", targetType = FieldType.INT32)
    public int anioLanzamiento;
    public List<String> canciones;
}
