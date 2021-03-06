/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders;

import java.io.File;
import java.util.ArrayList;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.paint.ImagePattern;

/**
 *
 * @author Family Desktop
 */
public class AssetManager {
    
    static private Background backgroundImage = null;
    static private ArrayList<ImagePattern> aliens = new ArrayList<>();  
    static private ImagePattern projectileLaser;
    
    static private Media backgroundMusic = null;
    static private AudioClip ennemyHitSound = null;
    static private AudioClip shootingSound = null;
    
    static private String fileURL(String relativePath)
    {
        return new File(relativePath).toURI().toString();
    }
    
    static public void preloadAllAssets()
    {
        Image background = new Image(fileURL("./assets/images/background.gif"));
        
        backgroundImage = new Background(
                                    new BackgroundImage(background,
                                    BackgroundRepeat.NO_REPEAT,
                                    BackgroundRepeat.NO_REPEAT,
                                    BackgroundPosition.DEFAULT,
                                    BackgroundSize.DEFAULT));
        
        
        aliens.add(new ImagePattern(new Image(fileURL("./assets/images/alien_1.png"))));
        aliens.add(new ImagePattern(new Image(fileURL("./assets/images/alien_2.png"))));
        aliens.add(new ImagePattern(new Image(fileURL("./assets/images/alien_3.png"))));
        aliens.add(new ImagePattern(new Image(fileURL("./assets/images/alien_4.png"))));
        aliens.add(new ImagePattern(new Image(fileURL("./assets/images/alien_5.png"))));
        
        projectileLaser = new ImagePattern(new Image(fileURL("./assets/images/Projectile.png")));
           
        // Preload all music tracks
        backgroundMusic = new Media(fileURL(".assets/music/music.wav"));
        
         // Preload all sound effects
        shootingSound = new AudioClip(fileURL("./assets/soundfx/Laser_Gun.mp3"));
        ennemyHitSound = new AudioClip(fileURL("./assets/soundfx/Plastic_on_Cement.mp3"));
        
    }
    
    static public Background getBackgroundImage()
    {
        return backgroundImage;
    }
    
    static public ImagePattern getSpecificEnnemy(int levelOfEnnemy)
    {
        return aliens.get(levelOfEnnemy);
    }
    
    static public ImagePattern getProjectileImage()
    {
        return projectileLaser;
    }

    static public AudioClip getShootingSound()
    {
        return shootingSound;
    }
    
    static public AudioClip getEnnemyHitSound()
    {
        return ennemyHitSound;
    }
}
