package batman.entity;

import java.awt.Graphics;

import batman.Game;
import batman.Handler;
import batman.Id;
import batman.states.BossState;
import batman.tile.Tile;

public class Card extends Entity{

	public Card(int x, int y, int width, int height, boolean solid, Id id, Handler handler) {
		super(x, y, width, height, solid, id, handler);
		setVelX(-8);
	}

	public void render(Graphics g) {
		g.drawImage(Game.card.getBufferedImage(),getX(),getY(),getWidth(),getHeight(),null);
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
			if(e.getId() == Id.player) {
				if(getBounds().intersects(e.getBounds())){
					die();
					if(e.hp > 0) {
						e.hp--;
						e.bossState = BossState.RECOVERING;
					}
						
				}
			}
		}
	}
}
