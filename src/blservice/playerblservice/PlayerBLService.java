package blservice.playerblservice;

import java.util.ArrayList;

import businesslogic.playerbl.RegressionItem;
import vo.HotZoneVO;
import vo.playervo.HotPlayersVO;
import vo.playervo.PlayerVO;

public interface PlayerBLService {
	public PlayerVO findPlayerData(int playerId,int season);//返回确切的某一个球员，默认场均
	public PlayerVO findPlayerData(String name,int season);//返回确切的某一个球员，默认场均
	
	//返回姓名包含输入的字符串的球员列表，返回值包括id,name,photo
	public ArrayList<PlayerVO> findPlayers(String msg);
	

	//排序球员列表
	public ArrayList<PlayerVO> sortPlayerInfo(ArrayList<sortParam> sortBy,int season);
	public ArrayList<PlayerVO> sortPlayerNormal(ArrayList<sortParam> sortBy,int season);
	public ArrayList<PlayerVO> sortPlayerNormalAvg(ArrayList<sortParam> sortBy,int season);
	public ArrayList<PlayerVO> sortPlayerHigh(ArrayList<sortParam> sortBy,int season);

	//返回筛选球员普通数据列表
	public ArrayList<PlayerVO> filterInfo(ArrayList<sortParam> sortBy, ArrayList<String> position, ArrayList<String> league, int numS, int numE,int season);
	public ArrayList<PlayerVO> filterNormal(ArrayList<sortParam> sortBy, ArrayList<String> position, ArrayList<String> league, int numS, int numE,int season);
	public ArrayList<PlayerVO> filterNormalAvg(ArrayList<sortParam> sortBy, ArrayList<String> position, ArrayList<String> league, int numS, int numE,int season);
	public ArrayList<PlayerVO> filterHigh(ArrayList<sortParam> sortBy, ArrayList<String> position, ArrayList<String> league, int numS, int numE,int season);

	//以下默认返回平均数据
	
	//返回HotPlayersVO中除upgradeRate外的全部数据
	public ArrayList<HotPlayersVO> DailyKing(int num, String sortBy,String date);//当日热门球员
	public ArrayList<HotPlayersVO> SeasonKing(int num, String sortBy,int season);//赛季热门球员
	//返回HotPlayersVO中的全部数据，其中value属性为赛季平均数据（暂定）
	public ArrayList<HotPlayersVO> hotPlayer(int num, String sortBy);//进步最快球员
	
	public PlayerVO avgLeague(char league,int season);//联盟平均数据 返回值包括 场均得分，场均篮板，场均助攻，罚球命中率，三分命中率
	
	/********************************************迭代三的分割线*****************************************************/
	
	/**
	 * @Param  isPerformance：true表示表现  ； isPreSeason:true表示季前赛
	 * 第一个vo是赛季表现 第二个是近5场比赛
	 */
	public ArrayList<HotZoneVO> getHotZone(int playerId,boolean isPerformance, boolean isPreSeason, int season);//返回热区数据
		
	public int[] playerVS(int player1ID, int player2ID,int season,ArrayList<String> attris);
	
	public double[] freeThrowShooting(int playerID,int season,boolean isClutch);
	
	public double[] reBound(int playerID,int season,boolean isClutch);

	/**用于player的五人，填充空间**/
	public String[] floorStats(int teamID,int season);
	
	public String[] scoring(int teamID,int season);
	
	public ArrayList<ArrayList<RegressionItem>> playerRegression(int playerID, int season);
	//正相关+负相关
	//负相关倒着显示，正相关不用

}
