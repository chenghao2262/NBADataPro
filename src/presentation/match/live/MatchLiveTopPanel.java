package presentation.match.live;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import blservice.matchblservice.MatchLiveBLService;
import businesslogic.matchbl.MatchLiveBL;
import businesslogic.matchbl.MatchLiveBLService_Stub;
import presentation.common.PhotoLabel;
import presentation.main.Mainframe;
import presentation.match.MatchDataAnalysePanel;
import presentation.match.MatchPanel;
import presentation.team.teamDetail.TeamMiddlePanel;
import vo.matchvo.MatchLiveItemVO;
import vo.matchvo.MatchLiveVO;

//注意，vo应在这里持有

public class MatchLiveTopPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MatchLiveContentPanel liveContentPanel;
	
	JLabel localScore;
	JLabel anotherNum;
	
	String liveId;
	public MatchLiveVO vo;
	private MatchLiveBLService service;
	
	private int scorePartCount = 4;
	private ArrayList<JLabel> partialLabels = new ArrayList<JLabel>();
	private Timer timer;
	
	private ArrayList<Integer> homeScores = new ArrayList<Integer>();
	private ArrayList<Integer> awayScores = new ArrayList<Integer>();

	protected JLabel localTeamPhotoLabel;
	protected JLabel anotherTeamPhotoLabel;
	protected JLabel VSLabel;
	protected JLabel localInfoGroupsLabel;
	protected JLabel anotherInfoGroupsLabel;
	protected JLabel dateLabel;
	protected JLabel partialScoreLabelGroups;//小比分群
	protected JLabel scoreLabel;//小比分群
	
	protected MatchDataAnalysePanel matchDataAnalysePanel;
	
	private int count = 0;
	
	public MatchLiveTopPanel(String liveId) {
		this.liveId = liveId;
		this.getVO();
		if(vo!=null){

			this.initialise();
		}
		// TODO Auto-generated constructor stub
	}
	
	private void getVO(){
//		service = new MatchLiveBLService_Stub();
		service = new MatchLiveBL();
		vo = service.getMatchLiveVO(liveId);
	}
	
	private void updateMessages(ArrayList<MatchLiveItemVO> itemVOs){
		itemVOs = vo.matchLiveItemVOs;
		
		this.checkExtra();
		this.clearMessages();
		
		int states = vo.status;
		System.out.println("比赛状态: "+states);
		if(states == 3 && timer != null){
			timer.stop();
		}
		int period = vo.currentPeriod;
		String currentScores = vo.currentScores;
		
		localScore.setText(currentScores.split(" ")[0]+"");
		anotherNum.setText(currentScores.split(" ")[1]+""); 
		
		
		for(int j=itemVOs.size()-1;j>=0;j--){
			MatchLiveItemVO itemVO = itemVOs.get(j);
			String time = itemVO.time;
			String hostMessage = itemVO.homeTeamMsg;
			String awayMessage = itemVO.awayTeamMsg;
			boolean isPeriod = itemVO.isPeriod;
			String dyScores = itemVO.currentScores;
			int currentPeriod = itemVO.period;
			if(isPeriod){
				System.out.println("******************period***************"+currentPeriod);
				if(period == 1){
					homeScores.add(Integer.parseInt(itemVO.currentScores.split(" ")[0]));
					awayScores.add(Integer.parseInt(itemVO.currentScores.split(" ")[1]));
					
					((JLabel) partialLabels.get(currentPeriod-1).getComponent(1)).setText(vo.currentScores.split(" ")[0]);
					((JLabel) partialLabels.get(currentPeriod-1).getComponent(2)).setText(vo.currentScores.split(" ")[1]);
				}else{
					int pastHome = 0;
					int pastAway = 0;
					for(int i=0;i<homeScores.size();i++){
						pastHome += homeScores.get(i);
						pastAway += awayScores.get(i);
					}

					homeScores.add(Integer.parseInt(itemVO.currentScores.split(" ")[0]) - pastHome);
					awayScores.add(Integer.parseInt(itemVO.currentScores.split(" ")[1]) - pastAway);
					
					((JLabel) partialLabels.get(currentPeriod-1).getComponent(1)).setText(homeScores.get(currentPeriod-1)+"");
					((JLabel) partialLabels.get(currentPeriod-1).getComponent(2)).setText(awayScores.get(currentPeriod-1)+"");

				}

				String score = new String();
				score = dyScores.split(" ")[0] +":"+dyScores.split(" ")[1];
				this.appendPeriod(currentPeriod, score);
			}else{
				String score = new String();
				score = dyScores.split(" ")[0] +":"+dyScores.split(" ")[1];
				this.appendMessage(time, hostMessage, score, awayMessage);
//				System.out.println(vo.homeTeamId+" "+vo.awayTeamId+time+" "+hostMessage+" "+score+" "+awayMessage);
			}
			
		}
	}
	
	public void update(){

		this.getVO();
		ArrayList<MatchLiveItemVO> itemVOs = vo.matchLiveItemVOs;
		if(count != 0 && itemVOs.size()!=count){
			count = itemVOs.size();
			updateMessages(itemVOs);
			System.out.println("changed");
		}else if(count == 0){
			count = itemVOs.size();
			updateMessages(itemVOs);
		}
//		System.out.println(vo.currentScores);
//		((JLabel) partialLabels.get(period-1).getComponent(1)).setText(vo.currentScores.split(" ")[0]);
//		((JLabel) partialLabels.get(period-1).getComponent(2)).setText(vo.currentScores.split(" ")[1]);
	}
	
	private void checkExtra(){
		if(vo.currentPeriod>partialLabels.size()){
			scorePartCount++;
			JLabel l = setSinglePartialScoreLabel(scorePartCount+"", "0", "0");
			partialLabels.add(l);
			partialScoreLabelGroups.add(l);
			checkExtra();
		}else{
		}
	}
	
	private void checking(){
		timer = new Timer(3000, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				update();
				System.out.println("check");
			}
		});
		timer.start();
		
	}
	
	protected void initialise(){

		this.setLayout(null);
		this.setBounds(0, 0, 1280,700);
		this.setBackground(Color.WHITE);
//		setDataLabel();
		setsetLocalTeamPhotoLabel();
		setAnotherTeamPhotoLabel();
		setVSLabel();
		setLocalInfoGroupsLabel();
		setAnotherInfoGroupsLabel();
		setScoreLabel();
//		setMatchDataAnalysePanel();
		setContent();
		
		this.checking();
	}

	protected void setVSLabel(){
		VSLabel = new JLabel("VS",JLabel.CENTER);
		VSLabel.setFont(new Font("Dialog",1,30));
		VSLabel.setForeground(Color.DARK_GRAY);
		VSLabel.setBackground(Color.WHITE);
		VSLabel.setOpaque(true);
		VSLabel.setBounds(550,15,130,130);
		this.add(VSLabel);

	}
	
	protected void setLocalInfoGroupsLabel(){
		localInfoGroupsLabel = new JLabel();
		localInfoGroupsLabel.setBounds(0,85,350,67);
		localInfoGroupsLabel.setOpaque(true);
		localInfoGroupsLabel.setBackground(Color.BLACK);

		localScore = new JLabel(vo.currentScores.split(" ")[0]+"",JLabel.CENTER);
		localScore.setFont(new Font("Dialog",1,30));
		localScore.setForeground(Color.WHITE);
		localScore.setBounds(270,0,80,55);
		localInfoGroupsLabel.add(localScore);

		JLabel playerName = new JLabel(" "+vo.homeTeamName,JLabel.LEADING);
		playerName.setFont(new Font("Dialog",1,20));
		playerName.setForeground(Color.WHITE);
		playerName.setBounds(5,0,260,55);
		localInfoGroupsLabel.add(playerName);
		
		this.add(localInfoGroupsLabel);
	}
	
	protected void setAnotherInfoGroupsLabel(){
		anotherInfoGroupsLabel = new JLabel();
		anotherInfoGroupsLabel.setBounds(910,85,370,67);
		anotherInfoGroupsLabel.setOpaque(true);
		anotherInfoGroupsLabel.setBackground(Color.BLACK);
		
		anotherNum = new JLabel(vo.currentScores.split(" ")[1]+"",JLabel.CENTER);
		anotherNum.setFont(new Font("Dialog",1,30));
		anotherNum.setForeground(Color.WHITE);
		anotherNum.setBounds(0,0,80,55);
		anotherInfoGroupsLabel.add(anotherNum);
		
		JLabel anotherName = new JLabel(" "+vo.awayTeamName,JLabel.RIGHT);
		anotherName.setFont(new Font("Dialog",1,20));
		anotherName.setForeground(Color.WHITE);
		anotherName.setBounds(80,0,230,55);
		anotherInfoGroupsLabel.add(anotherName);

		this.add(anotherInfoGroupsLabel);
	}
	
	protected JLabel setSinglePartialScoreLabel(String index,String score1,String score2){
		JLabel SinglePartialScoreLabel = new JLabel();
		SinglePartialScoreLabel.setOpaque(true);
		SinglePartialScoreLabel.setBackground(Color.LIGHT_GRAY);
		
		JLabel indexLabel = new JLabel(index,JLabel.CENTER);
		indexLabel.setFont(new Font("Dialog",1,15));
		indexLabel.setForeground(Color.WHITE);
		indexLabel.setBounds(0,0,50,20);
		SinglePartialScoreLabel.add(indexLabel);
		
		JLabel scoreLabel1 = new JLabel(score1,JLabel.CENTER);
		scoreLabel1.setFont(new Font("Dialog",1,15));
		scoreLabel1.setForeground(Color.WHITE);
		scoreLabel1.setBounds(0,20,50,20);
		SinglePartialScoreLabel.add(scoreLabel1);
		
		JLabel scoreLabel2 = new JLabel(score2,JLabel.CENTER);
		scoreLabel2.setFont(new Font("Dialog",1,15));
		scoreLabel2.setForeground(Color.WHITE);
		scoreLabel2.setBounds(0,40,50,20);
		SinglePartialScoreLabel.add(scoreLabel2);

		return SinglePartialScoreLabel;
	}

	protected void setContent() {
		liveContentPanel = new MatchLiveContentPanel(vo);
		this.add(liveContentPanel);
//		liveContentPanel.initialiseMatchTime(new MatchTime(1, 23, 77));
//		liveContentPanel.startTiming();
	}
	
	public void appendMessage(String time, String hostMessage, String scores, String guestMessage){
		liveContentPanel.appendMessage(time, hostMessage, scores, guestMessage);
	}
	
	public void appendPeriod(int period, String scores){
		liveContentPanel.appendPeriod(period, scores);
	}
	
	public void clearMessages(){
		liveContentPanel.clearMessages();;
	}
	
	protected void setsetLocalTeamPhotoLabel(){
		localTeamPhotoLabel = new PhotoLabel(new ImageIcon("teamsPNG/"+vo.homeTeamName+".png").getImage());
//		localTeamPhotoLabel.setHorizontalAlignment(JLabel.RIGHT);
		localTeamPhotoLabel.setBounds(350,23,200,125);
		localTeamPhotoLabel.setBackground(Color.WHITE);
		localTeamPhotoLabel.setOpaque(true);
		localTeamPhotoLabel.setVisible(true);
		localTeamPhotoLabel.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e) {
				Mainframe.getFrame().changeContent(new TeamMiddlePanel(vo.homeTeamName));
			}
		
		});
		this.add(localTeamPhotoLabel);
	}
	
	protected void setAnotherTeamPhotoLabel (){
		anotherTeamPhotoLabel = new PhotoLabel(new ImageIcon("teamsPNG/"+vo.awayTeamName+".png").getImage());
		anotherTeamPhotoLabel.setHorizontalAlignment(JLabel.RIGHT);
		anotherTeamPhotoLabel.setBounds(730,23,180,125);
		anotherTeamPhotoLabel.setBackground(Color.white);
		anotherTeamPhotoLabel.setOpaque(true);
		anotherTeamPhotoLabel.setVisible(true);
		anotherTeamPhotoLabel.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e) {
				Mainframe.getFrame().changeContent(new TeamMiddlePanel(vo.awayTeamName));
			}
		
		});
		this.add(anotherTeamPhotoLabel);
	}
	
	protected void setScoreLabel(){
		scoreLabel = new JLabel();
		scoreLabel.setBounds(350,150,560,62);
		scoreLabel.setOpaque(true);
		scoreLabel.setBackground(Color.LIGHT_GRAY);
		
		JLabel name = new JLabel(vo.homeTeamName,JLabel.CENTER);
		name.setFont(new Font("Dialog",1,15));
		name.setForeground(Color.WHITE);
		name.setBounds(0,20,35,20);
		scoreLabel.add(name);
		
		JLabel anotherName = new JLabel(vo.awayTeamName,JLabel.CENTER);
		anotherName.setFont(new Font("Dialog",1,15));
		anotherName.setForeground(Color.WHITE);
		anotherName.setBounds(0,40,35,20);
		scoreLabel.add(anotherName);

		this.add(scoreLabel);
		setPartialScoreGroupsLabel();
	}
	
	protected void setPartialScoreGroupsLabel(){
		partialScoreLabelGroups = new JLabel();
		partialScoreLabelGroups.setBounds(100,0,460,62);
		partialScoreLabelGroups.setOpaque(true);
		partialScoreLabelGroups.setBackground(Color.LIGHT_GRAY);
		partialScoreLabelGroups.setLayout(new GridLayout(1,5,0,0));
		
		for(int i=0;i<4;i++){
			JLabel l = setSinglePartialScoreLabel((i+1)+"","0"+"","0"+"");
			partialScoreLabelGroups.add(l);
			partialLabels.add(l);
		}

		scoreLabel.add(partialScoreLabelGroups);
	}

	public static void main(String args[]){
//		JFrame f = new JFrame();
//		f.setLayout(null);
//		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		MatchLiveTopPanel p = new MatchLiveTopPanel();
//		
//		f.add(p);
//		f.setBounds(50, 50, 1280, 700);
//		f.setVisible(true);
		
	}
}
