package org.iplacex.proyectos.discografia.artistas;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.util.List;

@Document("artistas")
public class Artista {
    @Id
    public String id;
    public String nombre;
    public List<String> estilos;
    @Field(name = "anioFundacion", targetType = FieldType.INT32)
    public int anioFundacion;
    public boolean estaActivo;
}