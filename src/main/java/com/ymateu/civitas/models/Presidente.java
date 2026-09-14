package com.ymateu.civitas.models;

public class Presidente {
    public Presidente() {}

    public Presidente(int carisma, String partido, float popularidade, int confianca, float apoio) {
        this.carisma = carisma;
        this.partido = partido;
        this.popularidade = popularidade;
        this.confianca = confianca;
        this.apoio = apoio;
    }

    private int carisma;
    private String partido;
    private float popularidade;
    private int confianca;
    private float apoio;

    public String getPartido() {
        return partido;
    }

    public void setPartido(String partido) {
        this.partido = partido;
    }

    public int getCarisma() {
        return carisma;
    }

    public void setCarisma(int carisma) {
        this.carisma = carisma;
    }

    public float getPopularidade() {
        return popularidade;
    }

    public void setPopularidade(float popularidade) {
        this.popularidade = popularidade;
    }

    public int getConfianca() {
        return confianca;
    }

    public void setConfianca(int confianca) {
        this.confianca = confianca;
    }

    public float getApoio() {
        return apoio;
    }

    public void setApoio(float apoio) {
        this.apoio = apoio;
    }

    @Override
    public String toString() {
        return "Presidente{" +
                "carisma=" + carisma +
                ", partido='" + partido + '\'' +
                ", popularidade=" + popularidade +
                ", confianca=" + confianca +
                ", apoio=" + apoio +
                '}';
    }
}
