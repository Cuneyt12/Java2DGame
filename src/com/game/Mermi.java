package com.game;

import java.awt.*;

public class Mermi extends Polygon {
    int oAGenislik = Main.GENISLIK;
    int oAYukseklik = Main.YUKSEKLIK;
    private double ortalaX = 0, ortalaY = 0;

    public static int[] cokgenXArray = {-5, 5, 5 -5, -5};
    public static int[] cokgenYArray = {-5, -5, 5, 5, -5};
    private int mermiGenislik = 5, mermiYukseklik = 6;

    public boolean ekranda = false;

    private double hareketAcisi = 0, xHiz = 50, yHiz = 50;

    public Mermi(double ortalaX, double ortalaY, double hareketAcisi){
        super(cokgenXArray, cokgenYArray, 3);
        this.ortalaX = ortalaX;
        this.ortalaY = ortalaY;
        this.hareketAcisi = hareketAcisi;
        this.ekranda = true;
        this.setxHiz(this.mermiXHizHareketAcisi(hareketAcisi) * 7);
        this.setyHiz(this.mermiYHizHareketAcisi(hareketAcisi) * 7);
    }

    public void xPozisyonDegistir(double arttir){
        this.ortalaX += arttir;
    }
    public void yPozisyonDegistir(double arttir){
        this.ortalaY += arttir;
    }

    public double mermiXHizHareketAcisi(double xHareketAcisi){
        return Math.cos(xHareketAcisi * Math.PI / 180);

    }
    public double mermiYHizHareketAcisi(double yHareketAcisi){
        return Math.sin(yHareketAcisi * Math.PI / 180);

    }
    public void hareketEt(){
        if (this.ekranda){
            this.xPozisyonDegistir(this.getxHiz());
            if (this.getOrtalaX() < 0 ){
                this.ekranda = false;
            }
            else if (this.getOrtalaX() > oAGenislik ){
                this.ekranda = false;
            }
            this.yPozisyonDegistir(this.getyHiz());
            if (this.getOrtalaY() < 0 ){
                this.ekranda = false;
            }
            else if (this.getOrtalaY() > oAYukseklik ){
                this.ekranda = false;
            }
        }
    }

    public double getOrtalaX() {
        return ortalaX;
    }

    public void setOrtalaX(double ortalaX) {
        this.ortalaX = ortalaX;
    }

    public double getOrtalaY() {
        return ortalaY;
    }

    public void setOrtalaY(double ortalaY) {
        this.ortalaY = ortalaY;
    }

    public double getHareketAcisi() {
        return hareketAcisi;
    }

    public void setHareketAcisi(double hareketAcisi) {
        this.hareketAcisi = hareketAcisi;
    }

    public double getxHiz() {
        return xHiz;
    }

    public void setxHiz(double xHiz) {
        this.xHiz = xHiz;
    }

    public double getyHiz() {
        return yHiz;
    }

    public void setyHiz(double yHiz) {
        this.yHiz = yHiz;
    }


}
