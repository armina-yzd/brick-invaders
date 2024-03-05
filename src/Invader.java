import processing.core.PApplet;
import processing.core.PImage;

public class Invader implements Interface {
    float i;
    int ydir;
    float y;
    float x;
    int endurnce;
    public static PApplet a = SpaceInvaders.processing;
    private PImage invader;

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }



    public Invader(float x){
        this.x=x;
         y=a.random(-200,0);
        i=a.random(0,3);

        if(i<=1){
            endurnce=10;
            ydir=2;
        } else if (i<=2) {
            endurnce=20;
            ydir=1;
        }else{
            endurnce=1;
            ydir=3;
        }
    }

    public void setYdir(int ydir) {
        this.ydir = ydir;
    }

    public int getYdir() {
        return ydir;
    }

    @Override
    public void ShowObject() {
        if(i==1){
            invader = a.loadImage("invader1.png");
        } else if (i==2) {
            invader = a.loadImage("invader2.png");
        }else{
            invader = a.loadImage("invader3.png");
        }

    }

    public void Showinvader(PImage invader1,PImage invader2,PImage invader3) {
        if(i<=1){
            a.image(invader1, x,y, 45,35);

        } else if (i<=2) {
            a.image(invader2, x,y, 50,40);
        }else{
            a.image(invader3, x,y, 50,35);
        }
        y+=ydir;

    }
    public boolean KillInvader(float bulletx, float bullety){

            if(x>=bulletx-82 && x<=bulletx+50  && y+50>=bullety && y<=bullety) {
                endurnce--;
                if (endurnce == 0) {
                    return true;
                }
            }

        return false;
    }
    public boolean removebullet(float bulletx, float bullety){

        if(x>=bulletx-82 && x<=bulletx+50  && y+50>=bullety && y<=bullety) {

                return true;
        }

        return false;
    }
    public int getScore(){
        if(i<=1){
            return 2;

        } else if (i<=2) {
            return 3;
        }
        return 1;


    }

}
