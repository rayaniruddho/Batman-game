package batman;

import java.awt.Graphics;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import batman.entity.Entity;
import batman.entity.mob.Gordon;
import batman.entity.mob.Joker;
import batman.entity.mob.KillerCroc;
import batman.entity.mob.Player;
import batman.tile.Line;
import batman.tile.Railing;
import batman.tile.Tile;
import batman.tile.Vat;
import batman.tile.Wall;

public class Handler {
	public List<Entity> entity = Collections.synchronizedList(new LinkedList<Entity>());
	public List<Tile> tile =  Collections.synchronizedList(new LinkedList<Tile>());
	//public List<HP> hp = Collections.synchronizedList(new LinkedList<HP>());
	/*public HP playerHP;
	public HP enemyHP;*/
	
	public void render(Graphics g){
		//synchronized(entity) {
		for(int i =0;i < entity.size();i++) {
			Entity e = entity.get(i);
			e.render(g);
		}
		
			for(Tile ti:tile){
				ti.render(g);
			}
	//	}
		/*synchronized(entity) {
			for(Entity en:entity){
				en.render(g);
				//en.health(g);
			}
		}*/
			
		
	}
	
	public Handler(){
		createLevel();
	}
	
	public void tick(){
		/*//synchronized(entity) {
			for(Entity en:entity){
				en.tick();
			}
		//}
*/		for(int i =0;i < entity.size();i++) {
			Entity e = entity.get(i);
			e.tick();
		}
	
	//	synchronized(tile) {
			/*for(Tile ti:tile){
				ti.tick();
			}*/
	//	}
		
	}
	
	public void addEntity(Entity en){
		entity.add(en);
	}
	
	public void removeEntity(Entity en){
		entity.remove(en);
	}
	
	public void addTile(Tile ti){
		tile.add(ti);
	}
		
	public void removeTile(Tile ti){
		tile.remove(ti);
	}
	
	/*public void addHP(HP h){
		hp.add(h);
	}
		
	public void removeHP(HP h){
		hp.remove(h);
	}*/
	
	public void createLevel(){		
		if(Game.level_1){
			for(int i=0;i<10;i++){
				addTile(new Wall(i*200,Game.getFrameHeight()-240,200,320,true,Id.wall,this));
			}
			
			for(int i=0;i<10;i++){
				addTile(new Railing(i*200,Game.getFrameHeight()-320,200,320,false,Id.wall,this));
			}		
			
			addEntity(new Player(140,230,200,250,true,Id.player,this,10));
			addEntity(new KillerCroc(800,180,230,270,true,Id.croc,this,10));

						
			/*addHP(new HP(10,20,102,12,"BATMAN",Color.red,10));
			addHP(new HP(1000,20,102,12,"KILLER CROC",Color.red,10));*/
			
			//System.out.println(entity);
		}		
		
		else if(Game.level_2){
			clearLevel();			
			
			addTile(new Line(0,370,145,1,true,Id.wall,this));
			addTile(new Line(145,500,70,1,true,Id.wall,this));
			
			for(int i=5;i<10;i++){
				addTile(new Wall(i*160-30,Game.getFrameHeight()-240,160,220,true,Id.wall,this));
			}		
				
			
			addEntity(new Player(0,100,200,250,true,Id.player,this,10));
			addEntity(new Gordon(480,105,140,250,true,Id.gordon,this,Gordon.State.HANGING));
			addEntity(new Joker(1050,Game.getFrameHeight()-490,250,250,true,Id.joker,this,10));
			for(int i=5;i<10;i++){
				addTile(new Railing(i*160-30,Game.getFrameHeight()-310,160,220,false,Id.wall,this));
			}
			
			addTile(new Vat(310,500,450,350,false,Id.wall,this));
			
			if(Game.difficulty  == 1) {
				Game.batCount = 10;
			}
			else if(Game.difficulty  == 2) {
				Game.batCount = 5;
			}
			else if(Game.difficulty  == 3) {
				Game.batCount = 3;
			}
			//System.out.println(entity);
		}
	}	
	
	public void clearLevel() {
		entity.clear();
		tile.clear();
		//hp.clear();
	}
}