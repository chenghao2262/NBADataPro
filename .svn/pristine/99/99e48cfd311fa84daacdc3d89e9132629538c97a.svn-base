package presentation.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public abstract class SuboptionLayer extends JLabel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private GuideLabel guideLabel;
	IMainFrame imainframe;
	
	Color entered = new Color(30,80,140);
	Color pressed = new Color(42,109,183);
	Color exicted= new Color(26,70,122);


	public SuboptionLayer(){
		this.setOpaque(false);
		this.setBackground(new Color(42,109,183));
		this.setBounds(150, 450, 120, 130);
		this.setLayout(null);
		this.setVisible(false);
		this.setBorder(BorderFactory.createEtchedBorder());
		makeSubOptions();
	}
	
	public abstract void  makeSubOptions(); 
	
	
	
	public void setMainFrame(IMainFrame imainframe){
		this.imainframe = imainframe;
	}
	
	public void link(GuideLabel guideLabel){
		this.guideLabel = guideLabel;
	}
	
	public void showSuboptions() {
		
		this.setLocation(145, guideLabel.getY()+155);
		this.setVisible(true);
	}
	
	public void hideSuboptions(){
		this.setVisible(false);
	}
	
	class SubOptionLabel extends JLabel implements MouseListener{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

			public SubOptionLabel(String option){
			this.setText(option);
			this.setFont(new Font("微软雅黑", Font.PLAIN, 16));
			this.setHorizontalAlignment(SwingConstants.CENTER);
			this.setForeground(Color.WHITE);
			this.setBackground(pressed);
			this.setOpaque(true);
			this.addMouseListener(this);
			
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
				// TODO Auto-generated method stub
				
				Timer timer = new Timer(200, new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						imainframe.update(SubOptionLabel.this.getText());
						guideLabel.mouseReleased(null);
						guideLabel.released = false;
						guideLabel.repaint();
						hideSuboptions();
					}
				});
				timer.setRepeats(false);
				timer.start();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
	}
}
