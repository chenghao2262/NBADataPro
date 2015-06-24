package businesslogic.matchbl;

import java.util.ArrayList;

import vo.matchvo.LiveListItemVO;
import blservice.matchblservice.MatchLiveListBLService;

public class MatchLiveListBLService_Stub implements MatchLiveListBLService{

	@Override
	public ArrayList<LiveListItemVO> getLiveList() {
		ArrayList<LiveListItemVO> vos = new ArrayList<LiveListItemVO>();
		for(int i=0;i<3;i++){
			LiveListItemVO vo = new LiveListItemVO();
			vo.liveID = "mock"+i;
			vo.hostName = "host";
			vo.guestName = "guest";
			vo.startTime = "time";
			vos.add(vo);
		}
		return vos;
	}

	
}
