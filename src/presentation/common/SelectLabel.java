package presentation.common;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

import presentation.main.Mainframe;

public class SelectLabel extends JLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private boolean isPopup = false;
	
	public boolean isSelected = false;
	Color entered;
	Color pressed;
	Color exicted;

	
	public SelectLabel(String text,Point p1,Point p2){
		this.setOpaque(true);
		this.setText(text);
		this.setFont(new Font("Dialog",1,15));
		this.setBackground(Color.BLACK);
		this.setForeground(Color.WHITE);
		this.setHorizontalAlignment(JLabel.CENTER);
		this.setBounds(p1.x, p1.y, p2.x, p2.y);
		this.addMouseListener(new LabelListener());
		this.entered = Color.LIGHT_GRAY;
		this.pressed = Color.GRAY;
		this.exicted = Color.BLACK;
	}
	
	public void setIsPopup(){
		this.isPopup = true;
	}
	
	public SelectLabel(String text,Point p1,Point p2,Color entered,Color pressed,Color exicted){
		this.setOpaque(true);
		this.setText(text);
		this.setFont(new Font("Dialog",1,15));
		this.setBackground(Color.BLACK);
		this.setForeground(Color.WHITE);
		this.setHorizontalAlignment(JLabel.CENTER);
		this.setBounds(p1.x, p1.y, p2.x, p2.y);
		this.addMouseListener(new LabelListener());
		this.entered = entered;
		this.pressed = pressed;
		this.exicted = exicted;
	}
	
	public SelectLabel(String text,Point p1){
		this.setOpaque(true);
		this.setText(text);
		this.setFont(new Font("Dialog",1,15));
		this.setBackground(Color.BLACK);
		this.setForeground(Color.WHITE);
		this.setHorizontalAlignment(JLabel.CENTER);
		this.setPreferredSize(new Dimension(p1.x,p1.y));
		this.addMouseListener(new LabelListener());
		this.entered = Color.LIGHT_GRAY;
		this.pressed = Color.GRAY;
		this.exicted = Color.BLACK;
	}

	class LabelListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			isSelected = true;
			SelectLabel l = (SelectLabel) e.getSource();
			l.setBackground(pressed);

		}

		@Override
		public void mousePressed(MouseEvent e) {
	
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if(SelectLabel.this.getText()!="热区"){
				Mainframe.getFrame().removeHotRegionFromPopup();
			}
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			SelectLabel l = (SelectLabel) e.getSource();
			l.setBackground(entered);
			SelectLabel.this.getParent().setVisible(true);
		}

		@Override
		public void mouseExited(MouseEvent e) {
			SelectLabel l = (SelectLabel) e.getSource();
			if(!l.isSelected){
				l.setBackground(exicted);
			}else{
				l.setBackground(pressed);
			}
			
			if(isPopup){

				int x = e.getX();
				if(x<=0 || x>=SelectLabel.this.getWidth()){
					SelectLabel.this.getParent().setVisible(false);
				}
			}
			
//			int count = SelectLabel.this.getParent().getComponentCount();
//			if(SelectLabel.this.getParent().getComponent(count-1) == SelectLabel.this){//如果是最下面的组件
//				int y = e.getY();
//				if(y>=SelectLabel.this.getHeight()){
//					SelectLabel.this.getParent().setVisible(false);
//				}
//			}
		}
		
		
	}

}
