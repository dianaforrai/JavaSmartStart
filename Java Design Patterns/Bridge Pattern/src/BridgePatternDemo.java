// Client
public class BridgePatternDemo {
    public static void main(String[] args) {
        Renderer raster = new RasterRenderer();
        Renderer vector = new VectorRenderer();

        Shape circle1 = new Circle(raster, 5);
        Shape circle2 = new Circle(vector, 7);

        Shape square1 = new Square(raster, 4);
        Shape square2 = new Square(vector, 6);

        circle1.draw();
        circle2.draw();
        square1.draw();
        square2.draw();
    }
}
