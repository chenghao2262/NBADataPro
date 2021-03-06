package presentation.common;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.*;


public class WaveLabel extends JLabel implements MouseListener{

	protected int alpha = 150;
	protected Point sourcePoint;
	protected int dr;
	protected Timer timer;
	public boolean released = false;
	
	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setBounds(100, 100, 400, 400);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLayout(null);
		f.setBackground(Color.BLACK);
		
		WaveLabel l = new WaveLabel();
		l.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		l.setBackground(Color.GRAY);
		l.setOpaque(true);
		l.setBounds(50, 50, 200, 200);
		f.add(l);
		f.setVisible(true);
	}
	
	public WaveLabel(){
		this.addMouseListener(this);
	}
	
	public void setLight(int alpha){
		this.alpha = alpha;
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if(released ){

			if(dr<this.getWidth() || dr<this.getHeight()){
				dr+=20;
				int x = sourcePoint.x - dr;
				int y = sourcePoint.y - dr;
				Graphics2D g2d = (Graphics2D) g;
				Color c = g2d.getColor();
				g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2d.setColor(new Color(50, 50, 50));
				g2d.fillOval(x, y, 2*dr, 2*dr);
				g2d.fillOval(x, y, 2*dr+4, 2*dr+4);
				g2d.fillOval(x, y, 2*dr+8, 2*dr+8);
				g2d.fillOval(x-2, y-2, 2*dr+4, 2*dr+4);
				g2d.setColor(c);
				
//				System.out.println("wave");
			}else{
				if(timer != null){
					timer.stop();
				}
				dr = 0;
			}
			
		}
	}
	
	private void makeWave(){
		if(timer != null){
			timer.stop();
		}
		timer = new Timer(30, new WaveAction());
		timer.start();
	}
	
	public void clearSourcePoint(){
		released = false;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(e!=null){
			sourcePoint = e.getPoint();
		}
		released = true;
		makeWave();
	}

	@Override
	public void mouseEntered(MouseEvent e) {

		released = false;
		this.repaint();
	}

	@Override
	public void mouseExited(MouseEvent e) {

		released = false;
		this.repaint();
	}
	
	class WaveAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			WaveLabel.this.repaint();
		}
		
	}

}
