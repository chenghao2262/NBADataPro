package presentation.gravity;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;

public class Ball extends JLabel implements IBall{
	private int vx;
	private int vy;

	public Ball(){
		this.setSize(100, 100);
		this.setBackground(Color.WHITE);
		this.setOpaque(true);
	}
	
	public void setVX(int vx){
		this.vx = vx;
	}
	
	public void setVY(int vy){
		this.vy = vy;
	}
	
	public int getVX(){
		return vx;
	}
	
	public int getVY(){
		return vy;
	}
	

	@Override
	public void changeLocation(int x, int y) {
		this.setLocation(x, y);
	}
}
