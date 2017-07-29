package batman.entity;

import java.awt.Graphics;
import java.awt.Rectangle;

import batman.Handler;
import batman.Id;
import batman.states.BossState;

public abstract class Entity {
	public int x,y,width,height,velX,velY;
	public boolean solid;
	public boolean jumping = false;
	public boolean falling = true;
	public double gravity = 0.0;
	public Id id;
	public int hp;
	public int phaseTime;
	public BossState bossState;
	public Handler handler;
	public int facing = 1; //0=left,1=right
	public int frame = 0;
	public int frameDelay = 0;
	public boolean animateX = false;
	public boolean animateY = true;
	public boolean run = false;
	public boolean touching = false;
	public static boolean kick = false,punch = false,batarang = false;
	
	public Entity(int x,int y,int width,int height,boolean solid,Id id,Handler handler){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		//this.solid = solid;
		this.id = id;
		this.handler = handler;
	}
	
	
	public abstract void render(Graphics g);
	
	public void tick(){
		/*x+=velX;
		y+=velY;*/
		
	};
	
	public void die(){
		handler.removeEntity(this);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}	

	public int getHeight() {
		return height;
	}	

	public void setY(int y) {
		this.y = y;
	}

	/*public boolean isSolid() {
		return solid;
	}*/	

	public void setVelX(int velX) {
		this.velX = velX;
	}

	public void setVelY(int velY) {
		this.velY = velY;
	}
	
	public int getVelX() {
		return velX;
	}

	public int getVelY() {
		return velY;
	}
	
	public Id getId(){
		return id;
	}
	
	public Rectangle getBounds(){
		return new Rectangle(getX(),getY(),width,height);
	}
	
	public Rectangle getBoundsTop(){
		return new Rectangle(getX()-10,getY(),width-20,5);
	}
	
	public Rectangle getBoundsBottom(){
		return new Rectangle(getX()-10,getY()+height-5,width-20,5);
	}
	
	public Rectangle getBoundsLeft(){
		return new Rectangle(getX(),getY()+10,5,height-20);
	}
	
	public Rectangle getBoundsRight(){
		return new Rectangle(getX()+width-5,getY()+10,5,height-20);
	}
}
