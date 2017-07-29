package batman.gfx.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import batman.Game;

public class Button {	

	public int x,y;
	public int width,height;
	
	public String label;
	public Color color;
	
	public Button(int x, int y, int width, int height, String label, Color color) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.label = label;
		this.color = color;
	}
	
	
	public void render(Graphics g){
		g.setColor(color);
		g.setFont(new Font("Lucida Sans Typewriter",Font.BOLD, 25));
		//g.drawRect(getX(), getY(), getWidth(), getHeight());
		
		FontMetrics fm = g.getFontMetrics();
		int stringX = (getWidth() - fm.stringWidth(getLabel()))/2;
		int stringY = (fm.getAscent() + (getHeight() - (fm.getAscent() + fm.getDescent()))/2);
		g.drawString(getLabel(), getX()+stringX, getY()+stringY);
	}
	
	public void triggerEvent(){
		if(getLabel().toLowerCase().contains("play"))
			Game.playing = true;
		else if(getLabel().toLowerCase().contains("objective"))
			Game.objective = true;
		else if(getLabel().toLowerCase().contains("instructions"))
			Game.instructions = true;
		else if(getLabel().toLowerCase().contains("choose")) {
			Game.chooseDifficulty = true;
		}		
		else if(getLabel().toLowerCase().contains("retry")) {
			Game.over = false;					
			Game.handler.clearLevel();
			Game.handler.createLevel();
			Game.playing = true;
			Game.outSound.stop();
		}			
		else if(getLabel().toLowerCase().contains("main")) {
			Game.playing = false;
			Game.over = false;
			Game.complete = false;
			Game.outSound.stop();
		}			
		else if(getLabel().toLowerCase().contains("     ")){
			Game.objective = false;
			Game.instructions = false;
			Game.chooseDifficulty = false;
		}
		
		else if(getLabel().toLowerCase().contains("easy")){
			Game.difficulty = 1;	
			Game.batCount = 10;
			Game.chooseDifficulty = false;
			
		}
		else if(getLabel().toLowerCase().contains("normal")){
			Game.difficulty = 2;
			Game.batCount = 5;
			Game.chooseDifficulty = false;
		}
		else if(getLabel().toLowerCase().contains("hard")){
			Game.difficulty = 3;
			Game.batCount = 3;
			Game.chooseDifficulty = false;
		}
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

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
}
