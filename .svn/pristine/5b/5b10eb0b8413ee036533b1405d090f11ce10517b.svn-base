package dataservice.teamdataservice;

import java.util.ArrayList;

import org.python.antlr.ast.Str;
import po.stats.EvolutionPO;
import po.stats.FiveUnitPO;
import po.stats.OffDefPO;
import po.stats.ShotAnlsPO;
import po.stats.TurnoverAndFoulPO;
import po.stats.WLProfilesPO;
import po.teampo.TeamDataPO;
import po.teampo.TeamInfoPO;
import vo.AttriOption;
import vo.HotZoneVO;

public interface TeamDataService {

	public TeamInfoPO findTeamInfo(int teamId);// 返回确切的某一个球队的基本信息
	public TeamInfoPO findTeamInfo(String teamName);// 返回确切的某一个球队的基本信息
	public TeamDataPO findTeamData(int teamId,int season);// 返回确切的某一个球队的比赛信息
	public TeamDataPO findTeamData(String teamName,int season);// 返回确切的某一个球队的比赛信息

	public ArrayList<TeamInfoPO> findTeamInfoList();// 返回球队基本信息列表，按id升序排列
	public ArrayList<TeamInfoPO> findTeamInfoList(String msg);// 返回球队基本信息列表，按id升序排列
	public ArrayList<TeamDataPO> findTeamDataList(int season);// 返回球队比赛信息列表，按id升序排列

	public ArrayList<TeamDataPO> sortTeamList(String sortBy,boolean order,int season);// 排序球队列表，true表示降序
	/*
	 * 某球队整个赛季某个属性每场比赛的数据
	 */
	public double[] specDataOfSeason(int teamID,String attri, int season);	//球队对比数据
	
	public FiveUnitPO top5manGroup(int teamID,int season,AttriOption attri);//5man group ,前10个组合的某项属性的数据
	
	//数组大小为[3][5]
	public double[][] position(int teamID,int season, AttriOption attri);//位置分析
	
	public ShotAnlsPO[] shootAnalysis(int teamID,int season);//数组大小为2，第0个为进攻数据，第1个位防守数据
	public ShotAnlsPO[] shotClockAnls(int teamID, int season);//投篮时间分析，同上
	
	public TurnoverAndFoulPO turnover_fouls(int teamID,int season);//失误犯规分析
	public OffDefPO off_defStats(int teamID,int season);
	
	//数组大小为[7][]
	public EvolutionPO envolution(ArrayList<String> names, String attri);//根据球队名称和所需属性返回球队历年该属性的数值
	/*
	 * 每个球员整个赛季上场时间>=30min 的比赛的+/-值
	 */
	public double[] net_everyMember(int playerID,int season);

	public WLProfilesPO wl_Profile(int teamID, int season);

	public TeamDataPO getAvgLeague(char league,int season);

	ArrayList<HotZoneVO> getHotZone(int playerId, boolean isPerformance, int matchType, int season);
}
