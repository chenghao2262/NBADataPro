package presentation.team.teamDetail;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import presentation.common.PhotoLabel;
import presentation.common.Season;
import presentation.common.SeasonComboBox;
import vo.teamvo.TeamVO;

public class TeamDetailTopPanel  extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JLabel TeamNameLabel;
	JLabel WinRate;
	JPanel TeamBasicInfoPanel;
	JPanel TeamAvgFieldPanel;
	JLabel teamPhotoLabel;
	TeamVO vo;
	
	TeamMiddlePanel observer;
	
	SeasonComboBox seasonComboBox;//赛季选择
	JLabel commitSeason;//提交赛季

	public TeamDetailTopPanel(TeamVO v){
		vo = v;

		this.setLayout(null);
		this.setBounds(0, 0, 1280,200);
		this.setBackground(Color.white);
		setTeamPhotoLabel();
		setTeamName();
		setWinRate();
		setSeasonComboBox();
		setTeamBasicInfoPanel();
		setTeamAvgFieldPanel();
	}
	
	/*
	 * 画一条线分割基础信息与胜负场数
	 */
	public void paint(Graphics g){
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setPaint(Color.gray);
		g2.draw(new Line2D.Double(700, 30, 700, 100));
//		g2.drawString("Line", 350, 250);
	}
	
	/*
	 * 在toplabel里添加球员图片；
	 */
	public void setTeamPhotoLabel (){
		teamPhotoLabel = new PhotoLabel(new ImageIcon("teamsPNG/"+vo.photo+".png").getImage());
		teamPhotoLabel.setBounds(0,15,230,185);
		teamPhotoLabel.setBackground(Color.WHITE);
		teamPhotoLabel.setOpaque(true);
		teamPhotoLabel.setVisible(true);
		this.add(teamPhotoLabel);
	}
	
	
	/*
	 * 添加球队所在地和名称label
	 */
	public void setTeamName(){
		TeamNameLabel = new JLabel(vo.location+" "+vo.teamName);
		TeamNameLabel.setFont(new Font("Dialog",1,40));
		TeamNameLabel.setForeground(Color.GRAY);
		TeamNameLabel.setBounds(260,40,440,60);
		this.add(TeamNameLabel);
		
	}
	
	public int setSeasonAttri(){
		int resultSeason = 0;
		String seasonSelected = (String) seasonComboBox.getSelectedItem();
		switch(seasonSelected){
			case"赛季":resultSeason = 2014;break;
			case"2014-2015":resultSeason = 2014;break;
			case"2013-2014":resultSeason = 2013;break;
			case"2012-2013":resultSeason = 2012;break;
			case"2011-2012":resultSeason = 2011;break;
			case"2010-2011":resultSeason = 2010;break;
			case"2009-2010":resultSeason = 2009;break;
			default:break;
		}
		return resultSeason;
	}
	
	public void registerObserver(TeamMiddlePanel o){
		observer = o;
	}
	
	public void setSeasonComboBox(){
		seasonComboBox = new SeasonComboBox();
		seasonComboBox.setlocation(new Point(1040,50));
		this.add(seasonComboBox);
		
		commitSeason = new JLabel("commit");
		commitSeason.setForeground(Color.GRAY);
		commitSeason.setFont(new Font("Dialog",0,18));
		commitSeason.setBounds(1200,52,80,30);
		commitSeason.addMouseListener(new SeasonCommitListener());
		this.add(commitSeason);
		this.updateUI();
	} 
	
	class SeasonCommitListener implements MouseListener{
		int times = 0;
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			times ++;
			if(times%2 == 1){
				commitSeason.setFont(new Font("Dialog",1,15));
			}else{
				commitSeason.setFont(new Font("Dialog",1,18));
			}
			observer.updateNoVS(setSeasonAttri());
			Season.SEASON = setSeasonAttri();
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			commitSeason.setCursor(new Cursor(Cursor.HAND_CURSOR));	
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	
	public void setWinRate(){
		WinRate = new JLabel("胜率: "+String.valueOf(vo.winRate).substring(0, 5));
		WinRate.setFont(new Font("微软雅黑",1,40));
		WinRate.setForeground(Color.DARK_GRAY);
		WinRate.setBounds(710,30,570,60);
		this.add(WinRate);
	}
	
	public void setTeamBasicInfoPanel(){
		TeamBasicInfoPanel = new JPanel();
		TeamBasicInfoPanel.setLayout(new GridLayout(4,1,0,0));
		TeamBasicInfoPanel.setBounds(260,100,200,90);
		TeamBasicInfoPanel.setBackground(Color.WHITE);
				
		JLabel leagueLabel = new JLabel("League:     "+vo.league);
		leagueLabel.setFont(new Font("Dialog",1,10));
		leagueLabel.setForeground(Color.DARK_GRAY);
		TeamBasicInfoPanel.add(leagueLabel);

		JLabel divisionLabel = new JLabel("Division:     " + vo.division);
		divisionLabel.setFont(new Font("Dialog",1,10));
		divisionLabel.setForeground(Color.DARK_GRAY);
		TeamBasicInfoPanel.add(divisionLabel);
		
		JLabel homeCourtLabel = new JLabel("Home court:	" + vo.homeCourt);
		homeCourtLabel.setFont(new Font("Dialog",1,10));
		homeCourtLabel.setForeground(Color.DARK_GRAY);
		TeamBasicInfoPanel.add(homeCourtLabel);
		
		JLabel foundTimeLabel = new JLabel("Found time:    " + vo.foundTime);
		foundTimeLabel.setFont(new Font("Dialog",1,10));
		foundTimeLabel.setForeground(Color.DARK_GRAY);
		TeamBasicInfoPanel.add(foundTimeLabel);
		
		TeamBasicInfoPanel.setVisible(true);
		this.add(TeamBasicInfoPanel);
	}

	public void setTeamAvgFieldPanel(){
		TeamAvgFieldPanel = new JPanel();
		TeamAvgFieldPanel.setLayout(new GridLayout(2,3,10,0));
		TeamAvgFieldPanel.setBounds(710,120,230,78);
		TeamAvgFieldPanel.setBackground(Color.WHITE);
				
		JLabel AvgFieldScoreLabel = new JLabel("场均得分");
		AvgFieldScoreLabel.setFont(new Font("Dialog",1,15));
		AvgFieldScoreLabel.setForeground(Color.GRAY);
		TeamAvgFieldPanel.add(AvgFieldScoreLabel);

		JLabel AvgFieldREBLabel = new JLabel("场均篮板");
		AvgFieldREBLabel.setFont(new Font("Dialog",1,15));
		AvgFieldREBLabel.setForeground(Color.GRAY);
		TeamAvgFieldPanel.add(AvgFieldREBLabel);
		
		JLabel AvgFieldAssistLabel = new JLabel("场均助攻");
		AvgFieldAssistLabel.setFont(new Font("Dialog",1,15));
		AvgFieldAssistLabel.setForeground(Color.GRAY);
		TeamAvgFieldPanel.add(AvgFieldAssistLabel);
		
		JLabel AvgFieldScore = new JLabel(Double.valueOf(String.valueOf(vo.avgPoint).substring(0, 5))+"");
		AvgFieldScore.setFont(new Font("Dialog",1,20));
		AvgFieldScore.setForeground(Color.GRAY);
		TeamAvgFieldPanel.add(AvgFieldScore);
		
		JLabel AvgFieldREB = new JLabel(Double.valueOf(String.valueOf(vo.avgRebound).substring(0, 5))+"");
		AvgFieldREB.setFont(new Font("Dialog",1,20));
		AvgFieldREB.setForeground(Color.GRAY);
		TeamAvgFieldPanel.add(AvgFieldREB);
		
		JLabel AvgFieldAssist = new JLabel(Double.valueOf(String.valueOf(vo.avgAssist).substring(0, 5))+"");
		AvgFieldAssist.setFont(new Font("Dialog",1,20));
		AvgFieldAssist.setForeground(Color.GRAY);
		TeamAvgFieldPanel.add(AvgFieldAssist);
				
		TeamAvgFieldPanel.setVisible(true);
		this.add(TeamAvgFieldPanel);
	}
	
	public void updateTeamDetailTopPanel(TeamVO v){
		 this.remove(TeamNameLabel);
		 this.remove(WinRate);
		 this.remove(TeamBasicInfoPanel);
		 this.remove(TeamAvgFieldPanel);
		 this.remove(teamPhotoLabel);
		 setWinRate();
		 setTeamBasicInfoPanel();
		 setTeamAvgFieldPanel();
		 setTeamPhotoLabel();
		 setTeamName();
		 seasonComboBox.setVisible(true);
		 commitSeason.setVisible(true);
	}
	
	
	public static void main(String[] args){
		JFrame jf = new JFrame();
		jf.setLayout(null);
		jf.setSize(1280,700);
		jf.setLocationRelativeTo(null);
		TeamVO vo = new TeamVO();
		vo.teamName = "Hawks";
		vo.league = 'E';
		vo.photo = "ATL";
		vo.location = "Atlanta";

		jf.add(new TeamDetailTopPanel(vo));
		jf.setVisible(true);
	}
}
