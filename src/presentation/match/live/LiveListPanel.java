package presentation.match.live;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JLabel;

import blservice.matchblservice.MatchLiveListBLService;
import blservice.matchblservice.MatchLiveListBL_Stub;
import businesslogic.matchbl.MatchLiveListBL;
import businesslogic.matchbl.MatchLiveListBLService_Stub;
import presentation.match.MatchListPanel;
import vo.matchvo.LiveListItemVO;

public class LiveListPanel extends MatchListPanel {
	
	MatchLiveListBLService listBL;
	
	protected void initialise(){
		removeAll();
		this.refreshList();
		setMatchTitleLabel();
		setTable();
	}

	public void setMatchTitleLabel(){
		matchTitleLabel = new JLabel("  直播列表",JLabel.LEADING);
		matchTitleLabel.setFont(new Font("Dialog",1,20));
		matchTitleLabel.setForeground(Color.WHITE);
		matchTitleLabel.setBackground(new Color(33,82,138));
		matchTitleLabel.setOpaque(true);
		matchTitleLabel.setBounds(0,0,1280,70);
		this.add(matchTitleLabel);
	}
	
	public void refreshList(){
//		listBL = new MatchLiveListBLService_Stub();
		listBL = new MatchLiveListBL();
	}
	
	public void setDate(){
		//不需要这个方法
	}
	
	public void setTable(){

		if(table!=null){
			this.remove(table);
		}
		
//		MatchLiveListBLService listBL = new MatchLiveListBL_Stub();
		ArrayList<LiveListItemVO> vos = listBL.getLiveList();
		
		String[] columns = {"","对阵球队","开始时间","直播ID"};
		
//		MatchVO2List m2l = new MatchVO2List();
		ArrayList<ArrayList<String>> datas = new ArrayList<ArrayList<String>>();
		for(LiveListItemVO vo : vos){
			ArrayList<String> tempLineInfos = new ArrayList<String>();
			tempLineInfos.add(new String());
			tempLineInfos.add(new String(vo.hostName+"-"+vo.guestName));
			tempLineInfos.add(new String(vo.startTime));
			tempLineInfos.add(new String(vo.liveID));
			
			datas.add(tempLineInfos);
		}
		
		ArrayList<Integer> wid = new ArrayList<Integer>();
		wid.add(0);
		for(int i = 0; i < 3;i++){
			wid.add(180);
		}
		wid.add(0);
		table = new LiveTablePane(datas,columns,wid,0,70,1280,420,30,true,false,0);
		this.add(table);
	}
}

