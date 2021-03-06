package businesslogic.matchbl;

import blservice.matchblservice.MatchLiveBLService;
import data.Stub;
import data.database.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
//import org.python.antlr.ast.Str;
import vo.matchvo.MatchLiveItemVO;
import vo.matchvo.MatchLiveVO;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenghao on 15/6/7.
 */
public class MatchLiveBL implements MatchLiveBLService{
    @Override
    public MatchLiveVO getMatchLiveVO(String liveId) {
        MatchLiveVO matchLiveVO = new MatchLiveVO();
        String sql = "select * from next_match where gameid = ?";
        matchLiveVO.liveID = liveId;
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        ArrayList<MatchLiveItemVO> matchLiveItemVOs = new ArrayList<>();
        matchLiveVO.matchLiveItemVOs = matchLiveItemVOs;
        String awayteamid = "";
        String hometeamid = "";
        try {
            Object[] arraylist = queryRunner
                    .query(sql,
                            new ArrayHandler(), liveId);
            if (arraylist.length == 0){
                sql = "select matchinfo.*,t1.abbr,t2.abbr,t1.teamid,t2.teamid from matchinfo,nba_game_id,team t1,team t2 where nba_game_id.gameid=? and nba_game_id.mid=matchinfo.mid and matchinfo.teamf=t1.tid and matchinfo.teaml=t2.tid";
                arraylist = queryRunner
                        .query(sql,
                                new ArrayHandler(), liveId);
                matchLiveVO.startTime = String.valueOf(arraylist[3]);
                matchLiveVO.awayTeamId = Integer.parseInt(String.valueOf(arraylist[4]));
                matchLiveVO.homeTeamId = Integer.parseInt(String.valueOf(arraylist[5]));
                matchLiveVO.awayTeamName = String.valueOf(arraylist[8]);
                matchLiveVO.awayTeamName = String.valueOf(arraylist[9]);
                awayteamid = String.valueOf(arraylist[10]);
                hometeamid = String.valueOf(arraylist[11]);
                matchLiveVO.status = 3;
            }else {
                sql = "select next_match.matchtime,next_match.status,t1.tid,t1.abbr,t2.tid,t2.abbr,next_match.awayteamid,next_match.hometeamid from next_match,team t1,team t2 where gameid = ? and awayteamid=t1.teamid and hometeamid= t2.teamid";
                arraylist = queryRunner
                        .query(sql,
                                new ArrayHandler(), liveId);
                matchLiveVO.startTime = String.valueOf(arraylist[0]);
                matchLiveVO.awayTeamId = Integer.parseInt(String.valueOf(arraylist[2]));
                matchLiveVO.homeTeamId = Integer.parseInt(String.valueOf(arraylist[4]));
                matchLiveVO.status = Integer.parseInt(String.valueOf(arraylist[1]));
                matchLiveVO.awayTeamName = String.valueOf(arraylist[3]);
                matchLiveVO.homeTeamName = String.valueOf(arraylist[5]);
                awayteamid = String.valueOf(arraylist[6]);
                hometeamid = String.valueOf(arraylist[7]);
            }
            sql = "select * from live where gameid=? order by period desc,serial desc";
            List<Object[]> arraylists = queryRunner
                    .query(sql,
                            new ArrayListHandler(), liveId);
            if (arraylists.size() == 0){
                matchLiveVO.currentPeriod = 0;
                matchLiveVO.currentScores = "00 00";
                matchLiveVO.currentTime = "00:00.0";
            }else{
                arraylist = arraylists.get(0);
                matchLiveVO.currentPeriod = Integer.parseInt(String.valueOf(arraylist[1]));
                matchLiveVO.currentTime = String.valueOf(arraylist[2]);
                matchLiveVO.currentScores = String.valueOf(arraylist[4]) +" " + String.valueOf(arraylist[3]);
            }
            for (int i = 0; i< arraylists.size();i++){
                arraylist = arraylists.get(i);
                String  teamid = String.valueOf(arraylist[6]).trim();
                MatchLiveItemVO matchLiveItemVO = new MatchLiveItemVO();
                String msg = String.valueOf(arraylist[5]);

                msg = msg.trim();
                if (msg.equals("本节比赛结束")){
                    matchLiveItemVO.isPeriod = true;
                }
                if (teamid.equals("0")){
                    matchLiveItemVO.awayTeamMsg = msg;
                    matchLiveItemVO.homeTeamMsg = msg;

                }else if(teamid.equals(awayteamid) ){
                    matchLiveItemVO.awayTeamMsg = msg;
                    matchLiveItemVO.homeTeamMsg = "";
                }else if(teamid.equals(hometeamid) ){
                    matchLiveItemVO.awayTeamMsg = "";
                    matchLiveItemVO.homeTeamMsg = msg;
                }
                matchLiveItemVO.period = Integer.parseInt(String.valueOf(arraylist[1]));
                matchLiveItemVO.time = String.valueOf(arraylist[2]);
                matchLiveItemVO.currentScores = String.valueOf(arraylist[3]) +" " + String.valueOf(arraylist[4]);
                matchLiveVO.matchLiveItemVOs.add(matchLiveItemVO);
                System.out.println(matchLiveItemVO.period+" "+matchLiveItemVO.homeTeamMsg+" "+matchLiveItemVO.awayTeamMsg+" "+matchLiveItemVO.time+" "+matchLiveItemVO.currentScores);
            }
            return matchLiveVO;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return matchLiveVO;
    }

    public static void main(String[] args){
        new Thread(){
            @Override
            public void run() {
                super.run();
                Stub.stub();
            }
        }.start();
        while (true){
            new MatchLiveBL().getMatchLiveVO("1");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
