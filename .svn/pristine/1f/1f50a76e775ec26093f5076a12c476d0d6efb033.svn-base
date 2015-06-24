package dataservice.teamdataservice;

import java.util.ArrayList;

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

public class TeamData_stub implements TeamDataService{

	@Override
	public double[] specDataOfSeason(int teamID, String attri, int season) {
		return new double[]{99,100,100,100,100,100,110};
	}

	@Override
	public double[] net_everyMember(int player,int season) {
		switch(player){
		case 1:	return new double[]{99,100,100,100,100,100,110};
		case 2:	return new double[]{1,2,3,4,5,6,7,8,9,0};
		case 3:	return new double[]{1,1,1,1,1,1,2};
		case 4:	return new double[]{2,2,2,2,2,2,3};
		case 5:	return new double[]{1,1,1,1,1,2,1};
		case 6:	return new double[]{1,1,1,1,2,1,1};
		default:	return new double[]{3,3,3,3,3,3,3};
		}
	}

	@Override
	public ShotAnlsPO[] shootAnalysis(int teamID,int season) {
		ShotAnlsPO[] po = new ShotAnlsPO[2];
	//	ShotAnlsPO po1 = new ShotAnlsPO();
		po[0].Att = new double[4];
		po[0].Att = new double[]{0.95,0.04,0.01,0};
		po[0].eFG = new double[]{0.476,0.605,0.879,0.528};
		po[0].Ast = new double[]{0.72,0.57,0.72,0};
		po[0].Pts = new double[]{56.7,24.0,2.7,0.7};
	//	ShotAnlsPO po2 = new ShotAnlsPO();
		po[1].Att = new double[]{0.69,0.26,0.03,0.02};
		po[1].eFG = new double[]{0.476,0.605,0.879,0.528};
		po[1].Ast = new double[]{0.72,0.57,0.72,0};
		po[1].Pts = new double[]{56.7,24.0,2.7,0.7};
		
	//	ShotAnlsPO[] result = new ShotAnlsPO[]{po1,po2};
		return po;
	}

	@Override
	public FiveUnitPO top5manGroup(int teamID, int season, AttriOption attri) {
		FiveUnitPO po = new FiveUnitPO();
		po.unitsName = new String[]{"Teague-Korver-Carroll-Millsap-Horford"," Schroder-Bazemore-Korver-Scott-Horford",
				" Teague-Korver-Carroll-Millsap-Antic"," Schroder-Korver-Carroll-Millsap-Horford",
				" Teague-Sefolosha-Korver-Millsap-Horford"," Schroder-Sefolosha-Korver-Scott-Horford",
				" Teague-Bazemore-Carroll-Millsap-Antic"," Teague-Bazemore-Korver-Millsap-Horford",
				" Schroder-Bazemore-Korver-Horford-Antic"," Teague-Korver-Carroll-Horford-Muscala"};
		if(attri==AttriOption.fiveMan_winPct)
			po.datas = new double[]{46,6,34,78,56,45,90,76,45,63};
		else
			po.datas = new double[]{6,46,4,8,5,5,90,76,45,63};
		return po;
	}

	@Override
	public double[][] position(int teamID, int season, AttriOption attri) {
			double[] own = new double[]{20.2,12.7,13.3,18.5,16.9};
			double[] opp = new double[]{17.4,18.0,16.5,17.1,14.1};
			double[] net = new double[]{2.8,-5.3,-3.1,1.4,2.7};
		if(attri!=AttriOption.position_per){
			own = new double[]{13,12.7,13.3,18.5,16.9};
			opp = new double[]{17.4,78,16.5,17.1,14.1};
			net = new double[]{2.8,-5.3,-3.1,1.4,2.7};
		}
		return new double[][]{own,opp,net};
	}

	@Override
	public ShotAnlsPO[] shotClockAnls(int teamID, int season) {
		ShotAnlsPO po1 = new ShotAnlsPO();
		po1.Att = new double[]{0.95,0.04,0.01,0};
		po1.eFG = new double[]{0.476,0.605,0.879,0.528};
		po1.Ast = new double[]{0.72,0.57,0.72,0};
		po1.Pts = new double[]{56.7,24.0,2.7,0.7};
		ShotAnlsPO po2 = new ShotAnlsPO();
		po2.Att = new double[]{0.69,0.26,0.03,0.02};
		po2.eFG = new double[]{0.476,0.605,0.879,0.528};
		po2.Ast = new double[]{0.72,0.57,0.72,0};
		po2.Pts = new double[]{56.7,24.0,2.7,0.7};
		
		ShotAnlsPO[] result = new ShotAnlsPO[]{po1,po2};
		return result;
	}

	@Override
	public TurnoverAndFoulPO turnover_fouls(int teamID, int season) {
		TurnoverAndFoulPO po = new TurnoverAndFoulPO(); 
		po.t_own = new double[]{125,539,497,7};
		po.t_opp = new double[]{135,598,567,17};
		po.f_own = new double[]{753,513,62,125};
		po.f_opp = new double[]{684,677,123,125};
		return po;
	}

	@Override
	public OffDefPO off_defStats(int teamID, int season) {
		OffDefPO po = new OffDefPO();
		po.off_pct = new double[]{0.466,0.527,0.78,0.25,0.71,0.15,0.056,0.50};
		po.def_pct = new double[]{0.439,0.492,0.76,0.28,0.74,0.17,0.060,0.59};
		po.off_notPct = new double[]{102.5,93,109};
		po.def_notPct = new double[]{97.1,94,103};
		return po;
	}

	@Override
	public EvolutionPO envolution(ArrayList<String> names,
			String attri) {
		EvolutionPO po  = new EvolutionPO();
		double[] d1 = new double[]{45,69,46,38,74,64,39,47}; 
//		double[] d2 = new double[]{56,47,74,56,93,54,69,56}; 
		double[] d2 = new double[]{getRandom(),getRandom(),getRandom(),getRandom(),
				getRandom(),getRandom(),getRandom(),getRandom()}; 
		double[] d5 = new double[]{37,95,45,84,82,89,39,46}; 
//		double[] d8 = new double[]{67,87,65,37,87,54,65,65}; 
		double[] d8 = new double[]{getRandom(),getRandom(),getRandom(),getRandom(),
				getRandom(),getRandom(),getRandom(),getRandom()}; 
		double[] d6 = new double[]{75,47,32,56,26,64,39,56}; 
		double[] d7 = new double[]{38,47,51,61,71,43,67};
		double[][] d3 = new double[][]{d6,d7,d1,d7,d2,d8,d2};
		double[][] d4 = new double[][]{d7,d5,d8,d6,d7,d2,d7}; 
		ArrayList<double[][]> ldd = new ArrayList<double[][]>();
		ldd.add(d3);
		if(names.size()==2)
			ldd.add(d4);
		po.datas = ldd;
		po.years = new double[]{2009,2010,2011,2012,2013,2014,2015};
		
		return po;
	}
	
	private int getRandom(){
		return (int)(40*Math.random())+35;
	}

	@Override
	public WLProfilesPO wl_Profile(int teamID, int season) {
		WLProfilesPO po = new WLProfilesPO();
		po.winp_good = new double[]{0.64,0.71,0.61,0.68,0.68,0.69,0.71,0.63,0.65,0.71,0.64};
		po.winp_average = new double[]{0.69,0.73,0.79,0.80,0.76,0.79,0.72,0.78,0.89,0.75,0.78};
		po.winp_poor = new double[]{0.82,0.75,0.77,0.70,0.76,0.72,0.76,0.79,0.66,0.72,0.69};
		
		po.net_good = new double[]{1.0,5.4,0.6,2.5,4.4,4.6,5.5,2.6,3.7,3.0,6.2};
		po.net_average = new double[]{5.4,4.3,6.1,6.8,5.2,6.6,4.9,7.1,6.7,6.4,6.1};
		po.net_poor = new double[]{8.3,6.8,8.4,6.2,6.6,5.3,5.8,6.6,5.6,6.0,4.3};
		
		return po;
	}

	@Override
	public TeamInfoPO findTeamInfo(int teamId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TeamInfoPO findTeamInfo(String teamName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TeamDataPO findTeamData(int teamId, int season) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TeamDataPO findTeamData(String teamName, int season) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<TeamInfoPO> findTeamInfoList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<TeamInfoPO> findTeamInfoList(String msg) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<TeamDataPO> findTeamDataList(int season) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<TeamDataPO> sortTeamList(String sortBy, boolean order,
			int season) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TeamDataPO getAvgLeague(char league, int season) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<HotZoneVO> getHotZone(int playerId, boolean isPerformance,
			int matchType, int season) {
		// TODO Auto-generated method stub
		return null;
	}

}
