package presentation.match.live;

import java.awt.Font;

import javax.swing.JLabel;

public class FontLabel extends JLabel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	FontLabel(){
		this.initialise(14);
	}
	
	FontLabel(int size){
		this.initialise(size);
	}
	
	private void initialise(int size){
		this.setFont(new Font("微软雅黑", Font.BOLD, size));
		this.setHorizontalAlignment(JLabel.CENTER);
		this.setOpaque(true);
	}
}
