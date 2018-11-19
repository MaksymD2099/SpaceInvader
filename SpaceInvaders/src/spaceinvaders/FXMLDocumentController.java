/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders;


import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


/**
 *
 * @author Family Desktop
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
     AnchorPane pane;
  
    @FXML
    private ImageView mainSpaceShip;
    
    @FXML
    private ImageView projectileLaser;
    
    @FXML
    private Label MouseXvalue;
    
    private double lastFrameTime = 0.0;
    
    private ArrayList<Circle> projectileList = new ArrayList<>();
    private ArrayList<Vector2D> projectileVelocityList = new ArrayList<>();
   
    @FXML
    public void mouseMoved(MouseEvent event)
    {
        double X = event.getSceneX();
        MouseXvalue.setText(X+ ""); //Debug
         
        mainSpaceShip.setX(X - 430);
             
    }
    
    public void addToPane(Node node)
    {
        pane.getChildren().add(node);
    }
    
    
    @FXML
    public void mouseClickedShoot(MouseEvent event)
    {
        //Shoot Laser        
        lastFrameTime = 0.0f;
        long initialTime = System.nanoTime();

        Circle projectile = new Circle(0, 0, 10/*AssetManager.getProjectileImage().getHeight() * 2*/);
        projectile.setCenterX(mainSpaceShip.getX());
        projectile.setCenterY(mainSpaceShip.getY());
        projectileList.add(projectile);
        projectileVelocityList.add(new Vector2D(600, 0));
        addToPane(projectile);
       
        
        new AnimationTimer()
        {
            @Override
            public void handle(long now) {

                
                // Time calculation                
                double currentTime = (now - initialTime) / 1000000000.0;
                double  frameDeltaTime = currentTime - lastFrameTime;
                lastFrameTime = currentTime;

                // Move projectiles every frame
                for (int i=0; i< projectileList.size(); i++)
                {
                    Circle projectile = projectileList.get(i);
                    Vector2D position = new Vector2D(projectile.getCenterX(), projectile.getCenterY());
                    Vector2D v = projectileVelocityList.get(i);
                    position = position.add(v.mult(frameDeltaTime));
                    projectile.setCenterX(position.getX());
                    projectile.setCenterY(position.getY());
                    
                    // collision with edges
                    if (projectile.getCenterX() - projectile.getRadius() < 0)
                    {
                        v.setX(Math.abs(v.getX()));
                    }

                    if (projectile.getCenterX() + projectile.getRadius() > pane.getWidth())
                    {
                        v.setX(-Math.abs(v.getX()));
                    }
                    
                    if (projectile.getCenterY() - projectile.getRadius() < 0)
                    {
                        v.setY(Math.abs(v.getY()));
                    }
                    
                    if (projectile.getCenterY() + projectile.getRadius() > pane.getHeight())
                    {
                        v.setY(-Math.abs(v.getY()));
                    }
                }
                
            }
        }.start();        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
        AssetManager.preloadAllAssets();
    } 
   }    
    

