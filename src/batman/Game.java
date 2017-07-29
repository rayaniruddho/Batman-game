package batman;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferStrategy;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import batman.gfx.Sprite;
import batman.gfx.SpriteSheet;
import batman.gfx.gui.Launcher;
import batman.input.KeyInput;
import batman.input.MouseInput;
import batman.tile.Vat;



public class Game extends Canvas implements Runnable{
	
	public static final int WIDTH = 650, HEIGHT = WIDTH/16*9,SCALE = 2;
	public static final String TITLE = "Batman:Knightfighter";
	
	
	private Thread thread;
	private boolean running = false;
	public static int level = 0;
	public static int batCount;
	public static int difficulty = 2;
	
	public static Handler handler;
	//public static HP batHealth;
	
	public static SpriteSheet sheet1,sheet2,sheet3,sheet4,sheet5,sheet6,sheet15,sheet16,sheet17,sheet20,sheet21;
	public static SpriteSheet sheet7,sheet8,sheet9,sheet10,sheet11,sheet12,sheet13,sheet14,sheet18,sheet19;
	public static Sprite player[] = new Sprite[66];
	public static Sprite player_kick[] = new Sprite[14];
	public static Sprite player_throw[] = new Sprite[6];
	public static Sprite player_hit[] = new Sprite[4];
	public static Sprite croc_kick[] = new Sprite[14];
	public static Sprite croc_run[] = new Sprite[24];
	public static Sprite croc_punch[] = new Sprite[10];
	public static Sprite enemy_hit[] = new Sprite[4];
	public static Sprite croc_down[] = new Sprite[2];
	public static Sprite joker_kick[] = new Sprite[4];
	public static Sprite joker_crowbar[] = new Sprite[4];
	public static Sprite joker_throw[] = new Sprite[2];
	public static Sprite batarang_pic[] = new Sprite[2];
	public static Sprite gordon[] = new Sprite[3];
	public static Sprite catwalk,catwalk2,railing,railing2,vat,line,card;
	public static Launcher launcher;
	public static MouseInput mouse;
	
	public static boolean playing = false;
	public static boolean level_1 = true;
	public static boolean level_2 = false;
	public static boolean objective = false;
	public static boolean instructions = false;
	public static boolean over = false;
	public static boolean complete = false;
	public static boolean chooseDifficulty = false;
	
	public static Image bgImage,bgImage2,chains,support,block1,block2,block3;
	public static Image image,obj,inst,level1,level2,gameOver,success,diffScreen;
	
	public static Sound outSound,victorySound,themeSound;
	public static int time = 0;
	//public Camera cam = new Camera();
 		
	public Game(){
		Dimension size = new Dimension(WIDTH*SCALE,HEIGHT*SCALE);
		setPreferredSize(size);
		setMaximumSize(size);
		setMinimumSize(size);
		bgImage = new ImageIcon(getClass().getResource("/Level1BG_back.png")).getImage();		
		image = new ImageIcon(getClass().getResource("/IntroScreen.png")).getImage();
		obj = new ImageIcon(getClass().getResource("/ObjectiveScreen.png")).getImage();
		inst = new ImageIcon(getClass().getResource("/InstructionScreen.png")).getImage();
		level1 = new ImageIcon(getClass().getResource("/Level1Screen.png")).getImage();
		level2 = new ImageIcon(getClass().getResource("/Level2Screen.png")).getImage();
		bgImage2 = new ImageIcon(getClass().getResource("/Level2BG_Revised_Back.png")).getImage();
		chains = new ImageIcon(getClass().getResource("/Chains.png")).getImage();
		support = new ImageIcon(getClass().getResource("/support.png")).getImage();
		gameOver = new ImageIcon(getClass().getResource("/GameOverScreen.png")).getImage();
		diffScreen = new ImageIcon(getClass().getResource("/diffScreen.png")).getImage();
		success = new ImageIcon(getClass().getResource("/SuccessScreen.png")).getImage();
		block1 = new ImageIcon(getClass().getResource("/Block1.png")).getImage();
		block2 = new ImageIcon(getClass().getResource("/Block2.png")).getImage();
		block3 = new ImageIcon(getClass().getResource("/Block3.png")).getImage();
	}	
	
	private void init(){
		handler = new Handler();
		
		sheet1 = new SpriteSheet("/player_complete.png");
		sheet2 = new SpriteSheet("/catwalk.png");
		sheet3 = new SpriteSheet("/railing.png");
		sheet4 = new SpriteSheet("/bat_kick_small.png");
		sheet5 = new SpriteSheet("/bat_throw.png");
		sheet6 = new SpriteSheet("/Batarang.png");
		sheet7 = new SpriteSheet("/bat_hit.png");
		sheet8 = new SpriteSheet("/croc_down.png");
		sheet9 = new SpriteSheet("/EnemyHit.png");
		sheet10 = new SpriteSheet("/croc_kick.png");
		sheet11 = new SpriteSheet("/croc_punch.png");
		sheet12 = new SpriteSheet("/croc_run.png");
		sheet13 = new SpriteSheet("/catwalk2.png");
		sheet14 = new SpriteSheet("/railing2.png");
		sheet15 = new SpriteSheet("/Level2BG_Revised_Vat.png");
		sheet16 = new SpriteSheet("/line.png");
		sheet17 = new SpriteSheet("/JokerCrowbar.png");
		sheet18 = new SpriteSheet("/JokerKick.png");
		sheet19 = new SpriteSheet("/JokerThrow.png");
		sheet20 = new SpriteSheet("/Gordon.png");
		sheet21 = new SpriteSheet("/JCard.png");
		
		launcher = new Launcher();		
		
		mouse = new MouseInput();
		
		addKeyListener(new KeyInput());		
		addMouseMotionListener(mouse);
		addMouseListener(mouse);
		
		outSound = new Sound("/audio/overMusic.wav");
		victorySound = new Sound("/audio/outro.wav");
		themeSound = new Sound("/audio/finish.wav");
		 
		
		catwalk = new Sprite(sheet2,1,1,200,320);
		railing = new Sprite(sheet3,1,1,200,320);
		catwalk2 = new Sprite(sheet13,1,1,200,320);
		railing2 = new Sprite(sheet14,1,1,200,320);
		vat = new Sprite(sheet15,1,1,667,396);
		line = new Sprite(sheet16,1,1,1,1);
		card = new Sprite(sheet21,1,1,40,22);
				
		
			for(int i=0;i<player.length;i++){
				player[i] = new Sprite(sheet1,i+1,1,200,250);
			}
			for(int i=0;i<player_kick.length;i++){
				player_kick[i] = new Sprite(sheet4,i+1,1,230,250);
			}
			for(int i=0;i<player_throw.length;i++){
				player_throw[i] = new Sprite(sheet5,i+1,1,280,250);
			}	
			for(int i=0;i<player_hit.length;i++){
				player_hit[i] = new Sprite(sheet7,i+1,1,320,250);
			}	
			for(int i=0;i<croc_run.length;i++){
				croc_run[i] = new Sprite(sheet12,i+1,1,220,300);
			}	
			for(int i=0;i<croc_kick.length;i++){
				croc_kick[i] = new Sprite(sheet10,i+1,1,350,300);
			}	
			for(int i=0;i<croc_punch.length;i++){
				croc_punch[i] = new Sprite(sheet11,i+1,1,260,300);
			}	
			for(int i=0;i<enemy_hit.length;i++){
				enemy_hit[i] = new Sprite(sheet9,i+1,1,275,300);
			}	
			for(int i=0;i<croc_down.length;i++){
				croc_down[i] = new Sprite(sheet8,i+1,1,390,300);
			}
			for(int i=0;i<batarang_pic.length;i++){
				batarang_pic[i] = new Sprite(sheet6,i+1,1,30,30);
			}
			for(int i=0;i<gordon.length;i++){
				gordon[i] = new Sprite(sheet20,i+1,1,190,320);
			}			
			for(int i=0;i<joker_throw.length;i++){
				joker_throw[i] = new Sprite(sheet19,i+1,1,245,305);
			}	
			for(int i=0;i<joker_kick.length;i++){
				joker_kick[i] = new Sprite(sheet18,i+1,1,305,300);
			}	
			for(int i=0;i<joker_crowbar.length;i++){
				joker_crowbar[i] = new Sprite(sheet17,i+1,1,340,307);
			}	
	}
	
	public synchronized void start(){
		if(running) return;
		running = true;
		thread = new Thread(this,"Thread");
		thread.start();
	}
	
	public static int getFrameWidth(){
		return WIDTH*SCALE;
	}
	
	public static int getFrameHeight(){
		return HEIGHT*SCALE;
	}
	
	public synchronized void stop(){
		if(!running) return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		init();
		requestFocus();
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		double delta = 0.0;
		double ns = 1000000000.0/60.0;
		int frames = 0;
		int ticks = 0;
		int count = 0;
		while(running){			
			if(level_2 && count == 0) {
				init();
				count++;
			}
			long now = System.nanoTime();
			delta += (now - lastTime)/ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				ticks++;
				delta--;			
				render();
				frames++;
			}
			if(System.currentTimeMillis()-timer > 1000){
				timer += 1000;
				//System.out.println(frames + " frames per second " + ticks + " updates per second");
				frames = 0;
				ticks = 0;
			}
		}
		stop();
	}	
	
	public void render(){
		BufferStrategy bs = getBufferStrategy();
		if(bs == null){
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		
		if(over && !playing) {
			g.drawImage(gameOver, 0, 0, getFrameWidth(),getFrameHeight(),this);
			launcher.render(g);
		}
		
		if(complete) {
			g.drawImage(success, 0, 0, getFrameWidth(),getFrameHeight(),this);
			launcher.render(g);
		}
		
		if(chooseDifficulty) {
			g.drawImage(diffScreen, 0, 0, getFrameWidth(),getFrameHeight(),this);
			//System.out.println(difficulty);
			launcher.render(g);
		}
		
		else if(playing && level_1 && time<=200 && !objective && !instructions){
			g.drawImage(level1, 0, 0, getFrameWidth(),getFrameHeight(),this);
			time++;
			//themeSound.stop();
		}
		else if(playing && level_1 && time>200 && !objective && !instructions){
			g.drawImage(bgImage, 0, 0, getFrameWidth(),getFrameHeight(),this);	
			//synchronized(handler) {
				handler.render(g);
			//}
			//batHealth.render(g);
		}
		else if(playing && level_2 && time<=200 && !objective && !instructions) {
			g.drawImage(level2, 0, 0, getFrameWidth(),getFrameHeight(),this);
			//batCount = 5;
			time++;
			//themeSound.stop();
		}
		else if(playing && level_2 && time>200 && !objective && !instructions){
			g.drawImage(bgImage2, 0, 0, getFrameWidth(), getFrameHeight(),this);	
			g.drawImage(block1, 0, 370, 145, 130, this);	
			g.drawImage(block2, 0, 500, 215, 130, this);	
			g.drawImage(block3, 0, 630, 300, 100, this);	
			//g.drawImage(chains, 0, 0, getFrameWidth()+390, getFrameHeight(),this);	
			g.drawImage(support, 230, 140, getFrameWidth()-105, getFrameHeight(),this);
			g.drawImage(chains, 400, 0, 320, 288, this);
			//init();
			//handler = new Handler();
			//synchronized(handler) {
			handler.render(g);
			//}
		}
		else if(!playing && !objective && !instructions && !over && !chooseDifficulty){
			g.drawImage(image, 0, 0, getFrameWidth(),getFrameHeight(),this);
			launcher.render(g);
		}		
		else if(!playing && objective && !instructions){
			g.drawImage(obj, 0, 0, getFrameWidth(),getFrameHeight(),this);
			launcher.render(g);
		}
		else if(!playing && !objective && instructions){
			g.drawImage(inst, 0, 0, getFrameWidth(),getFrameHeight(),this);
			launcher.render(g);
		}
		
		g.dispose();
		bs.show();
	}
	
	public void tick(){
		if(playing){
			handler.tick();
			//themeSound.play();
			//sound.close();
		}
		//sound.play();

		//if(over)
			//sound.stop();

		/*for(Entity e:handler.entity){
			if(e.getId() == Id.player){				
				cam.tick(e);
			}
		}*/
		
	}
	
	public static void main(String[] args){
				
		Game game = new Game();	
		JFrame Frame = new JFrame(TITLE);
		Frame.add(game);
		Frame.pack();
		Frame.setResizable(true);
		Frame.setLocationRelativeTo(null);
		Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		Frame.setVisible(true);
		game.start();
	}
}