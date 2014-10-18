/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package displayexample.Entities;

import static org.lwjgl.opengl.ARBTessellationShader.GL_TRIANGLES;
import org.lwjgl.opengl.GL11;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2f;

/**
 *
 * @author Fabien
 */
public class Box2D extends AbstractEntity2D {
	protected float size;
	public Box2D(float x, float y, float size) {
		this.x = x;
		this.y = y;
		this.size = size;
	}
	public Box2D() {
		this.x = this.y = this.size = 0;
	}
	@Override
	public void setUp() {
		// We don't need anything here for a box
	}
	@Override
	public void destroy() {
		// We don't need anything here for a box
	}
	@Override 
	public void draw() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);	
 
	    // set the color of the quad (R,G,B,A)
	    GL11.glColor3f(0.5f,0.5f,1.0f);
 
	    // draw quad
	    GL11.glBegin(GL11.GL_QUADS);
	        GL11.glVertex2f(x,y);
		GL11.glVertex2f(x+size,y);
		GL11.glVertex2f(x+size,y+size);
		GL11.glVertex2f(x,y+size);
	    GL11.glEnd();
	}
        
    public void changeSize(float x) {
       this.size+=x;
    }
}

