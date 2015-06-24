package presentation.team;

/*
 * 忘记加监听了
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.EtchedBorder;

import presentation.common.MultiWaveLabel;
import presentation.common.PhotoLabel;
import presentation.common.WaveLabel;
import presentation.main.Mainframe;
import presentation.player.playerDetail.PlayerMiddlePanel;
import presentation.team.teamDetail.TeamMiddlePanel;



public class SingleTeamLabel extends MultiWaveLabel{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JLabel TeamPicLabel;
	JLabel ItemsJLabel;
	String teamName;
	
	SingleDivisionLabel singleDivision;
	Color color;
	
	
	public SingleTeamLabel(String path,String location,String teamName, Color color){
		this.color = color;
		this.setLayout(null);
		this.setBounds(0, 80, 188,78);
		this.setOpaque(true);
		this.setBackground(color);
		this.setVisible(true);
//		setItemLabel();
		setTeamNameLabel(teamName);
		setTeamLocationLabel(location);
		setTeamPicLabel(path);
		this.teamName = teamName;
		this.addMouseListener(new SingleTeamAction());
		this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		
	}
	
	
	
	public void setTeamPicLabel(String path){
		TeamPicLabel = new PhotoLabel(new ImageIcon(path).getImage());
		TeamPicLabel.setBounds(0,0,140,75);
		TeamPicLabel.setVisible(true);
		this.add(TeamPicLabel);
	}
	
	/*team name changes*/
	public void setTeamNameLabel(String TeamName){
		JLabel TeamNameLabel = new JLabel(TeamName);
		TeamNameLabel.setFont(new Font("Dialog",1,13));
		TeamNameLabel.setForeground(Color.GRAY);
		TeamNameLabel.setBounds(87,40,150,30);
		this.add(TeamNameLabel);

	}
	public void setTeamLocationLabel(String location){
		JLabel TeamLocationLabel = new JLabel(location);
		TeamLocationLabel.setFont(new Font("Dialog",1,13));
		TeamLocationLabel.setForeground(Color.DARK_GRAY);
		TeamLocationLabel.setBounds(87,10,150,30);
		this.add(TeamLocationLabel);
	}
	
	public void setSingleDivision(SingleDivisionLabel singleDivision){
		this.singleDivision = singleDivision;
	}
	
	
	public static void main(String[] args){
		JFrame jf = new JFrame();
		jf.setLayout(null);
		jf.setSize(1280,700);
		jf.setLocationRelativeTo(null);
		jf.getContentPane().add(new SingleTeamLabel("teamsPNG/ATL.png","Atlanta","Hawks",Color.WHITE));
		jf.setVisible(true);
	}
	
	class SingleTeamAction implements MouseListener{

		@Override
		public void mouseClicked
		(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			Mainframe.getFrame().restoreIni();
			Mainframe.getFrame().changeContent(new TeamMiddlePanel(SingleTeamLabel.this.teamName));
			SingleTeamLabel.this.repaint();			
			SingleTeamLabel.this.setBackground(color);
			SingleTeamLabel.this.setForeground(color.GRAY);
		}

		@Override
		public void mouseEntered(MouseEvent e) {
//			SingleTeamLabel.this.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2, true));
			SingleTeamLabel.this.setBackground(color.lightGray);//new Color(100, 100, 200)
			SingleTeamLabel.this.setForeground(color.WHITE);
			SingleTeamLabel.this.repaint();
		}

		@Override
		public void mouseExited(MouseEvent e) {
			SingleTeamLabel.this.setBackground(color);
			SingleTeamLabel.this.setForeground(color.GRAY);
		}
		
	}
}
