package blservice.chartsblservice;

import java.util.ArrayList;

import javax.swing.JPanel;

import vo.AttriOption;
import vo.playervo.PlayerStatsVO;
import vo.teamvo.TeamWLProfilesVO;
import vo.teamvo.Top5statsVO;

public interface ChartBLService {
	public Top5statsVO top5manGroup(int teamID,int season, AttriOption attri, boolean isTeam);//top 5-man group*
	
	public ArrayList<JPanel> shootingAnls_t(int teamID,int season);//投篮分析*
	
	public ArrayList<JPanel> shootingAnls_p(int playerID,int season, boolean isClutch);//投篮分析*
	
	public ArrayList<JPanel> shotClockAnls_t(int teamID,int season);//投篮时间分析*
	
	public ArrayList<JPanel> shotClockAnls_p(int playerID,int season, boolean isClutch);//投篮时间分析*
	
	public ArrayList<JPanel> off_def(int teamID,int season);//攻防对比 (team)*
	
	public ArrayList<JPanel> turnover_fouls(int teamID,int season);//失误犯规分析(team)*
	
	public ArrayList<JPanel> position(int teamID,int season, AttriOption attri, boolean isTeam);//位置分析*
	
	public JPanel evolution(ArrayList<String> names,String attri,boolean isTeam);
	
	public ArrayList<JPanel> on_off(int playerID,int season);//on off 统计(player)*
	
	public PlayerStatsVO turnovers(int playerID,int season,boolean isClutch);//(player)*
	
	public PlayerStatsVO passing(int playerID,int season,boolean isClutch);//(player)*

	public PlayerStatsVO shotBlock(int playerID,int season,boolean isClutch);//(player)*
	
	public TeamWLProfilesVO wl_Profile(int teamID, int season);//(team)*

}
