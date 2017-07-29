package batman.entity.mob;

import java.awt.Graphics;

import batman.Game;
import batman.Handler;
import batman.Id;
import batman.entity.Entity;
import batman.states.BossState;
import batman.tile.Tile;

public class Gordon extends Entity {

	public enum State {
		HANGING, SAVED, JUMPING;
	}
	private int once = 0;

	public State state;

	public Gordon(int x, int y, int width, int height, boolean solid, Id id, Handler handler, State state) {
		super(x, y, width, height, solid, id, handler);
		this.state = state;
	}

	public void render(Graphics g) {
		if (state == State.SAVED) {
			g.drawImage(Game.gordon[1].getBufferedImage(), 770, Game.getFrameHeight()-485, width, height, null);
		}
		else if (state == State.JUMPING) {
			//g.drawImage(Game.gordon[0].getBufferedImage(), x, y, width, height, null);
		} 
		else if (state == State.HANGING) {
			g.drawImage(Game.gordon[0].getBufferedImage(), x, y, width, height, null);
		} 
	}

	public void tick() {

		for (Tile t : handler.tile) {
			if (!t.solid)
				break;
			if (t.getId() == Id.wall) {
				if (getBounds().intersects(t.getBounds())) {
					setVelY(0);
					setVelX(0);
					setX(770);
					setY(Game.getFrameHeight()-485);
					state = State.SAVED;
					once++;
				} else {
					for (int i = 0; i < Game.handler.entity.size(); i++) {
						Entity en = handler.entity.get(i);
						if (en.getId() == Id.player) {
							if(state == State.SAVED && once == 1) {
								en.bossState = null;
							}
							if (getBounds().intersects(en.getBounds()) && once == 0) {
								//once++;
								setX(en.getX());
								setY(en.getY());
								state = State.JUMPING;
								en.bossState = BossState.SAVING;
							}
						}
					}
				}
			}
		}
	}
}
