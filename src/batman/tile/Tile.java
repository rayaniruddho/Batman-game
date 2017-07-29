package batman.tile;

import java.awt.Graphics;
import java.awt.Rectangle;

import batman.Handler;
import batman.Id;

public abstract class Tile {
	public int x,y,width,height,velX,velY;
	public boolean solid;
	public Handler handler;
	public Id id;
	
	public Tile(int x,int y,int width,int height,boolean solid,Id id,Handler handler){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.solid = solid;
		this.id = id;
		this.handler = handler;
	}
	
	public abstract void render(Graphics g);

	public void tick(){
		x+=velX;
		y+=velY;
	};

	public void die(){
		handler.removeTile(this);
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

	public void setY(int y) {
		this.y = y;
	}

	public boolean isSolid() {
		return solid;
	}

	public void setSolid(boolean solid) {
		this.solid = solid;
	}	

	public void setVelX(int velX) {
		this.velX = velX;
	}

	public void setVelY(int velY) {
		this.velY = velY;
	}
	
	public Rectangle getBounds(){
		return new Rectangle(getX(),getY(),width,height);
	}

	public Id getId() {
		return id;
	}
}