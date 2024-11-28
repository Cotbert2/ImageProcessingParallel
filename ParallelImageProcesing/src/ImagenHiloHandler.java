import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;

public class ImagenHiloHandler extends  Thread{

    private String csvFilePath;
    private String currentPhoto;


    public ImagenHiloHandler(String currentPhoto, String csvFilePath) {
        super(currentPhoto);
        this.csvFilePath = csvFilePath;
        this.currentPhoto = currentPhoto;
    }

    public void run() {

        String csvLoadData = "";
        try {
            File archivo = new File(Constants.INPUT_PATH + currentPhoto);
            System.out.println("Image size: " + archivo.length() + " bytes");

            csvLoadData += currentPhoto + "," + archivo.length() + ",";

            BufferedImage imagen = ImageIO.read(archivo);

            int altura = imagen.getHeight();
            int ancho = imagen.getWidth();

            System.out.println("Procesando imagen de " + ancho + "x" + altura);

            csvLoadData += ancho + "," + altura + ",";

            int numeroHilos = 4;
            Thread[] hilos = new Thread[numeroHilos];

            int filasPorHilo = altura / numeroHilos;
            int finFila;

            long inicio = System.nanoTime();

            for (int i = 0; i < numeroHilos; i++) {
                int inicioFila = i * filasPorHilo;

                if (i == numeroHilos - 1) {
                    finFila = altura;
                } else {
                    finFila = inicioFila + filasPorHilo;
                }

                hilos[i] = new Thread(new FiltroGris(imagen, inicioFila, finFila));
                hilos[i].start();
            }

            for (Thread hilo : hilos) {
                hilo.join();
            }

            File archivoSalida = new File(Constants.OUTPUT_PATH_CONCURRENTE + currentPhoto);
            ImageIO.write(imagen, "jpg", archivoSalida);

            long fin = System.nanoTime(); // Registrar tiempo final

            System.out.println("Imagen procesada y guardada como 'output_" + currentPhoto + ".jpg'");
            System.out.println("Tiempo de ejecución: " + (fin - inicio) / 1_000_000 + " ms");

            csvLoadData += (fin - inicio) / 1_000_000 + "\n";

            File csvFile = new File(csvFilePath);
            if (!csvFile.exists()) {
                csvFile.createNewFile();
            }

            FileWriter csvWriter = new FileWriter(csvFile, true);
            csvWriter.write(csvLoadData);
            csvWriter.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
