package dataservice.playerdataservice;

import java.util.ArrayList;

import po.playerpo.PlayerPO;
import po.stats.EvolutionPO;
import po.stats.FiveUnitPO;
import po.stats.OnOffPO;
import po.stats.RegressionPO;
import po.stats.ShotAnlsPO;
import vo.AttriOption;
import vo.HotZoneVO;

public interface PlayerDataService {
	
	//返回某一球员某项属性整个赛季的数据 eg:选择得分属性,该球员本赛季每场比赛的的分数组
	public double[] specDataOfSeason(int playerID,String attri, int season);

	public FiveUnitPO top5manGroup(int playerID,int season,AttriOption attri);//5man group ,前10个组合的某项属性的数据

	//返回值数组大小[3][5]
	public double[][] position(int playerID,int season, AttriOption attri);//位置分析
	
	public ShotAnlsPO shootAnalysis(int playerID,int season);//数组第0个为进攻数据，第1个位防守数据
	public ShotAnlsPO shootAnalysis_clutch(int playerID,int season);//关键时刻数据
	public ShotAnlsPO shotClockAnls(int playerID, int season);//投篮时间分析，同上
	public ShotAnlsPO shotClockAnls_clutch(int playerID, int season);//关键时刻数据
	
	//数组大小为[7][]
	public EvolutionPO envolution(ArrayList<String> names, String attri);//根据球员名称和所需属性返回球员历年该属性的数值

	public OnOffPO on_off(int playerID,int season);
	
	public double[] turnovers(int playerID, int season);//表Turnovers and Ball Handling 由左至右全部数据
	public double[] turnovers_clutch(int playerID, int season);//关键时刻表Turnovers and Ball Handling 由左至右全部数据
	public double[] passing(int playerID, int season);//Passing Stats 
	public double[] passing_clutch(int playerID, int season);
	public double[] shotBlock(int playerID, int season);//Shot Blocking 
	public double[] shotBlock_clutch(int playerID, int season);
	
	public RegressionPO playerRegression(int playerID, int season);


	PlayerPO findPlayerData(int playerId, int season);
	PlayerPO findPlayerData(String name, int season);

	ArrayList<PlayerPO> findPlayers(String msg);

	ArrayList<PlayerPO> sortPlayer(String order, int season);

	ArrayList<PlayerPO> filter(String order, ArrayList<String> position, ArrayList<String> league, int numS, int numE, int season);


	ArrayList<PlayerPO> getTeamMemberList(int teamID, int season);

	double[] getFreeThrowShooting(int playerID, int season, boolean isClutch);

	double[] getReBound(int playerID, int season, boolean isClutch);

	String[] getFloorStats(int teamID, int season);

	String[] getScoring(int teamID, int season);

	ArrayList<HotZoneVO> getHotZone(int playerId, boolean isPerformance, int matchType, int season);
}
