package batman.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import batman.Game;
import batman.Id;
import batman.entity.Batarang;
import batman.entity.Entity;
import batman.states.BossState;

public class KeyInput implements KeyListener {

	public boolean key_pressed = false;

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		for (int i = 0; i < Game.handler.entity.size(); i++) {
			Entity en = Game.handler.entity.get(i);
			// Entity en2 = Game.handler.entity.get(i);

			if (en.getId() == Id.player) {
				en.kick = false;
				en.punch = false;
				en.batarang = false;
				switch (key) {
				case KeyEvent.VK_W:
					if (!en.jumping && en.bossState != BossState.RECOVERING && !en.touching) {
						en.jumping = true;
						en.gravity = 10.0;
						// en.animateY = true;
					}
					break;
				case KeyEvent.VK_A:
					if (en.bossState != BossState.RECOVERING) {
						en.setVelX(-5);
						en.facing = 0;
						break;
					}
					/*
					 * if(en2.getId() == Id.croc || en2.getId() == Id.joker){
					 * if(en.getBoundsLeft().intersects(en2.getBoundsRight())) en.setVelX(0); }
					 */

				case KeyEvent.VK_D:
					if (en.bossState != BossState.RECOVERING) {
						en.setVelX(5);
						en.facing = 1;
						break;
					}
					/*
					 * if(en2.getId() == Id.croc || en2.getId() == Id.joker){
					 * if(en.getBoundsRight().intersects(en2.getBoundsLeft())) en.setVelX(0); }
					 */

				case KeyEvent.VK_SHIFT:
					en.run = true;
					break;
				case KeyEvent.VK_SPACE:
					if (!key_pressed) {
						key_pressed = true;
						en.batarang = true;
						if(Game.batCount>0) {
							switch (en.facing) {
							case 0:
								Game.handler.addEntity(new Batarang(en.getX(), en.getY() + 60, 30, 30, true, Id.batarang,
										Game.handler, 0));
								Game.batCount--;
								break;
							case 1:
								Game.handler.addEntity(new Batarang(en.getX() + en.getWidth(), en.getY() + 60, 30, 30, true,
										Id.batarang, Game.handler, 1));
								Game.batCount--;
								break;
							}
						}						
						//System.out.println(Game.handler.entity);
						//System.out.println(en.batarang);
					}
					break;
				case KeyEvent.VK_P:
					if (!key_pressed) {
						key_pressed = true;
						en.punch = true;
					}
					break;
				case KeyEvent.VK_K:
					if (!key_pressed) {
						key_pressed = true;
						en.kick = true;

					}
					break;
				}
				;
			}
		}

	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		for (int i = 0; i < Game.handler.entity.size(); i++) {
			Entity en = Game.handler.entity.get(i);
			if (en.getId() == Id.player) {
				switch (key) {
				case KeyEvent.VK_W:
					en.setVelY(0);
					break;

				case KeyEvent.VK_A:
					en.setVelX(0);
					break;
				case KeyEvent.VK_D:
					en.setVelX(0);
					break;
				case KeyEvent.VK_SHIFT:
					en.run = false;
					break;
				case KeyEvent.VK_P:
					key_pressed = false;
					en.punch = false;
					break;
				case KeyEvent.VK_K:
					key_pressed = false;
					en.kick = false;

					break;
				case KeyEvent.VK_SPACE:
					key_pressed = false;
					en.batarang = false;
					break;
				}
				;
			}
		}
	}

	public void keyTyped(KeyEvent arg0) {
		// not needed here
	}
}