package batman.entity.mob;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import batman.Game;
import batman.Handler;
import batman.Id;
import batman.entity.Card;
import batman.entity.Entity;
import batman.gfx.gui.HP;
import batman.states.BossState;

public class Joker extends Entity{
	
	private Random random;
	private int frame = 0;
	private int frameDelay = 0;
	private int delay = 0;
	
	public HP jokerHealth;

	public Joker(int x, int y, int width, int height, boolean solid, Id id, Handler handler, int hp) {
		super(x, y, width, height, solid, id, handler);
		this.hp = hp;
		bossState = BossState.IDLE;
		random = new Random();
	}

	public void render(Graphics g) {
		
		jokerHealth = new HP(1000,80,210,35,"JOKER",Color.GREEN,hp);
		
		if(bossState == BossState.DOWN){
			g.drawImage(Game.enemy_hit[3].getBufferedImage(), x, y, width, height, null);			
		}
		else{
			if(bossState == BossState.RECOVERING){				
				g.drawImage(Game.enemy_hit[2].getBufferedImage(), x, y, width, height, null);				
			}
					
			else if(bossState == BossState.KICKING){				
				g.drawImage(Game.joker_kick[frame%4].getBufferedImage(), x,y,width,height, null);				
			}
			
			else if(bossState == BossState.PUNCHING){
				g.drawImage(Game.joker_crowbar[frame%4].getBufferedImage(),x,y,width,height, null);
			}
			
			else if(bossState == BossState.THROWING) {
				g.drawImage(Game.joker_throw[frame%2].getBufferedImage(),x,y,width,height, null);
			}
			
			else if(bossState == BossState.IDLE){				
				g.drawImage(Game.joker_kick[0].getBufferedImage(), x,y,width,height, null);
			}		
		}
		jokerHealth.render(g);
	}
	
	public void tick() {
		//System.out.println("Joker "+bossState+" "+hp);
		if(hp>0)
			phaseTime++;
		
		if(hp<=0){
			hp = 0;
			setVelX(0);
			bossState = BossState.DOWN;
			phaseTime = 0;
			
			Game.level++;
			if(Game.level>200){				
				Game.level_1=false;
				Game.time = 0;
				Game.level_2=false;		
				//Game.level = -200;
				Game.complete = true;
				
				Game.handler.clearLevel();
				Game.victorySound.play();
				//die();
			}
		}
		else {
			for(int i=0;i<handler.entity.size();i++){
				Entity e = handler.entity.get(i);
				if(e.getId() == Id.player){
					if(e.bossState == BossState.DOWN || e.bossState == BossState.RECOVERING) {
						bossState = BossState.IDLE;
					}
					if(getBoundsLeft().intersects(e.getBounds())){
						if(e.bossState == BossState.RECOVERING) {
							bossState = BossState.IDLE;
						}
						else if(phaseTime>=100 && (e.bossState!=BossState.DOWN || e.bossState != BossState.RECOVERING))
							chooseState();
						else if((bossState==BossState.KICKING || bossState==BossState.PUNCHING) && bossState!=BossState.RECOVERING && e.bossState!= BossState.RECOVERING){
							if(e.hp>0){
								e.bossState=BossState.RECOVERING;
								e.hp--;
								
								//phaseTime=0;
								//bossState = BossState.IDLE;
							}
							
						}
					}
					else if(e.getX() > Game.getFrameWidth()/2){
						delay++;
						if(delay > 60) {
							bossState = BossState.THROWING;
							//synchronized(Game.handler.entity) {
							handler.addEntity(new Card(1050,Game.getFrameHeight()-390,40,22,true,Id.card,handler));
							//delay = 0;
							//}
						}
					}
					else {
						bossState = BossState.IDLE;
					}
					if(bossState == BossState.THROWING && bossState!=BossState.RECOVERING && e.bossState!= BossState.RECOVERING){
						//System.out.println("I am called");
						/*if(e.hp>0){
							for(int j=0;i<handler.entity.size();i++) {
								Entity en = handler.entity.get(i);
								if(en.getId() == Id.card) {
									if(e.getBounds().intersects(en.getBounds())) {
										e.bossState = BossState.RECOVERING;
										//e.hp--;
									}								
								}
							}
							
							
							//bossState = BossState.IDLE;
						}	*/				
						delay = 0;
					}
				}
			}
			
			if(bossState == BossState.PUNCHING){
				frameDelay++;
				if(frameDelay>=15){
					frame++;
					if(frame>=4){
						frame = 0;
						//punch = false;
					}
					frameDelay = 0;
				}
			}
			else if(bossState == BossState.KICKING){
					frameDelay++;
					if(frameDelay>=15){
						frame++;
						if(frame>=4){
							frame = 0;
							//kick = false;
						}
						frameDelay = 0;
					}
			}
			else if(bossState == BossState.THROWING) {
				frameDelay++;
				if(frameDelay>=15){
					frame++;
					if(frame>=2){
						frame = 0;
						//kick = false;
					}
					frameDelay = 0;
				}
			}
			
		}
		
	}
	
	public void chooseState(){
		int nextPhase = random.nextInt(2);
		/*
		if(nextPhase == 0){
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
