import processing.core.PApplet;
import processing.core.PImage;

public class Bullet implements Interface{
    public static PApplet a = SpaceInvaders.processing;
    int ydir=15;
    float ypos;
    float xpos;
    private PImage bullet1;

    public Bullet(int ypos,int xpos){
        this.ypos = ypos;
        this.xpos=xpos;
    }

    public float getYpos() {
        return ypos;
    }

    public void setYpos(float ypos) {
        this.ypos = ypos;
    }

    public void setXpos(float xpos) {
        this.xpos = xpos;
    }

    public float getXpos() {
        return xpos;
    }

    @Override
    public void ShowObject() {
        bullet1 = a.loadImage("bullet.png");
    }
    public void Movebullet(PImage bullet){


        a.image(bullet,xpos-47,ypos-60, 15,30);
        a.image(bullet,xpos-6,ypos-100, 15,30);
        a.image(bullet,xpos+35,ypos-60, 15,30);

        ypos-=ydir;
    }
}
