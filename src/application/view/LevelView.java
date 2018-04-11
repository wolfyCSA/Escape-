/**
 * 
 * @author wolfyCSA
 *
 */

package application.view;

import application.model.Level;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class LevelView extends GridPane{

	//private Level model; //replaced by Main.model
	//private int imgSize;
	
	//public LevelView( Level model ) {
		//this.model = model;
	//}//end constructor
	public LevelView() {}
	
	// TODO: create the other getter methods 
	
	public ImageView chooseImage(String code) {
		switch( code ) {
			case "p" : return getPlayerImage();
			case " " : return getDefaultImage();
			case "b" : return getBulletImage();
			default: return getDefaultImage();
		}//end switch
	}//end chooseImage()
	
	
	public ImageView getPlayerImage() {
		//TODO: update, to grab spriteLink from profile object
		ImageView img = new ImageView("File:data/Escape Sprites/playership.png");
		
		return img;
	}//end getPlayerImage()
	
	public ImageView getBulletImage() {
		//TODO: update, to grab spriteLink from cortex entry
		ImageView img = new ImageView("File:data/test_sprites/Yello_Dot.png");
		
		return img;
	}//end getBulletImage()
	
	public ImageView getEnemyImage() {
		//TODO: update, to grab spriteLink from cortex entry
		ImageView img = new ImageView("File:test_sprites/test_enemy.png");
		
		return img;
	}//end getEnemyImage()
	
	public ImageView getDefaultImage() {
		//TODO: update, to grab spriteLink from cortex entry
		ImageView img = new ImageView("File:test_sprites/default.png");
		
		return img;
	}//end getDefaultImage()
	
}//end class LevelView