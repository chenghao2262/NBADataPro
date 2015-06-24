package presentation.diagram;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import presentation.common.GeneralComboBox;
import presentation.common.SelectLabel;
import blservice.chartsblservice.ChartBLService;
import blservice.playerblservice.PlayerBLService;
import businesslogic.playerbl.PlayerBL_stub;
import businesslogic.statsbl.ChartBL;

public class Shooting extends Diagram{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	ArrayList<SelectLabel> selectLabelGroups1 = new ArrayList<SelectLabel>();
	ArrayList<SelectLabel> selectLabelGroups2 = new ArrayList<SelectLabel>();
	
	ArrayList<JPanel> shootingPanels = new ArrayList<JPanel>();
	ChartBLService chartbl = new ChartBL();
	PlayerBLService playerbl = new PlayerBL_stub();
	
	JPanel ShootingPanel;
	
	boolean isteam;
	static boolean isClutch = false;//默认非关键数据
	static boolean isTime = true;//默认投篮分析；投篮分析，投篮时间分析选择
	int ID;
	int season;
	static int whichDiagram = 0;//默认efg
	
	GeneralComboBox chooser;
	ArrayList<String> chooses = new ArrayList<String>();
	
	SelectLabel	S_TButton;//投篮时间分析
	SelectLabel S_PButton;//投篮分析
	JLabel ButtonsGroup = new JLabel();//选择是投篮时间分析或者是投篮分析
	
	//关键时刻 只有球员有
	SelectLabel YesButton;//关键时刻
	SelectLabel NoButton;//非关键时刻
	JLabel ClutchGroup = new JLabel();//选择是否查看关键时刻
	
	Color entered = new Color(30,80,140);
	Color pressed = new Color(42,109,183);
	Color exicted= new Color(26,70,122);
	
	public Shooting(int id,boolean isTeam,int season) {
		super("     投篮分析");
		this.isteam = isTeam;
		this.ID = id;
		this.season = season;
		initialise();
	}
	
	private void initialise(){
		setChooses();
		setChooserComboBox();
		setT_PLabelGroups();
		setSelectedGroups1(S_TButton);
		if(!isteam){
			setClutchLabelGroups();
			setSelectedGroups2(NoButton);
		}
		/**
		 * 初始化
		 * 
		 */
		setShootingDiagram();
	}
	
	public void setChooses(){
		chooses.clear();
		chooses.add("属性(默认eFG)");
		chooses.add ("eFG");
		chooses.add("Ast");
		chooses.add("Pts");
		if(!isteam){
			chooses.add("Blk");
		}
	}
	
	public void setChooserComboBox(){
		chooser = new GeneralComboBox(chooses);
		if(isteam){
			chooser.setlocation(new Point(900,10));
		}else{
			chooser.setlocation(new Point(820,10));
		}
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
				Shooting.whichDiagram = judgeAttri(chooser.getSelectedItem().toString());
				System.out.println("whichDiagram:  " + whichDiagram);
				
				Shooting.this.setVisible(false);
				if(ShootingPanel != null){
					Shooting.this.remove(ShootingPanel);
				}
				setShootingDiagram();
				Shooting.this.setVisible(true);

			}
		}
	}
	
	public int judgeAttri(String selectedItem){
		switch(selectedItem){
		case "属性(默认eFG)":	return 0;
		case "eFG":			return 0;
		case "Ast":			return 1;
		case "Pts":			return 2;
		case "Blk":			return 3;
		default:			return -1;
		}
	}
	
	public void setSelectedGroups1(SelectLabel s){
		selectLabelGroups1.clear();
		selectLabelGroups1.add(S_TButton);
		selectLabelGroups1.add(S_PButton);
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
		selectLabelGroups2.add(YesButton);
		selectLabelGroups2.add(NoButton);
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

	//投篮时间分析，投篮分析选择
	public void setT_PLabelGroups(){
		ButtonsGroup = new JLabel();
		ButtonsGroup.setBackground(exicted);
		ButtonsGroup.setOpaque(true);
		if(isteam){
			ButtonsGroup.setBounds(1095,5,95,45);
		}else{
			ButtonsGroup.setBounds(990,5,95,45);
		}
		setS_TButton();
		setS_PButton();
		titleLabel.add(ButtonsGroup);
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
	
	public void setS_TButton(){
		Point p1 = new Point(5,7);
		Point p2 = new Point(40,30);
		S_TButton = new SelectLabel("时间",p1,p2,entered,pressed,exicted);
		S_TButton.setForeground(Color.WHITE);
		S_TButton.setBackground(exicted);
		S_TButton.setOpaque(true);
		S_TButton.addMouseListener(new S_TButtonListener());
		ButtonsGroup.add(S_TButton);
	}
	
	class S_TButtonListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			setVisible(false);
			setSelectedGroups1(S_TButton);
			Shooting.isTime = true;
			if(ShootingPanel != null){
				Shooting.this.remove(ShootingPanel);
			}			
			Shooting.this.setShootingDiagram();
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
	
	public void setS_PButton(){
		Point p1 = new Point(50,7);
		Point p2 = new Point(40,30);
		S_PButton = new SelectLabel("类型",p1,p2,entered,pressed,exicted);
		S_PButton.setForeground(Color.WHITE);
		S_PButton.setBackground(exicted);
		S_PButton.setOpaque(true);
		S_PButton.addMouseListener(new S_PButtonListener());
		ButtonsGroup.add(S_PButton);
	}

	class S_PButtonListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			setVisible(false);
			setSelectedGroups1(S_PButton);
			Shooting.isTime = false;
			if(ShootingPanel != null){
				Shooting.this.remove(ShootingPanel);
			}			
			setShootingDiagram();
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
			setSelectedGroups2(NoButton);
			Shooting.isClutch = false;
			if(ShootingPanel != null){
				Shooting.this.remove(ShootingPanel);
			}			
			setShootingDiagram();
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
			setSelectedGroups2(YesButton);
			Shooting.isClutch = true;
			if(ShootingPanel != null){
				Shooting.this.remove(ShootingPanel);
			}			
			setShootingDiagram();
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
		if(isteam){
			if(isTime){
				shootingPanels = chartbl.shotClockAnls_t(ID, season);
			}else{
				shootingPanels = chartbl.shootingAnls_t(ID, season);
			}
		}else{
			if(isTime){
				shootingPanels = chartbl.shotClockAnls_p(ID,season,isClutch);
			}else{
				shootingPanels = chartbl.shootingAnls_p(ID,season,isClutch);
			}
		}
	}
	
	public void setShootingDiagram(){
		ShootingPanel = new JPanel();
		getResultPanels();
		ShootingPanel.setLayout(new FlowLayout());
		ShootingPanel.setBackground(Color.WHITE);
		ShootingPanel.setOpaque(true);
		ShootingPanel.setBounds(0, 60, 1280, 360);
		if(isteam){
			JPanel off = shootingPanels.get(3);
			JPanel def = shootingPanels.get(4);
			JPanel bar = shootingPanels.get(whichDiagram);
			off.setPreferredSize(new Dimension(350,320));
			def.setPreferredSize(new Dimension(350,320));
			bar.setPreferredSize(new Dimension(350,320));
			ShootingPanel.add(off);		
			ShootingPanel.add(bar);
			ShootingPanel.add(def);
		}else{
			JPanel shot = shootingPanels.get(4);
			JPanel chose = shootingPanels.get(whichDiagram);

			shot.setPreferredSize(new Dimension(400,320));
			chose.setPreferredSize(new Dimension(480,320));

			ShootingPanel.add(shot);
			ShootingPanel.add(chose);
		}
		this.add(ShootingPanel);
//		System.out.println(" I AM USED");
		repaint();
	}
	
	public static void main(String[] args){
		JFrame jf = new JFrame();
		jf.setLayout(null);
		jf.setSize(1280,700);
		jf.setLocationRelativeTo(null);
		jf.add(new Shooting(1,true,2014));
		jf.setVisible(true);
	}


}
