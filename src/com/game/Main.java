package com.game;

import com.game.board.Board;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class Main extends JFrame {
    public static final int GENISLIK = 500;
    public static final int YUKSEKLIK = 500;

    public Main() {

        initUI();
    }

    private void initUI() {
        setSize(GENISLIK, YUKSEKLIK);
        setTitle("Java Game");
        setResizable(false);
        setFocusable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        Board b = new Board();
        b.requestFocus();
        b.addKeyListener(b);
        b.setFocusable(true);
        b.setFocusTraversalKeysEnabled(false);

        this.add(b);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            Main m = new Main();
            m.setVisible(true);
        });
    }
}