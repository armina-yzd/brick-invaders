import processing.core.PApplet;
import processing.core.PImage;

public class Spaceship implements Interface {
    public static PApplet a = SpaceInvaders.processing;
    private static PImage spaceship;

    @Override
    public void ShowObject() {
        spaceship = a.loadImage("spaceship.png");
    }

    public static void showspaceship(PImage spaceship) {
        int directionX = a.mouseX - 64;
        int directionY = a.mouseY - 80;

        a.image(spaceship, directionX,directionY, 128,105);
    }

    public static boolean lose(float x,float y){
        if(a.mouseX-64-45<=x && a.mouseX-64+128>=x && a.mouseY-80<=y && a.mouseY-80+105>=y ){
            return true;
        }
        return false;
    }

    public static boolean losechangiz(float x,float y){

        if(x-128<=a.mouseX-64 && x+300>=a.mouseX-64  && y+200>=a.mouseY-80 && y<=a.mouseY-80) {
            return true;

        }



        return false;
    }

}
