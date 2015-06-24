package businesslogic.playerbl;

import java.util.ArrayList;

import blservice.playerblservice.PlayerBLService;
import blservice.playerblservice.sortParam;
import vo.HotZoneVO;
import vo.playervo.HotPlayersVO;
import vo.playervo.PlayerVO;

public class PlayerBL_stub implements PlayerBLService{

	@Override
	public ArrayList<HotPlayersVO> DailyKing(int num, String sortBy,String Date) {
		ArrayList<HotPlayersVO>  result = new ArrayList<HotPlayersVO>();
		HotPlayersVO vo = new HotPlayersVO();
		vo.name = "Aaron Brooks";
		vo.number = 12;
		vo.photo = "Aaron Brooks";
		vo.position = "F";
		vo.teamName = "Hawks";
		vo.teamPhoto = "BKN";
		vo.value = 39;
		HotPlayersVO vo3 = new HotPlayersVO();
		vo3.name = "John Wall";
		vo3.number = 7;
		vo3.photo = "John Wall";
		vo3.position = "E";
		vo3.teamName = "Chicago";
		vo3.teamPhoto = "CHI";
		vo3.value = 37;
		
		if(sortBy.equals("avgPoint"))
			result.add(vo);
		else
			result.add(vo3);
		
		HotPlayersVO vo2 = new HotPlayersVO();
		vo2.name = "Jimmy Bulter";
		vo2.number = 9;
		vo2.photo = "101106";
		vo2.position = "W";
		vo2.teamName = "Bull";
		vo2.teamPhoto = "DET";
		vo2.value = 29;
		HotPlayersVO vo4 = new HotPlayersVO();

		vo4.name = "Paul Gasol";
		vo4.number = 17;
		vo4.photo = "101131";
		vo4.position = "F";
		vo4.teamName = "Cavaliers";
		vo4.teamPhoto = "HOU";
		vo4.value = 23;
		
		HotPlayersVO vo5 = new HotPlayersVO();

		vo5.name = "Paul Gasol";
		vo5.number = 11;
		vo5.photo = "101236";
		vo5.position = "E";
		vo5.teamName = "Magic";
		vo5.teamPhoto = "MIL";
		vo5.value = 21;
		
		HotPlayersVO vo6 = new HotPlayersVO();

		vo6.name = "Paul Gasol";
		vo6.number = 3;
		vo6.photo = "101187";
		vo6.position = "F";
		vo6.teamName = "Pistons";
		vo6.teamPhoto = "NOP";
		vo6.value = 19;
		
		result.add(vo2);
		result.add(vo4);
		result.add(vo5);
		result.add(vo6);
			
		return result;
	}

	@Override
	public ArrayList<HotPlayersVO> SeasonKing(int num, String sortBy,int season) {
		ArrayList<HotPlayersVO>  result = new ArrayList<HotPlayersVO>();
		HotPlayersVO vo = new HotPlayersVO();
		vo.name = "Aaron Brooks";
		vo.number = 12;
		vo.photo = "Aaron Brooks";
		vo.position = "F";
		vo.teamName = "Hawks";
		vo.teamPhoto = "ATL";
		vo.value = 39;
		HotPlayersVO vo3 = new HotPlayersVO();
		vo3.name = "Aaron Brooks";
		vo3.number = 12;
		vo3.photo = "Aaron Brooks";
		vo3.position = "F";
		vo3.teamName = "Hawks";
		vo3.teamPhoto = "BKN";
		vo3.value = 39;
		
		if(sortBy.equals("avgPoint"))
			result.add(vo);
		else
			result.add(vo3);
		
		HotPlayersVO vo2 = new HotPlayersVO();
		vo2.name = "Aaron Brooks";
		vo2.number = 12;
		vo2.photo = "Aaron Brooks";
		vo2.position = "F";
		vo2.teamName = "Hawks";
		vo2.teamPhoto = "ATL";
		vo2.value = 39;
		result.add(vo2);
		result.add(vo2);
		result.add(vo2);
		result.add(vo2);
			
		return result;
	}

	@Override
	public ArrayList<HotPlayersVO> hotPlayer(int num, String sortBy) {
		ArrayList<HotPlayersVO>  result = new ArrayList<HotPlayersVO>();
		HotPlayersVO vo = new HotPlayersVO();
		vo.name = "Aaron Brooks";
		vo.number = 12;
		vo.photo = "Aaron Brooks";
		vo.position = "F";
		vo.teamName = "Hawks";
		vo.teamPhoto = "ATL";
		vo.upgradeRate = 39.98;
		vo.value = 39;
		
		HotPlayersVO vo3 = new HotPlayersVO();
		vo3.name = "Aaron Brooks";
		vo3.number = 12;
		vo3.photo = "Aaron Brooks";
		vo3.position = "F";
		vo3.teamName = "Hawks";
		vo.upgradeRate = 39.98;
		vo3.teamPhoto = "BKN";
		vo3.value = 39;
		
		if(sortBy.equals("avgPoint"))
			result.add(vo);
		else
			result.add(vo3);
		
		HotPlayersVO vo2 = new HotPlayersVO();
		vo2.name = "Aaron Brooks";
		vo2.number = 12;
		vo2.photo = "Aaron Brooks";
		vo2.position = "F";
		vo2.teamName = "Hawks";
		vo2.teamPhoto = "ATL";
		vo2.upgradeRate = 39.98;

		vo2.value = 39;
		result.add(vo2);
		result.add(vo2);
		result.add(vo2);
		result.add(vo2);
			
		return result;
	}

	@Override
	public PlayerVO findPlayerData(int playerId,int season) {
		PlayerVO vo = new PlayerVO();
		vo.photo = "Aaron Gray";
		vo.name = "Aaron Gray";
		vo.team = "Hawks";
		return vo;
	}

	@Override
	public PlayerVO findPlayerData(String name,int season) {
		PlayerVO vo = new PlayerVO();
		vo.photo = "Aaron Gray";
		vo.name = "Aaron Gray";
		vo.team = "Hawks";
		vo.position = "后卫";
		vo.avgAssist = 3.2;
		return vo;
	}

	@Override
	public ArrayList<PlayerVO> sortPlayerInfo(ArrayList<sortParam> sortBy,int season) {
		ArrayList<PlayerVO> vol = new ArrayList<PlayerVO>();
		PlayerVO vo1 = new PlayerVO();
		vo1.photo = "Aaron Brooks";
		vo1.name = "Aaron Brooks";
		vol.add(vo1);
		
		return vol;
	}

	@Override
	public ArrayList<PlayerVO> sortPlayerNormal(ArrayList<sortParam> sortBy,int season) {
		ArrayList<PlayerVO> vol = new ArrayList<PlayerVO>();
		PlayerVO vo = new PlayerVO();
		vo.photo = "Aaron Grey";
		vo.name = "Aaron Grey";
		vol.add(vo);
		return vol;
	}

	@Override
	public ArrayList<PlayerVO> sortPlayerNormalAvg(ArrayList<sortParam> sortBy,int season) {
		ArrayList<PlayerVO> vol = new ArrayList<PlayerVO>();
		PlayerVO vo = new PlayerVO();
		vo.photo = "Aaron Brooks";
		vo.name = "Aaron Brooks";
		vol.add(vo);
		return vol;
	}

	@Override
	public ArrayList<PlayerVO> sortPlayerHigh(ArrayList<sortParam> sortBy,int season) {
		ArrayList<PlayerVO> vol = new ArrayList<PlayerVO>();
		PlayerVO vo = new PlayerVO();
		vo.photo = "Aaron Brooks";
		vo.name = "Aaron Brooks";
		vol.add(vo);
		return vol;
	}

	@Override
	public ArrayList<PlayerVO> filterInfo(ArrayList<sortParam> sortBy,
			ArrayList<String> position, ArrayList<String> league, int numS,
			int numE,int season) {
		ArrayList<PlayerVO> vol = new ArrayList<PlayerVO>();
		PlayerVO vo1 = new PlayerVO();
		vo1.photo = "Aaron Brooks";
		vo1.name = "Aaron Brooks";
		vol.add(vo1);
		PlayerVO vo2 = new PlayerVO();
		vo2.photo = "Aaron Gray";
		vo2.name = "Aaron Gray";
		vol.add(vo2);
		return vol;
	
	}

	@Override
	public ArrayList<PlayerVO> filterNormal(ArrayList<sortParam> sortBy,
			ArrayList<String> position, ArrayList<String> league, int numS,
			int numE,int season) {
		ArrayList<PlayerVO> vol = new ArrayList<PlayerVO>();
		PlayerVO vo1 = new PlayerVO();
		vo1.photo = "Aaron Brooks";
		vo1.name = "Aaron Brooks";
		vol.add(vo1);
		PlayerVO vo2 = new PlayerVO();
		vo2.photo = "Aaron Gray";
		vo2.name = "Aaron Gray";
		vol.add(vo2);
		return vol;
	
	}

	@Override
	public ArrayList<PlayerVO> filterNormalAvg(ArrayList<sortParam> sortBy,
			ArrayList<String> position, ArrayList<String> league, int numS,
			int numE,int season) {
		ArrayList<PlayerVO> vol = new ArrayList<PlayerVO>();
		PlayerVO vo2 = new PlayerVO();
		vo2.photo = "Aaron Gray";
		vo2.name = "Aaron Gray";
		vol.add(vo2);
		return vol;
	}
	

	@Override
	public ArrayList<PlayerVO> filterHigh(ArrayList<sortParam> sortBy,
			ArrayList<String> position, ArrayList<String> league, int numS,
			int numE,int season) {
		ArrayList<PlayerVO> vol = new ArrayList<PlayerVO>();
		PlayerVO vo1 = new PlayerVO();
		vo1.photo = "Aaron Brooks";
		vo1.name = "Aaron Brooks";
		vol.add(vo1);
		
		return vol;
	
	}
	
	//联盟平均数据 返回值包括 场均得分，场均篮板，场均助攻，罚球命中率，三分命中率
	@Override
	public PlayerVO avgLeague(char l,int season) {
		PlayerVO vo = new PlayerVO();
		vo.avgPoint = 5;
		vo.avgRebound = 10;
		vo.avgAssist = 4;
		vo.penalty = 100.0;
		vo.three = 90.0;
		return vo;
	}

	
	@Override
	public ArrayList<PlayerVO> findPlayers(String msg) {
		ArrayList<PlayerVO>  result = new ArrayList<PlayerVO>();
		PlayerVO vo = new PlayerVO();
		vo.name = "Aaron Brooks";
		vo.photo = "Aaron Brooks";
		for(int i = 0;i < 10;i++){
			result.add(vo);
		}
		return result;
	}

	public ArrayList<HotZoneVO> getHotZone(int playerId, boolean isPerformance,
			boolean isPreSeason) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] playerVS(int player1id, int player2id, int season,
			ArrayList<String> attris) {
		int[] temp = {1,1,1,1,1,1,1,1,1,1};
		return temp;
	}

	@Override
	public double[] freeThrowShooting(int playerID, int season, boolean isClutch) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double[] reBound(int playerID, int season, boolean isClutch) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] floorStats(int teamID, int season) {
		String[] d = new String[8];
		for(int i = 0;i<8;i++){
			d[i] = i*1.0 +"";
		}
		return d;
	}

	@Override
	public String[] scoring(int teamID, int season) {
		String[] d = new String[9];
		for(int i = 0;i<9;i++){
			d[i] = i*1.0 +"";
		}
		return d;
	}

	@Override
	public ArrayList<ArrayList<RegressionItem>> playerRegression(int playerID,
			int season) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<HotZoneVO> getHotZone(int playerId, boolean isPerformance,
			boolean isPreSeason, int season) {
		// TODO Auto-generated method stub
		return null;
	}

}
