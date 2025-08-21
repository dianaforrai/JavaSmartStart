public class Fan {
    private int speed = 0; // 0 = off

    public void increaseSpeed() {
        speed++;
        System.out.println("Fan speed increased to " + speed);
    }

    public void decreaseSpeed() {
        if(speed > 0) speed--;
        System.out.println("Fan speed decreased to " + speed);
    }
}
