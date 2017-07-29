package batman.gfx;

import java.awt.image.BufferedImage;

public class Sprite {
	
	public SpriteSheet sheet;
	public BufferedImage image;
	
	public Sprite(SpriteSheet sheet,int x,int y,int width,int height){ 
		switch(width){
			case 200:
				image = sheet.getSprite(x, y);
				break;
			case 230:
				image = sheet.getKickSprite(x, y);
				break;
			case 280:
				image = sheet.getThrowSprite(x, y);
				break;
			case 30:
				image = sheet.getBatarangSprite(x, y);
				break;
			case 320:
				image = sheet.getBatHitSprite(x,y);
				break;
			case 390:
				image = sheet.getCrocDownSprite(x,y);
				break;
			case 275:
				image = sheet.getEnemyHitSprite(x,y);
				break;
			case 350:
				image = sheet.getCrocKickSprite(x,y);
				break;
			case 260:
				image = sheet.getCrocPunchSprite(x,y);
				break;
			case 220:
				image = sheet.getCrocRunSprite(x,y);
				break;
			case 667:
				image = sheet.getVatSprite(x,y);
				break;
			case 340:
				image = sheet.getJokerCrowbarSprite(x,y);
				break;
			case 305:
				image = sheet.getJokerKickSprite(x,y);
				break;
			case 245:
				image = sheet.getJokerThrowSprite(x,y);
				break;
			case 190:
				image = sheet.getGordonSprite(x,y);
				break;
			case 40:
				image = sheet.getCardSprite(x,y);
				break;
			case 1:
				image = sheet.getLineSprite(x,y);
				break;
		}
	}
	
	public BufferedImage getBufferedImage(){
		return image;
	}
}