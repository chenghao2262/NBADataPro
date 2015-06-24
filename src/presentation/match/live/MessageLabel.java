package presentation.match.live;

import java.awt.Color;
import java.awt.Font;

import javax.swing.*;

public class MessageLabel extends Message{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static int height = 40;
	public static boolean DARK = false;

	public MessageLabel(String time, String hostMessage, String scores, String guestMessage){
		this.initialise();
		this.setHost(hostMessage);
		this.setGuest(guestMessage);
		this.setTime(time);
		this.setScores(scores);
		
	}
	
	
	private void initialise(){
		DARK = !DARK;
		if(DARK){
			this.setBackground(Color.LIGHT_GRAY);
		}else{
			this.setBackground(Color.white);
		}
		this.setLayout(null);
		this.setBounds(0, 0, 1200, height);
//		this.setBackground(Color.BLUE);
		this.setOpaque(true);
	}
	
	private void setTime(String time){
		JLabel timeLabel = new FontLabel();
		timeLabel.setBackground(new Color(200, 200, 200, 100));
		timeLabel.setText(time);
		timeLabel.setBounds(0, 0, 100, height);
		this.add(timeLabel);
	}
	
	private void setHost(String hostMessage){
		MessageTextArea hostLabel = new MessageTextArea();
		hostLabel.setText(hostMessage);		
		hostLabel.setBounds(102, 0, 493, height);
		this.add(hostLabel);
	}
	
	private void setScores(String scores){
		JLabel scoresLabel = new FontLabel();
		scoresLabel.setBackground(new Color(200, 200, 200, 100));
		scoresLabel.setText(scores);
		scoresLabel.setBounds(597, 0, 98, height);
		this.add(scoresLabel);
	}
	
	private void setGuest(String guest){
		MessageTextArea guestLabel = new MessageTextArea();
		guestLabel.setText(guest);
		guestLabel.setBounds(697, 0, 493, height);
		this.add(guestLabel);
	}
	
	public static void main(String args[]){
		JFrame f = new JFrame();
		f.setBounds(0, 0, 1280, 700);
		f.setLayout(null);
		
		MessageLabel l = new MessageLabel("12:23", "lskadjfla森拉克毒奶粉历史课的发就仨的了发就撒丝路宽带放假拉山口的就发skdjf啊三度空间法拉第13242", "20:123", "skldajf");
		f.add(l);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
}

class MessageTextArea extends JTextArea{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	MessageTextArea(){
		this.setBorder(null);
		this.setOpaque(false);
		this.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		this.setLineWrap(true);
		this.setAutoscrolls(true);
	}
	
	
}
