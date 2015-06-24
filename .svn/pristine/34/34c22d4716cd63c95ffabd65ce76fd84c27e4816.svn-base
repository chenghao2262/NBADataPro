package presentation.diagram;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import presentation.common.SelectLabel;
import vo.playervo.PlayerStatsVO;
import blservice.chartsblservice.ChartBLService;
import blservice.playerblservice.PlayerBLService;
import businesslogic.playerbl.PlayerBL_stub;
import businesslogic.statsbl.ChartBL;

public class T_P_S extends Diagram{

	/**
	 * 失误统计
	 * 传球统计
	 * 盖帽统计
	 * 
	 */
	private static final long serialVersionUID = 1L;

	int ID;
	int season;
	static int type = 0;//默认失误统计，记录统计种类
	static boolean isClutch = false;
	String[] data;
	
	JPanel T_P_SPanel;
	PlayerStatsVO playerStatsvo;
	
	SelectLabel YesButton;//关键时刻
	SelectLabel NoButton;//非关键时刻
	JLabel ClutchGroup = new JLabel();//选择是否查看关键时刻
	
	SelectLabel turnoversButton;//失误统计
	SelectLabel passingButton;//传球统计
	SelectLabel shotBlockButton;//盖帽统计
	JLabel ItemGroups = new JLabel();//选择查看那种统计
	
	ArrayList<SelectLabel> selectLabelGroups1 = new ArrayList<SelectLabel>();
	ArrayList<SelectLabel> selectLabelGroups2 = new ArrayList<SelectLabel>();
	
	JPanel Table;
	
	double[] turn = new double[5];
	double[] pass = new double[9];
	double[] shotblock = new double[9];
	
	int lenT[] = {156,156,156,156,156};
	int lenP[] = {40,40,40,40,40,156,156,156,80};
	int lenS[] = {40,40,40,40,40,40,156,156,80};

	String[] turnName = {"OffFouls","BadPasses","BallHandling","other","HandsRating"};
	String[] passName = {"3Pt","Jump","Close","Dunk","Total","Passing T/O's","Assist/Bad Pass","Pass Rating","AST48"};
	String[] shotblockName = {"Jump%","Close%","Dunk%","All","BLK48","Blk%","ShootFouls","Blk/Fouls","Blk/Rat"};

	ChartBLService chartbl = new ChartBL();
	PlayerBLService playerbl = new PlayerBL_stub();
	
	public T_P_S(int id,int seaosn) {
		super("     失误统计");
		setClutchLabelGroups();
		setSelectedGroups1(NoButton);
		setItemLabelGroups();
		setSelectedGroups2(turnoversButton);
		setTPSDiagram();
		setTable();
	}
	
	public void setSelectedGroups1(SelectLabel s){
		selectLabelGroups1.clear();
		selectLabelGroups1.add(YesButton);
		selectLabelGroups1.add(NoButton);
		for(SelectLabel sl : selectLabelGroups1){
			if(sl != s){
				sl.setBackground(exicted);
				sl.isSelected = false;
			}else{
				sl.setBackground(pressed);
				sl.isSelected = true;
			}
		}
	}
	
	public void setSelectedGroups2(SelectLabel s){
		selectLabelGroups2.clear();
		selectLabelGroups2.add(turnoversButton);
		selectLabelGroups2.add(passingButton);
		selectLabelGroups2.add(shotBlockButton);
		for(SelectLabel sl : selectLabelGroups2){
			if(sl != s){
				sl.setBackground(exicted);
				sl.isSelected = false;
			}else{
				sl.setBackground(pressed);
				sl.isSelected = true;
			}
		}
	}

	//是否关键时刻选择
	public void setClutchLabelGroups(){
		ClutchGroup = new JLabel();
		ClutchGroup.setBackground(exicted);
		ClutchGroup.setOpaque(true);
		ClutchGroup.setBounds(1095,5,165,45);
		setYesButton();
		setNoButton();
		titleLabel.add(ClutchGroup);
	}
	
	//三选一
	public void setItemLabelGroups(){
		ItemGroups = new JLabel();
		ItemGroups.setBackground(exicted);
		ItemGroups.setOpaque(true);
		ItemGroups.setBounds(900,5,140,45);
		setTurnOversButton();
		setPassingButton();
		setBlockButton();
		titleLabel.add(ItemGroups);
	}

	public void setTurnOversButton(){
		Point p1 = new Point(5,7);
		Point p2 = new Point(40,30);
		turnoversButton = new SelectLabel("失误",p1,p2,entered,pressed,exicted);
		turnoversButton.setForeground(Color.WHITE);
		turnoversButton.setBackground(exicted);
		turnoversButton.setOpaque(true);
		turnoversButton.addMouseListener(new TurnOverButtonListener());
		ItemGroups.add(turnoversButton);
	}
	
	class TurnOverButtonListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			setVisible(false);
			setSelectedGroups2(turnoversButton);
			T_P_S.type = 0;
			T_P_S.this.titleLabel.setText("     失误统计");
			if(T_P_SPanel != null){
				T_P_S.this.remove(T_P_SPanel);
			}		
			if(Table != null){
				T_P_S.this.remove(Table);
			}			
			setTPSDiagram();
			setTable();
			setVisible(true);
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
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
	
	public void setPassingButton(){
		Point p1 = new Point(50,7);
		Point p2 = new Point(40,30);
		passingButton = new SelectLabel("传球",p1,p2,entered,pressed,exicted);
		passingButton.setForeground(Color.WHITE);
		passingButton.setBackground(exicted);
		passingButton.setOpaque(true);
		passingButton.addMouseListener(new PassingButtonListener());
		ItemGroups.add(passingButton);
	}
	
	class PassingButtonListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			setVisible(false);
			setSelectedGroups2(passingButton);
			T_P_S.type = 1;
			T_P_S.this.titleLabel.setText("     传球统计");
			if(T_P_SPanel != null){
				T_P_S.this.remove(T_P_SPanel);
			}
			if(Table != null){
				T_P_S.this.remove(Table);
			}			
			setTPSDiagram();
			setTable();
			setVisible(true);
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
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

	public void setBlockButton(){
		Point p1 = new Point(95,7);
		Point p2 = new Point(40,30);
		shotBlockButton = new SelectLabel("盖帽",p1,p2,entered,pressed,exicted);
		shotBlockButton.setForeground(Color.WHITE);
		shotBlockButton.setBackground(exicted);
		shotBlockButton.setOpaque(true);
		shotBlockButton.addMouseListener(new BlockButtonListener());
		ItemGroups.add(shotBlockButton);
	}
	
	class BlockButtonListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			setVisible(false);
			setSelectedGroups2(shotBlockButton);
			T_P_S.type = 2;
			T_P_S.this.titleLabel.setText("     盖帽统计");
			if(T_P_SPanel != null){
				T_P_S.this.remove(T_P_SPanel);
			}
			if(Table != null){
				T_P_S.this.remove(Table);
			}			
			setTPSDiagram();
			setTable();
			setVisible(true);
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
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
	
	public void setYesButton(){
		Point p1 = new Point(90,7);
		Point p2 = new Point(70,30);
		YesButton = new SelectLabel("关键时刻",p1,p2,entered,pressed,exicted);
		YesButton.setForeground(Color.WHITE);
		YesButton.setBackground(exicted);
		YesButton.setOpaque(true);
		YesButton.addMouseListener(new YesButtonListener());
		ClutchGroup.add(YesButton);
	}

	class YesButtonListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			setVisible(false);
			setSelectedGroups1(YesButton);
			T_P_S.isClutch = true;
			if(T_P_SPanel != null){
				T_P_S.this.remove(T_P_SPanel);
			}		
			if(Table != null){
				T_P_S.this.remove(Table);
			}			
			setTPSDiagram();
			setTable();
			setVisible(true);
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
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
	
	public void setNoButton(){
		Point p1 = new Point(5,7);
		Point p2 = new Point(80,30);
		NoButton = new SelectLabel("非关键时刻",p1,p2,entered,pressed,exicted);
		NoButton.setForeground(Color.WHITE);
		NoButton.setBackground(exicted);
		NoButton.setOpaque(true);
		NoButton.addMouseListener(new NoButtonListener());
		ClutchGroup.add(NoButton);
	}
	
	class NoButtonListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			setVisible(false);
			setSelectedGroups1(NoButton);
			T_P_S.isClutch = false;
			if(T_P_SPanel != null){
				T_P_S.this.remove(T_P_SPanel);
			}
			if(Table != null){
				T_P_S.this.remove(Table);
			}			
			setTPSDiagram();
			setTable();
			setVisible(true);
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
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

	public void getResultPanels(){
		playerStatsvo = new PlayerStatsVO();
		switch(type){
		case 0: playerStatsvo = chartbl.turnovers(ID, season, isClutch);break;
		case 1:	playerStatsvo = chartbl.passing(ID, season, isClutch);break;
		case 2:	playerStatsvo = chartbl.shotBlock(ID, season, isClutch);break;
			default: playerStatsvo = chartbl.turnovers(ID, season, isClutch);break;
		}
	}
	
	public void setTPSDiagram(){
		getResultPanels();
		T_P_SPanel = new JPanel();
		T_P_SPanel.setLayout(new GridLayout());
		T_P_SPanel.setBackground(Color.BLACK);
		T_P_SPanel.setOpaque(true);
		T_P_SPanel.setBounds(0, 60, 500, 340);

		JPanel item = playerStatsvo.chart;
		item.setPreferredSize(new Dimension(400,320));
		T_P_SPanel.add(item);		
			
		this.add(T_P_SPanel);
		repaint();
	}
	
	void processArray(){
		switch(type){
		case 0: turn = playerStatsvo.data;break;
		case 1:	pass = playerStatsvo.data;break;
		case 2:	shotblock = playerStatsvo.data;break;
			default: turn = playerStatsvo.data;break;
		}
	}
	
	void setTable(){
		switch(type){
		case 0: setT_Tabel();break;
		case 1:	setP_Tabel();break;
		case 2:	setS_Tabel();break;
			default: setT_Tabel();break;
		}
	}
	
	void setT_Tabel(){
		processArray();
		Table = new JPanel();
		Table.setLayout(new GridLayout(2,1,0,0));
		Table.setBounds(510, 150, 750, 80);
		
		JLabel TLabel = new JLabel("Turnovers and Ball Handling",JLabel.CENTER);
		TLabel.setFont(new Font("Dialog",1,15));
		TLabel.setForeground(Color.WHITE);
		Table.add(TLabel);
		
		JLabel TData = new JLabel();
		TData.setLayout(new GridLayout());

		for(int i = 0;i < 5;i++){
			TData.add(setSingleBlockLabel(lenT[i],turnName[i],turn[i]));
		}
		Table.add(TData);
		
		this.add(Table);
	}
	
	void setP_Tabel(){
		processArray();
		Table = new JPanel();
		Table.setLayout(new GridLayout(2,1,0,0));
		Table.setBounds(510, 150, 770, 80);
		
		JLabel TLabel = new JLabel("Passing Stats",JLabel.CENTER);
		TLabel.setFont(new Font("Dialog",1,15));
		TLabel.setForeground(Color.WHITE);
		Table.add(TLabel);
		
		JLabel TData = new JLabel();
		TData.setLayout(new GridLayout());

		for(int i = 0;i < 9;i++){
			TData.add(setSingleBlockLabel(lenP[i],passName[i],pass[i]));
		}
		Table.add(TData);
		
		this.add(Table);
	}
	
	void setS_Tabel(){
		processArray();
		Table = new JPanel();
		Table.setLayout(new GridLayout(2,1,0,0));
		Table.setBounds(510, 150, 770, 80);
		
		JLabel TLabel = new JLabel("Shot Blocking",JLabel.CENTER);
		TLabel.setFont(new Font("Dialog",1,15));
		TLabel.setForeground(Color.WHITE);
		Table.add(TLabel);
		
		JLabel TData = new JLabel();
		TData.setLayout(new GridLayout());

		for(int i = 0;i < 9;i++){
			TData.add(setSingleBlockLabel(lenS[i],shotblockName[i],shotblock[i+3]));
//			System.out.println(shotblock[i]);
		}
		Table.add(TData);
		
		this.add(Table);
	}
	
	public JLabel setSingleBlockLabel(int length,String title,double data){
		JLabel singleBolckLabel = new JLabel();
		singleBolckLabel.setPreferredSize(new Dimension(length,30));
		singleBolckLabel.setLayout(new GridLayout(2,1,1,0));
		singleBolckLabel.setOpaque(true);
		singleBolckLabel.setBackground(Color.LIGHT_GRAY);
		
		JLabel itemLabel = new JLabel(title,JLabel.CENTER);
		itemLabel.setFont(new Font("Dialog",1,10));
		itemLabel.setForeground(Color.WHITE);
		singleBolckLabel.add(itemLabel);

		JLabel dataLabel = new JLabel(String.valueOf(data),JLabel.CENTER);
		dataLabel.setFont(new Font("Dialog",1,10));
		dataLabel.setForeground(Color.WHITE);
		singleBolckLabel.add(dataLabel);
		
		return singleBolckLabel;
	}

	public static void main(String[] args){
		JFrame jf = new JFrame();
		jf.setLayout(null);
		jf.setSize(1280,700);
		jf.setLocationRelativeTo(null);
		jf.add(new T_P_S(1,2014));
		jf.setVisible(true);
	}

	
}
