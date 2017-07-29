package batman.gfx.gui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class HP extends Canvas{

	//private Color color;
	private Color foregroundColor;
	private int xPos;
	private int yPos;
	private int width;
	private int height;
	private String name;
	
	private int progress;
	
	public HP(int x, int y, int width, int height, String name, Color foregroundColor, int progress) {
		this.xPos = x;
		this.yPos = y;
		this.width = width;
		this.height = height;
		this.name = name;
		this.foregroundColor = foregroundColor;
		this.progress = progress;
	}
	
	public void setProgress(int hp) {
		this.progress = hp;
	}
	
	public int getProgress() {
		return progress;
	}
	
	public void render(Graphics g) {
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("Lucida Sans Typewriter",Font.BOLD, 25));
		g.drawString(name, xPos, yPos-20);

		g.setColor(Color.DARK_GRAY);
		g.fillRect(xPos,yPos,width,height);
		
		g.setColor(foregroundColor);
		g.fillRoundRect(xPos+5,yPos+5,progress*20,height-10, 4, 4);
	}

}
