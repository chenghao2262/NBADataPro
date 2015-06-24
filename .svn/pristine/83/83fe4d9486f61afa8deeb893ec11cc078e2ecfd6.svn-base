package presentation.match.live;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

public class ScrollLockLabel extends JLabel implements MouseListener{
	
	private Scrollable scrollPane;
	
	public static boolean state = true; //置底

	public ScrollLockLabel(){
		this.setBounds(1160, 0, 40, 40);
		this.setHorizontalAlignment(JLabel.CENTER);
		this.setOpaque(true);
		this.setStyle();
		this.addMouseListener(this);
	}
	
	public void setScroll(Scrollable scroll){
		scrollPane = scroll;
	}
	
	private void setStyle(){
		if(state){
			this.setText("-");
		}else{
			this.setText("|");
		}
	}
	
	private void setUnstyle(){
		if(!state){
			this.setText("-");
		}else{
			this.setText("|");
		}
	}
	
	public void changeState(){
		state = !state;
		setStyle();
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
		state = !state;
		this.setStyle();
		scrollPane.update();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		this.setUnstyle();
	}

	@Override
	public void mouseExited(MouseEvent e) {
		this.setStyle();
	}
}
