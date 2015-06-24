package presentation.diagram;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import blservice.chartsblservice.ChartBLService;
import businesslogic.statsbl.ChartBL;
import vo.teamvo.TeamWLProfilesVO;

public class WLProfiles extends Diagram{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JPanel wlPanel;
	
	JPanel wlStatsPanel;
	TeamWLProfilesVO vo;
	
	ChartBLService chartbl = new ChartBL();

	public WLProfiles(int teamID, int season) {
		super("     胜负分析");
		
		vo = chartbl.wl_Profile(teamID, season);
		setWLProfiles();
		setWLStats();
	}
	
	private void setWLProfiles(){
		wlPanel = new JPanel();
		JLabel chart = vo.chart;
		wlPanel.setBackground(Color.WHITE);
		wlPanel.setOpaque(true);
		wlPanel.setLayout(null);
		wlPanel.setBounds(20,65,435,325);
	//	chart.setPreferredSize(new Dimension(380,320));
		wlPanel.add(chart);
		
		this.add(wlPanel);
	}
	
	private void setWLStats(){
		wlStatsPanel = new JPanel();
		String[] teamType = new String[]{"","Points For","Points Agn","Net Points","Off eFG%","Def eFG%",
				"FTA Net","Rebounds","Turnovers","Assist","Blocks","Pace"};
		
		
		JLabel titleLabel = new JLabel("NET POINTS OF EVERY LEVEL",JLabel.CENTER);
		titleLabel.setFont(new Font("Dialog",1,15));
		titleLabel.setBackground(Color.white);
		titleLabel.setBounds(460, 60, 820, 70);
		titleLabel.setOpaque(true);
		
		wlStatsPanel.setLayout(new GridLayout(4,12,1,1));
		wlStatsPanel.setBounds(460, 130, 820, 220);
		
		for(int i=0;i<12;i++)
			wlStatsPanel.add(getLabel(teamType[i],Color.GRAY));
		
		wlStatsPanel.add(getLabel("good",Color.LIGHT_GRAY));
		for(int i=0;i<11;i++)
			wlStatsPanel.add(getLabel(vo.good[i]+"",Color.LIGHT_GRAY));
		
		wlStatsPanel.add(getLabel("average",Color.GRAY));
		for(int i=0;i<11;i++)
			wlStatsPanel.add(getLabel(vo.average[i]+"",Color.GRAY));
		
		wlStatsPanel.add(getLabel("poor",Color.LIGHT_GRAY));
		for(int i=0;i<11;i++)
			wlStatsPanel.add(getLabel(vo.poor[i]+"",Color.LIGHT_GRAY));
		
		this.add(titleLabel);
		this.add(wlStatsPanel);
	}
	private JLabel getLabel(String title, Color color){
		JLabel label = new JLabel(title,JLabel.CENTER);
		label.setFont(new Font("Dialog",1,10));
		label.setForeground(Color.WHITE);
		label.setOpaque(true);
		label.setBackground(color);
		
		return label;
	}
	public static void main(String[] args){
		JFrame jf = new JFrame();
		jf.setLayout(null);
		jf.setSize(1280,700);
		jf.setLocationRelativeTo(null);
		jf.add(new WLProfiles(1,2014));
		jf.setVisible(true);
	}	
}
