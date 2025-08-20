// Renderer interface
interface Renderer {
    void renderCircle(float radius);
    void renderSquare(float side);
}

// Concrete Renderer: Raster
class RasterRenderer implements Renderer {
    @Override
    public void renderCircle(float radius) {
        System.out.println("Raster rendering a circle with radius " + radius);
    }

    @Override
    public void renderSquare(float side) {
        System.out.println("Raster rendering a square with side " + side);
    }
}

// Concrete Renderer: Vector
class VectorRenderer implements Renderer {
    @Override
    public void renderCircle(float radius) {
        System.out.println("Vector rendering a circle with radius " + radius);
    }

    @Override
    public void renderSquare(float side) {
        System.out.println("Vector rendering a square with side " + side);
    }
}

// Shape abstraction
abstract class Shape {
    protected Renderer renderer;

    protected Shape(Renderer renderer) {
        this.renderer = renderer;
    }

    abstract void draw();
}

// Concrete Shape: Circle
class Circle extends Shape {
    private float radius;

    public Circle(Renderer renderer, float radius) {
        super(renderer);
        this.radius = radius;
    }

    @Override
    void draw() {
        renderer.renderCircle(radius);
    }
}

// Concrete Shape: Square
class Square extends Shape {
    private float side;

    public Square(Renderer renderer, float side) {
        super(renderer);
        this.side = side;
    }

    @Override
    void draw() {
        renderer.renderSquare(side);
    }
}

