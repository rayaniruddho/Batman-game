package batman.entity.mob;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import batman.Game;
import batman.Handler;
import batman.Id;
import batman.entity.Entity;
import batman.gfx.gui.HP;
import batman.states.BossState;
import batman.tile.Tile;

public class KillerCroc extends Entity{
	
	private Random random;
	private int frame = 0;
	private int frameDelay = 0;
	private int facing = 0;
	//private int count = 0;
	
	public HP crocHealth;
	
	public KillerCroc(int x, int y, int width, int height,boolean solid, Id id, Handler handler,int hp) {
		super(x, y, width, height, true, id, handler);
		//this.x = x;
		bossState = BossState.IDLE;
		this.hp = hp;
		random = new Random();
	}
	
	public void render(Graphics g) {
		
		crocHealth = new HP(1000,80,210,35,"KILLER CROC",Color.YELLOW,hp);
		
		if(bossState == BossState.DOWN){
			if(facing == 0 ){
				g.drawImage(Game.croc_down[1].getBufferedImage(), x, y, width, height, null);
			}
			if(facing == 1){
				g.drawImage(Game.croc_down[0].getBufferedImage(), x, y, width, height, null);				
			}
		}
		else{
			if(bossState == BossState.RECOVERING){
				if(facing == 0 ){
					g.drawImage(Game.enemy_hit[1].getBufferedImage(), x, y, width, height, null);
				}
				if(facing == 1){
					g.drawImage(Game.enemy_hit[0].getBufferedImage(), x, y, width, height, null);				
				}
			}
			else if(bossState == BossState.RUNNING){
		
				if(facing == 0 ){
					g.drawImage(Game.croc_run[frame%12+12].getBufferedImage(), x,y,width,height, null);
				}
				if(facing == 1){
					g.drawImage(Game.croc_run[frame%12].getBufferedImage(), x,y,width,height, null);
				}
			}
		
			else if(bossState == BossState.KICKING){
				//System.out.println("kicking");
				if(facing == 0 ){
					g.drawImage(Game.croc_kick[frame%7+7].getBufferedImage(), x,y,width,height, null);
				}
				if(facing == 1){
					g.drawImage(Game.croc_kick[frame%7].getBufferedImage(), x,y,width,height, null);
				}
			}
			
			else if(bossState == BossState.PUNCHING){
				//System.out.println("punching");
				if(facing == 0 ){
					g.drawImage(Game.croc_punch[frame%5+5].getBufferedImage(), x,y,width,height, null);
				}
				if(facing == 1){
					g.drawImage(Game.croc_punch[frame%5].getBufferedImage(),x,y,width,height, null);
				}
			}
			
			else if(bossState == BossState.IDLE){
				if(facing == 0 ){
					g.drawImage(Game.croc_run[23].getBufferedImage(), x,y,width,height, null);
				}
				if(facing == 1){
					g.drawImage(Game.croc_run[0].getBufferedImage(), x,y,width,height, null);
				}
			}		
		}
		crocHealth.render(g);
	}

	/*public void health(Graphics g) {
		crocHealth.render(g);
	}*/
	
	public void tick(){
		
		//System.out.println("Croc "+bossState+" "+hp);
		x += velX;
		y += velY;
		
		if(x<=0) x = 0;
		//if(y<=0) y = 0;
		if(x+width>=Game.getFrameWidth()) x = Game.getFrameWidth()-width;
		
		//if(hp<=0) die();
		if(hp>0)
			phaseTime++;
		
		if(hp<=0){
			hp = 0;
			setVelX(0);
			bossState = BossState.DOWN;
			phaseTime = 0;
			
			Game.level++;
			if(Game.level>100){				
				Game.level_1=false;
				Game.time = 0;
				Game.level = 0;
				Game.level_2=true;		
				//Game.level = -200;
				Game.handler.clearLevel();
				//die();
				
				/*for(Entity e:handler.entity) {
					e.die();
				}*/
			}
		}
		
		else {
			if(bossState == BossState.IDLE || bossState == BossState.RECOVERING) setVelX(0);
			
			for(Tile t:handler.tile){
				if(!t.solid) break;
				if(t.getId() == Id.wall){				
					if(getBoundsBottom().intersects(t.getBounds())){
						setVelY(0);
						if(falling) falling = false;
					}
					else{
						if(!falling){
							gravity = 0.8;
							falling = true;
						}
					}
					if(getBoundsLeft().intersects(t.getBounds())){
						setVelX(3);
					}
					if(getBoundsRight().intersects(t.getBounds())){
						setVelX(-3);
					}
				}
			}
			if(falling){
				gravity +=0.1;
				setVelY((int)gravity);
			}
			
			for(int i=0;i<handler.entity.size();i++){
				Entity e = handler.entity.get(i);
				if(e.getId() == Id.player && e.bossState!=BossState.RECOVERING){
					//System.out.println("here");
					if((e.bossState == BossState.DOWN) || (e.bossState == BossState.RECOVERING)) {
						bossState = BossState.IDLE;
						//System.out.println("still");
					}
					else {
						if(phaseTime>=150) {
							bossState = BossState.RUNNING;
							//System.out.println("running");
							//phaseTime = 0;
						}
						if(getBoundsLeft().intersects(e.getBounds())){
							setVelX(0);
							facing = 0;	
							//System.out.println(e.bossState);
							if(e.bossState == BossState.RECOVERING) {
								bossState = BossState.IDLE;
							//	System.out.println("still again");
							}
							else if(phaseTime>=200 && (e.bossState!=BossState.DOWN || e.bossState != BossState.RECOVERING)) {
								chooseState();
							//	System.out.println("choosing");
							}
								
							if(((bossState==BossState.KICKING) || (bossState==BossState.PUNCHING)) && bossState!=BossState.RECOVERING && e.bossState!= BossState.RECOVERING){
							//	System.out.println("kick or punch");
								//count++;
								//if(count>40) {
									if(e.hp>0){
										e.hp--;
										e.bossState = BossState.RECOVERING;	
								//		System.out.println(e.bossState);
								//		System.out.println("hit");
										//count = 0;
										
										//phaseTime=0;
										//bossState = BossState.IDLE;
									}
								
													
							}
									
						}
						else if(getBoundsRight().intersects(e.getBounds())){
							setVelX(0);
							facing = 1;					
							if(e.bossState == BossState.RECOVERING) {
								bossState = BossState.IDLE;
							}
							else if(phaseTime>=100 && (e.bossState!=BossState.DOWN || e.bossState != BossState.RECOVERING))
								chooseState();
							if((bossState==BossState.KICKING || bossState==BossState.PUNCHING) && bossState!=BossState.RECOVERING && e.bossState!= BossState.RECOVERING){
								if(e.hp>0){
									e.bossState=BossState.RECOVERING;
									e.hp--;
									
									//phaseTime=0;
									//bossState = BossState.IDLE;
								}
								
							}
								
						}
						else if(bossState == BossState.RUNNING){
							if(e.getX()<getX()){
								facing = 0;
								setVelX(-3);
							}
							else if(e.getX()>getX()){
								facing = 1;
								setVelX(3);
							}
							else setVelX(0);
						}
					}
									
				}
			}
			
			if(bossState == BossState.PUNCHING){
				frameDelay++;
				if(frameDelay>=10){
					frame++;
					if(frame>=5){
						frame = 0;
						//punch = false;
					}
					frameDelay = 0;
				}
			}
			else if(bossState == BossState.KICKING){
					frameDelay++;
					if(frameDelay>=10){
						frame++;
						if(frame>=7){
							frame = 0;
							//kick = false;
						}
						frameDelay = 0;
					}
			}
			else if(bossState == BossState.RUNNING){
				frameDelay++;
				if(frameDelay>=10){
					frame++;
					if(frame>=12){
						frame = 0;
					}
					frameDelay = 0;
				}
			}
		}
		
	}	
	
	public void chooseState(){
		int nextPhase = random.nextInt(2);
		
		/*if(nextPhase == 0){
			bossState = BossState.IDLE;	
			phaseTime = 0;			
		}*/
		if(nextPhase == 0){
			bossState = BossState.KICKING;
			phaseTime = 0;
		}
		else if(nextPhase == 1){
			bossState = BossState.PUNCHING;
			phaseTime = 0;
		}
	}
}
