public class ImagenesHilos {
    public static void main(String[] args) {
        //Iniciar un nuevo hilo para cada imagen
        for(int i = 1; i <= 30; i++) new ImagenHiloHandler("img" + i + ".jpg", Constants.CONCURRENTE_CSV_CONJUNTO).start();
    }
}
