package batman.tile;

import java.awt.Color;
import java.awt.Graphics;

import batman.Game;
import batman.Handler;
import batman.Id;

public class Wall extends Tile{

	public Wall(int x, int y, int width, int height, boolean solid, Id id, Handler handler) {
		super(x, y, width, height, solid, id, handler);
	}

	public void render(Graphics g) {
		if(Game.level_1)
			g.drawImage(Game.catwalk.getBufferedImage(),x,y,width,height/10,null );
		else if(Game.level_2)
			g.drawImage(Game.catwalk2.getBufferedImage(),x,y,width,height/9,null );
	}

	public void tick() {
		
	}
}