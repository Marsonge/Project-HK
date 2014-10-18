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
public interface Entity2D {
	public float getX();
	public float getY();
        public void setX(float x);
        public void setY(float y);
        public void setLocation(float x, float y);
        
	public void setUp();
	public void destroy();
	public void draw();
}