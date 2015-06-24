package businesslogic.matchbl;

import java.util.ArrayList;

import vo.matchvo.MatchLiveItemVO;
import vo.matchvo.MatchLiveVO;
import blservice.matchblservice.MatchLiveBLService;

public class MatchLiveBLService_Stub implements MatchLiveBLService{

	@Override
	public MatchLiveVO getMatchLiveVO(String liveId) {
		MatchLiveVO vo = new MatchLiveVO();
		vo.awayTeamName = "ATL";
		vo.homeTeamName = "BKN";
		vo.matchLiveItemVOs = new ArrayList<MatchLiveItemVO>();
		vo.currentScores = "10:20";
		vo.currentPeriod = 6;
		

		for(int i=0;i<5;i++){
			MatchLiveItemVO itemVO = new MatchLiveItemVO();
			itemVO.awayTeamMsg = "away team msg";
			itemVO.homeTeamMsg = "home team msg";
			itemVO.currentScores = "0:0";
			vo.matchLiveItemVOs.add(itemVO);
		}
		
		return vo;
	}

}
