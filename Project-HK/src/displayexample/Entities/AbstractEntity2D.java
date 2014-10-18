/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package displayexample.Entities;

/**
 *
 * @author Fabien
 */
public abstract class AbstractEntity2D implements Entity2D {
	protected float x;
	protected float y;
	public float getX() { return x; }
	public float getY() { return y; }
        public void setX(float x) { this.x = x; }
        public void setY(float y) { this.y = y; }
        public void setLocation(float x, float y) {
    		this.x = x;
    		this.y = y;
        }
       
	public abstract void setUp();
	public abstract void destroy();
	public abstract void draw();
}