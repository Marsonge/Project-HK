/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package displayexample;

/**
 *
 * @author Fabien
 */
import displayexample.Entities.Box2D;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;


public class DisplayExample {
        float x;
        float y;
        Box2D box;
	public void start() {
		x=15;
                y=15;
                try {
			Display.setTitle("Exemple de fenêtre !");
                        Display.setDisplayMode(new DisplayMode(800,600));
			Display.create();
                        
                        
                        
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}
		box = new Box2D(30.0f, 50.0f, 25.0f);
                        box.setUp();
		// init OpenGL here
                        GL11.glMatrixMode(GL11.GL_PROJECTION);
	GL11.glLoadIdentity();
	GL11.glOrtho(0, 800, 0, 600, 1, -1);
	GL11.glMatrixMode(GL11.GL_MODELVIEW);
		
		while (!Display.isCloseRequested()&& !Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
			
			// render OpenGL here
                        
			box.draw();
                        pollInput();
                        
			Display.update();
                        Display.sync(60);
		}
		
		Display.destroy();
                System.exit(0);
	}
        
        public void pollInput(){
            /*
            Prend en compte lorsqu'on presse une touche ou le clic gauche/droit souris
            
            */
            
            if(Keyboard.isKeyDown(Keyboard.KEY_Q)||Keyboard.isKeyDown(Keyboard.KEY_LEFT)) box.setX(box.getX()-2);
            if(Keyboard.isKeyDown(Keyboard.KEY_D)||Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) box.setX(box.getX()+2);
            if(Keyboard.isKeyDown(Keyboard.KEY_Z)||Keyboard.isKeyDown(Keyboard.KEY_UP)) box.setY(box.getY()+2);
            if(Keyboard.isKeyDown(Keyboard.KEY_S)||Keyboard.isKeyDown(Keyboard.KEY_DOWN)) box.setY(box.getY()-2);
            if (Mouse.isButtonDown(0)) {
                x = (float)Mouse.getX();
                y = (float)Mouse.getY();
                   //box.setLocation(x,y);
                   box.changeSize(2.00f);
                   //box.setUp();
                System.out.println("MOUSE DOWN @ X: " + x + " Y: " + y);
            }
            if(Mouse.isButtonDown(1)){
                box.changeSize(-2.00f);
                   //box.setUp();
            }
            
        }
	
	public static void main(String[] argv) {
		DisplayExample displayExample = new DisplayExample();
		displayExample.start();
	}
}