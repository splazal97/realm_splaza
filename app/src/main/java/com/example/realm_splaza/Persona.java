package com.example.realm_splaza;

import io.realm.RealmObject;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;

public class Persona extends RealmObject {
    @PrimaryKey
    private String dni;

    @Index
    private String nombre;
    private int edad;
    private String genero;
    private int numTel;

    public Persona(String dni, String nombre, int edad, String genero, int numTel) {
        this.dni = dni;
        this.nombre = nombre;
        this.edad = edad;
        this.genero = genero;
        this.numTel = numTel;
    }
    public Persona (){

    }
    @Override
    public String toString() {
        return "Persona{" +
                "dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", genero='" + genero + '\'' +
                ", numTel ='" + numTel + '\'' +
                '}';
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getNumTel(){return  numTel;}
    public void setNumTel(int numTel){this.numTel = numTel;}
}
