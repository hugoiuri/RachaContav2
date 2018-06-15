package br.com.hugo.rachacontav2.model;

import java.io.Serializable;

public class Pessoa implements Serializable {
    private String name;
    private byte[] foto;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }
}
