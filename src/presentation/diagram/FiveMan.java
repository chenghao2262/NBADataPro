package presentation.diagram;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import blservice.chartsblservice.ChartBLService;
import blservice.playerblservice.PlayerBLService;
import businesslogic.playerbl.PlayerBL_stub;
import businesslogic.statsbl.ChartBL;
import presentation.common.GeneralComboBox;
import vo.AttriOption;
import vo.teamvo.Top5statsVO;

public class FiveMan extends Diagram{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	ArrayList<String> chooses = new ArrayList<String>();
	GeneralComboBox chooser;
	JPanel fiveManPanel;
	JPanel diagramSample = new JPanel();
	JPanel playerStatsPanel;
	JPanel scoringPanel;
	
	Top5statsVO fiveStats;
	boolean isteam;
	int teamID;
	int season;
	
	String[] floorStats = new String[8];
	String[] scoring = new String[9];
	
	ChartBLService chartbl = new ChartBL();
	PlayerBLService playerbl = new PlayerBL_stub();
	
	
	public FiveMan(int teamID,int season,boolean isTeam){
		super("     五人组合");
		this.isteam = isTeam;
		this.teamID = teamID;
		this.season = season;
		this.floorStats = playerbl.floorStats(teamID, season);
		this.scoring = playerbl.scoring(teamID, season);
		initialise();
	}
	
	public void initialise(){
		setChooses();
		setChooserComboBox();
		set5ManDiagram();
		setDiagramSample();
		if(!isteam){
			setPlayerStatsTabel();
			setScoringTabel();
		}
	}
	
	public void setChooses(){
		chooses.clear();
		chooses.add("属性(默认胜率)");
		chooses.add ("分钟");
		chooses.add("进攻");
		chooses.add("防守");
		chooses.add("+/-");
		chooses.add("胜场");
		chooses.add("负场");
		chooses.add("胜率");
		chooses.add("有效命中率");
		chooses.add("对方有效命中率");
		chooses.add("罚球数");
		chooses.add("近距离投篮％");
		chooses.add("对方近距离投篮％");
		chooses.add("篮板");
		chooses.add("净失误率");
	}
	
	public void setChooserComboBox(){
		chooser = new GeneralComboBox(chooses);
		chooser.setsize(200, 40);
		chooser.setlocation(new Point(1000,10));
		chooser.addItemListener(new chooserListener());
		titleLabel.add(chooser);
		this.updateUI();
	} 
	
	class chooserListener implements ItemListener{
		@Override
		public void itemStateChanged(ItemEvent e) {
			int choosed = e.getStateChange();
			if(choosed == ItemEvent.SELECTED){
				System.out.println(e.getItem());
			
				FiveMan.this.setVisible(false);
				if(fiveManPanel != null){
					remove(fiveManPanel);
				}
				set5ManDiagram();
				FiveMan.this.setVisible(true);
			}
		}
	}

	public static AttriOption judgeAttri(String selectedItem){
		switch(selectedItem){
		case "属性(默认胜率)":return AttriOption.fiveMan_winPct;
		case "分钟":return AttriOption.fiveMan_min;
		case "进攻":return AttriOption.fiveMan_off;
		case "防守":return AttriOption.fiveMan_def;
		case "+/-":return AttriOption.fiveMan_plusMinus;
		case "胜场":return AttriOption.fiveMan_win;
		case "负场":return AttriOption.fiveMan_lose;
		case "胜率":return AttriOption.fiveMan_winPct;
		
		case "有效命中率":	 return AttriOption.fiveMan_efg;
		case "对方有效命中率":	 return AttriOption.fiveMan_efga;
		case "罚球数":		 return AttriOption.fiveMan_fta;
		case "近距离投篮％":	 return AttriOption.fiveMan_close;
		case "对方近距离投篮％":return AttriOption.fiveMan_dClose;
		case "篮板":			 return AttriOption.fiveMan_reb;
		case "净失误率":		 return AttriOption.fiveMan_to;
		default:			 return null;
		}
	}
	
	public void set5ManDiagram(){
		fiveManPanel = new JPanel();
		fiveStats = chartbl.top5manGroup(teamID,season,judgeAttri(chooser.getSelectedItem().toString()),isteam);
		if(fiveStats == null){
			return;
		}
		JPanel jp = fiveStats.chart;
		fiveManPanel.setBackground(Color.WHITE);
		fiveManPanel.setOpaque(true);
		
		if(isteam){
			fiveManPanel.setBounds(20,60, 600, 325);
			jp.setPreferredSize(new Dimension(600,320));
		}else{
			fiveManPanel.setBounds(0,65,380,325);
			jp.setPreferredSize(new Dimension(380,320));
		}
		fiveManPanel.add(jp);
		this.add(fiveManPanel);
	}
	
	//图例
	public void setDiagramSample(){
		diagramSample.setBackground(Color.WHITE);
		diagramSample.setOpaque(true);
		if(isteam){
			diagramSample.setBounds(630, 70, 640, 325);
			diagramSample.setLayout(new GridLayout(5,2,30,1));
		}else{
			diagramSample.setBounds(380, 70, 540, 325);
			diagramSample.setLayout(new GridLayout(5,2,15,1));
		}
		
		String[] names = fiveStats.names;
		for(int i = 0; i < names.length; i++ ){
			JLabel jl = new JLabel(i+1+ "         " + names[i]);
			if(isteam){
				jl.setFont(new Font("Dialog",1,12));
			}else{
				jl.setFont(new Font("Dialog",0,10));
			}
			diagramSample.add(jl);
		}
		this.add(diagramSample);
	}
	
	//player特有的详细数据表格
	public void setPlayerStatsTabel(){
		playerStatsPanel = new JPanel();
		
		String[] floorStatsName = new String[8];
		int[] length = new int[8];
		floorStatsName[0] = "Min";  	length[0] = 30;
		floorStatsName[1] = "Net Pts";	length[1] = 80;
		floorStatsName[2] = "Off";		length[2] = 30;
		floorStatsName[3] = "Def";		length[3] = 30;
		floorStatsName[4] = "Net48";	length[4] = 40;
		floorStatsName[5] = "W";		length[5] = 30;
		floorStatsName[6] = "L";		length[6] = 30;
		floorStatsName[7] = "Win%";		length[7] = 30;

		playerStatsPanel.setLayout(new GridLayout(2,1,0,0));
		playerStatsPanel.setBounds(925, 100, 350, 80);

		JLabel titleLabel = new JLabel("FLOOR TIME STATS",JLabel.CENTER);
		titleLabel.setFont(new Font("Dialog",1,15));
		titleLabel.setForeground(Color.WHITE);
		playerStatsPanel.add(titleLabel);
		
		JLabel playerStatsData = new JLabel();
		playerStatsData.setLayout(new GridLayout());
		playerStatsData.setBounds(925, 100, 350, 70);

		for(int i = 0;i < 8;i++){
			playerStatsData.add(setSingleBlockLabel(length[i],floorStatsName[i],floorStats[i]));
		}
		playerStatsPanel.add(playerStatsData);
		
		this.add(playerStatsPanel);
	}
	
	public JLabel setSingleBlockLabel(int length,String title,String data){
		JLabel singleBolckLabel = new JLabel();
		singleBolckLabel.setPreferredSize(new Dimension(length,30));
		singleBolckLabel.setLayout(new GridLayout(2,1,1,0));
		singleBolckLabel.setOpaque(true);
		singleBolckLabel.setBackground(Color.LIGHT_GRAY);
		
		JLabel itemLabel = new JLabel(title,JLabel.CENTER);
		itemLabel.setFont(new Font("Dialog",1,10));
		itemLabel.setForeground(Color.WHITE);
		singleBolckLabel.add(itemLabel);

		JLabel dataLabel = new JLabel(data,JLabel.CENTER);
		dataLabel.setFont(new Font("Dialog",1,10));
		dataLabel.setForeground(Color.WHITE);
		singleBolckLabel.add(dataLabel);
		
		return singleBolckLabel;
	}
	
	public void setScoringTabel(){
		scoringPanel = new JPanel();
		
		String[] scoringName = new String[9];
		int[] length = new int[9];
		scoringName[0] = "BY";  	length[0] = 40;
		scoringName[1] = "FG.";		length[1] = 30;
		scoringName[2] = "FGA";		length[2] = 30;
		scoringName[3] = "FG%";		length[3] = 45;
		scoringName[4] = "eFG%";	length[4] = 45;
		scoringName[5] = "Ast'd";	length[5] = 45;
		scoringName[6] = "Blk'd";	length[6] = 45;
		scoringName[7] = "FTM";		length[7] = 30;
		scoringName[8] = "Pts";		length[8] = 30;

		scoringPanel.setLayout(new GridLayout(2,1,0,0));
		scoringPanel.setBounds(925, 250, 350, 80);

		JLabel titleLabel = new JLabel("SCORING",JLabel.CENTER);
		titleLabel.setFont(new Font("Dialog",1,15));
		titleLabel.setForeground(Color.WHITE);
		scoringPanel.add(titleLabel);
		
		JLabel ScoringData = new JLabel();
		ScoringData.setLayout(new GridLayout());

		for(int i = 0;i < 9;i++){
			ScoringData.add(setSingleBlockLabel(length[i],scoringName[i],scoring[i]));
		}
		scoringPanel.add(ScoringData);
		
		this.add(scoringPanel);
	}
	
	public static void main(String[] args){
		JFrame jf = new JFrame();
		jf.setLayout(null);
		jf.setSize(1280,700);
		jf.setLocationRelativeTo(null);
		jf.add(new FiveMan(1,2014,true));
		jf.setVisible(true);
	}	
}
