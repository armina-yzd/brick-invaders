import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

public class Changiz implements  Interface {
    public static PApplet a = SpaceInvaders.processing;
    private static PImage changiz1;
    private static PFont mono;

    static float x = 230;
    static float y = -250;
    static boolean pos = true;
    static int endure = 1000;

    public static float getY() {
        return y;
    }

    public static float getX() {
        return x;
    }

    public static int getEndure() {
        return endure;
    }

    @Override
    public void ShowObject() {
        changiz1 = a.loadImage("changiz.png");
    }

    public static void showchangiz(PImage changiz) {
        mono = a.createFont("waltographUI.ttf", 30);
        a.textFont(mono);
        a.text(Changiz.getEndure(), 340, 50);


        a.image(changiz, x, y, 300, 250);

        if (pos) {
            x++;
            if (x > 500) {
                y++;
                x--;
                if (y % 90 == 0) {
                    x++;
                    pos = false;

                }
            }
        } else {
            x--;
            if (x < 0) {
                y++;
                x++;
                if (y % 90 == 0) {
                    x--;
                    pos = true;

                }

            }
        }
    }

    public static void movechangizfirst(PImage changiz) {

        if (y >= 60 && a.frameCount > 3350) {
            showchangiz(changiz);
        } else if (y < 60){
            a.image(changiz, x, y, 300, 250);
        y++;
        endure = 1000;

        }else{
            mono = a.createFont("waltographUI.ttf", 30);
            a.textFont(mono);
            a.text(1000, 340, 50);

            a.image(changiz, x,y, 300,250);
            endure = 1000;


        }

    }

    public static boolean Killchangiz(float bulletx, float bullety){

        if(x>=bulletx-320 && x<=bulletx  && y>=bullety-220 && y<=bullety-210) {
            endure--;
            if (endure == 0) {
                return true;
            }
        }

        return false;
    }
    public static boolean removebullet(float bulletx, float bullety){

        if(x>=bulletx-320 && x<=bulletx  && y>=bullety-220 && y<=bullety-200) {
                return true;

        }

        return false;
    }

}
