/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.awt.image.BufferedImage;

/**
 * docente andrespillajo
 * Integantes: Mateo Garcia, sebastian Parra,Jefferson Yepez
 */
public class FiltroGris implements Runnable{
    private final BufferedImage imagen;
    private final int inicioFila;
    private final int finFila;

    public FiltroGris(BufferedImage imagen, int inicioFila, int finFila) {
        this.imagen = imagen;
        this.inicioFila = inicioFila;
        this.finFila = finFila;
    }

    @Override
    public void run() {
        for (int y = inicioFila; y < finFila; y++) {
            for (int x = 0; x < imagen.getWidth(); x++) {
                int pixel = imagen.getRGB(x, y);

                int rojo = (pixel >> 16) & 0xff;
                int verde = (pixel >> 8) & 0xff;
                int azul = pixel & 0xff;

                int gris = (rojo + verde + azul) / 3;

                int nuevoPixel = (gris << 16) | (gris << 8) | gris;

                imagen.setRGB(x, y, nuevoPixel);
            }
        }
    }

}
