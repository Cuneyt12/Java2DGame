package com.game.board;

import com.game.Main;
import com.game.Mermi;
import com.game.target.Target;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class Board extends JPanel implements KeyListener, ActionListener {
    Timer timer = new Timer(1, this);
    private int atisAdedi = 0;
    private BufferedImage img, imgFace;
    public static ArrayList<Mermi> mermiler = new ArrayList<Mermi>();
    Target target = new Target();
    int kalanHak = 2;
    boolean mermiBittiMi = false;
    private int isabetliAtisSayisi = 0;
    private int toplamAtisAdedi = 0;
    private boolean hedefBittiMi = false;
    private int derece = 0;
    private int toplamPuan = 150;

    public Board(){
        try {
            img = ImageIO.read(new FileImageInputStream(new File("C:\\Users\\Cüneyt\\IdeaProjects\\2DGame\\src\\rocket.png")));
            imgFace = ImageIO.read(new FileImageInputStream(new File("C:\\Users\\Cüneyt\\IdeaProjects\\2DGame\\src\\face.png")));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("YOK");
        }
        setBackground(Color.black);
       timer.start();
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        super.paint(g2d);
        AffineTransform id  = new AffineTransform();
        g2d.setColor(Color.WHITE);
        g2d.drawString("Kalan Hak: " + (this.kalanHak + 1), 15, 15);
        g2d.setColor(Color.red);
        g2d.rotate(Math.toRadians(this.derece), getWidth() / 2, getHeight() - 70);
        g2d.drawImage(img, getWidth() / 2, getHeight() - 70, img.getWidth() / 10 , img.getHeight() / 10, this);
        g2d.setTransform(id);

        for (int i = 0; i < target.hedefler.size(); i++){
           // g2d.setColor(Color.red);
            //g2d.fillOval((int)target.hedefler.get(i).getX(), (int)target.hedefler.get(i).getY(), 20, 20);
            g2d.drawImage(imgFace, (int)target.hedefler.get(i).getX(), (int)target.hedefler.get(i).getY(), img.getWidth() / 15 , img.getHeight() / 15, this);
        }
        if (this.hedefBittiMi){
            this.hedefBittiMi = false;
            String msgg = "Oyun bitti. Toplam puanınız: " + this.toplamPuan;
            JOptionPane.showMessageDialog(this, msgg);
            System.exit(0);
            return;
        }

        for (Mermi m: mermiler){
            m.hareketEt();
            if (this.atisAdedi == 15 && (mermiler.get(mermiler.size() - 1).getOrtalaY() < 0 || mermiler.get(mermiler.size() - 1).getOrtalaX() < 0 ||
                    mermiler.get(mermiler.size() - 1).getOrtalaX() > Main.GENISLIK) && this.kalanHak >= 0){
                if(this.kalanHak > 0){
                    this.atisAdedi = 0;
                    timer.stop();
                    int msg = JOptionPane.showConfirmDialog(null, "Kalan oyun hakkınız " + (this.kalanHak ) + ". Devam etmek ister misiniz?", "Oyun bitti.", JOptionPane.YES_NO_OPTION);
                    if (msg == JOptionPane.YES_OPTION){
                        this.kalanHak--;
                        this.mermiBittiMi = false;
                        timer.start();
                    }
                    else{
                        this.toplamPuan += ((this.isabetliAtisSayisi * 10) - ((this.toplamAtisAdedi - this.isabetliAtisSayisi) * 10));
                        String msgg = "Oyun bitti. Toplam puanınız: " + this.toplamPuan;
                        JOptionPane.showMessageDialog(this, msgg);
                        System.exit(0);
                    }

                }
                else if((mermiler.get(mermiler.size() - 1).getOrtalaY() < 0 || mermiler.get(mermiler.size() - 1).getOrtalaX() < Main.GENISLIK ||
                        mermiler.get(mermiler.size() - 1).getOrtalaX() > Main.GENISLIK) && this.kalanHak == 0){
                    timer.stop();
                    this.toplamPuan += ((this.isabetliAtisSayisi * 10) - ((this.toplamAtisAdedi - this.isabetliAtisSayisi) * 10));
                    String msg = "Oyun bitti. Toplam puanınız: " + this.toplamPuan;
                    JOptionPane.showMessageDialog(this, msg);
                    System.exit(0);
                }
            }
            if (m.ekranda){
                g2d.setTransform(id);
                g2d.translate(m.getOrtalaX() , m.getOrtalaY());
                g2d.draw(m);
            }
        }
    }
    @Override
    public void repaint() {
        super.repaint();
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        int c = keyEvent.getKeyCode();

        if (c == KeyEvent.VK_LEFT){
            this.derece -= 5;
        }
        else if (c == KeyEvent.VK_RIGHT){
            this.derece += 5;
        }
        else if(c == KeyEvent.VK_UP){
            if (this.atisAdedi < 15){
                this.atisAdedi++;
                this.toplamAtisAdedi++;
                mermiler.add(new Mermi(this.getWidth() / 2 + 25,
                        this.getHeight() - 70, this.derece - 90));
            }
        }
    }
    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        target.createTarget();
        for (Target t : target.hedefler){
                if (t.getY() < Main.YUKSEKLIK - 40 && !t.isKonum()){
                    t.setY(t.getY() + t.getHiz());
                }
                 else {
                     t.setKonum(true);
                 }
                 if (t.isKonum()){
                     t.setHiz(t.getHiz() + 0.03);
                     t.setY(t.getY() - t.getHiz());
                     if (t.getY() < 0){
                         t.setKonum(false);
                     }
                 }
        }
        if (kontrolEt())
           this.isabetliAtisSayisi++;
        repaint();
    }

    public boolean kontrolEt(){
        for(Mermi mermi : mermiler){
            for(Target t : target.hedefler){
                if (new Rectangle((int)mermi.getOrtalaX(),(int)mermi.getOrtalaY(),10,10).intersects(new Rectangle((int)t.getX(),(int)t.getY(),20,20))) {
                    target.hedefler.remove(t);
                    mermiler.remove(mermi);
                    if (target.hedefler.size() == 0){
                        this.hedefBittiMi = true;
                    }
                    return true;
                }
            }
        }
        return false;
    }
}