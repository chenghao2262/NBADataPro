package presentation.gravity;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Motion {
	
	private IBall ball;
	private IGround ground;
	
	private Timer timer;
	
	int g = 5;
	int unitTime = 1;
	
	
	public Motion(IBall ball, IGround ground){
		this.ball = ball;
		ball.setVX(23);
		ball.setVY(0);
		ball.changeLocation(500, 500);
		
		this.ground = ground;
		MotionAction action = new MotionAction();
		timer = new Timer(20, action);
	}
	
	public void act(){
		timer.start();
	}

	public static void main(String args[]){
		JFrame f = new JFrame();
		f.setSize(1300, 768);
		f.setLayout(null);
		GroundPanel ground = new GroundPanel();
		Ball ball = new Ball();
		Motion motion = new Motion(ball, ground);
		ground.add(ball);
		f.add(ground);
		f.setVisible(true);
		motion.act();
	}
	
	class MotionAction implements ActionListener{

		private int width;
		private int height;
		
		public MotionAction() {
			width = ground.getSize().width;
			height = ground.getSize().height;
		}
		
		public void actionPerformed(ActionEvent e) {
			int currentX = ball.getX();
			int currentY = ball.getY();
			
			
			int currentVX = ball.getVX();
			int currentVY = ball.getVY();

			if(currentX<=0 || (currentX+100)>=width){
				currentVX = (int) (-currentVX*0.9);
			}
			if(currentY<=0 || (currentY+100)>=height){
				currentVY = (int) (-currentVY*0.9);
			}
			
			
			int vx = currentVX;
			int vy = currentVY + g*unitTime;
			
			if(vx == 1){
				vx =0;
			}
			if(vy == 1){
				vy = 0;
			}
			
			ball.setVX(vx);
			ball.setVY(vy);
			
			
			
			int x = currentX + currentVX*unitTime;
			int y = currentY + ((currentVY + vy)/2)*unitTime;
			
			ball.changeLocation(x, y);
			
			System.out.println("vx: "+vx+"     vy: "+vy);
		}
		
	}

}
