public class ImagenConcurrente {
    public static void main(String[] args) {
        new ImagenHiloHandler("img1.jpg", Constants.OUTPUT_PATH_CONCURRENTE).start();
    }
}
