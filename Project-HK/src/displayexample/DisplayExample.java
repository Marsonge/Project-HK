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
import displayexample.Entities.Entity2D;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;


public class DisplayExample {
        float x;
        float y;
        Entity2D box;
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
            if (Mouse.isButtonDown(0)) {

                x = (float)Mouse.getX();

                y = (float)Mouse.getY();
                   box = new Box2D(x, y, 25.0f);
                   box.setUp();
                   box.draw();
                System.out.println("MOUSE DOWN @ X: " + x + " Y: " + y);
        }
            
        }
	
	public static void main(String[] argv) {
		DisplayExample displayExample = new DisplayExample();
		displayExample.start();
	}
}