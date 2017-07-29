package batman.tile;

import java.awt.Graphics;

import batman.Game;
import batman.Handler;
import batman.Id;

public class Vat extends Tile{

	public Vat(int x, int y, int width, int height, boolean solid, Id id, Handler handler) {
		super(x, y, width, height, solid, id, handler);
	}

	public void render(Graphics g) {
		g.drawImage(Game.vat.getBufferedImage(),x,y,width,height,null );
	}

}
