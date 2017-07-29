package batman.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {
	private BufferedImage sheet;
	
	public SpriteSheet(String path){
		try {
			sheet = ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public BufferedImage getSprite(int x,int y){
		return sheet.getSubimage(x*200-200, y*320-320, 200, 250);
	}
	public BufferedImage getKickSprite(int x,int y){
		return sheet.getSubimage(x*230-230, y*320-320, 230, 250);
	}
	public BufferedImage getThrowSprite(int x,int y){
		return sheet.getSubimage(x*280-280, y*320-320, 280, 250);
	}
	public BufferedImage getBatarangSprite(int x,int y){
		return sheet.getSubimage(x*30-30, y*30-30, 30, 30);
	}
	public BufferedImage getBatHitSprite(int x,int y){
		return sheet.getSubimage(x*320-320, y*320-320, 320, 250);
	}
	public BufferedImage getEnemyHitSprite(int x,int y){
		return sheet.getSubimage(x*275-275, y*275-275, 275, 320);
	}
	public BufferedImage getCrocDownSprite(int x,int y){
		return sheet.getSubimage(x*390-390, y*300-300, 390, 300);
	}
	public BufferedImage getCrocKickSprite(int x,int y){
		return sheet.getSubimage(x*350-350, y*350-350, 350, 300);
	}
	public BufferedImage getCrocPunchSprite(int x,int y){
		return sheet.getSubimage(x*260-260, y*260-260, 260, 300);
	}
	public BufferedImage getCrocRunSprite(int x,int y){
		return sheet.getSubimage(x*220-220, y*220-220, 220, 300);
	}
	public BufferedImage getVatSprite(int x,int y){
		return sheet.getSubimage(0, 0, 667, 396);
	}
	public BufferedImage getLineSprite(int x,int y){
		return sheet.getSubimage(0, 0, 1, 1);
	}
	public BufferedImage getJokerKickSprite(int x,int y){
		return sheet.getSubimage(x*305-305, y*220-220, 305, 300);
	}
	public BufferedImage getJokerThrowSprite(int x,int y){
		return sheet.getSubimage(x*245-245, y*220-220, 245, 305);
	}
	public BufferedImage getJokerCrowbarSprite(int x,int y){
		return sheet.getSubimage(x*340-340, y*220-220, 340, 307);
	}
	public BufferedImage getGordonSprite(int x,int y){
		return sheet.getSubimage(x*190-190, y*220-220, 190, 320);
	}
	public BufferedImage getCardSprite(int x,int y){
		return sheet.getSubimage(x*40-40, y*22-22, 40, 22);
	}
}