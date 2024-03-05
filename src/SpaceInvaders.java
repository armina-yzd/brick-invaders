import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

import java.util.ArrayList;

public class SpaceInvaders extends PApplet {

    public static PApplet processing;

    ArrayList<Integer> scores=Database.GetScore();
    int highScore=0;

    PFont mono;
    PImage img;
    PImage img1;
    PImage img2;
    PImage img3;
    PImage img4;
    PImage spaceship;
    PImage bigoli;
    PImage invader1,invader2,invader3;
    PImage bullets;
    PImage changizs;
    PImage rocket;


    boolean check=true;
    int lives=3;
    static boolean menu=true;
    static boolean help=false;
    static boolean game=false;
    static  boolean changiz=false;
    static boolean lose=false;
    static boolean win=false;

    boolean speed=true;
    boolean shootbullet=true;
    int[] index=new int[3];
    int count=0;
    int score=0;

    Bullet[] bullet=new Bullet[3];

    ArrayList<Invader> invaders=new ArrayList<>();


    public static void main(String[] args) {
        PApplet.main("SpaceInvaders", args);
    }

    public void setup() {
        processing = this;

        img = loadImage("background.png");
        img1 = loadImage("help.png");
        img2 = loadImage("game.png");
        img3 = loadImage("RIP.png");
        img4 = loadImage("win.jpg");
        spaceship = loadImage("spaceship.png");
        bigoli = loadImage("star.png");
        invader1 = loadImage("invader1.png");
        invader2 = loadImage("invader2.png");
        invader3 = loadImage("invader3.png");
        bullets = loadImage("bullet.png");
        changizs = loadImage("changiz.png");
        rocket = loadImage("rocket.png");

        bullet[0]=new Bullet(mouseY,mouseX);
        bullet[1]=new Bullet(mouseY,mouseX);
        bullet[2]=new Bullet(mouseY,mouseX);
        index[0]=-1;
        index[1]=-1;
        index[2]=-1;

        for(int i=0; i<scores.size(); i++){
            if(scores.get(i)>highScore){
                highScore=scores.get(i);
            }
        }

    }

    public void settings() {
        size(800, 600);
    }

    public void draw() {
        if(menu){
           

            background(img);

            
            fill(0);
            mono = createFont("waltographUI.ttf", 33);
            textFont(mono);
            text("Start Game", 290, 295);
            text("Help", 355, 360);

            if (mouseX >= 290 && mouseX <= 500 && mouseY >= 275 && mouseY <= 300) {
                if (mousePressed) {
                   game=true;
                   menu=false;
                }
            }

            if (mouseX >= 355 && mouseX <= 430 && mouseY >= 340 && mouseY <= 365) {
                if (mousePressed) {
                   help=true;
                   menu=false;
                }
            }

        }if(help){
            background(img1);

            fill(225);
            mono = createFont("waltographUI.ttf", 60);
            textFont(mono);
            text("<-",740,590);

            mono = createFont("waltographUI.ttf", 16);
            textFont(mono);
            text("Hi Welcome To Space Invaders",260,150);
            text("We Want to Protect Galaxy From Aliens",220,200);
            text("Please Help Us In This Journey You Can Move",195,250);
            text("The SpaceShip With Moos And Shoot Aliens To Kill ",170,300);
            text("Them, There Are Some Presents That Help You ",195,350);
            text("in The Game To Help You Achieve Success ",220,400);
            text("Hope You Enjoy This Journey",260,450);



            if (mouseX >= 740 && mouseX <= 778 && mouseY >= 558 && mouseY <= 580) {
                if (mousePressed) {
                   menu=true;
                   help=false;
                }
            }


        }if(game){
            background(img2);

            fill(225);
            mono = createFont("waltographUI.ttf", 23);
            textFont(mono);
            text("Score : "+score,20,50);
            int x=755;
            for (int i=0; i<lives;i++) {

                Rocket.showRocket(x, 20,rocket);
                textSize(50);
                x -= 45;
            }
            if(frameCount>2750){
                changiz=true;

            }
            if(frameCount>=3000){

                if(Changiz.getY()>=360){
                    lose=true;
                }
                if (frameCount > 3350) {
                   shootbullet=true;
                }else {
                    shootbullet=false;
                }
                fill(225);
                Changiz.movechangizfirst(changizs);

                for(int j=0; j<3; j++){
                    if(Changiz.Killchangiz(bullet[j].getXpos(),bullet[j].getYpos())){
                        win=true;
                        game=false;
                    }
                    if(Changiz.removebullet(bullet[j].getXpos(),bullet[j].getYpos())){
                        index[j]=-1;

                    }

                }
                if(Spaceship.losechangiz(Changiz.getX(),Changiz.getY()) && count==0){
                    lives--;
                     if(lives==0) {
                        lose=true;
                        game=false;
                    }
                    count=frameCount;
                }

            }
            if(frameCount-count==15){
                count=0;
            }


            Spaceship.showspaceship(spaceship);

            if(frameCount%60==30 && !changiz ){
                Invader invader1=new Invader(random(5,120));
                Invader invader2=new Invader(random(160,280));
                Invader invader3=new Invader(random(320,440));
                Invader invader4=new Invader(random(480,600));
                Invader invader5=new Invader(random(640,745));

                invaders.add(invader1);
                invaders.add(invader2);
                invaders.add(invader3);
                invaders.add(invader4);
                invaders.add(invader5);

                speed=true;

            }
            if(frameCount>=1200 && speed ){
                for(int i=invaders.size()-1; i>invaders.size()-5; i--){
                    invaders.get(i).setYdir(invaders.get(i).getYdir()+1);
                }
                speed=false;

            }

            for(int i=0; i<invaders.size(); i++){
                invaders.get(i).Showinvader(invader1,invader2,invader3);
            }
            for (int i=0; i<invaders.size(); i++){
                for(int j=0; j<3; j++){
                    if(invaders.get(i).KillInvader(bullet[j].getXpos(),bullet[j].getYpos())){
                        index[j]=-1;
                        score+=invaders.get(i).getScore();
                        invaders.remove(i);
                        break;
                    }
                    if(invaders.get(i).removebullet(bullet[j].getXpos(),bullet[j].getYpos())){
                        index[j]=-1;
                        break;
                    }

                }
            }


            if( frameCount>=300 &&!changiz ){
                Bigoli.Showbigoli(bigoli);
            }
            if(Bigoli.Getbigoli(mouseX - 64,mouseY - 80)){
                score+=20;
                Bigoli.setY(-600);
            }

            if(frameCount%30==10 && frameCount>28 && shootbullet){
                index[0]=1;
                bullet[0].setYpos(mouseY);
                bullet[0].setXpos(mouseX);

            }

            if(index[0]==1){
                bullet[0].Movebullet(bullets);

            }

            if(frameCount%30==20 && frameCount>28 && shootbullet){
                index[1]=1;
                bullet[1].setYpos(mouseY);
                bullet[1].setXpos(mouseX);

            }

            if(index[1]==1){
                bullet[1].Movebullet(bullets);
            }


            if(frameCount%30==0 && frameCount>28 && shootbullet){
                bullet[2].setYpos(mouseY);
                bullet[2].setXpos(mouseX);
                index[2]=1;
            }


            if(index[2]==1){
                bullet[2].Movebullet(bullets);

            }
            for (int i=0; i<invaders.size(); i++){

                if(Spaceship.lose(invaders.get(i).getX(),invaders.get(i).getY()) ){
                    lives--;
                    invaders.remove(i);

                    if(lives==0) {
                        lose=true;
                        game=false;
                    }
                    break;
                }

            }



        }if(lose){
            if(check){
                Database.AddScore(score);
                if(score>highScore){
                    highScore=score;
                }
                check=false;
            }
            background(img3);
            fill(225);
            mono = createFont("waltographUI.ttf", 60);
            textFont(mono);
            text("Game Over",170,130);

            mono = createFont("waltographUI.ttf", 30);
            textFont(mono);
            text("Your Score : "+score,30,260);
            text("High Score : "+highScore,30,360);
            text("Play Again",540,550);
            if (mouseX >= 545 && mouseX <= 775 && mouseY >= 520 && mouseY <= 550) {
                if (mousePressed) {
                    score=0;
                    lives=3;
                    check=true;
                    game=true;
                    changiz=false;
                    frameCount=0;
                    invaders.clear();
                    lose=false;
                }
            }

        }if(win){
            if(check){
                Database.AddScore(score);
                if(score>highScore){
                    highScore=score;
                }
                check=false;
            }
            background(img4);

            fill(225);
            mono = createFont("waltographUI.ttf", 70);
            textFont(mono);

            text("You Won",210,80);
            mono = createFont("waltographUI.ttf", 30);
            textFont(mono);
            text("Your Score : "+score,250,140);
            text("High Score : "+highScore,250,190);

            mono = createFont("waltographUI.ttf", 40);
            textFont(mono);
            text("Play Again",280,510);
            if (mouseX >= 210 && mouseX <= 590 && mouseY >= 530 && mouseY <= 565) {
                if (mousePressed) {
                    score=0;
                    lives=3;
                    check=true;
                    game=true;
                    changiz=false;
                    frameCount=0;
                    invaders.clear();
                    win=false ;
                }
            }

        }
    }
    
}
