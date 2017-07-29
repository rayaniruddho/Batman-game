package batman.gfx.gui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import batman.Game;

public class Launcher extends JPanel{
	
	public Button[] buttons;
	public Button[] diff;
	public Button backButton,retryButton,mainButton;
		
	public Launcher(){		
		buttons = new Button[4];
		diff = new Button[3];
		
		buttons[0] = new Button(580,400,200,70,"OBJECTIVE",Color.BLACK);
		buttons[1] = new Button(580,480,200,70,"INSTRUCTIONS",Color.BLACK);
		buttons[2] = new Button(580,560,200,70,"CHOOSE DIFFICULTY",Color.BLACK);
		buttons[3] = new Button(580,640,200,70,"PLAY GAME",Color.BLACK);
		
		retryButton = new Button(380,620,250,100,"RETRY",Color.WHITE);
		mainButton = new Button(680,620,250,100,"MAIN MENU",Color.WHITE);
		
		diff[0] = new Button(580,200,200,70,"EASY",Color.BLACK);
		diff[1] = new Button(580,350,200,70,"NORMAL",Color.BLACK);
		diff[2] = new Button(580,500,200,70,"HARD",Color.BLACK);
		
		backButton = new Button(1000,50,270,100,"      ",Color.BLACK);
	}
	
	public void render(Graphics g){
		if(Game.over) {
			retryButton.render(g);
			mainButton.render(g);
		}
		
		if(Game.chooseDifficulty) {
			for(int i = 0;i<diff.length;i++) {
				diff[i].render(g);
			}
			//backButton.render(g);
		}
		/*if(Game.complete) {
			mainButton.render(g);
		}*/
		if(!Game.playing && !Game.objective && !Game.instructions && !Game.over && !Game.complete && !Game.chooseDifficulty){
			for(int i=0;i<buttons.length;i++){
				buttons[i].render(g);
			}
		}		
		if(!Game.playing && (Game.objective || Game.instructions || Game.chooseDifficulty)){
			backButton.render(g);
		}
	}
}