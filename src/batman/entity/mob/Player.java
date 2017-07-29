package batman.entity.mob;

import java.awt.Color;
import java.awt.Graphics;

import batman.Game;
import batman.Handler;
import batman.Id;
import batman.entity.Entity;
import batman.gfx.gui.BatarangCount;
import batman.gfx.gui.HP;
import batman.states.BossState;
import batman.tile.Tile;

public class Player extends Entity {

	public HP batHealth;
	public BatarangCount batarangCount;
	//public int batCount;
	//public boolean touching = false;

	public Player(int x, int y, int width, int height, boolean solid, Id id, Handler handler, int hp) {
		super(x, y, width, height, true, id, handler);
		this.hp = hp;
	}

	public void render(Graphics g) {

		batHealth = new HP(80, 80, 210, 30, "BATMAN", Color.red, hp);
		batarangCount = new BatarangCount(650,80,50,50,Game.batCount);

		if (bossState == BossState.DOWN) {
			//System.out.println("here");
			if (facing == 0) {
				g.drawImage(Game.player_hit[2].getBufferedImage(), x, y, width, height, null);
			}
			if (facing == 1) {
				g.drawImage(Game.player_hit[1].getBufferedImage(), x, y, width, height, null);
			}
		} else if (bossState == BossState.RECOVERING) {
		//	bossState = BossState.RECOVERING;
			if (facing == 0) {
				g.drawImage(Game.player_hit[3].getBufferedImage(), x, y, width, height, null);
			}
			if (facing == 1) {
				g.drawImage(Game.player_hit[0].getBufferedImage(), x, y, width, height, null);
			}
		} 
		else if(bossState == BossState.SAVING) {
			g.drawImage(Game.gordon[2].getBufferedImage(), x, y, width, height, null);
		}
		else {
			if (punch) {
				if (facing == 0) {
					g.drawImage(Game.player[frame % 3 + 60].getBufferedImage(), x, y, width, height, null);
				}
				if (facing == 1) {
					g.drawImage(Game.player[frame % 3 + 63].getBufferedImage(), x, y, width, height, null);
				}
			}

			else if (kick) {
				if (facing == 0) {
					g.drawImage(Game.player_kick[frame % 7 + 7].getBufferedImage(), x, y, width, height, null);
				}
				if (facing == 1) {
					g.drawImage(Game.player_kick[frame % 7].getBufferedImage(), x, y, width, height, null);
				}
			}

			else if (batarang) {
				//System.out.println(batarang + "P");
				if (facing == 0) {
					g.drawImage(Game.player_throw[frame % 3 + 3].getBufferedImage(), x, y, width, height, null);
				}
				if (facing == 1) {
					g.drawImage(Game.player_throw[frame % 3].getBufferedImage(), x, y, width, height, null);
				}
			}

			else if (animateX && !animateY && run) {
				if (facing == 0) {
					g.drawImage(Game.player[frame % 12 + 36].getBufferedImage(), x, y, width, height, null);
				}
				if (facing == 1) {
					g.drawImage(Game.player[frame % 12 + 48].getBufferedImage(), x, y, width, height, null);
				}
			}

			else if (animateX && !animateY && !run) {
				if (facing == 0) {
					g.drawImage(Game.player[frame % 12].getBufferedImage(), x, y, width, height, null);
				}
				if (facing == 1) {
					g.drawImage(Game.player[frame % 12 + 12].getBufferedImage(), x, y, width, height, null);
				}

			} else if (animateY) {
				if (facing == 0 && jumping) {
					g.drawImage(Game.player[frame % 3 + 24].getBufferedImage(), x, y, width, height, null);
				}
				if (facing == 0 && falling) {
					g.drawImage(Game.player[frame % 3 + 30].getBufferedImage(), x, y, width, height, null);
				}
				if (facing == 1 && jumping) {
					g.drawImage(Game.player[frame % 3 + 27].getBufferedImage(), x, y, width, height, null);
				}
				if (facing == 1 && falling) {
					g.drawImage(Game.player[frame % 3 + 33].getBufferedImage(), x, y, width, height, null);
				}
			} else if (bossState == null) {
				if (facing == 0) {
					g.drawImage(Game.player[0].getBufferedImage(), x, y, width, height, null);
				}
				if (facing == 1) {
					g.drawImage(Game.player[12].getBufferedImage(), x, y, width, height, null);
				}
			}
		}

		batHealth.render(g);
		batarangCount.render(g);
	}

	/*
	 * public void health(Graphics g) { batHealth.render(g); }
	 */

	public void tick() {
		//System.out.println("Player " + bossState + " " + hp);
		//System.out.println(getId());
		//System.out.println(falling + " " + touching);
		if (getY() > Game.getFrameHeight()) {
			hp = 0;
		}

		if (hp <= 0) {
			//System.out.println("here");
			hp = 0;
			bossState = BossState.DOWN;
			//System.out.println(bossState);
			setVelX(0);
			setVelY(0);
			punch = false;
			kick = false;
			batarang = false;
			run = false;
			animateX = false;
			animateY = false;

			// bossState = null;

			Game.level++;
			if (Game.level > 100) {
				Game.playing = false;
				Game.over = true;			
				die();
				Game.outSound.play();
				// hp = 10;
			}
		}
		
		else {
			if (bossState == BossState.RECOVERING) {
				phaseTime++;
				if (phaseTime > 60) {
					bossState = null;
					//setVelX(0);
					//setVelY(0);
					phaseTime = 0;
				}
			}

			if (!run) {
				x += velX * 0.5;
				y += velY;
			} else {
				x += 1.5 * velX;
				y += velY;
			}

			/*
			 * phaseTime++; if(hit) hp--;
			 */
			if (x <= 0)
				x = 0;
			// if(y<=0) y = 0;
			if (x + width >= Game.getFrameWidth())
				x = Game.getFrameWidth() - width;
			// if(y+height>=500) y = 500 - height;

			if (velX != 0)
				animateX = true;
			else
				animateX = false;

			if (jumping || falling)
				animateY = true;
			else
				animateY = false;

			/*
			 * for(int i=0;i<Game.handler.entity.size();i++){ Entity en =
			 * Game.handler.entity.get(i); if(en.getId() == Id.croc || en.getId() ==
			 * Id.joker){ if(!batarang && (en.punch || en.kick)){ hit = true; if(hit){ punch
			 * = false; kick = false; batarang = false; } phaseTime = 0; if(phaseTime>20){
			 * hit = false; } }
			 * 
			 * } }
			 */

			for (int i=0;i<Game.handler.tile.size();i++) {
				Tile t = Game.handler.tile.get(i);
				if (!t.solid)
					break;
				if (t.getId() == Id.wall) {
					if (getBoundsTop().intersects(t.getBounds())) {
						setVelY(0);
						//System.out.println("touching up");
						if (jumping) {
							jumping = false;
							gravity = 0.8;
							falling = true;
							//touching = false;
						}
					} else if (getBoundsBottom().intersects(t.getBounds())) {
						//bossState = null;
						//System.out.println("touching down");
						touching = true;
						setVelY(0);
						if (falling) {
							falling = false;
							animateY = false;
						}
					} else if (!falling && !jumping) {
						falling = true;
						touching = false;
						gravity = 0.8;
						// falling = true;
					}
					/*else {
						touching = false;
					}*/
					
					if (getBoundsLeft().intersects(t.getBounds())) {
						setVelX(0);
						x = t.getX() + t.width;
					}
					if (getBoundsRight().intersects(t.getBounds())) {
						setVelX(0);
						x = t.getX() - t.width;
					}
				
				}
			}

			for (int i = 0; i < Game.handler.entity.size(); i++) {
				Entity e = handler.entity.get(i);
				if (e.getId() == Id.croc || e.getId() == Id.joker) {
					if (getBoundsLeft().intersects(e.getBounds())) {
						/*if(e.bossState == BossState.KICKING || e.bossState == BossState.PUNCHING) {
							bossState = BossState.RECOVERING;
						}*/
						if ((punch || kick) && bossState != BossState.RECOVERING) {
							e.hp--;
							e.bossState = BossState.RECOVERING;
							punch = false;
							kick = false;
						}

					} else if (getBoundsRight().intersects(e.getBounds())) {
						/*if(e.bossState == BossState.KICKING || e.bossState == BossState.PUNCHING) {
							bossState = BossState.RECOVERING;
						}*/
						if ((punch || kick) && bossState != BossState.RECOVERING && e.bossState!=BossState.RECOVERING) {
							e.hp--;
							e.bossState = BossState.RECOVERING;
							punch = false;
							kick = false;
						}
					}
					if (batarang && bossState != BossState.RECOVERING) {
						// System.out.println("I am called");
						if (e.hp > 0) {
							//e.bossState = BossState.RECOVERING;
							//e.hp--;
							punch = false;
							kick = false;
							batarang = false;
						}
					}
				}
			}

			if (jumping) {
				gravity -= 0.15;
				setVelY((int) -gravity);
				if (gravity <= 0.6) {
					jumping = false;
					falling = true;
				}
			} else if (falling) {
				gravity += 0.15;
				setVelY((int) gravity);
			}
			if (punch) {
				frameDelay++;
				if (frameDelay >= 3) {
					frame++;
					if (frame >= 3) {
						frame = 0;
						// punch = false;
					}
					frameDelay = 0;
				}
			} else if (kick) {
				frameDelay++;
				if (frameDelay >= 3) {
					frame++;
					if (frame >= 7) {
						frame = 0;
						// kick = false;
					}
					frameDelay = 0;
				}
			}

			else if (batarang) {
				frameDelay++;
				if (frameDelay >= 3) {
					frame++;
					if (frame >= 3) {
						frame = 0;
						// batarang = false;
					}
					frameDelay = 0;
				}
			}

			else {
				if (animateX && !animateY && !run) {
					frameDelay++;
					if (frameDelay >= 5) {
						frame++;
						if (frame >= 12) {
							frame = 0;
						}
						frameDelay = 0;
					}
				} else if (animateX && !animateY && run) {
					frameDelay++;
					if (frameDelay >= 3) {
						frame++;
						if (frame >= 12) {
							frame = 0;
						}
						frameDelay = 0;
					}
				}
				if (animateY) {
					frameDelay++;
					if (frameDelay >= 30) {
						frame++;
						if (frame >= 3) {
							frame = 0;
						}
						frameDelay = 0;
					}
				}
			}
		}
		
	}
}