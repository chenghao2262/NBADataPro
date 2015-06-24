package presentation.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.border.EtchedBorder;


public class GuideLabel extends JLabel implements MouseListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private IMainFrame imainframe;
	
	private boolean selected = false;
	private SuboptionLayer suboptionLayer;
	private boolean hasSuboptions = false;
	private IGuideContainer container;
	
	private String son;

	Color entered = new Color(30,80,140);
	Color pressed = new Color(42,109,183);
	Color exicted= new Color(26,70,122);
	
	private Point sourcePoint;
	private int dr;
	private Timer timer;
	public boolean released = false;
	
	
		public GuideLabel(){
			initialise();
		}
		
		public GuideLabel(String text){
			this.setText(text);
			this.setFont(new Font("Dialog",1,20));
			this.setHorizontalAlignment(JLabel.CENTER);
			initialise();
		}
		
		
		
		private void initialise(){
			this.setSize(120, 130);
			this.setBackground(pressed);
			this.setForeground(Color.WHITE);
			this.setOpaque(true);
			this.addMouseListener(this);
			this.setFont(new Font("微软雅黑", Font.BOLD, 20));
			this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		}
		
		public void clearSourcePoint(){
			released = false;
		}
/**
 * observer
 * */		
		public void setMainFrame(IMainFrame imainframe){
			this.imainframe = imainframe;
		}
		
		public void setContainer(IGuideContainer container){
			this.container = container;
					
		}
		
		public void linkSuboption(SuboptionLayer suboption){
			this.suboptionLayer = suboption;
			suboption.link(this);
			hasSuboptions = true;
		}

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			imainframe.update(this.getText());
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			if(selected){
				
			}else{
				this.setSize(130, 130);
			}
			
			released = false;
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			if(selected){
				
			}else{
				this.setSize(120, 130);
			}	
			released = false;
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if(e!=null){
				sourcePoint = e.getPoint();
			}
			if(selected){
				setUnselected();
			}else{
				setSelected();
				if(suboptionLayer != null){
					suboptionLayer.showSuboptions();
				}
			}	
			released = true;
			makeWave();
//			selected = !selected;
//			System.out.println(selected);
		}
		
		public void update(){
			if(selected){
				selected = false;
				setUnselected();
			}
		}
		
		private void setSelected(){
			selected = true;
			this.setSize(150, 130);
			container.notifyOtherGuides(this);
		}
		
		private void setUnselected(){
			selected = false;
			this.setSize(120, 130);
			if(suboptionLayer != null){
				suboptionLayer.hideSuboptions();
			}
		}
		
		private void makeWave(){
			if(timer != null){
				timer.stop();
			}
			timer = new Timer(20, new WaveAction());
			timer.start();
		}
		
		private void setSigned(){
			
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
					g2d.setColor(new Color(50, 50, 50, 20));
					g2d.fillOval(x, y, 2*dr, 2*dr);
					g2d.fillOval(x-2, y-2, 2*dr+4, 2*dr+4);
					g2d.setColor(c);
					
//					System.out.println("wave");
				}else{
					timer.stop();
					dr = 0;
				}
				
			}
		}
		
		class WaveAction implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				GuideLabel.this.repaint();
			}
			
		}
		
		public static void main(String args[]){
			JFrame f = new JFrame();
			f.setBounds(40, 40, 500, 500);
			f.setLayout(null);
			GuideLabel l = new GuideLabel("Button");
			l.setLocation(50, 50);
			f.add(l);
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			f.setVisible(true);
		}
}
