package presentation.statistics.teamKing;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import presentation.common.SeasonComboBox;

public class TeamKingPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JLabel TitleLabel;
	
	TeamKingOptionsPanel teamKingOptionsPanel;
	
	SeasonComboBox seasonComboBox;//赛季选择
	JLabel commitSeason;//提交赛季
	
	Color entered = new Color(30,80,140);

	public TeamKingPanel(){
		this.setLayout(null);
		this.setBounds(150, 125,1130,600);
		this.setBackground(Color.WHITE);
		setTitle();
		setSeasonComboBox();
		setTeamKingOptionsPanel();
	}
	
	public void setTitle(){
		TitleLabel = new JLabel(" 赛季 联盟球队数据王",JLabel.LEADING);
		TitleLabel.setFont(new Font("Dialog",1,25));
		TitleLabel.setForeground(Color.WHITE);
		TitleLabel.setBackground(entered);
		TitleLabel.setOpaque(true);
		TitleLabel.setBounds(0,0,1130,100);
		this.add(TitleLabel);
		this.updateUI();
		//new Color(33,82,138)
	}
	
	public void setSeasonComboBox(){
		seasonComboBox = new SeasonComboBox();
		seasonComboBox.setlocation(new Point(550,25));
		TitleLabel.add(seasonComboBox);
		
		commitSeason = new JLabel("commit");
		commitSeason.setForeground(Color.WHITE);
		commitSeason.setFont(new Font("Dialog",0,18));
		commitSeason.setBounds(750,20,100,50);
		commitSeason.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				commitSeason.setFont(new Font("Dialog",1,15));
				int season = setAttri();
				System.out.println(season);
				teamKingOptionsPanel.season = season;
				teamKingOptionsPanel.remove(teamKingOptionsPanel.teamKingContentPanel);

				if(teamKingOptionsPanel.FildScoreButton.isSelected){
					teamKingOptionsPanel.setTeamKingContentPanel("avgPoint");
				}
				if(teamKingOptionsPanel.REBButton.isSelected){
					teamKingOptionsPanel.setTeamKingContentPanel("avgRebound");
				}
				if(teamKingOptionsPanel.AssitButton.isSelected){
					teamKingOptionsPanel.setTeamKingContentPanel("avgAssist");
				}
				if(teamKingOptionsPanel.BSButton.isSelected){
					teamKingOptionsPanel.setTeamKingContentPanel("avgBlockShot");
				}
				if(teamKingOptionsPanel.TPSPButton.isSelected){
					teamKingOptionsPanel.setTeamKingContentPanel("three");
				}
				if(teamKingOptionsPanel.StealButton.isSelected){
					teamKingOptionsPanel.setTeamKingContentPanel("avgSteal");
				}
				if(teamKingOptionsPanel.FGPButton.isSelected){
					teamKingOptionsPanel.setTeamKingContentPanel("shot");
				}
				if(teamKingOptionsPanel.FTPButton.isSelected){
					teamKingOptionsPanel.setTeamKingContentPanel("penalty");
				}
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
			
		});
		TitleLabel.add(commitSeason);
	}
	
	public int setAttri(){
		int resultSeason = 0;
		String seasonSelected = (String) seasonComboBox.getSelectedItem();
		switch(seasonSelected){
			case"赛季":resultSeason = 14;break;
			case"2014-2015":resultSeason = 14;break;
			case"2013-2014":resultSeason = 13;break;
			case"2012-2013":resultSeason = 12;break;
			case"2011-2012":resultSeason = 11;break;
			case"2010-2011":resultSeason = 10;break;
			case"2009-2010":resultSeason = 13;break;
			default:break;
		}
		return resultSeason;
	}
	


	public void setTeamKingOptionsPanel(){
		teamKingOptionsPanel = new TeamKingOptionsPanel();
		this.add(teamKingOptionsPanel);
	}

	public static void main(String[] args){
		JFrame jf = new JFrame();
		jf.setLayout(null);
		jf.setSize(1280,700);
		jf.setLocationRelativeTo(null);
		jf.add(new TeamKingPanel());
		jf.setVisible(true);
	}

}
