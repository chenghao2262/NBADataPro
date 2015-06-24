package businesslogic.matchbl;

import blservice.matchblservice.MatchLiveListBLService;
import data.database.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import vo.matchvo.LiveListItemVO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenghao on 15/6/14.
 */
public class MatchLiveListBL implements MatchLiveListBLService {

    @Override
    public ArrayList<LiveListItemVO> getLiveList() {
        String sql = "select next_match.gameid,next_match.matchtime,t1.abbr,t2.abbr from next_match,team t1,team t2 where next_match.awayteamid=t1.teamid and next_match.hometeamid=t2.teamid";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        ArrayList<LiveListItemVO> arrayList = new ArrayList<>();
        try {
            List<Object[]> list = queryRunner.query(sql, new ArrayListHandler());
            for (int i = 0; i< list.size();i++){
                Object[] objects = list.get(i);
                for (int k=0;k<objects.length;k++){
                    System.out.print(objects[k]+" ");
                }
                System.out.println();
                LiveListItemVO liveListItemVO = new LiveListItemVO();
                liveListItemVO.liveID = objects[0]==null?null:String.valueOf(objects[0]);
                liveListItemVO.startTime = objects[1]==null?null:String.valueOf(objects[1]);
                liveListItemVO.guestName = objects[2]==null?null:String.valueOf(objects[2]);
                liveListItemVO.hostName = objects[3]==null?null:String.valueOf(objects[3]);
                arrayList.add(liveListItemVO);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    public static void main(String[] args){
        new MatchLiveListBL().getLiveList();
    }
}
