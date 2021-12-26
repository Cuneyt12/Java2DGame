package com.game.target;

import com.game.Main;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Target {
    public static final int ADET = 15;
    private double x;
    private double y;
    private double hiz = 1;
    int sayac = 0;
    public ArrayList<Target> hedefler = new ArrayList<Target>();
    int toplamHedef=0;
    Random rnd = new Random();
    private boolean konum = false;

    public Target(){

    }

    public Target(double x, double y, double hiz, boolean konum) {
        this.x = x;
        this.y = y;
        this.hiz = hiz;
        this.konum = konum;
    }


    public void createTarget(){
        int randomSayi = 0;
        sayac++;
        if (toplamHedef < ADET){
            randomSayi = rnd.nextInt(200) + 2;
            if (randomSayi > 100){
                if (sayac >= randomSayi) {
                    hedefler.add(new Target(rnd.nextInt(Main.GENISLIK), 0, getHiz(), false));
                    toplamHedef++;
                    sayac=0;
                }
            }
        }
    }

    public double getX() {
        return this.x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return this.y;
    }

    public void setY(double y) {
        this.y = y;
    }
    public double getHiz() {
        return this.hiz;
    }

    public void setHiz(double hiz) {
        this.hiz = hiz;
    }

    public boolean isKonum() {
        return konum;
    }

    public void setKonum(boolean konum) {
        this.konum = konum;
    }
}
