public class MultipleSecuencial {
    public static void main(String[] args) {
        for (int i = 1; i <= 30; i++) {
            ImagenSecuencial imagenSecuencial = new ImagenSecuencial("img" + i + ".jpg", Constants.OUTPUT_PATH_SECUENCIAL);
        }
    }
}
