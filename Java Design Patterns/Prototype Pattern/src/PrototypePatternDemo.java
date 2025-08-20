public class PrototypePatternDemo {
    public static void main(String[] args) {
        // Create registry and add shapes
        ShapeRegistry registry = new ShapeRegistry();
        registry.addShape("circle1", new Circle(5));
        registry.addShape("rectangle1", new Rectangle(10, 20));

        // Clone shapes from registry
        Shape clonedCircle = registry.getShape("circle1");
        Shape clonedRectangle = registry.getShape("rectangle1");

        // Draw cloned shapes
        clonedCircle.draw();
        clonedRectangle.draw();

        // Modify cloned shapes without affecting original
        ((Circle) clonedCircle).setRadius(15);
        ((Rectangle) clonedRectangle).setWidth(30);

        System.out.println("After modifying clones:");
        clonedCircle.draw();
        clonedRectangle.draw();

        // Original shapes remain unchanged
        System.out.println("Original shapes:");
        registry.getShape("circle1").draw();
        registry.getShape("rectangle1").draw();
    }
}
