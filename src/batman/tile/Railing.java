package batman.tile;

import java.awt.Graphics;

import batman.Game;
import batman.Handler;
import batman.Id;

public class Railing extends Tile{

	public Railing(int x, int y, int width, int height, boolean solid, Id id, Handler handler) {
		super(x, y, width, height, solid, id, handler);
	}

	public void render(Graphics g) {
		if(Game.level_1)
			g.drawImage(Game.railing.getBufferedImage(),x,y,width,height/4,null );
		else if(Game.level_2)
			g.drawImage(Game.railing2.getBufferedImage(),x,y,width,height/3,null );
	}

	public void tick() {
		
	}
}