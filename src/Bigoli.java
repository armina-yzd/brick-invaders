import processing.core.PApplet;
import processing.core.PImage;

public class Bigoli implements Interface {
    public static PApplet a = SpaceInvaders.processing;
    static private PImage bigoli1;
    static float y=0;
    static float x=a.random(15,745);
    static float ydir=4;

    public static void setY(float y) {
        Bigoli.y = y;
        x=a.random(15,745);
    }

    @Override
    public void ShowObject() {

        bigoli1 = a.loadImage("star.png");
    }

    public static void Showbigoli(PImage bigoli) {

        a.image(bigoli, x,y, 35,30);
        y+=ydir;
        if(y>1000){
            y=0;
            x=a.random(15,745);
        }
    }

    public static boolean Getbigoli(float ssx, float ssy){

        if(ssx-35<=x && ssx+128>=x && ssy<=y && ssy+105>=y ) {
            return true;
        }

        return false;
    }

}

