package batman.entity;
import java.awt.Graphics;

import batman.Game;
import batman.Handler;
import batman.Id;
import batman.states.BossState;
import batman.tile.Tile;

public class Batarang extends Entity{
	
	//public int bat_x=0,bat_velX;
	int facing;

	public Batarang(int x, int y, int width, int height,boolean solid, Id id, Handler handler,int facing) {
		super(x, y, width, height, true, id, handler);
		this.facing = facing;
		
		switch(facing){
		case 0:
			setVelX(-8);
			break;
		case 1:
			setVelX(8);
			break;
		}
	}

	public void render(Graphics g) {
		if(facing == 0)
			g.drawImage(Game.batarang_pic[1].getBufferedImage(),getX(),getY(),getWidth(),getHeight(),null );
		else if(facing == 1)
			g.drawImage(Game.batarang_pic[0].getBufferedImage(),getX(),getY(),getWidth(),getHeight(),null );		

	}

	public void tick() {
		
		x += velX;
		
		for(int i=0;i<handler.tile.size();i++){
			Tile t = handler.tile.get(i);
			
			if(t.isSolid()){
				if(getBoundsLeft().intersects(t.getBounds()) || getBoundsRight().intersects(t.getBounds())) 
					die();
			}
		}
		for(int i=0;i<handler.entity.size();i++){
			Entity e = handler.entity.get(i);
			
			if(e.getId() == Id.croc || e.getId() == Id.joker){
				if(getBounds().intersects(e.getBounds())){
					die();
					//e.die();
					if(e.hp > 0) {
						e.hp--;
						e.bossState = BossState.RECOVERING;
						e.phaseTime = 0;
					}
						
				}
			}
		}
	}
}