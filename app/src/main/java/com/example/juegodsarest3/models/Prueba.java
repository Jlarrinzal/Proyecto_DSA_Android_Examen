package com.example.juegodsarest3.models;

public class Prueba {

    String nickname;

    String fecha;

    double puntos;

    String avatar;

    public Prueba(){}

    public Prueba(String nickname, String fecha, double puntos, String avatar){
        this.nickname=nickname;
        this.fecha=fecha;
        this.puntos=puntos;
        this.avatar=avatar;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getPuntos() {
        return puntos;
    }

    public void setPuntos(double puntos) {
        this.puntos = puntos;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
