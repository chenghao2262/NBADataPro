package presentation.match.live;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import vo.matchvo.MatchLiveVO;

public class MatchLiveContentPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private MessageScrollPane scrollPane;
	private MessageContainerPane messageContainer;
	
	private TailLabel tail;
	public MatchLiveVO vo;

	public MatchLiveContentPanel(MatchLiveVO vo){
		this.vo = vo;
		this.initialise();
	}
	
	private void initialise(){
		this.repaint();
		
		this.setSize(1200, 540);
		this.setLocation(40, 215);
		this.setBackground(Color.white);
		this.setLayout(null);
		
		this.setHeader();
		this.setContent();
		this.setTail();
		
	}
	
	private void setHeader(){
		HeaderLabel header = new HeaderLabel();
		this.add(header);
	}
	
	private void setContent(){
		messageContainer = new MessageContainerPane();
		scrollPane = new MessageScrollPane(messageContainer);
		this.add(scrollPane);
		scrollPane.repaint();
	}
	
	public void appendMessage(String time, String hostMessage, String scores, String guestMessage){
		scrollPane.appendMessage(time, hostMessage, scores, guestMessage);
	}
	
	public void appendPeriod(int period, String scores){
		scrollPane.appendPeriod(period, scores);
	}
	
	public void clearMessages(){
//		messageContainer.initialise();;
		this.remove(scrollPane);
		this.repaint();
		this.setContent();
	}
	
	private void setTail(){
		tail = new TailLabel();
		tail.setBounds(0, 440, 1200, 40);
		this.add(tail);
	}
	
	public void initialiseMatchTime(MatchTime time){
		tail.changeCurrentTime(time);
	}

	public void startTiming(){
		tail.startTiming();
	}

	class HeaderLabel extends JLabel{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private int height;
		HeaderLabel(){
			height = 30;
			this.initialise();
		}
		
		private void initialise(){
			this.setLayout(null);
			this.setBounds(0, 0, 1200, height);
			
			this.setTime();
			this.setHost();
			this.setScores();
			this.setGuest();
		}
		
		private void setTime(){
			JLabel timeLabel = new FontLabel();
			timeLabel.setBackground(new Color(42, 109, 183));
			timeLabel.setText("时间");
			timeLabel.setBounds(0, 0, 100, height);
			this.add(timeLabel);
		}
		
		private void setHost(){
			JLabel hostLabel = new FontLabel();
			hostLabel.setBackground(new Color(42, 109, 183));;
			hostLabel.setText("主场：" + vo.homeTeamName);
			hostLabel.setBounds(102, 0, 493, height);
			this.add(hostLabel);
		}
		
		private void setScores(){
			JLabel scoresLabel = new FontLabel();
			scoresLabel.setBackground(new Color(42, 109, 183));
			scoresLabel.setText("比分");
			scoresLabel.setBounds(597, 0, 98, height);
			this.add(scoresLabel);
		}
		
		private void setGuest(){
			JLabel guestLabel = new FontLabel();
			guestLabel.setBackground(new Color(42, 109, 183));
			guestLabel.setText("客场：" + vo.awayTeamName);
			guestLabel.setBounds(697, 0, 493, height);
			this.add(guestLabel);
		}
	}

	class TailLabel extends JLabel{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		MatchTime time;
		Timer timer;
		ActionListener action;
		JLabel timeLabel;

		TailLabel(){
//			this.initialise();
		}
		
		private void initialise(){
			this.setBounds(0, 500, 1200, 40);
			this.setBackground(Color.black);
			this.setOpaque(true);
			this.setLayout(null);
			
			this.setCurrentTime();
			this.setScrollLock();
			this.setCurrentScores();
		}
		
		private void setCurrentTime(){
			timeLabel = new FontLabel();
			timeLabel.setText("00:00.00");
			timeLabel.setBounds(0, 0, 100, 40);
			this.add(timeLabel);
			System.out.println("set current time");
		}
		
		public void changeCurrentTime(MatchTime time){
			timeLabel.setText(time.toString());
			this.time = time;
		}
		
		public void startTiming(){
			timer = new Timer(10, new Action());
			timer.start();
		}
		
		private void setCurrentScores(){
			
		}
		
		private void setScrollLock(){
			ScrollLockLabel lockLabel = new ScrollLockLabel();
			this.add(lockLabel);
			lockLabel.setScroll(scrollPane);
		}
		
		
		class Action implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				time = TailLabel.this.time.advanceByDecimal();
				changeCurrentTime(time);
				if(time.toString().equals("00:00.00")){
					timer.stop();
				}
//					System.out.println(time.toString());
			}
			
		}
	}
}


