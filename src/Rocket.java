import processing.core.PApplet;
import processing.core.PImage;

public class Rocket implements Interface{
    public static PApplet a = SpaceInvaders.processing;

    private static PImage rocket1;

    @Override
    public void ShowObject() {
        rocket1 = a.loadImage("rocket.png");
    }

    public static void showRocket(int x,int y,PImage rocket) {
        a.image(rocket, x,y, 40,55);

    }
}
