package batman.gfx.gui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import batman.Game;

public class BatarangCount extends Canvas{
	
	private int x, y;	
	private int width, height;
	private int count;

	public BatarangCount(int x, int y, int width, int height, int count) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.count = count;
	}
	
	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(x-5, y-5, width, height+10);		
		
		g.drawImage(Game.batarang_pic[0].getBufferedImage(), x, y, 30, 30, null);
		
		g.setColor(Color.BLACK);
		g.setFont(new Font("Lucida Sans Typewriter",Font.BOLD, 25));
		g.drawString(count+"", x, y+50);
	}
}
