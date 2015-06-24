package presentation.match.live;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class PeriodMessageLabel extends Message {

	public PeriodMessageLabel(int period, String scores){
		this.initialise();
		this.setText("第"+period+"小节结束"+"          当前比分"+" "+scores);
	}
	
	private void initialise(){
		this.setHorizontalAlignment(JLabel.CENTER);
		this.setLayout(null);
		this.setBounds(0, 0, 1200, MessageLabel.height);
		this.setBackground(new Color(30, 30, 60));
		this.setOpaque(true);
		this.setFont(new Font("微软雅黑", Font.BOLD, 16));
		this.setForeground(Color.white);
	}

}
