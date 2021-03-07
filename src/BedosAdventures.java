import acm.graphics.*;
import acm.program.*;
import acm.util.MediaTools;
import acm.util.RandomGenerator;
import java.awt.event.KeyEvent;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


public class BedosAdventures extends GraphicsProgram {
	
	public static final int APPLICATION_HEIGHT = 295 * 2;
	public static final int APPLICATION_WIDTH = 600 * 2;
	private GImage startbg = new GImage("startbg.png");
	private GImage start1 = new GImage("start23.png");
	private GImage start2 = new GImage("start1.png");
	private GImage enemy1 = new GImage("japonleft.GIF");
	private GImage enemy2 = new GImage("japonright.GIF");
	private RandomGenerator rg = new RandomGenerator();
	private GImage olu2 = new GImage("ölü2.png");
	private GImage olu3 = new GImage("ölü3.png");
	private GImage ewolf = new GImage("kurtleft.GIF");
	private GImage fwolf = new GImage("kurtright.GIF");
	private GImage dwolf = new GImage("ölükurt1.png");
	private GImage cwolf = new GImage("ölükurt2.png");
	private GImage devilright = new GImage("devilright.GIF");
	private GImage devilleft = new GImage("devilleft.GIF");
	private GImage devildeadleft = new GImage("devildeadleft.png");
	private GImage devildeadright = new GImage("devildeadright.png");
	private ArrayList<GImage> right;  
	private ArrayList<GImage> left; 
	private ArrayList<GImage> up; 
	private ArrayList<GImage> down; 
	private GImage charrightmove1 = new GImage("charrightmove1.png");
	private GImage charleftmove1 = new GImage("charleftmove1.png");
	private GImage charupmove1 = new GImage("charupmove1.png");
	private GImage chardownmove1 = new GImage("chardownmove1.png");
	private GImage charrightmove2 = new GImage("charrightmove2.png");
	private GImage charleftmove2 = new GImage("charleftmove2.png");
	private GImage charupmove2 = new GImage("charupmove2.png");
	private GImage chardownmove2 = new GImage("chardownmove2.png");
	AudioClip deaths = MediaTools.loadAudioClip("death.au");
	AudioClip fires = MediaTools.loadAudioClip("fire.au");
	AudioClip gameovers = MediaTools.loadAudioClip("game over.au");
	AudioClip song = MediaTools.loadAudioClip("theme song.au");
	private static GImage back = new GImage("arka.png");
	int count = 0;
	int countleft = 0;
	int countright = 0;
	int countup = 0;
	int countdown = 0;
	int mcountright = 0;
	int mcountleft = 0;
	int mcountup = 0;
	int mcountdown = 0;
	int move;
	int score = 0;
	int crwolfcount = 0;
	int clwolfcount = 0;
	int crjaponcount = 0;
	int cljaponcount = 0;
	int crdevilcount = 0;
	int cldevilcount = 0;
	GLabel scoreshow;
	GLabel endscoreshow;
	GLabel gameover;
	GLabel again;
	GRect end;
	GLabel howto1;
	GLabel howto2;
	public void run() {
		//run();
		starting();
		olu2.scale(2);
		olu3.scale(2);
		back.scale(2);
		devildeadleft.scale(1.5);
		devildeadright.scale(1.5);
		ewolf.scale(1.5);
		dwolf.scale(1.5);
		fwolf.scale(1.5);
		cwolf.scale(1.5);
		enemy1.scale(2);
		enemy2.scale(2);
		devilright.scale(1.5);
		devilleft.scale(1.5);
		charrightmove1.scale(3);
		charrightmove2.scale(3);
		charleftmove1.scale(3);
		charleftmove2.scale(3);
		charupmove1.scale(3);
		charupmove2.scale(3);
		chardownmove1.scale(3);
		chardownmove2.scale(3);
		add(back);
		add(charrightmove1, getWidth()/2 - charrightmove1.getWidth() / 2, getHeight() / 2 + 50);
		howto1 = new GLabel("Use arrow keys to move and space to shoot");
		howto1.setFont("Impact-25");
		howto1.setColor(Color.WHITE);
		add(howto1, getWidth() / 2 - howto1.getWidth() / 2, getHeight() / 2 - 100);
		howto2 = new GLabel("If enemies cross the whole area, you lose");
		howto2.setFont("Impact-25");
		howto2.setColor(Color.RED);
		add(howto2, getWidth() / 2 - howto2.getWidth() / 2, getHeight() / 2 - 50);
		click();
		add(back);
		song.play();
		add(charrightmove1);
		right = new ArrayList<GImage>();
		left = new ArrayList<GImage>();  
		up = new ArrayList<GImage>();  
		down = new ArrayList<GImage>();  
		addKeyListeners();
		addMouseListeners();
		score();
		try {
			 Runtime.getRuntime().exec("bodybasics.exe");
			} catch (Exception hata) {
			 System.out.println("Hata: " + hata.getMessage());
			}
		while(true){
			japanleft();
			japanright();
			wolfleft();
			wolfright();
			devilright();
			devilleft();
			devilright.move(0.75, 0);
			devilleft.move(-0.75, 0);
			ewolf.move(-1.5, 0);
			fwolf.move(1.5, 0);
			enemy1.move(-1, 0);
			enemy2.move(1, 0);
			if(enemy1.getX() < -enemy1.getWidth() || enemy2.getX() > getWidth() || ewolf.getX() < -ewolf.getWidth() || fwolf.getX() > getWidth() || devilleft.getX() < -devilleft.getWidth() || devilright.getX() > getWidth()){
				endscore();
			}
			if(countright == 1){
				animateAduketRight();
			} 
			if(countleft == 1){
				animateAduketLeft();
			}
			if(countup == 1){
				animateAduketUp();
			}
			if(countdown == 1){
				animateAduketDown();
			}
			pause(10);
		}
	}
	
	public void createLazer(){
		
		if(count == 0 && mcountright == 0){
			GImage lazer = new GImage("aduketright.png");
		    double x = charrightmove1.getX() + charrightmove1.getWidth()/2 - 4;
			double y =  charrightmove1.getY()+ charrightmove1.getHeight()/2 - 12; 
			lazer.scale(0.1);
			add(lazer, x , y );
			right.add(lazer);
			countright = 1;
			fires.play();
		}
		else if(count == 0 && mcountright == 1){
			GImage lazer = new GImage("aduketright.png");
		    double x = charrightmove2.getX() + charrightmove2.getWidth()/2 - 4;
			double y =  charrightmove2.getY()+ charrightmove2.getHeight()/2 - 12; 
			lazer.scale(0.1);
			add(lazer, x , y );
			right.add(lazer);
			countright = 1;
			fires.play();
		}
		if(count == 1 && mcountleft == 0){
			GImage lazer = new GImage("aduketleft.png");
		    double x = charleftmove1.getX() + charleftmove1.getWidth()/2 - 32;
			double y =  charleftmove1.getY()+ charleftmove1.getHeight()/2 - 12; 
			lazer.scale(0.1);
			add(lazer, x , y );
			left.add(lazer);
			countleft = 1;
			fires.play();
		}
		else if(count == 1 && mcountleft == 1){
			GImage lazer = new GImage("aduketleft.png");
		    double x = charleftmove2.getX() + charleftmove2.getWidth()/2 - 32;
			double y =  charleftmove2.getY()+ charleftmove2.getHeight()/2 - 12; 
			lazer.scale(0.1);
			add(lazer, x , y );
			left.add(lazer);
			countleft = 1;
			fires.play();
		}
		if(count == 2 && mcountup == 0){
			GImage lazer = new GImage("aduketup.png");
		    double x = charupmove1.getX() + charupmove1.getWidth()/2 - 16;
			double y =  charupmove1.getY()+ charupmove1.getHeight()/2 - 32; 
			lazer.scale(0.1);
			add(lazer, x , y );
			up.add(lazer);
			countup = 1;
			fires.play();
		}
		else if(count == 2 && mcountup == 1){
			GImage lazer = new GImage("aduketup.png");
		    double x = charupmove2.getX() + charupmove2.getWidth()/2 - 16;
			double y =  charupmove2.getY()+ charupmove2.getHeight()/2 - 32; 
			lazer.scale(0.1);
			add(lazer, x , y );
			up.add(lazer);
			countup = 1;
			fires.play();
		}
		if(count == 3 && mcountup == 0){
			GImage lazer = new GImage("aduketdown.png");
		    double x = chardownmove1.getX() + chardownmove1.getWidth()/2 - 16;
			double y =  chardownmove1.getY()+ chardownmove1.getHeight()/2 - 12; 
			lazer.scale(0.1);
			add(lazer, x , y );
			down.add(lazer);
			countdown = 1;
			fires.play();
		}
		else if(count == 3 && mcountup == 1){
			GImage lazer = new GImage("aduketdown.png");
		    double x = chardownmove2.getX() + chardownmove2.getWidth()/2 - 16;
			double y =  chardownmove2.getY()+ chardownmove2.getHeight()/2 - 12; 
			lazer.scale(0.1);
			add(lazer, x , y );
			down.add(lazer);
			countdown = 1;
			fires.play();
		}
	}
			
		
	private void animateAduketRight() {
		for(int i = right.size() - 1; i >= 0; i--) {
			GImage oval = right.get(i);
			oval.move(3, 0);
			if(oval.getX() > getWidth()) {
				remove(right.get(i));
				right.remove(i);
			}
			//JAPANLEFT
			if( getElementAt( oval.getX(), oval.getY()) == enemy1  ){
				remove(right.get(i));
				right.remove(i);
				deaths.play();
				score = score + 25;
				remove(scoreshow);
				score();
				remove(enemy1);
				add(olu2,enemy1.getX(),enemy1.getY()+60);
				cljaponcount = 0;
			}
			if( getElementAt( oval.getX()+32, oval.getY() + 10) == enemy1  ){
				remove(right.get(i));
				right.remove(i);
				deaths.play();
				score = score + 25;
				remove(scoreshow);
				score();
				remove(enemy1);
				add(olu2,enemy1.getX(),enemy1.getY()+60);
				cljaponcount = 0;
			}
			if( getElementAt( oval.getX()+32, oval.getY()+18) == enemy1  ){
				remove(right.get(i));
				right.remove(i);
				deaths.play();
				score = score + 25;
				remove(scoreshow);
				score();
				remove(enemy1);
				add(olu2,enemy1.getX(),enemy1.getY()+60);
				cljaponcount = 0;
			}
			if( getElementAt( oval.getX(), oval.getY()+18) == enemy1  ){
				remove(right.get(i));
				right.remove(i);
				deaths.play();
				score = score + 25;
				remove(scoreshow);
				score();
				remove(enemy1);
				add(olu2,enemy1.getX(),enemy1.getY()+60);
				cljaponcount = 0;
			}
			//JAPANRIGHT
			if( getElementAt( oval.getX(), oval.getY()) == enemy2  ){
				remove(right.get(i));
				right.remove(i);
				deaths.play();
				score = score + 25;
				remove(scoreshow);
				score();
				remove(enemy2);
				add(olu3,enemy2.getX(),enemy2.getY()+60);
				crjaponcount = 0;
			}
			if( getElementAt( oval.getX()+32, oval.getY() + 10) == enemy2  ){
				remove(right.get(i));
				right.remove(i);
				deaths.play();
				score = score + 25;
				remove(scoreshow);
				score();
				remove(enemy2);
				add(olu3,enemy2.getX(),enemy2.getY()+60);
				crjaponcount = 0;
			}
			if( getElementAt( oval.getX()+32, oval.getY()+18) == enemy2  ){
				remove(right.get(i));
				right.remove(i);
				deaths.play();
				score = score + 25;
				remove(scoreshow);
				score();
				remove(enemy2);
				add(olu3,enemy2.getX(),enemy2.getY()+60);
				crjaponcount = 0;
			}
			if( getElementAt( oval.getX(), oval.getY()+18) == enemy2  ){
				remove(right.get(i));
				right.remove(i);
				deaths.play();
				score = score + 25;
				remove(scoreshow);
				score();
				remove(enemy2);
				add(olu3,enemy2.getX(),enemy2.getY()+60);
				crjaponcount = 0;
			}
			//WOLFLEFT
			if( getElementAt( oval.getX(), oval.getY()) == ewolf  ){
				remove(right.get(i));
				right.remove(i);
				deaths.play();
				score = score + 50;
				remove(scoreshow);
				score();
				remove(ewolf);
				add(dwolf,ewolf.getX(),ewolf.getY()+60);
				clwolfcount = 0;
			}
			if( getElementAt( oval.getX()+32, oval.getY() + 10) == ewolf  ){
				remove(right.get(i));
				right.remove(i);
				deaths.play();
				score = score + 50;
				remove(scoreshow);
				score();
				remove(ewolf);
				add(dwolf,ewolf.getX(),ewolf.getY()+60);
				clwolfcount = 0;
			}
			if( getElementAt( oval.getX()+32, oval.getY()+18) == ewolf  ){
				remove(right.get(i));
				right.remove(i);
				deaths.play();
				score = score + 50;
				remove(scoreshow);
				score();
				remove(ewolf);
				add(dwolf,ewolf.getX(),ewolf.getY()+60);
				clwolfcount = 0;
			}
			if( getElementAt( oval.getX(), oval.getY()+18) == ewolf  ){
				remove(right.get(i));
				right.remove(i);
				deaths.play();
				score = score + 50;
				remove(scoreshow);
				score();
				remove(ewolf);
				add(dwolf,ewolf.getX(),ewolf.getY()+60);	
				clwolfcount = 0;
			}
			//WOLFRIGHT 
			if( getElementAt( oval.getX(), oval.getY()) == fwolf  ){
				remove(right.get(i));
				right.remove(i);
				deaths.play();
				score = score + 50;
				remove(scoreshow);
				score();
				remove(fwolf);
				add(cwolf,fwolf.getX(),fwolf.getY()+60);
				crwolfcount = 0;
			}
			if( getElementAt( oval.getX()+32, oval.getY() + 10) == fwolf  ){
				remove(right.get(i));
				right.remove(i);
				deaths.play();
				score = score + 50;
				remove(scoreshow);
				score();
				remove(fwolf);
				add(cwolf,fwolf.getX(),fwolf.getY()+60);
				crwolfcount = 0;
			}
			if( getElementAt( oval.getX()+32, oval.getY()+18) == fwolf  ){
				remove(right.get(i));
				right.remove(i);
				deaths.play();
				score = score + 50;
				remove(scoreshow);
				score();
				remove(fwolf);
				add(cwolf,fwolf.getX(),fwolf.getY()+60);
				crwolfcount = 0;
			}
			if( getElementAt( oval.getX(), oval.getY()+18) == fwolf  ){
				remove(right.get(i));
				right.remove(i);
				deaths.play();
				score = score + 50;
				remove(scoreshow);
				score();
				remove(fwolf);
				add(cwolf,fwolf.getX(),fwolf.getY()+60);	
				crwolfcount = 0;
			}
			//DEVILLEFT
			if( getElementAt( oval.getX(), oval.getY()) == devilleft  ){
				remove(right.get(i));
				right.remove(i);
				deaths.play();
				score = score + 75;
				remove(scoreshow);
				score();
				remove(devilleft);
				add(devildeadleft,devilleft.getX(),devilleft.getY()+60);
				cldevilcount = 0;
			}
			if( getElementAt( oval.getX()+32, oval.getY() + 10) == devilleft  ){
				remove(right.get(i));
				right.remove(i);
				deaths.play();
				score = score + 75;
				remove(scoreshow);
				score();
				remove(devilleft);
				add(devildeadleft,devilleft.getX(),devilleft.getY()+60);
				cldevilcount = 0;
			}
			if( getElementAt( oval.getX()+32, oval.getY()+18) == devilleft  ){
				remove(right.get(i));
				right.remove(i);
				deaths.play();
				score = score + 75;
				remove(scoreshow);
				score();
				remove(devilleft);
				add(devildeadleft,devilleft.getX(),devilleft.getY()+60);
				cldevilcount = 0;
			}
			if( getElementAt( oval.getX(), oval.getY()+18) == devilleft  ){
				remove(right.get(i));
				right.remove(i);
				deaths.play();
				score = score + 75;
				remove(scoreshow);
				score();
				remove(devilleft);
				add(devildeadleft,devilleft.getX(),devilleft.getY()+60);	
				cldevilcount = 0;
			}
			//DEVILRIGHT
			if( getElementAt( oval.getX(), oval.getY()) == devilright  ){
				remove(right.get(i));
				right.remove(i);
				deaths.play();
				score = score + 75;
				remove(scoreshow);
				score();
				remove(devilright);
				add(devildeadright,devilright.getX(),devilright.getY()+60);
				crdevilcount = 0;
			}
			if( getElementAt( oval.getX()+32, oval.getY() + 10) == devilright  ){
				remove(right.get(i));
				right.remove(i);
				deaths.play();
				score = score + 75;
				remove(scoreshow);
				score();
				remove(devilright);
				add(devildeadright,devilright.getX(),devilright.getY()+60);
				crdevilcount = 0;
			}
			if( getElementAt( oval.getX()+32, oval.getY()+18) == devilright  ){
				remove(right.get(i));
				right.remove(i);
				deaths.play();
				score = score + 75;
				remove(scoreshow);
				score();
				remove(devilright);
				add(devildeadright,devilright.getX(),devilright.getY()+60);
				crdevilcount = 0;
			}
			if( getElementAt( oval.getX(), oval.getY()+18) == devilright  ){
				remove(right.get(i));
				right.remove(i);
				deaths.play();
				score = score + 75;
				remove(scoreshow);
				score();
				remove(devilright);
				add(devildeadright,devilright.getX(),devilright.getY()+60);	
				crdevilcount = 0;
			}
		}
		
	}
	
	private void animateAduketLeft(){
		for(int i = left.size() - 1; i >= 0; i--) {
			GImage oval = left.get(i);
			oval.move(-3, 0);
			if(oval.getX() < 0) {
				remove(left.get(i));
				left.remove(i);
			}
			//JAPAN
			if( getElementAt( oval.getX(), oval.getY()) == enemy1  ){
				remove(left.get(i));
				left.remove(i);
				deaths.play();
				score = score + 25;
				remove(scoreshow);
				score();
				remove(enemy1);
				add(olu2,enemy1.getX(),enemy1.getY()+60);
				cljaponcount = 0;
			}
			if( getElementAt( oval.getX()+32, oval.getY() + 10) == enemy1  ){
				remove(left.get(i));
				left.remove(i);
				deaths.play();
				score = score + 25;
				remove(scoreshow);
				score();
				add(olu2,enemy1.getX(),enemy1.getY()+60);
			}
			if( getElementAt( oval.getX()+32, oval.getY()+18) == enemy1  ){
				remove(left.get(i));
				left.remove(i);
				deaths.play();
				score = score + 25;
				remove(scoreshow);
				score();
				remove(enemy1);
				add(olu2,enemy1.getX(),enemy1.getY()+60);
				cljaponcount = 0;
			}
			if( getElementAt( oval.getX(), oval.getY()+18) == enemy1  ){
				remove(left.get(i));
				left.remove(i);
				deaths.play();
				score = score + 25;
				remove(scoreshow);
				score();
				remove(enemy1);
				add(olu2,enemy1.getX(),enemy1.getY()+60);
				cljaponcount = 0;
			}
			//JAPANRIGHT
			if( getElementAt( oval.getX(), oval.getY()) == enemy2  ){
				remove(left.get(i));
				left.remove(i);
				deaths.play();
				score = score + 25;
				remove(scoreshow);
				score();
				remove(enemy2);
				add(olu3,enemy2.getX(),enemy2.getY()+60);
				crjaponcount = 0;
			}
			if( getElementAt( oval.getX()+32, oval.getY() + 10) == enemy2  ){
				remove(left.get(i));
				left.remove(i);
				deaths.play();
				score = score + 25;
				remove(scoreshow);
				score();
				remove(enemy2);
				add(olu3,enemy2.getX(),enemy2.getY()+60);
				crjaponcount = 0;
			}
			if( getElementAt( oval.getX()+32, oval.getY()+18) == enemy2  ){
				remove(left.get(i));
				left.remove(i);
				deaths.play();
				score = score + 25;
				remove(scoreshow);
				score();
				remove(enemy2);
				add(olu3,enemy2.getX(),enemy2.getY()+60);	
				crjaponcount = 0;
			}
			if( getElementAt( oval.getX(), oval.getY()+18) == enemy2  ){
				remove(left.get(i));
				left.remove(i);
				deaths.play();
				score = score + 25;
				remove(scoreshow);
				score();
				remove(enemy2);
				add(olu3,enemy2.getX(),enemy2.getY()+60);
				crjaponcount = 0;
			}
			//WOLFLEFT
			if( getElementAt( oval.getX(), oval.getY()) == ewolf  ){
				remove(left.get(i));
				left.remove(i);
				deaths.play();
				score = score + 50;
				remove(scoreshow);
				score();
				remove(ewolf);
				add(dwolf,ewolf.getX(),ewolf.getY()+60);
				clwolfcount = 0;
			}
			if( getElementAt( oval.getX()+32, oval.getY() + 10) == ewolf  ){
				remove(left.get(i));
				left.remove(i);
				deaths.play();
				score = score + 50;
				remove(scoreshow);
				score();
				remove(ewolf);
				add(dwolf,ewolf.getX(),ewolf.getY()+60);
				clwolfcount = 0;
			}
			if( getElementAt( oval.getX()+32, oval.getY()+18) == ewolf  ){
				remove(left.get(i));
				left.remove(i);
				deaths.play();
				score = score + 50;
				remove(scoreshow);
				score();
				remove(ewolf);
				add(dwolf,ewolf.getX(),ewolf.getY()+60);
				clwolfcount = 0;
			}
			if( getElementAt( oval.getX(), oval.getY()+18) == ewolf  ){
				remove(left.get(i));
				left.remove(i);
				deaths.play();
				score = score + 50;
				remove(scoreshow);
				score();
				remove(ewolf);
				add(dwolf,ewolf.getX(),ewolf.getY()+60);
				clwolfcount = 0;
			}
			//WOLFRIGHT
			if( getElementAt( oval.getX(), oval.getY()) == fwolf  ){
				remove(left.get(i));
				left.remove(i);
				deaths.play();
				score = score + 50;
				remove(scoreshow);
				score();
				remove(fwolf);
				add(cwolf,fwolf.getX(),fwolf.getY()+60);
				crwolfcount = 0;
			}
			if( getElementAt( oval.getX()+32, oval.getY() + 10) == fwolf  ){
				remove(left.get(i));
				left.remove(i);
				deaths.play();
				score = score + 50;
				remove(scoreshow);
				score();
				remove(fwolf);
				add(cwolf,fwolf.getX(),fwolf.getY()+60);
				crwolfcount = 0;
			}
			if( getElementAt( oval.getX()+32, oval.getY()+18) == fwolf  ){
				remove(left.get(i));
				left.remove(i);
				deaths.play();
				score = score + 50;
				remove(scoreshow);
				score();
				remove(fwolf);
				add(cwolf,fwolf.getX(),fwolf.getY()+60);
				crwolfcount = 0;
			}
			if( getElementAt( oval.getX(), oval.getY()+18) == fwolf  ){
				remove(left.get(i));
				left.remove(i);
				deaths.play();
				score = score + 50;
				remove(scoreshow);
				score();
				remove(fwolf);
				add(cwolf,fwolf.getX(),fwolf.getY()+60);	
				crwolfcount = 0;
			}
			//DEVILLEFT
			if( getElementAt( oval.getX(), oval.getY()) == devilleft  ){
				remove(left.get(i));
				left.remove(i);
				deaths.play();
				score = score + 75;
				remove(scoreshow);
				score();
				remove(devilleft);
				add(devildeadleft,devilleft.getX(),devilleft.getY()+60);
				cldevilcount = 0;
			}
			if( getElementAt( oval.getX()+32, oval.getY() + 10) == devilleft  ){
				remove(left.get(i));
				left.remove(i);
				deaths.play();
				score = score + 75;
				remove(scoreshow);
				score();
				remove(devilleft);
				add(devildeadleft,devilleft.getX(),devilleft.getY()+60);
				cldevilcount = 0;
			}
			if( getElementAt( oval.getX()+32, oval.getY()+18) == devilleft  ){
				remove(left.get(i));
				left.remove(i);
				deaths.play();
				score = score + 75;
				remove(scoreshow);
				score();
				remove(devilleft);
				add(devildeadleft,devilleft.getX(),devilleft.getY()+60);
				cldevilcount = 0;
			}
			if( getElementAt( oval.getX(), oval.getY()+18) == devilleft  ){
				remove(left.get(i));
				left.remove(i);
				deaths.play();
				score = score + 75;
				remove(scoreshow);
				score();
				remove(devilleft);
				add(devildeadleft,devilleft.getX(),devilleft.getY()+60);
				cldevilcount = 0;
			}
			//DEVILRIGHT
			if( getElementAt( oval.getX(), oval.getY()) == devilright  ){
				remove(left.get(i));
				left.remove(i);
				deaths.play();
				score = score + 75;
				remove(scoreshow);
				score();
				remove(devilright);
				add(devildeadright,devilright.getX(),devilright.getY()+60);
				crdevilcount = 0;
			}
			if( getElementAt( oval.getX()+32, oval.getY() + 10) == devilright  ){
				remove(left.get(i));
				left.remove(i);
				deaths.play();
				score = score + 75;
				remove(scoreshow);
				score();
				remove(devilright);
				add(devildeadright,devilright.getX(),devilright.getY()+60);
				crdevilcount = 0;
			}
			if( getElementAt( oval.getX()+32, oval.getY()+18) == devilright  ){
				remove(left.get(i));
				left.remove(i);
				deaths.play();
				score = score + 75;
				remove(scoreshow);
				score();
				remove(devilright);
				add(devildeadright,devilright.getX(),devilright.getY()+60);
				crdevilcount = 0;
			}
			if( getElementAt( oval.getX(), oval.getY()+18) == devilright  ){
				remove(left.get(i));
				left.remove(i);
				deaths.play();
				score = score + 75;
				remove(scoreshow);
				score();
				remove(devilright);
				add(devildeadright,devilright.getX(),devilright.getY()+60);	
				crdevilcount = 0;
			}
		}
		
	}
	
	private void animateAduketUp(){
		for(int i = up.size() - 1; i >= 0; i--) {
			GImage oval = up.get(i);
			oval.move(0, -3);
			if(oval.getY() < 0) {
				remove(up.get(i));
				up.remove(i);
			}	
			//JAPANLEFT
			if( getElementAt( oval.getX(), oval.getY()) == enemy1  ){
				remove(up.get(i));
				up.remove(i);
				deaths.play();
				score = score + 25;
				remove(scoreshow);
				score();
				remove(enemy1);
				add(olu2,enemy1.getX(),enemy1.getY()+60);
				cljaponcount = 0;
			}
			if( getElementAt( oval.getX()+32, oval.getY() + 10) == enemy1  ){
				remove(up.get(i));
				up.remove(i);
				deaths.play();
				score = score + 25;
				remove(scoreshow);
				score();
				remove(enemy1);
				add(olu2,enemy1.getX(),enemy1.getY()+60);
				cljaponcount = 0;
			}
			if( getElementAt( oval.getX()+32, oval.getY()+18) == enemy1  ){
				remove(up.get(i));
				up.remove(i);
				deaths.play();
				score = score + 25;
				remove(scoreshow);
				score();
				remove(enemy1);
				add(olu2,enemy1.getX(),enemy1.getY()+60);
				cljaponcount = 0;
				
			}
			if( getElementAt( oval.getX(), oval.getY()+18) == enemy1  ){
				remove(up.get(i));
				up.remove(i);
				deaths.play();
				score = score + 25;
				remove(scoreshow);
				score();
				remove(enemy1);
				add(olu2,enemy1.getX(),enemy1.getY()+60);
				cljaponcount = 0;
			}
			//JAPANRIGHT
			if( getElementAt( oval.getX(), oval.getY()) == enemy2  ){
				remove(up.get(i));
				up.remove(i);
				deaths.play();
				score = score + 25;
				remove(scoreshow);
				score();
				remove(enemy2);
				add(olu3,enemy2.getX(),enemy2.getY()+60);
				crjaponcount = 0;
			}
			if( getElementAt( oval.getX()+32, oval.getY() + 10) == enemy2  ){
				remove(up.get(i));
				up.remove(i);
				deaths.play();
				score = score + 25;
				remove(scoreshow);
				score();
				remove(enemy2);
				add(olu3,enemy2.getX(),enemy2.getY()+60);
				crjaponcount = 0;
			}
			if( getElementAt( oval.getX()+32, oval.getY()+18) == enemy2  ){
				remove(up.get(i));
				up.remove(i);
				deaths.play();
				score = score + 25;
				remove(scoreshow);
				score();
				remove(enemy2);
				add(olu3,enemy2.getX(),enemy2.getY()+60);
				crjaponcount = 0;
				
			}
			if( getElementAt( oval.getX(), oval.getY()+18) == enemy2  ){
				remove(up.get(i));
				up.remove(i);
				deaths.play();
				score = score + 25;
				remove(scoreshow);
				score();
				remove(enemy2);
				add(olu3,enemy2.getX(),enemy2.getY()+60);
				crjaponcount = 0;
			}
			//WOLFLEFT
			if( getElementAt( oval.getX(), oval.getY()) == ewolf  ){
				remove(up.get(i));
				up.remove(i);
				deaths.play();
				score = score + 50;
				remove(scoreshow);
				score();
				remove(ewolf);
				add(dwolf,ewolf.getX(),ewolf.getY()+60);
				clwolfcount = 0;
			}
			if( getElementAt( oval.getX()+32, oval.getY() + 10) == ewolf  ){
				remove(up.get(i));
				up.remove(i);
				deaths.play();
				score = score + 50;
				remove(scoreshow);
				score();
				remove(ewolf);
				add(dwolf,ewolf.getX(),ewolf.getY()+60);
				clwolfcount = 0;
			}
			if( getElementAt( oval.getX()+32, oval.getY()+18) == ewolf  ){
				remove(up.get(i));
				up.remove(i);
				deaths.play();
				score = score + 50;
				remove(scoreshow);
				score();
				remove(ewolf);
				add(dwolf,ewolf.getX(),ewolf.getY()+60);
				clwolfcount = 0;
			}
			if( getElementAt( oval.getX(), oval.getY()+18) == ewolf  ){
				remove(up.get(i));
				up.remove(i);
				deaths.play();
				score = score + 50;
				remove(scoreshow);
				score();
				remove(ewolf);
				add(dwolf,ewolf.getX(),ewolf.getY()+60);
				clwolfcount = 0;
			}
			//WOLFRIGHT
			if( getElementAt( oval.getX(), oval.getY()) == fwolf  ){
				remove(up.get(i));
				up.remove(i);
				deaths.play();
				score = score + 50;
				remove(scoreshow);
				score();
				remove(fwolf);
				add(cwolf,fwolf.getX(),fwolf.getY()+60);
				crwolfcount = 0;
			}
			if( getElementAt( oval.getX()+32, oval.getY() + 10) == fwolf  ){
				remove(up.get(i));
				up.remove(i);
				deaths.play();
				score = score + 50;
				remove(scoreshow);
				score();
				remove(fwolf);
				add(cwolf,fwolf.getX(),fwolf.getY()+60);
				crwolfcount = 0;
			}
			if( getElementAt( oval.getX()+32, oval.getY()+18) == fwolf  ){
				remove(up.get(i));
				up.remove(i);
				deaths.play();
				score = score + 50;
				remove(scoreshow);
				score();
				remove(fwolf);
				add(cwolf,fwolf.getX(),fwolf.getY()+60);
				crwolfcount = 0;
			}
			if( getElementAt( oval.getX(), oval.getY()+18) == fwolf  ){
				remove(up.get(i));
				up.remove(i);
				deaths.play();
				score = score + 50;
				remove(scoreshow);
				score();
				remove(fwolf);
				add(cwolf,fwolf.getX(),fwolf.getY()+60);	
				crwolfcount = 0;
			}
			//DEVILLEFT
			if( getElementAt( oval.getX(), oval.getY()) == devilleft  ){
				remove(up.get(i));
				up.remove(i);
				deaths.play();
				score = score + 75;
				remove(scoreshow);
				score();
				remove(devilleft);
				add(devildeadleft,devilleft.getX(),devilleft.getY()+60);
				cldevilcount = 0;
			}
			if( getElementAt( oval.getX()+32, oval.getY() + 10) == devilleft  ){
				remove(up.get(i));
				up.remove(i);
				deaths.play();
				score = score + 75;
				remove(scoreshow);
				score();
				remove(devilleft);
				add(devildeadleft,devilleft.getX(),devilleft.getY()+60);
				cldevilcount = 0;
			}
			if( getElementAt( oval.getX()+32, oval.getY()+18) == devilleft  ){
				remove(up.get(i));
				up.remove(i);
				deaths.play();
				score = score + 75;
				remove(scoreshow);
				score();
				remove(devilleft);
				add(devildeadleft,devilleft.getX(),devilleft.getY()+60);
				cldevilcount = 0;
			}
			if( getElementAt( oval.getX(), oval.getY()+18) == devilleft  ){
				remove(up.get(i));
				up.remove(i);
				deaths.play();
				score = score + 75;
				remove(scoreshow);
				score();
				remove(devilleft);
				add(devildeadleft,devilleft.getX(),devilleft.getY()+60);	
				cldevilcount = 0;
			}
			//DEVILRIGHT
			if( getElementAt( oval.getX(), oval.getY()) == devilright  ){
				remove(up.get(i));
				up.remove(i);
				deaths.play();
				score = score + 75;
				remove(scoreshow);
				score();
				remove(devilright);
				add(devildeadright,devilright.getX(),devilright.getY()+60);
				crdevilcount = 0;
			}
			if( getElementAt( oval.getX()+32, oval.getY() + 10) == devilright  ){
				remove(up.get(i));
				up.remove(i);
				deaths.play();
				score = score + 75;
				remove(scoreshow);
				score();
				remove(devilright);
				add(devildeadright,devilright.getX(),devilright.getY()+60);
				crdevilcount = 0;
			}
			if( getElementAt( oval.getX()+32, oval.getY()+18) == devilright  ){
				remove(up.get(i));
				up.remove(i);
				deaths.play();
				score = score + 75;
				remove(scoreshow);
				score();
				remove(devilright);
				add(devildeadright,devilright.getX(),devilright.getY()+60);
				crdevilcount = 0;
			}
			if( getElementAt( oval.getX(), oval.getY()+18) == devilright  ){
				remove(up.get(i));
				up.remove(i);
				deaths.play();
				score = score + 75;
				remove(scoreshow);
				score();
				remove(devilright);
				add(devildeadright,devilright.getX(),devilright.getY()+60);
				crdevilcount = 0;
			}
		}
		
	}
	private void animateAduketDown(){
		for(int i = down.size() - 1; i >= 0; i--) {
			GImage oval = down.get(i);
			oval.move(0, 3);
			if(oval.getY() > getHeight()) {
				remove(down.get(i));
				down.remove(i);
			}
			//JAPANLEFT
			if( getElementAt( oval.getX(), oval.getY()) == enemy1  ){
				remove(down.get(i));
				down.remove(i);
				deaths.play();
				score = score + 25;
				remove(scoreshow);
				score();
				remove(enemy1);
				add(olu2,enemy1.getX(),enemy1.getY()+60);
				cljaponcount = 0;
			}
			if( getElementAt( oval.getX()+32, oval.getY() + 10) == enemy1  ){
				remove(down.get(i));
				down.remove(i);
				deaths.play();
				score = score + 25;
				remove(scoreshow);
				score();
				remove(enemy1);
				add(olu2,enemy1.getX(),enemy1.getY()+60);
				cljaponcount = 0;
			}
			if( getElementAt( oval.getX()+32, oval.getY()+18) == enemy1  ){
				remove(down.get(i));
				down.remove(i);
				deaths.play();
				score = score + 25;
				remove(scoreshow);
				score();
				remove(enemy1);
				add(olu2,enemy1.getX(),enemy1.getY()+60);	
				cljaponcount = 0;
			}
			if( getElementAt( oval.getX(), oval.getY()+18) == enemy1  ){
				remove(down.get(i));
				down.remove(i);
				deaths.play();
				score = score + 25;
				remove(scoreshow);
				score();
				remove(enemy1);
				add(olu2,enemy1.getX(),enemy1.getY()+60);
				cljaponcount = 0;
			}
			//JAPANRIGHT
			if( getElementAt( oval.getX(), oval.getY()) == enemy2  ){
				remove(down.get(i));
				down.remove(i);
				deaths.play();
				score = score + 25;
				remove(scoreshow);
				score();
				remove(enemy2);
				add(olu3,enemy2.getX(),enemy2.getY()+60);
				crjaponcount = 0;
			}
			if( getElementAt( oval.getX()+32, oval.getY() + 10) == enemy2  ){
				remove(down.get(i));
				down.remove(i);
				deaths.play();
				score = score + 25;
				remove(scoreshow);
				score();
				remove(enemy2);
				add(olu3,enemy2.getX(),enemy2.getY()+60);
				crjaponcount = 0;
			}
			if( getElementAt( oval.getX()+32, oval.getY()+18) == enemy2  ){
				remove(down.get(i));
				down.remove(i);
				deaths.play();
				score = score + 25;
				remove(scoreshow);
				score();
				remove(enemy2);
				add(olu3,enemy2.getX(),enemy2.getY()+60);	
				crjaponcount = 0;
			}
			if( getElementAt( oval.getX(), oval.getY()+18) == enemy2  ){
				remove(down.get(i));
				down.remove(i);
				deaths.play();
				score = score + 25;
				remove(scoreshow);
				score();
				remove(enemy2);
				add(olu3,enemy2.getX(),enemy2.getY()+60);
				crjaponcount = 0;
			}
			//WOLFLEFT
			if( getElementAt( oval.getX(), oval.getY()) == ewolf  ){
				remove(down.get(i));
				down.remove(i);
				deaths.play();
				score = score + 50;
				remove(scoreshow);
				score();
				remove(ewolf);
				add(dwolf,ewolf.getX(),ewolf.getY()+60);
				clwolfcount = 0;
			}
			if( getElementAt( oval.getX()+32, oval.getY() + 10) == ewolf  ){
				remove(down.get(i));
				down.remove(i);
				deaths.play();
				score = score + 50;
				remove(scoreshow);
				score();
				remove(ewolf);
				add(dwolf,ewolf.getX(),ewolf.getY()+60);
				clwolfcount = 0;
			}
			if( getElementAt( oval.getX()+32, oval.getY()+18) == ewolf  ){
				remove(down.get(i));
				down.remove(i);
				deaths.play();
				score = score + 50;
				remove(scoreshow);
				score();
				remove(ewolf);
				add(dwolf,ewolf.getX(),ewolf.getY()+60);
				clwolfcount = 0;
			}
			if( getElementAt( oval.getX(), oval.getY()+18) == ewolf  ){
				remove(down.get(i));
				down.remove(i);
				deaths.play();
				score = score + 50;
				remove(scoreshow);
				score();
				remove(ewolf);
				add(dwolf,ewolf.getX(),ewolf.getY()+60);	
				clwolfcount = 0;
			}
			//WOLFRIGHT
			if( getElementAt( oval.getX(), oval.getY()) == fwolf  ){
				remove(down.get(i));
				down.remove(i);
				deaths.play();
				score = score + 50;
				remove(scoreshow);
				score();
				remove(fwolf);
				add(cwolf,fwolf.getX(),fwolf.getY()+60);
				crwolfcount = 0;
			}
			if( getElementAt( oval.getX()+32, oval.getY() + 10) == fwolf  ){
				remove(down.get(i));
				down.remove(i);
				deaths.play();
				score = score + 50;
				remove(scoreshow);
				score();
				remove(fwolf);
				add(cwolf,fwolf.getX(),fwolf.getY()+60);
				crwolfcount = 0;
			}
			if( getElementAt( oval.getX()+32, oval.getY()+18) == fwolf  ){
				remove(down.get(i));
				down.remove(i);
				deaths.play();
				score = score + 50;
				remove(scoreshow);
				score();
				remove(fwolf);
				add(cwolf,fwolf.getX(),fwolf.getY()+60);
				crwolfcount = 0;
			}
			if( getElementAt( oval.getX(), oval.getY()+18) == fwolf  ){
				remove(down.get(i));
				down.remove(i);
				deaths.play();
				score = score + 50;
				remove(scoreshow);
				score();
				remove(fwolf);
				add(cwolf,fwolf.getX(),fwolf.getY()+60);	
				crwolfcount = 0;
			}
			//DEVILLEFT
			if( getElementAt( oval.getX(), oval.getY()) == devilleft  ){
				remove(down.get(i));
				down.remove(i);
				deaths.play();
				score = score + 75;
				remove(scoreshow);
				score();
				remove(devilleft);
				add(devildeadleft,devilleft.getX(),devilleft.getY()+60);
				cldevilcount = 0;
			}
			if( getElementAt( oval.getX()+32, oval.getY() + 10) == devilleft  ){
				remove(down.get(i));
				down.remove(i);
				deaths.play();
				score = score + 75;
				remove(scoreshow);
				score();
				remove(devilleft);
				add(devildeadleft,devilleft.getX(),devilleft.getY()+60);
				cldevilcount = 0;
			}
			if( getElementAt( oval.getX()+32, oval.getY()+18) == devilleft  ){
				remove(down.get(i));
				down.remove(i);
				deaths.play();
				score = score + 75;
				remove(scoreshow);
				score();
				remove(devilleft);
				add(devildeadleft,devilleft.getX(),devilleft.getY()+60);
				cldevilcount = 0;
			}
			if( getElementAt( oval.getX(), oval.getY()+18) == devilleft  ){
				remove(down.get(i));
				down.remove(i);
				deaths.play();
				score = score + 75;
				remove(scoreshow);
				score();
				remove(devilleft);
				add(devildeadleft,devilleft.getX(),devilleft.getY()+60);
				cldevilcount = 0;
			}
			//DEVILRIGHT
			if( getElementAt( oval.getX(), oval.getY()) == devilright  ){
				remove(down.get(i));
				down.remove(i);
				deaths.play();
				score = score + 75;
				remove(scoreshow);
				score();
				remove(devilright);
				add(devildeadright,devilright.getX(),devilright.getY()+60);
				crdevilcount = 0;
			}
			if( getElementAt( oval.getX()+32, oval.getY() + 10) == devilright  ){
				remove(down.get(i));
				down.remove(i);
				deaths.play();
				score = score + 75;
				remove(scoreshow);
				score();
				remove(devilright);
				add(devildeadright,devilright.getX(),devilright.getY()+60);
				crdevilcount = 0;
			}
			if( getElementAt( oval.getX()+32, oval.getY()+18) == devilright  ){
				remove(down.get(i));
				down.remove(i);
				deaths.play();
				score = score + 75;
				remove(scoreshow);
				score();
				remove(devilright);
				add(devildeadright,devilright.getX(),devilright.getY()+60);
				crdevilcount = 0;
			}
			if( getElementAt( oval.getX(), oval.getY()+18) == devilright  ){
				remove(down.get(i));
				down.remove(i);
				deaths.play();
				score = score + 75;
				remove(scoreshow);
				score();
				remove(devilright);
				add(devildeadright,devilright.getX(),devilright.getY()+60);	
				crdevilcount = 0;
			}
		}
	}
	
	public void keyPressed(KeyEvent e){
		move = e.getKeyCode();
		if(move == 39 && count == 1 && mcountleft == 0 && charleftmove1.getX() < getWidth() - charleftmove1.getWidth()){
			double charx = charleftmove1.getX();
			double chary = charleftmove1.getY();
			add(charrightmove2, charx, chary);
			remove(charleftmove1);
			charrightmove2.move(10, 0);
			count = 0;
			countright = 1;
			mcountright = 1;
		}
		else if(move == 39 && count == 1 && mcountleft == 1 && charleftmove2.getX() < getWidth() - charleftmove2.getWidth()){
			double charx = charleftmove2.getX();
			double chary = charleftmove2.getY();
			add(charrightmove1, charx, chary);
			remove(charleftmove2);
			charrightmove1.move(10, 0);
			count = 0;
			countright = 1;
			mcountright = 0;
		}
		if(move == 39 && count == 2 && mcountup == 0 && charupmove1.getX() < getWidth() - charupmove1.getWidth()){
			double charx = charupmove1.getX();
			double chary = charupmove1.getY();
			add(charrightmove2, charx, chary);
			remove(charupmove1);
			charrightmove2.move(10, 0);
			count = 0;
			countright = 1;
			mcountright = 1;
		}
		else if(move == 39 && count == 2 && mcountup == 1 && charupmove2.getX() < getWidth() - charupmove2.getWidth()){
			double charx = charupmove2.getX();
			double chary = charupmove2.getY();
			add(charrightmove1, charx, chary);
			remove(charupmove2);
			charrightmove1.move(10, 0);
			count = 0;
			countright = 1;
			mcountright = 0;
		}
		if(move == 39 && count == 3 && mcountdown == 0 && chardownmove1.getX() < getWidth() - chardownmove1.getWidth()){
			double charx = chardownmove1.getX();
			double chary = chardownmove1.getY();
			add(charrightmove2, charx, chary);
			remove(chardownmove1);
			charrightmove2.move(10, 0);
			count = 0;
			countright = 1;
			mcountright = 1;
		}	
		else if(move == 39 && count == 3 && mcountdown == 1 && chardownmove2.getX() < getWidth() - chardownmove2.getWidth()){
			double charx = chardownmove2.getX();
			double chary = chardownmove2.getY();
			add(charrightmove1, charx, chary);
			remove(chardownmove2);
			charrightmove1.move(10, 0);
			count = 0;
			countright = 1;
			mcountright = 0;
		}
		if(move == 39 && count == 0 && mcountright == 0 && charrightmove1.getX() < getWidth() - charrightmove1.getWidth()){
			double charx = charrightmove1.getX();
			double chary = charrightmove1.getY();
			add(charrightmove2, charx, chary);
			remove(charrightmove1);
			charrightmove2.move(10, 0);
			count = 0;
			countright = 1;
			mcountright = 1;
		}
		else if(move == 39 && count == 0 && mcountright == 1 && charrightmove2.getX() < getWidth() - charrightmove2.getWidth()){
			double charx = charrightmove2.getX();
			double chary = charrightmove2.getY();
			add(charrightmove1, charx, chary);
			remove(charrightmove2);
			charrightmove1.move(10, 0);
			count = 0;
			countright = 1;
			mcountright = 0;
		}
		if(move == 37 && count == 0 && mcountright == 0 && charrightmove1.getX() > 0){
			double charx = charrightmove1.getX();
			double chary = charrightmove1.getY();
			add(charleftmove2, charx, chary);
			remove(charrightmove1);
			charleftmove2.move(-10, 0);
			count = 1;
			countleft = 1;
			mcountleft = 1;
		}
		else if(move == 37 && count == 0 && mcountright == 1 && charrightmove2.getX() > 0){
			double charx = charrightmove2.getX();
			double chary = charrightmove2.getY();
			add(charleftmove1, charx, chary);
			remove(charrightmove2);
			charleftmove1.move(-10, 0);
			count = 1;
			countleft = 1;
			mcountleft = 0;
		}
		if(move == 37 && count == 2 && mcountup == 0 && charupmove1.getX() > 0){
			double charx = charupmove1.getX();
			double chary = charupmove1.getY();
			add(charleftmove2, charx, chary);
			remove(charupmove1);
			charleftmove2.move(-10, 0);
			count = 1;
			countleft = 1;
			mcountleft = 1;
		}
		else if(move == 37 && count == 2 && mcountup == 1 && charupmove2.getX() > 0){
			double charx = charupmove2.getX();
			double chary = charupmove2.getY();
			add(charleftmove1, charx, chary);
			remove(charupmove2);
			charleftmove1.move(-10, 0);
			count = 1;
			countleft = 1;
			mcountleft = 0;
		}
		if(move == 37 && count == 3 && mcountdown == 0 && chardownmove1.getX() > 0){
			double charx = chardownmove1.getX();
			double chary = chardownmove1.getY();
			add(charleftmove2, charx, chary);
			remove(chardownmove1);
			charleftmove2.move(-10, 0);
			count = 1;
			countleft = 1;
			mcountleft = 1;
		}
		else if(move == 37 && count == 3 && mcountdown == 1 && chardownmove2.getX() > 0){
			double charx = chardownmove2.getX();
			double chary = chardownmove2.getY();
			add(charleftmove1, charx, chary);
			remove(chardownmove2);
			charleftmove1.move(-10, 0);
			count = 1;
			countleft = 1;
			mcountleft = 0;
		}
		
		if(move == 37 && count == 1 && mcountleft == 0 && charleftmove1.getX() > 0){
			double charx = charleftmove1.getX();
			double chary = charleftmove1.getY();
			add(charleftmove2, charx, chary);
			remove(charleftmove1);
			charleftmove2.move(-10, 0);
			count = 1;
			countleft = 1;
			mcountleft = 1;
		}
		else if(move == 37 && count == 1 && mcountleft == 1 && charleftmove2.getX() > 0){
			double charx = charleftmove2.getX();
			double chary = charleftmove2.getY();
			add(charleftmove1, charx, chary);
			remove(charleftmove2);
			charleftmove1.move(-10, 0);
			count = 1;
			countleft = 1;
			mcountleft = 0;
		}
		if(move == 38 && count == 0 && mcountright == 0 && charrightmove1.getY() > 150){
			double charx = charrightmove1.getX();
			double chary = charrightmove1.getY();
			add(charupmove2, charx, chary);
			remove(charrightmove1);
			charupmove2.move(0, -10);
			count = 2;
			countup = 1;
			mcountup = 1;
		}
		else if(move == 38 && count == 0 && mcountright == 1 && charrightmove2.getY() > 150){
			double charx = charrightmove2.getX();
			double chary = charrightmove2.getY();
			add(charupmove1, charx, chary);
			remove(charrightmove2);
			charupmove1.move(0, -10);
			count = 2;
			countup = 1;
			mcountup = 0;
		}
		if(move == 38 && count == 1 && mcountleft == 0 && charleftmove1.getY() > 150){
			double charx = charleftmove1.getX();
			double chary = charleftmove1.getY();
			add(charupmove2, charx, chary);
			remove(charleftmove1);
			charupmove2.move(0, -10);
			count = 2;
			countup = 1;
			mcountup = 1;
		}
		else if(move == 38 && count == 1 && mcountleft == 1 && charleftmove2.getY() > 150){
			double charx = charleftmove2.getX();
			double chary = charleftmove2.getY();
			add(charupmove1, charx, chary);
			remove(charleftmove2);
			charupmove1.move(0, -10);
			count = 2;
			countup = 1;
			mcountup = 0;
		}
		if(move == 38 && count == 3 && mcountdown == 0 && chardownmove1.getY() > 150){
			double charx = chardownmove1.getX();
			double chary = chardownmove1.getY();
			add(charupmove2, charx, chary);
			remove(chardownmove1);
			charupmove2.move(0, -10);
			count = 2;
			countup = 1;
			mcountup = 1;
		}
		else if(move == 38 && count == 3 && mcountdown == 1 && chardownmove2.getY() > 150){
			double charx = chardownmove2.getX();
			double chary = chardownmove2.getY();
			add(charupmove1, charx, chary);
			remove(chardownmove2);
			charupmove1.move(0, -10);
			count = 2;
			countup = 1;
			mcountup = 0;
		}
		if(move == 38 && count == 2 && mcountup == 0 && charupmove1.getY() > 150){
			double charx = charupmove1.getX();
			double chary = charupmove1.getY();
			add(charupmove2, charx, chary);
			remove(charupmove1);
			charupmove2.move(0, -10);
			count = 2;
			countup = 1;
			mcountup = 1;
		}
		else if(move == 38 && count == 2 && mcountup == 1 && charupmove2.getY() > 150){
			double charx = charupmove2.getX();
			double chary = charupmove2.getY();
			add(charupmove1, charx, chary);
			remove(charupmove2);
			charupmove1.move(0, -10);
			count = 2;
			countup = 1;
			mcountup = 0;
		}
		if(move == 40 && count == 0 && mcountright == 0 && charrightmove1.getY() < getHeight() - 48 - charrightmove1.getHeight()){
			double charx = charrightmove1.getX();
			double chary = charrightmove1.getY();
			add(chardownmove2, charx, chary);
			remove(charrightmove1);
			chardownmove2.move(0, 10);
			count = 3;
			countdown = 1;
			mcountdown = 1;
		}
		else if(move == 40 && count == 0 && mcountright == 1 && charrightmove2.getY() < getHeight() - 48 - charrightmove2.getHeight()){
			double charx = charrightmove2.getX();
			double chary = charrightmove2.getY();
			add(chardownmove1, charx, chary);
			remove(charrightmove2);
			chardownmove1.move(0, 10);
			count = 3;
			countdown = 1;
			mcountdown = 0;
		}
		if(move == 40 && count == 1 && mcountleft == 0 && charleftmove1.getY() < getHeight() - 48 - charleftmove1.getHeight()){
			double charx = charleftmove1.getX();
			double chary = charleftmove1.getY();
			add(chardownmove2, charx, chary);
			remove(charleftmove1);
			chardownmove2.move(0, 10);
			count = 3;
			countdown = 1;
			mcountdown = 1;
		}
		else if(move == 40 && count == 1 && mcountleft == 1 && charleftmove2.getY() < getHeight() - 48 - charleftmove2.getHeight()){
			double charx = charleftmove2.getX();
			double chary = charleftmove2.getY();
			add(chardownmove1, charx, chary);
			remove(charleftmove2);
			chardownmove1.move(0, 10);
			count = 3;
			countdown = 1;
			mcountdown = 0;
		}
		if(move == 40 && count == 2 && mcountup == 0 && charupmove1.getY() < getHeight() - 48 - charupmove1.getHeight()){
			double charx = charupmove1.getX();
			double chary = charupmove1.getY();
			add(chardownmove2, charx, chary);
			remove(charupmove1);
			chardownmove2.move(0, 10);
			count = 3;
			countdown = 1;
			mcountdown = 1;
		}
		else if(move == 40 && count == 2 && mcountup == 1 && charupmove2.getY() < getHeight() - 48 - charupmove2.getHeight()){
			double charx = charupmove2.getX();
			double chary = charupmove2.getY();
			add(chardownmove1, charx, chary);
			remove(charupmove2);
			chardownmove1.move(0, 10);
			count = 3;
			countdown = 1;
			mcountdown = 0;
		}
		if(move == 40 && count == 3 && mcountdown == 0 && chardownmove1.getY() < getHeight() - 48 - chardownmove1.getHeight()){
			double charx = chardownmove1.getX();
			double chary = chardownmove1.getY();
			add(chardownmove2, charx, chary);
			remove(chardownmove1);
			chardownmove2.move(0, 10);
			count = 3;
			countdown = 1;
			mcountdown = 1;
		}
		else if(move == 40 && count == 3 && mcountdown == 1 && chardownmove2.getY() < getHeight() - 48 - chardownmove2.getHeight()){
			double charx = chardownmove2.getX();
			double chary = chardownmove2.getY();
			add(chardownmove1, charx, chary);
			remove(chardownmove2);
			chardownmove1.move(0, 10);
			count = 3;
			countdown = 1;
			mcountdown = 0;
		} 
		if(move == 32){
			createLazer();
		}
		
		
		
	}
	
	public void wolfleft(){
		double z = rg.nextDouble(150, getHeight() - ewolf.getHeight() - 48);
		double x = getWidth() - 96;
		if(clwolfcount == 0){	
			add(ewolf,x,z);	
			clwolfcount = 1;
		}
	}
	
	public void wolfright(){
		double z = rg.nextDouble(150, getHeight() - fwolf.getHeight() - 48);
		double x = 0;
		if(crwolfcount == 0){
			add(fwolf,x,z);
			crwolfcount = 1;
		}
	}
	
	public void japanleft(){
		double z = rg.nextDouble(150, getHeight() - enemy1.getHeight() - 48);
		double x = getWidth() - 96;
		if(cljaponcount == 0){
			add(enemy1,x,z);	
			cljaponcount = 1;
		}
	}
	
	public void japanright(){
		double z = rg.nextDouble(150, getHeight() - enemy2.getHeight()- 48);
		double x = 0;
		if(crjaponcount == 0){
			add(enemy2,x,z);
			crjaponcount = 1;
		}
	}
	
	public void devilright(){
		double z = rg.nextDouble(150, getHeight() - devilleft.getHeight() - 48);
		double x = 0;
		if(crdevilcount == 0){
			add(devilright,x,z);
			crdevilcount = 1;
		}
	}
	
	public void devilleft(){
		double z = rg.nextDouble(150, getHeight() - devilleft.getHeight() - 48);
		double x = getWidth() - 96;
		if(cldevilcount == 0){
			add(devilleft,x,z);	
			cldevilcount = 1;
		}
	}
	//************************************************************************
	
	public void starting(){	
		start1.scale(5.4);
		start2.scale(3.8);
		startbg.scale(1.2);
		GLabel first = new GLabel ("- BEDO'S ADVENTURE -");
		first.setFont("Impact-65");
		first.setColor(Color.WHITE);
		add(startbg);
		add(first,getWidth()/2 - first.getWidth()/2, getHeight()/2 + first.getHeight()/2 );
		add( start1,100,getHeight()/2 - start1.getHeight()/2  );
		add( start2,1000,getHeight()/2 - start2.getHeight()/2  );
		pause(3000);
		remove(first);
	}
	public void click(){	
		
		GLabel wait = new GLabel ("Click to Start");
		wait.setFont("Impact-35");
		wait.setColor(Color.WHITE);
		add(wait,getWidth()/2 - wait.getWidth()/2, getHeight()/2 + wait.getHeight()/2);	
		waitForClick();
		remove(wait);	
		
	}
	
	private void score(){
		scoreshow = new GLabel("Score: " + score);
		scoreshow.setFont("Impact-25");
		scoreshow.setColor(Color.RED);
		add(scoreshow, getWidth() - scoreshow.getWidth() - 25, scoreshow.getHeight() + 50);
	}
	
	private void endscore(){
		song.stop();
		gameovers.play();
		pause(1000);
		removeAll();
		end = new GRect(getWidth(), getHeight());
		end.setFilled(true);
		end.setColor(Color.WHITE);
		add(end);
		gameover = new GLabel("Game Over");
		gameover.setFont("Impact-75");
		gameover.setColor(Color.RED);
		add(gameover, getWidth() / 2 - gameover.getWidth() / 2, getHeight() / 2);
		endscoreshow = new GLabel("Your score is: " + score);
		endscoreshow.setFont("Impact-50");
		endscoreshow.setColor(Color.BLACK);
		add(endscoreshow, getWidth() / 2 - endscoreshow.getWidth() / 2, getHeight() / 2 - 100);
		pause(2000);
		again = new GLabel("Click to play again");
		again.setFont("Impact-50");
		again.setColor(Color.BLACK);
		add(again, getWidth() / 2 - again.getWidth() / 2, getHeight() / 2 + again.getHeight() + 50);
		waitForClick();
		removeAll();
		count = 0;
		countleft = 0;
		countright = 0;
		countup = 0;
		countdown = 0;
		mcountright = 0;
		mcountleft = 0;
		mcountup = 0;
		mcountdown = 0;
		score = 0;
		crwolfcount = 0;
		clwolfcount = 0;
		crjaponcount = 0;
		cljaponcount = 0;
		crdevilcount = 0;
		cldevilcount = 0;
		add(back);
		add(charrightmove1, getWidth()/2 - charrightmove1.getWidth() / 2, getHeight() / 2 + 50);
		howto1 = new GLabel("Use arrow keys to move and space to shoot");
		howto1.setFont("Impact-25");
		howto1.setColor(Color.WHITE);
		add(howto1, getWidth() / 2 - howto1.getWidth() / 2, getHeight() / 2 - 100);
		howto2 = new GLabel("If enemies cross the whole area, you lose");
		howto2.setFont("Impact-25");
		howto2.setColor(Color.RED);
		add(howto2, getWidth() / 2 - howto2.getWidth() / 2, getHeight() / 2 -50);
		click();
		add(back);
		add(charrightmove1);
		right = new ArrayList<GImage>();
		left = new ArrayList<GImage>();  
		up = new ArrayList<GImage>();  
		down = new ArrayList<GImage>();  
		score();
		while(true){
			japanleft();
			japanright();
			wolfleft();
			wolfright();
			devilright();
			devilleft();
			devilright.move(0.75, 0);
			devilleft.move(-0.75, 0);
			ewolf.move(-1.5, 0);
			fwolf.move(1.5, 0);
			enemy1.move(-1, 0);
			enemy2.move(1, 0);
			if(enemy1.getX() < -enemy1.getWidth() || enemy2.getX() > getWidth() || ewolf.getX() < -ewolf.getWidth() || fwolf.getX() > getWidth() || devilleft.getX() < -devilleft.getWidth() || devilright.getX() > getWidth()){
				endscore();
			}
			if(countright == 1){
				animateAduketRight();
			} 
			if(countleft == 1){
				animateAduketLeft();
			}
			if(countup == 1){
				animateAduketUp();
			}
			if(countdown == 1){
				animateAduketDown();
			}
			pause(10);
		}
	}

}
	
