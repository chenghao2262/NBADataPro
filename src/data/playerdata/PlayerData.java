package data.playerdata;

import data.database.JDBCUtils;
import dataservice.playerdataservice.PlayerDataService;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import po.playerpo.PlayerPO;
import po.stats.*;
import vo.AttriOption;
import vo.HotZoneVO;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class PlayerData implements PlayerDataService {
    /*
    public PlayerPO findPlayerData(int playerId) {
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("timeOnCourt");
        arrayList.add("startSession");
        arrayList.add("matchNum");
        arrayList.add("FGP");
        arrayList.add("TPSP");
        arrayList.add("FTP");
        arrayList.add("BS");
        arrayList.add("BSP");
        arrayList.add("assist");
        arrayList.add("assistP");
        arrayList.add("rebound");
        arrayList.add("reboundP");
        arrayList.add("steal");
        arrayList.add("stealP");
        arrayList.add("turnover");
        arrayList.add("turnoverP");
        arrayList.add("foul");
        arrayList.add("score");
        arrayList.add("offensive");
        arrayList.add("defense");
        arrayList.add("effiency");
        arrayList.add("GmSc");
        arrayList.add("TSP");
        arrayList.add("OSE");
        arrayList.add("OREB");
        arrayList.add("DREB");
        arrayList.add("utiliation");
        arrayList.add("AvgBS");
        arrayList.add("Avgassist");
        arrayList.add("Avgrebound");
        arrayList.add("Avgsteal");
        arrayList.add("Avgturnover");
        arrayList.add("Avgfoul");
        arrayList.add("Avgscore");
        arrayList.add("Avgoffensive");
        arrayList.add("Avgdefense");
        String sql = getSql(arrayList, "p.pid=?");
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        try {
            Object[] objects = queryRunner.query(sql, new ArrayHandler(),
                    playerId);
            PlayerPO dataPO = getPlayerDataPO(arrayList, objects);
            return dataPO;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new PlayerPO();
    }

    public ArrayList<PlayerPO> findPlayerList(ArrayList<String> list) {
        String sql = getSql(list);
        sql += " group by p.pid order by p.pid";
        System.out.println(sql);
        ArrayList<PlayerPO> arrayList = new ArrayList<PlayerPO>();
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        try {
            List<Object[]> resultList = queryRunner.query(sql,
                    new ArrayListHandler());
            for (int i = 0; i < resultList.size(); i++) {
                Object[] objects = resultList.get(i);
                PlayerPO dataPO = getPlayerDataPO(list, objects);
                arrayList.add(dataPO);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return arrayList;
    }

    public ArrayList<PlayerPO> sortPlayerList(ArrayList<String> list,
                                              String mainProperty, boolean order) {
        String sql = getSql(list);
        sql += " group by p.pid order by " + mainProperty;
        if (order) {
            sql += " desc";
        }
        ArrayList<PlayerPO> arrayList = new ArrayList<PlayerPO>();
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        try {
            List<Object[]> resultList = queryRunner.query(sql,
                    new ArrayListHandler());
            for (int i = 0; i < resultList.size(); i++) {
                Object[] objects = resultList.get(i);
                PlayerPO dataPO = getPlayerDataPO(list, objects);
                arrayList.add(dataPO);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return arrayList;
    }

    public ArrayList<PlayerPO> findTop50(ArrayList<String> list,
                                         String mainProperty, ArrayList<String> position,
                                         ArrayList<String> division) {
        String where1 = "playerposition.position='" + position.get(0) + "'";
        for (int i = 1; i < position.size(); i++) {
            where1 += " or playerposition.position='" + position.get(i) + "'";
        }
        String where2 = "team.subarea='" + division.get(0) + "'";
        for (int i = 1; i < division.size(); i++) {
            where2 += " or team.subarea='" + division.get(i) + "'";
        }
        String from = "playerposition,team";
        String where = "p.tid=team.tid and p.pid=playerposition.pid and ("
                + where1 + ") and (" + where2 + ")";
        return getTOP50(list, mainProperty, from, where);
    }

    public ArrayList<PlayerPO> findTop50_pos(ArrayList<String> list,
                                             String mainProperty, ArrayList<String> position) {
        String where = "playerposition.position='" + position.get(0) + "'";
        for (int i = 1; i < position.size(); i++) {
            where += " or playerposition.position='" + position.get(i) + "'";
        }
        where = "p.pid=playerposition.pid and (" + where + ")";
        String from = "playerposition";
        return getTOP50(list, mainProperty, from, where);
    }

    public ArrayList<PlayerPO> findTop50_div(ArrayList<String> list,
                                             String mainProperty, ArrayList<String> division) {
        String where = "team.subarea='" + division.get(0) + "'";
        for (int i = 1; i < division.size(); i++) {
            where += " or team.subarea='" + division.get(i) + "'";
        }
        where = "p.tid=team.tid and (" + where + ")";
        String from = "team";
        return getTOP50(list, mainProperty, from, where);
    }

    public ArrayList<PlayerPO> findTop50(ArrayList<String> list,
                                         String mainProperty) {
        String sql = getSql(list);
        sql += " group by p.pid order by " + mainProperty + " desc limit 0,50";
        ArrayList<PlayerPO> arrayList = new ArrayList<PlayerPO>();
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        try {
            List<Object[]> resultList = queryRunner.query(sql,
                    new ArrayListHandler());
            for (int i = 0; i < resultList.size(); i++) {
                Object[] objects = resultList.get(i);
                PlayerPO dataPO = getPlayerDataPO(list, objects);
                arrayList.add(dataPO);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return arrayList;
    }

    public PlayerPO findPlayerInfo(int playerId) {
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("photo");
        arrayList.add("conference");
        arrayList.add("division");
        arrayList.add("posititon");
        arrayList.add("playerName");
        arrayList.add("Team");

        String sql = getSql(arrayList, "p.pid=?");
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        try {
            Object[] objects = queryRunner.query(sql, new ArrayHandler(),
                    playerId);
            PlayerPO dataPO = getPlayerDataPO(arrayList, objects);
            return dataPO;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new PlayerPO();
    }


    private PlayerPO getPlayerDataPO(ArrayList<String> list, Object[] objects) {
        PlayerPO dataPO = new PlayerPO();
        dataPO.id = Integer.parseInt(String.valueOf(objects[0]));
        System.out.println(dataPO.id);
        for (int j = 0; j < list.size(); j++) {
//			setValue(dataPO, list.get(j), objects[j + 1]);
        }
        return dataPO;
    }
    */

//	private void setValue(PlayerPO dataPO, String string, Object object) {
//		System.out.println(string + ":" + object);
//		switch (string) {
//		case "timeOnCourt":
//			dataPO.timeOnCourt = getTime(Integer.parseInt(String
//					.valueOf(object)));
//			return;
//		case "startSession":
//			System.out.println("test");
//			dataPO.startSession = object == null ? 0 : Integer.parseInt(String
//					.valueOf(object));
//			System.out.println("testend");
//			return;
//		case "matchNum":
//			dataPO.matchNum = object == null ? -11 : Integer.parseInt(String
//					.valueOf(object));
//			return;
//		case "FGP":
//			dataPO.FGP = object == null ? -11 : Double.parseDouble(String
//					.valueOf(object));
//			return;
//		case "TPSP":
//			dataPO.TPSP = object == null ? -11 : Double.parseDouble(String
//					.valueOf(object));
//			return;
//		case "FTP":
//			dataPO.FTP = object == null ? -11 : Double.parseDouble(String
//					.valueOf(object));
//			return;
//		case "BS":
//			dataPO.BS = object == null ? -11 : Integer.parseInt(String
//					.valueOf(object));
//			return;
//		case "BSP":
//			dataPO.BSP = object == null ? -11 : Double.parseDouble(String
//					.valueOf(object));
//			return;
//		case "assist":
//			dataPO.assist = object == null ? -11 : Integer.parseInt(String
//					.valueOf(object));
//			return;
//		case "assistP":
//			dataPO.assistP = object == null ? -11 : Double.parseDouble(String
//					.valueOf(object));
//			return;
//		case "rebound":
//			dataPO.rebound = object == null ? -11 : Integer.parseInt(String
//					.valueOf(object));
//			return;
//		case "reboundP":
//			dataPO.reboundP = object == null ? -11 : Double.parseDouble(String
//					.valueOf(object));
//			return;
//		case "steal":
//			dataPO.steal = object == null ? -11 : Integer.parseInt(String
//					.valueOf(object));
//			return;
//		case "stealP":
//			dataPO.stealP = object == null ? -11 : Double.parseDouble(String
//					.valueOf(object));
//			return;
//		case "turnover":
//			dataPO.turnover = object == null ? -11 : Integer.parseInt(String
//					.valueOf(object));
//			return;
//		case "turnoverP":
//			dataPO.turnoverP = object == null ? -11 : Double.parseDouble(String
//					.valueOf(object));
//			return;
//		case "foul":
//			dataPO.foul = object == null ? -11 : Integer.parseInt(String
//					.valueOf(object));
//			return;
//		case "score":
//			dataPO.score = object == null ? -11 : Integer.parseInt(String
//					.valueOf(object));
//			return;
//		case "offensive":
//			dataPO.offensive = object == null ? -11 : Integer.parseInt(String
//					.valueOf(object));
//			return;
//		case "defense":
//			dataPO.defense = object == null ? -11 : Integer.parseInt(String
//					.valueOf(object));
//			return;
//		case "effiency":
//			dataPO.effiency = object == null ? -11 : Double.parseDouble(String
//					.valueOf(object));
//			return;
//		case "GmSc":
//			dataPO.GmSc = object == null ? -11 : Double.parseDouble(String
//					.valueOf(object));
//			return;
//		case "TSP":
//			dataPO.TSP = object == null ? -11 : Double.parseDouble(String
//					.valueOf(object));
//			return;
//		case "OSE":
//			dataPO.OSE = object == null ? -11 : Double.parseDouble(String
//					.valueOf(object));
//			return;
//		case "OREB":
//			dataPO.OREB = object == null ? -11 : Double.parseDouble(String
//					.valueOf(object));
//			return;
//		case "DREB":
//			dataPO.DREB = object == null ? -11 : Double.parseDouble(String
//					.valueOf(object));
//			return;
//		case "utiliation":
//			dataPO.utiliation = object == null ? -11 : Double
//					.parseDouble(String.valueOf(object));
//			return;
//		case "AvgBS":
//			dataPO.AvgBS = object == null ? -11 : Double.parseDouble(String
//					.valueOf(object));
//			return;
//		case "Avgassist":
//			dataPO.Avgassist = object == null ? -11 : Double.parseDouble(String
//					.valueOf(object));
//			return;
//		case "Avgrebound":
//			dataPO.Avgrebound = object == null ? -11 : Double
//					.parseDouble(String.valueOf(object));
//			return;
//		case "Avgsteal":
//			dataPO.Avgsteal = object == null ? -11 : Double.parseDouble(String
//					.valueOf(object));
//			return;
//		case "Avgturnover":
//			dataPO.Avgturnover = object == null ? -11 : Double
//					.parseDouble(String.valueOf(object));
//			return;
//		case "Avgfoul":
//			dataPO.Avgfoul = object == null ? -11 : Double.parseDouble(String
//					.valueOf(object));
//			return;
//		case "Avgscore":
//			dataPO.Avgscore = object == null ? -11 : Double.parseDouble(String
//					.valueOf(object));
//			return;
//		case "Avgoffensive":
//			dataPO.Avgoffensive = object == null ? -11 : Double
//					.parseDouble(String.valueOf(object));
//			return;
//		case "photo":
//			dataPO.photo = object == null ? null : String.valueOf(object);
//			return;
//		case "conference":
//			dataPO.conference = object == null ? null : String.valueOf(object);
//			return;
//		case "division":
//			dataPO.division = object == null ? null : String.valueOf(object);
//			return;
//		case "posititon":
//			dataPO.posititon = object == null ? "" : String.valueOf(object);
//			return;
//		case "playerName":
//			dataPO.playerName = object == null ? null : String.valueOf(object);
//			return;
//		case "Team":
//			dataPO.Team = object == null ? null : String.valueOf(object);
//			return;
//		default:
//			break;
//		}
//	}

//    private String getTime(int timeS) {
//        DecimalFormat df = new DecimalFormat("00");
//        int hour = timeS / 3600;
//        int minute = (timeS - 3600 * hour) / 60;
//        int second = (timeS - 3600 * hour - 60 * minute) / 60;
//        return df.format(hour) + ":" + df.format(minute) + ":"
//                + df.format(second);
//    }
//
//    private String getSql(ArrayList<String> list) {
//        // TODO Auto-generated method stub
//        return getSql(list, null, null);
//    }
//
//    private String getSql(ArrayList<String> list, String wheres) {
//        return getSql(list, wheres, null);
//    }
//
//    private String getSql(ArrayList<String> list, String wheres, String froms) {
//        PlayerMaterialSql materialSql = new PlayerMaterialSql();
//        String query = queryHandel(list, materialSql).trim();
//        ArrayList<String> whereList = materialSql.getWheres();
//        if (wheres != null) {
//            whereList.add(wheres);
//        }
//        String where = "";
//        if (whereList.size() > 0) {
//            where = "where " + where;
//            where += whereList.get(0);
//            for (int i = 1; i < whereList.size(); i++) {
//                where += " and " + whereList.get(i);
//            }
//        }
//        String from = materialSql.getMaterial().trim();
//        if (froms != null) {
//            from = from + "," + froms;
//        }
//
//        String sql = "select " + query + " from " + from + " " + where;
//        return sql;
//    }
//
//    private String queryHandel(ArrayList<String> list,
//                               PlayerMaterialSql materialSql) {
//        if (list.size() == 0 || list == null) {
//            return "*";
//        }
//        String query = "";
//        for (String string : list) {
//            query += getQuery(string, materialSql);
//        }
//        query = "p.pid," + query;
//        query = query.substring(0, query.length() - 1);
//        return query;
//    }
//
//    private String[] querys = Query.playerQuerys;
//
//    private String getQuery(String string, PlayerMaterialSql materialSql) {
//        switch (string) {
//            case "timeOnCourt":
//                return querys[0];
//            case "startSession":
//                new StartSessionHandel().addMaterial(materialSql);
//                return querys[1];
//            case "matchNum":
//                return querys[2];
//            case "FGP":
//                return querys[3];
//            case "TPSP":
//                return querys[4];
//            case "FTP":
//                return querys[5];
//            case "BS":
//                return querys[6];
//            case "BSP":
//                new BlockrateHandel().addMaterial(materialSql);
//                return querys[7];
//            case "assist":
//                return querys[8];
//            case "assistP":
//                new HelprateHandel().addMaterial(materialSql);
//                return querys[9];
//            case "rebound":
//                return querys[10];
//            case "reboundP":
//                new BasrateHandel().addMaterial(materialSql);
//                return querys[11];
//            case "steal":
//                return querys[12];
//            case "stealP":
//                new InterprateHandel().addMaterial(materialSql);
//                return querys[13];
//            case "turnover":
//                return querys[14];
//            case "turnoverP":
//                new MisrateHandel().addMaterial(materialSql);
//                return querys[15];
//            case "foul":
//                return querys[16];
//            case "score":
//                return querys[17];
//            case "offensive":
//                return querys[18];
//            case "defense":
//                return querys[19];
//            case "effiency":
//                new EfficHandel().addMaterial(materialSql);
//                return querys[20];
//            case "GmSc":
//                new GmScHandel().addMaterial(materialSql);
//                return querys[21];
//            case "TSP":
//                new RealinrateHandel().addMaterial(materialSql);
//                return querys[22];
//            case "OSE":
//                return querys[23];
//            case "OREB":
//                new BasrateHandel().addMaterial(materialSql);
//                return querys[24];
//            case "DREB":
//                new BasrateHandel().addMaterial(materialSql);
//                return querys[25];
//            case "utiliation":
//                new UsereateHandel().addMaterial(materialSql);
//                return querys[26];
//            case "AvgBS":
//                return querys[27];
//            case "Avgassist":
//                return querys[28];
//            case "Avgrebound":
//                return querys[29];
//            case "Avgsteal":
//                return querys[30];
//            case "Avgturnover":
//                return querys[31];
//            case "Avgfoul":
//                return querys[32];
//            case "Avgscore":
//                return querys[33];
//            case "Avgoffensive":
//                return querys[34];
//            case "Avgdefense":
//                return querys[35];
//            case "photo":
//                new PhotoHandel().addMaterial(materialSql);
//                return querys[36];
//            case "conference":
//                new ConferenceHandel().addMaterial(materialSql);
//                return querys[37];
//            case "division":
//                new DivisionHandel().addMaterial(materialSql);
//                return querys[38];
//            case "posititon":
//                new PosititonHandel().addMaterial(materialSql);
//                return querys[39];
//            case "playerName":
//                new PlayerNameHandel().addMaterial(materialSql);
//                return querys[40];
//            case "Team":
//                new TeamHandel().addMaterial(materialSql);
//                return querys[41];
//            default:
//                break;
//        }
//        return "";
//    }
//
//
//    private ArrayList<PlayerPO> getTOP50(ArrayList<String> list,
//                                         String mainProperty, String from, String where) {
//        if (mainProperty.equals("s_r_a")) {
//            mainProperty = "(p.score+p.allbas+p.helpatt)";
//        } else if (mainProperty.equals("DD")) {
//            from = from
//                    + ","
//                    + "(select vv.pid,ddd.DD from (select distinct pid from playerscore) as vv left join (select pid,count(mid) as DD from playerscore where (score>10 and allbas>10)or(score>10 and helpatt>10)or(score>10 and interp>10)or(score>10 and block>10)or(allbas>10 and helpatt>10)or(allbas>10 and interp>10)or(allbas>10 and block>10)or(helpatt>10 and interp>10)or(helpatt>10 and block>10)or(interp>10 and block>10) group by pid) as ddd on vv.pid=ddd.pid) as d";
//            where = where + " and p.pid=d.pid";
//            mainProperty = "d.DD";
//        }
//        String sql = getSql(list, where, from);
//
//        sql += " group by p.pid order by " + mainProperty + " desc limit 0,50";
//        ArrayList<PlayerPO> arrayList = new ArrayList<PlayerPO>();
//        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
//        try {
//            List<Object[]> resultList = queryRunner.query(sql,
//                    new ArrayListHandler());
//            list.remove("s_r_a");
//            list.remove("DD");
//            for (int i = 0; i < resultList.size(); i++) {
//                Object[] objects = resultList.get(i);
//                PlayerPO dataPO = getPlayerDataPO(list, objects);
//                arrayList.add(dataPO);
//            }
//        } catch (SQLException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        return arrayList;
//    }

    @Override
    public double[] specDataOfSeason(int playerID, String attri, int season) {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "select "+getSort(attri)+" from playerscore"+season +" where pid=?";
        try {
            List<Object[]> lists =
                    queryRunner.query(sql,new ArrayListHandler(),playerID);
            double[] res = new double[lists.size()];
            for(int i = 0;i < lists.size();i++){
                res[i] = lists.get(i)[0]==null?0:Double.parseDouble(String.valueOf(lists.get(i)[0]));
            }
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public FiveUnitPO top5manGroup(int playerID, int season, AttriOption attri) {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        String query = "";
        String table = "";
        switch (attri) {
            case fiveMan_close:
                query = "close";
                table = "player_top_five_man_floor_units_details";
                break;
            case fiveMan_dClose:
                query = "dclose";
                table = "player_top_five_man_floor_units_details";
                break;
            case fiveMan_def:
                query = "def";
                table = "player_top_five_man_floor_units";
                break;
            case fiveMan_efg:
                query = "efg";
                table = "player_top_five_man_floor_units_details";
                break;
            case fiveMan_efga:
                query = "efga";
                table = "player_top_five_man_floor_units_details";
                break;
            case fiveMan_fta:
                query = "fta";
                table = "player_top_five_man_floor_units_details";
                break;
            case fiveMan_lose:
                query = "l";
                table = "player_top_five_man_floor_units";
                break;
            case fiveMan_min:
                query = "min";
                table = "player_top_five_man_floor_units";
                break;
            case fiveMan_off:
                query = "off";
                table = "player_top_five_man_floor_units";
                break;
            case fiveMan_plusMinus:
                query = "plus_minus";
                table = "player_top_five_man_floor_units";
                break;
            case fiveMan_reb:
                query = "reb";
                table = "player_top_five_man_floor_units_details";
                break;
            case fiveMan_to:
                query = "t_o";
                table = "player_top_five_man_floor_units_details";
                break;
            case fiveMan_win:
                query = "w";
                table = "player_top_five_man_floor_units";
                break;
            case fiveMan_winPct:
                query = "win_per";
                table = "player_top_five_man_floor_units";
                break;
            default:
                return null;
        }
        String sql = "select year,pid,sharp,unit," + query + " from " + table + " where pid=? and year=? order by sharp ";
        FiveUnitPO fiveUnitPO = new FiveUnitPO();
        fiveUnitPO.unitsName = new String[10];
        fiveUnitPO.datas = new double[10];
        try {
            List<Object[]> lists = queryRunner.query(sql, new ArrayListHandler(), playerID, season);
            for (int i = 0; i < 10; i++) {
                Object[] objects = lists.get(i);
                fiveUnitPO.unitsName[i] = objects[3] == null ? null : String.valueOf(objects[3]);
                fiveUnitPO.datas[i] = objects[4] == null ? -1 : Double.parseDouble(String.valueOf(objects[4]));
            }
            return fiveUnitPO;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public double[][] position(int playerID, int season, AttriOption attri) {
        String query = "";
        switch (attri) {
            case position_ast:
                query = "ast";
                break;
            case position_blk:
                query = "blk";
                break;
            case position_efg:
                query = "efg_per";
                break;
            case position_fga:
                query = "fga";
                break;
            case position_fta:
                query = "fta";
                break;
            case position_ifg:
                query = "ifg";
                break;
            case position_per:
                query = "per";
                break;
            case position_pf:
                query = "pf";
                break;
            case position_pts:
                query = "pts";
                break;
            case position_reb:
                query = "reb";
                break;
            case position_to:
                query = "t_o";
                break;
            default:
                return null;
        }
        String sql = "select player_48_minute_production_by_positon.year,player_48_minute_production_by_positon.tid,player_48_minute_production_by_positon.position " + ",player_48_minute_production_by_positon." + query + " ,opponent_production_by_position." + query + " ,net_production_by_position." + query + " from player_48_minute_production_by_positon,opponent_counterpart_48_minute_production,net_production_by_position where " +
                "player_48_minute_production_by_positon.tid=? and player_48_minute_production_by_positon.year=? and player_48_minute_production_by_positon.tid=opponent_production_by_position.tid and team_production_by_position.year=opponent_production_by_position.year " +
                "and team_production_by_position.position=opponent_production_by_position.position and team_production_by_position.tid=net_production_by_position.tid and team_production_by_position.year=net_production_by_position.year" +
                " and team_production_by_position.position=net_production_by_position.position";
        System.out.println(sql);
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        double[][] res = new double[3][5];
        try {
            List<Object[]> lists = queryRunner.query(sql, new ArrayListHandler(), playerID, season);
            for (int i = 0; i < 3; i++) {
                ;
                for (int j = 3; j < lists.size(); j++) {
                    Object[] objects = lists.get(i);
                    res[i][j-3] = objects[j] == null ? -1 : Double.parseDouble(String.valueOf(objects[j]));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < res.length; i++) {
            double[] ress = res[i];
            for (int j = 0; j < ress.length; j++) {
                System.out.print(ress[j] + " ");
            }
            System.out.println();
        }
        return res;
    }

    private String getSort(String field) {
        switch (field) {
            case "id":
                return "id";
            case "name":
                return "playerName";
            case "team":
                return "Team";
            case "teamName":
                return "abbr";
            case "hight":
                return "height";
            case "weigth":
                return "weight";
            case "school":
                return "school";
            case "birth":
                return "birth";
            case "exp":
                return "exp";
            case "age":
                return "age";
            case "assist":
                return "assist";
            case "blockShot":
                return "BS";
            case "defend":
                return "defense";
            case "efficiency":
                return "effiency";
            case "fault":
                return "turnover";
            case "foul":
                return "foul";
            case "minute":
                return "timeOnCourt";
            case "numOfGame":
                return "matchNum";
            case "offend":
                return "offensive";
            case "penalty":
                return "FTP";
            case "point":
                return "score";
            case "rebound":
                return "rebound";
            case "shot":
                return "FGP";
            case "start":
                return "startSession";
            case "steal":
                return "steal";
            case "doubleTwo":
                return "doubleTwo";
            case "three":
                return "TPSP";
            case "avgAssist":
                return "Avgassist";
            case "avgBlockShot":
                return "AvgBS";
            case "avgDefend":
                return "Avgdefense";
            case "avgFault":
                return "Avgturnover";
            case "avgFoul":
                return "Avgfoul";
            case "avgMinute":
                return "avgMinute";
            case "avgOffend":
                return "Avgoffensive";
            case "avgPoint":
                return "Avgscore";
            case "avgRebound":
                return "Avgrebound";
            case "avgSteal":
                return "Avgsteal";
            case "assistEfficient":
                return "assistP";
            case "blockShotEfficient":
                return "BSP";
            case "defendReboundEfficient":
                return "DREB";
            case "faultEfficient":
                return "turnoverP";
            case "frequency":
                return "utiliation";
            case "gmSc":
                return "GmSc";
            case "offendReboundEfficient":
                return "OREB";
            case "realShot":
                return "TSP";
            case "reboundEfficient":
                return "reboundP";
            case "shotEfficient":
                return "OSE";
            case "stealEfficient":
                return "stealP";
        }
        return null;
    }

    @Override
    public ShotAnlsPO shootAnalysis(int playerID, int season) {
        String sql = "select * from player_stats_player_shot_selection where year=? and pid=?";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        System.out.println("---------");
        try {
            List<Object[]> lists =
                    queryRunner.query(sql, new ArrayListHandler(), season, playerID);
            if (lists.size() < 5){
                return null;
            }
            ShotAnlsPO shotAnlsPO = new ShotAnlsPO();
            shotAnlsPO.Att = new double[4];
            for (int i = 0; i < 4;i++){
                shotAnlsPO.Att[i] = lists.get(i)[4] == null?0:Double.parseDouble(String.valueOf(lists.get(i)[4]))/100;
            }
            shotAnlsPO.eFG = new double[4];
            for (int i = 0; i < 4;i++){
                shotAnlsPO.eFG[i] = lists.get(i)[5] == null?0:Double.parseDouble(String.valueOf(lists.get(i)[5]));
            }
            shotAnlsPO.Ast = new double[4];
            for (int i = 0; i < 4;i++){
                shotAnlsPO.Ast[i] = lists.get(i)[6] == null?0:Double.parseDouble(String.valueOf(lists.get(i)[6]))/100;
            }
            shotAnlsPO.Blk = new double[4];
            for (int i = 0; i < 4;i++){
                shotAnlsPO.Blk[i] = lists.get(i)[7] == null?0:Double.parseDouble(String.valueOf(lists.get(i)[7]))/100;
            }
            shotAnlsPO.Pts = new double[4];
            for (int i = 0; i < 4;i++){
                shotAnlsPO.Pts[i] = lists.get(i)[8] == null?0:Double.parseDouble(String.valueOf(lists.get(i)[8]));
            }
            return shotAnlsPO;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ShotAnlsPO shootAnalysis_clutch(int playerID, int season) {
        String sql = "select * from clutch_stats_player_shot_selection where year=? and pid=?";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        System.out.println("---------");
        try {
            List<Object[]> lists =
                    queryRunner.query(sql, new ArrayListHandler(), season, playerID);
            if (lists.size() < 5){
                return null;
            }
            ShotAnlsPO shotAnlsPO = new ShotAnlsPO();
            shotAnlsPO.Att = new double[4];
            for (int i = 0; i < 4;i++){
                shotAnlsPO.Att[i] = lists.get(i)[4] == null?0:Double.parseDouble(String.valueOf(lists.get(i)[4]))/100;
            }
            shotAnlsPO.eFG = new double[4];
            for (int i = 0; i < 4;i++){
                shotAnlsPO.eFG[i] = lists.get(i)[5] == null?0:Double.parseDouble(String.valueOf(lists.get(i)[5]));
            }
            shotAnlsPO.Ast = new double[4];
            for (int i = 0; i < 4;i++){
                shotAnlsPO.Ast[i] = lists.get(i)[6] == null?0:Double.parseDouble(String.valueOf(lists.get(i)[6]))/100;
            }
            shotAnlsPO.Blk = new double[4];
            for (int i = 0; i < 4;i++){
                shotAnlsPO.Blk[i] = lists.get(i)[7] == null?0:Double.parseDouble(String.valueOf(lists.get(i)[7]))/100;
            }
            shotAnlsPO.Pts = new double[4];
            for (int i = 0; i < 4;i++){
                shotAnlsPO.Pts[i] = lists.get(i)[8] == null?0:Double.parseDouble(String.valueOf(lists.get(i)[8]));
            }
            return shotAnlsPO;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ShotAnlsPO shotClockAnls(int playerID, int season) {
        String sql = "select * from player_stats_player_shot_clock_usage where year=? and pid=?";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        System.out.println("---------");
        try {
            List<Object[]> lists =
                    queryRunner.query(sql, new ArrayListHandler(), season, playerID);
            if (lists.size() < 5){
                return null;
            }

            for(int i = 0; i < lists.size();i++){
                Object[] objects = lists.get(i);
                for ( int j = 0 ; j < objects.length;j++){
                    System.out.print(objects[j]+" ");
                }
                System.out.println();
            }
            ShotAnlsPO shotAnlsPO = new ShotAnlsPO();
            shotAnlsPO.Att = new double[4];
            for (int i = 0; i < 4;i++){
                shotAnlsPO.Att[i] = lists.get(i)[4] == null?0:Double.parseDouble(String.valueOf(lists.get(i)[4]))/100;
            }
            shotAnlsPO.eFG = new double[4];
            for (int i = 0; i < 4;i++){
                shotAnlsPO.eFG[i] = lists.get(i)[5] == null?0:Double.parseDouble(String.valueOf(lists.get(i)[5]));
            }
            shotAnlsPO.Ast = new double[4];
            for (int i = 0; i < 4;i++){
                shotAnlsPO.Ast[i] = lists.get(i)[6] == null?0:Double.parseDouble(String.valueOf(lists.get(i)[6]))/100;
            }
            shotAnlsPO.Blk = new double[4];
            for (int i = 0; i < 4;i++){
                shotAnlsPO.Blk[i] = lists.get(i)[7] == null?0:Double.parseDouble(String.valueOf(lists.get(i)[7]))/100;
            }
            shotAnlsPO.Pts = new double[4];
            for (int i = 0; i < 4;i++){
                shotAnlsPO.Pts[i] = lists.get(i)[8] == null?0:Double.parseDouble(String.valueOf(lists.get(i)[8]));
            }
            return shotAnlsPO;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ShotAnlsPO shotClockAnls_clutch(int playerID, int season) {
        String sql = "select * from clutch_stats_player_shot_clock_usage where year=? and pid=?";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        System.out.println("---------");
        try {
            List<Object[]> lists =
                    queryRunner.query(sql, new ArrayListHandler(), season, playerID);
            if (lists.size() < 5){
                return null;
            }

            for(int i = 0; i < lists.size();i++){
                Object[] objects = lists.get(i);
                for ( int j = 0 ; j < objects.length;j++){
                    System.out.print(objects[j]+" ");
                }
                System.out.println();
            }
            ShotAnlsPO shotAnlsPO = new ShotAnlsPO();
            shotAnlsPO.Att = new double[4];
            for (int i = 0; i < 4;i++){
                shotAnlsPO.Att[i] = lists.get(i)[4] == null?0:Double.parseDouble(String.valueOf(lists.get(i)[4]))/100;
            }
            shotAnlsPO.eFG = new double[4];
            for (int i = 0; i < 4;i++){
                shotAnlsPO.eFG[i] = lists.get(i)[4] == null?0:Double.parseDouble(String.valueOf(lists.get(i)[5]));
            }
            shotAnlsPO.Ast = new double[4];
            for (int i = 0; i < 4;i++){
                shotAnlsPO.Ast[i] = lists.get(i)[4] == null?0:Double.parseDouble(String.valueOf(lists.get(i)[6]))/100;
            }
            shotAnlsPO.Blk = new double[4];
            for (int i = 0; i < 5;i++){
                shotAnlsPO.Blk[i] = lists.get(i)[4] == null?0:Double.parseDouble(String.valueOf(lists.get(i)[7]))/100;
            }
            shotAnlsPO.Pts = new double[4];
            for (int i = 0; i < 4;i++){
                shotAnlsPO.Pts[i] = lists.get(i)[4] == null?0:Double.parseDouble(String.valueOf(lists.get(i)[8]));
            }
            return shotAnlsPO;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public EvolutionPO envolution(ArrayList<String> names, String attri) {
        EvolutionPO evolutionPO = new EvolutionPO();
        evolutionPO.datas = new ArrayList<>();
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        try {
            for (int n = 0; n < names.size(); n++) {
                String sql_1 = "select pid from player where display_name_en = ?";
                Object object = queryRunner.query(sql_1, new ScalarHandler("pid"), names.get(n));
                if (object == null){
                    continue;
                }
                double[][] data = new double[6][];
                int tid = Integer.parseInt(String.valueOf(object));
                String sql = "select matchinfo.seasonf,count(a.mid) from (select pid,mid from playerscore where pid=?) as a,matchinfo where a.mid=matchinfo.mid group by matchinfo.seasonf";
                List<Object[]> list = queryRunner.query(sql, new ArrayListHandler(), tid);
                for (int i = 0;i<list.size();i++){
                    Object[] objects = list.get(i);
                    int season = Integer.parseInt(String.valueOf(objects[0]));
                    int count = Integer.parseInt(String.valueOf(objects[1]));
                    data[season-2009] = new double[count];
                }
                for(int i = 0; i< data.length;i++){
                    if (data[i] == null){
                        data[i] = new double[1];
                    }
                }
                sql = "select matchinfo.seasonf,a.attr from (select "+getSort(attri)+" as attr,mid from playerscore where pid = ?) as a,matchinfo where a.mid=matchinfo.mid order by matchinfo.seasonf";
                List<Object[]> list1 = queryRunner.query(sql, new ArrayListHandler(), tid);
                int[] points = new int[6];
                for (int i = 0;i<list.size();i++){
                    Object[] objects = list.get(i);
                    int season = Integer.parseInt(String.valueOf(objects[0]));
                    int count = Integer.parseInt(String.valueOf(objects[1]));
                    data[season-2009][points[season-2009]++] = count;
                }
                evolutionPO.datas.add(data);
            }
            return evolutionPO;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args){
//        double d = Double.parseDouble("+1.1");
//        System.out.println(d);
        new PlayerData().on_off(51, 2013);
    }
    @Override
    public OnOffPO on_off(int playerID, int season) {
        String sql = "select * from on_court_off_court_stats where year=? and pid=?";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        System.out.println("---------");
        try {
            List<Object[]> lists =
                    queryRunner.query(sql, new ArrayListHandler(), season, playerID);
            for(int i = 0; i < lists.size();i++){
                Object[] objects = lists.get(i);
                System.out.print(i+" ");
                for ( int j = 0 ; j < objects.length;j++){
                    System.out.print(objects[j]+" ");
                }
                System.out.println();
            }

            if (lists.size()<28){
                return null;
            }

            OnOffPO po = new OnOffPO();
            po.pPts_on = new double[3];
            po.pPts_off = new double[3];
            po.pPts_net = new double[3];
            for(int i=0;i<3;i++){
                po.pPts_on[i] = lists.get(i+1)[4]==null?0:Double.parseDouble(String.valueOf(lists.get(i+1)[4]));
                po.pPts_off[i] = lists.get(i+1)[5]==null?0:Double.parseDouble(String.valueOf(lists.get(i+1)[5]));
                po.pPts_net[i] = lists.get(i+1)[6]==null?0:Double.parseDouble(String.valueOf(lists.get(i+1)[6]));
            }
            po.Pts_on = new double[3];
            po.Pts_off = new double[3];
            po.Pts_net = new double[3];
            for(int i=0;i<3;i++){
                po.Pts_on[i] = lists.get(i+4)[4]==null?0:Double.parseDouble(String.valueOf(lists.get(i+4)[4]));
                po.Pts_off[i] = lists.get(i+4)[5]==null?0:Double.parseDouble(String.valueOf(lists.get(i+4)[5]));
                po.Pts_net[i] = lists.get(i+4)[6]==null?0:Double.parseDouble(String.valueOf(lists.get(i+4)[6]));
            }
            po.FG_on = new double[2];
            po.FG_off = new double[2];
            po.FG_net = new double[2];
            for(int i=0;i<2;i++){
                po.FG_on[i] = lists.get(i+7)[4]==null?0:Double.parseDouble(String.valueOf(lists.get(i+7)[4]).substring(0,String.valueOf(lists.get(i+7)[4]).length()-1));
                po.FG_off[i] = lists.get(i+7)[5]==null?0:Double.parseDouble(String.valueOf(lists.get(i+7)[5]).substring(0, String.valueOf(lists.get(i + 7)[5]).length() - 1));
                po.FG_net[i] = lists.get(i+7)[6]==null?0:Double.parseDouble(String.valueOf(lists.get(i+7)[6]).substring(0, String.valueOf(lists.get(i + 7)[6]).length() - 1));
            }
            po.Ast_on = new double[2];
            po.Ast_off = new double[2];
            po.Ast_net = new double[2];
            for(int i=0;i<2;i++){
                po.Ast_on[i] = lists.get(i+9)[4]==null?0:Double.parseDouble(String.valueOf(lists.get(i + 9)[4]).substring(0, String.valueOf(lists.get(i + 9)[4]).length() - 1));
                po.Ast_off[i] = lists.get(i+9)[5]==null?0:Double.parseDouble(String.valueOf(lists.get(i+9)[5]).substring(0, String.valueOf(lists.get(i + 9)[5]).length() - 1));
                po.Ast_net[i] = lists.get(i+9)[6]==null?0:Double.parseDouble(String.valueOf(lists.get(i+9)[6]).substring(0, String.valueOf(lists.get(i + 9)[6]).length() - 1));
            }
            po.Blk_on = new double[2];
            po.Blk_off = new double[2];
            po.Blk_net = new double[2];
            for(int i=0;i<2;i++){
                po.Blk_on[i] = lists.get(i+11)[4]==null?0:Double.parseDouble(String.valueOf(lists.get(i+11)[4]).substring(0, String.valueOf(lists.get(i + 11)[4]).length() - 1));
                po.Blk_off[i] = lists.get(i+11)[5]==null?0:Double.parseDouble(String.valueOf(lists.get(i+11)[5]).substring(0, String.valueOf(lists.get(i + 11)[5]).length() - 1));
                po.Blk_net[i] = lists.get(i+11)[6]==null?0:Double.parseDouble(String.valueOf(lists.get(i+11)[6]).substring(0, String.valueOf(lists.get(i + 11)[6]).length() - 1));
            }
            po.reb_on = new double[3];
            po.reb_off = new double[3];
            po.reb_net = new double[3];
            for(int i=0;i<3;i++){
                po.reb_on[i] = lists.get(i + 14)[4] == null ? 0 : Double.parseDouble(String.valueOf(lists.get(i + 14)[4]).substring(0, String.valueOf(lists.get(i + 14)[4]).length() - 1));
                po.reb_off[i] = lists.get(i + 14)[5] == null ? 0 : Double.parseDouble(String.valueOf(lists.get(i + 14)[5]).substring(0, String.valueOf(lists.get(i + 14)[5]).length() - 1));
                po.reb_net[i] = lists.get(i + 14)[6] == null ? 0 : Double.parseDouble(String.valueOf(lists.get(i + 14)[6]).substring(0, String.valueOf(lists.get(i + 14)[6]).length() - 1));
            }
            po.FTM_on = new double[4];
            po.FTM_off = new double[4];
            po.FTM_net = new double[4];
            for(int i=0;i<4;i++){
                po.FTM_on[i] = lists.get(i + 18)[4] == null ? 0 : Double.parseDouble(String.valueOf(lists.get(i + 18)[4]));
                po.FTM_off[i] = lists.get(i + 18)[5] == null ? 0 : Double.parseDouble(String.valueOf(lists.get(i + 18)[5]));
                po.FTM_net[i] = lists.get(i + 18)[6] == null ? 0 : Double.parseDouble(String.valueOf(lists.get(i + 18)[6]));
            }
            po.turnover_on = new double[3];
            po.turnover_off = new double[3];
            po.turnover_net = new double[3];
            for(int i=0;i<3;i++){
                po.FTM_on[i] = lists.get(i + 22)[4] == null ? 0 : Double.parseDouble(String.valueOf(lists.get(i + 22)[4]));
                po.FTM_off[i] = lists.get(i + 22)[5] == null ? 0 : Double.parseDouble(String.valueOf(lists.get(i + 22)[5]));
                po.FTM_net[i] = lists.get(i + 22)[6] == null ? 0 : Double.parseDouble(String.valueOf(lists.get(i + 22)[6]));
            }
            po.foul_on = new double[3];
            po.foul_off = new double[3];
            po.foul_net = new double[3];
            for(int i=0;i<3;i++){
                po.foul_on[i] = lists.get(i + 25)[4] == null ? 0 : Double.parseDouble(String.valueOf(lists.get(i + 25)[4]));
                po.foul_off[i] = lists.get(i + 25)[5] == null ? 0 : Double.parseDouble(String.valueOf(lists.get(i + 25)[5]));
                po.foul_net[i] = lists.get(i + 25)[6] == null ? 0 : Double.parseDouble(String.valueOf(lists.get(i + 25)[6]));
            }
            return po;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public double[] turnovers(int playerID, int season) {
        String sql = "select * from player_stats_player_turnovers_and_ball_handling where year=? and pid=?";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        try {
            Object[] objects = queryRunner.query(sql,new ArrayHandler(),season,playerID);
            return change_double(objects);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private double[] change_double(Object[] objects) {
        double[] ds = new double[objects.length];
        for(int i = 3; i < objects.length; i++){
            ds[i] = objects[i]==null?0:Double.parseDouble(String.valueOf(objects[i]));
        }
        return ds;
    }

    @Override
    public double[] turnovers_clutch(int playerID, int season) {
        String sql = "select * from clutch_stats_player_turnovers_and_ball_handling where year=? and pid=?";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        try {
            Object[] objects = queryRunner.query(sql,new ArrayHandler(),season,playerID);
            return change_double(objects);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public double[] passing(int playerID, int season) {
        String sql = "select * from player_stats_player_passing_stats where year=? and pid=?";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        try {
            Object[] objects = queryRunner.query(sql,new ArrayHandler(),season,playerID);
            return change_double(objects);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public double[] passing_clutch(int playerID, int season) {
        String sql = "select * from clutch_stats_player_passing_stats where year=? and pid=?";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        try {
            Object[] objects = queryRunner.query(sql,new ArrayHandler(),season,playerID);
            return change_double(objects);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public double[] shotBlock(int playerID, int season) {
        String sql = "select * from player_stats_player_shot_blocking where year=? and pid=?";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        try {
            Object[] objects = queryRunner.query(sql,new ArrayHandler(),season,playerID);
            return change_double(objects);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public double[] shotBlock_clutch(int playerID, int season) {
        String sql = "select * from clutch_stats_player_shot_blocking where year=? and pid=?";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        try {
            Object[] objects = queryRunner.query(sql,new ArrayHandler(),season,playerID);
            return change_double(objects);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public RegressionPO playerRegression(int playerID, int season) {
        String sql =  "select * from playerscore"+season + " as p where p.pid = ?";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        try {
            List<Object[]> list =
            queryRunner.query(sql,new ArrayListHandler(),playerID);
            int length = list.size();
            RegressionPO regressionPO = new RegressionPO();
            regressionPO.points = new double[length];
            regressionPO.x = new double[length][6];
            for (int i = 0; i < length; i++){
                Object[] objects = list.get(i);
                regressionPO.points[i] = objects[18]==null?0:Double.parseDouble(String.valueOf(objects[18]));
                regressionPO.x[i][0] = objects[12]==null?0:Double.parseDouble(String.valueOf(objects[12]));
                regressionPO.x[i][1] = objects[13]==null?0:Double.parseDouble(String.valueOf(objects[13]));
                regressionPO.x[i][2] = objects[14]==null?0:Double.parseDouble(String.valueOf(objects[14]));
                regressionPO.x[i][3] = objects[15]==null?0:Double.parseDouble(String.valueOf(objects[15]));
                regressionPO.x[i][4] = objects[16]==null?0:Double.parseDouble(String.valueOf(objects[16]));
                regressionPO.x[i][5] = objects[17]==null?0:Double.parseDouble(String.valueOf(objects[17]));
            }
            return regressionPO;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public PlayerPO findPlayerData(int playerId, int season) {
        String sql = "select * from playerpo" + season + " where pid=?";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        try {
            Object[] objects = queryRunner.query(sql, new ArrayHandler(),
                    playerId);
            PlayerPO playerPO = getPlayerPO(objects);
            return playerPO;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

    private PlayerPO getPlayerPO(Object[] objects) {
        PlayerPO playerPO = new PlayerPO();
        int i = 0;
        playerPO.id = objects[i++] == null ? -1 : Integer.parseInt(String.valueOf(objects[i - 1]));
        playerPO.timeOnCourt = objects[i++] == null ? -1 : Integer.parseInt(String.valueOf(objects[i - 1]));
        playerPO.startSession = objects[i++] == null ? 0 : Integer.parseInt(String.valueOf(objects[i - 1]));
        playerPO.matchNum = objects[i++] == null ? -1 : Integer.parseInt(String.valueOf(objects[i - 1]));
        playerPO.FGP = objects[i++] == null ? -1 : Double.parseDouble(String.valueOf(objects[i - 1]));
        playerPO.TPSP = objects[i++] == null ? -1 : Double.parseDouble(String.valueOf(objects[i - 1]));
        playerPO.FTP = objects[i++] == null ? -1 : Double.parseDouble(String.valueOf(objects[i - 1]));
        playerPO.BS = objects[i++] == null ? -1 : Integer.parseInt(String.valueOf(objects[i - 1]));
        playerPO.BSP = objects[i++] == null ? -1 : Double.parseDouble(String.valueOf(objects[i - 1]));
        playerPO.assist = objects[i++] == null ? -1 : Integer.parseInt(String.valueOf(objects[i - 1]));
        playerPO.assistP = objects[i++] == null ? -1 : Double.parseDouble(String.valueOf(objects[i - 1]));
        playerPO.rebound = objects[i++] == null ? -1 : Integer.parseInt(String.valueOf(objects[i - 1]));
        playerPO.reboundP = objects[i++] == null ? -1 : Double.parseDouble(String.valueOf(objects[i - 1]));
        playerPO.steal = objects[i++] == null ? -1 : Integer.parseInt(String.valueOf(objects[i - 1]));
        playerPO.stealP = objects[i++] == null ? -1 : Double.parseDouble(String.valueOf(objects[i - 1]));
        playerPO.turnover = objects[i++] == null ? -1 : Integer.parseInt(String.valueOf(objects[i - 1]));
        playerPO.turnoverP = objects[i++] == null ? -1 : Double.parseDouble(String.valueOf(objects[i - 1]));
        playerPO.foul = objects[i++] == null ? -1 : Integer.parseInt(String.valueOf(objects[i - 1]));
        playerPO.score = objects[i++] == null ? -1 : Integer.parseInt(String.valueOf(objects[i - 1]));
        playerPO.offensive = objects[i++] == null ? -1 : Integer.parseInt(String.valueOf(objects[i - 1]));
        playerPO.defense = objects[i++] == null ? -1 : Integer.parseInt(String.valueOf(objects[i - 1]));
        playerPO.effiency = objects[i++] == null ? -1 : Integer.parseInt(String.valueOf(objects[i - 1]));
        playerPO.GmSc = objects[i++] == null ? -1 : Double.parseDouble(String.valueOf(objects[i - 1]));
        playerPO.TSP = objects[i++] == null ? -1 : Double.parseDouble(String.valueOf(objects[i - 1]));
        playerPO.OSE = objects[i++] == null ? -1 : Double.parseDouble(String.valueOf(objects[i - 1]));
        playerPO.OREB = objects[i++] == null ? -1 : Double.parseDouble(String.valueOf(objects[i - 1]));
        playerPO.DREB = objects[i++] == null ? -1 : Double.parseDouble(String.valueOf(objects[i - 1]));
        playerPO.utiliation = objects[i++] == null ? -1 : Double.parseDouble(String.valueOf(objects[i - 1]));
        playerPO.AvgBS = objects[i++] == null ? -1 : Double.parseDouble(String.valueOf(objects[i - 1]));
        playerPO.Avgassist = objects[i++] == null ? -1 : Double.parseDouble(String.valueOf(objects[i - 1]));
        playerPO.Avgrebound = objects[i++] == null ? -1 : Double.parseDouble(String.valueOf(objects[i - 1]));
        playerPO.Avgsteal = objects[i++] == null ? -1 : Double.parseDouble(String.valueOf(objects[i - 1]));
        playerPO.Avgturnover = objects[i++] == null ? -1 : Double.parseDouble(String.valueOf(objects[i - 1]));
        playerPO.Avgfoul = objects[i++] == null ? -1 : Double.parseDouble(String.valueOf(objects[i - 1]));
        playerPO.Avgscore = objects[i++] == null ? -1 : Double.parseDouble(String.valueOf(objects[i - 1]));
        playerPO.Avgoffensive = objects[i++] == null ? -1 : Double.parseDouble(String.valueOf(objects[i - 1]));
        playerPO.Avgdefense = objects[i++] == null ? -1 : Double.parseDouble(String.valueOf(objects[i - 1]));
        playerPO.doubleTwo = objects[i++] == null ? 0 : Integer.parseInt(String.valueOf(objects[i - 1]));
        playerPO.avgMinute = objects[i++] == null ? -1 : Double.parseDouble(String.valueOf(objects[i - 1]));
        playerPO.photo = objects[i++] == null ? null : String.valueOf(objects[i - 1]);
        playerPO.playerName = objects[i++] == null ? null : String.valueOf(objects[i - 1]);
        playerPO.height = objects[i++] == null ? -1 : Integer.parseInt(String.valueOf(objects[i - 1]));
        playerPO.weight = objects[i++] == null ? -1 : Integer.parseInt(String.valueOf(objects[i - 1]));
        playerPO.age = objects[i++] == null ? -1 : Integer.parseInt(String.valueOf(objects[i - 1]));
        playerPO.exp = objects[i++] == null ? -1 : Integer.parseInt(String.valueOf(objects[i - 1]));
        playerPO.birth = objects[i++] == null ? null : String.valueOf(objects[i - 1]);
        playerPO.school = objects[i++] == null ? null : String.valueOf(objects[i - 1]);
        playerPO.league = (objects[i++] == null && String.valueOf(objects[i - 1]).length() > 0) ? '\0' : String.valueOf(objects[i - 1]).charAt(0);
        playerPO.division = objects[i++] == null ? null : String.valueOf(objects[i - 1]);
        playerPO.Team = objects[i++] == null ? null : String.valueOf(objects[i - 1]);
        playerPO.abbr = objects[i++] == null ? null : String.valueOf(objects[i - 1]);
        playerPO.number = (int)(Math.random()*50);
        return playerPO;
    }

    @Override
    public PlayerPO findPlayerData(String name, int season) {
        String sql = "select * from playerpo" + season + " where display_name_en=?";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        try {
            Object[] objects = queryRunner.query(sql, new ArrayHandler(),
                    name);
            PlayerPO playerPO = getPlayerPO(objects);
            return playerPO;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public ArrayList<PlayerPO> findPlayers(String msg) {
        String sql = "select pid,display_name_en,playerid from player where display_name_en like ? or display_name_ch like ?";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        String condition = "%" + msg + "%";
        ArrayList<PlayerPO> arrayList = new ArrayList<>();
        try {
            List<Object[]> lists = queryRunner.query(sql, new ArrayListHandler(),
                    condition, condition);
            for (int i = 0; i < lists.size(); i++) {
                Object[] objects = lists.get(i);
                PlayerPO playerVO = new PlayerPO();
                playerVO.id = objects[0] == null ? -1 : Integer.parseInt(String.valueOf(objects[0]));
                playerVO.playerName = objects[1] == null ? null : String.valueOf(objects[1]);
                playerVO.photo = objects[2] == null ? null : String.valueOf(objects[2]);
                arrayList.add(playerVO);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return arrayList;
    }

    @Override
    public ArrayList<PlayerPO> sortPlayer(String order, int season) {
        String sql = "select * from playerpo" + season + " order by " + order;
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        ArrayList<PlayerPO> arrayList = new ArrayList<>();
        try {
            List<Object[]> lists = queryRunner.query(sql, new ArrayListHandler());
            for (int i = 0; i < lists.size(); i++) {
                Object[] objects = lists.get(i);
                PlayerPO playerPO = getPlayerPO(objects);
                arrayList.add(playerPO);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return arrayList;
    }

    @Override
    public ArrayList<PlayerPO> filter(String order, ArrayList<String> position, ArrayList<String> league, int numS, int numE, int season) {
        String where = "";
        if (position.size() > 0){
            String where1 = "";
            for ( int i=0;i<position.size()-1;i++){
                String temp = "%"+position.get(i)+"%";
                where1 = " player.position like " +temp+" or";
            }
            String temp = "%"+position.get(position.size()-1)+"%";
            where1 = " player.position like '" +temp+"'";
            where1 = "( " + where1 + " )";
            where = "and " + where1;
        }
        if (league.size() > 0){
            String where1 = "";
            for ( int i=0;i<league.size()-1;i++){
                String temp = league.get(i);
                where1 = " playerpo.league='" +temp+"' or";
            }
            String temp = league.get(league.size()-1);
            where1 = " playerpo.league='" +temp+"'";
            where1 = "( " + where1 + " )";
            where = "and " + where1;
        }
        String sql = "select * from playerpo" + season + " as playerpo,player where player.pid=playerpo.pid "+where+" and playerpo.age >= ? and playerpo.age<= ? order by " + order;
        System.out.println("-------------------"+sql+" "+numS+" "+numE+" "+season);

        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        ArrayList<PlayerPO> arrayList = new ArrayList<>();
        try {
            List<Object[]> lists = queryRunner.query(sql, new ArrayListHandler(),numS,numE);
            for (int i = 0; i < lists.size(); i++) {
                Object[] objects = lists.get(i);
                PlayerPO playerPO = getPlayerPO(objects);
                arrayList.add(playerPO);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return arrayList;
    }

    @Override
    public ArrayList<PlayerPO> getTeamMemberList(int teamID, int season) {
        String sql = "select * from playerpo" + season + " as playerpo,team where playerpo.team_name_en=team.team_name_en and team.tid=?";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        ArrayList<PlayerPO> arrayList = new ArrayList<>();
        try {
            List<Object[]> lists = queryRunner.query(sql, new ArrayListHandler(),teamID);
            for (int i = 0; i < lists.size(); i++) {
                Object[] objects = lists.get(i);
                PlayerPO playerPO = getPlayerPO(objects);
                arrayList.add(playerPO);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return arrayList;
    }

    @Override
    public double[] getFreeThrowShooting(int playerID, int season, boolean isClutch) {
        String sql = "select * from clutch_stats_player_free_throw_shooting_and_foul_drawing where year=? and pid=?";
        if (!isClutch){
            sql = "select * from player_stats_player_free_throw_shooting_and_foul_drawing where year=? and pid=?";
        }
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        try {
            Object[] objects =
                queryRunner.query(sql,new ArrayHandler(),season,playerID);
            return change_double(objects);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public double[] getReBound(int playerID, int season, boolean isClutch) {
        String sql = "select * from clutch_stats_player_rebounding where year=? and pid=?";
        if (!isClutch){
            sql = "select * from player_stats_player_rebounding where year=? and pid=?";
        }
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        try {
            Object[] objects =
                    queryRunner.query(sql,new ArrayHandler(),season,playerID);
            return change_double(objects);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String[] getFloorStats(int teamID, int season) {
        String sql = "select * from player_stats_player_floor_time_statistics where year=? and pid=?";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        try {
            Object[] objects =
                    queryRunner.query(sql,new ArrayHandler(),season,teamID);
            return change_String(objects);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String[] change_String(Object[] objects) {
        String[] res = new String[objects.length -3];
        for(int i = 3;i<objects.length;i++){
            res[i-3]=objects[i]==null?null:String.valueOf(objects[i]);
        }
        return res;
    }

    @Override
    public String[] getScoring(int teamID, int season) {
        String sql = "select * from player_stats_player_scoring where year=? and pid=?";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        try {
            Object[] objects =
                    queryRunner.query(sql,new ArrayHandler(),season,teamID);
            return change_String(objects);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<HotZoneVO> getHotZone(int playerId, boolean isPerformance, int matchType, int season) {
        if (isPerformance){
            String sql = "select * from hot_zone where seasonf=? and match_type=? and pid=?";
            QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
            ArrayList<HotZoneVO> arrayList = new ArrayList<>();
            try {
                List<Object[]> list =
                        queryRunner.query(sql,new ArrayListHandler(),season,matchType,playerId);
                for (int i = 0; i < list.size();i++){
                    Object[] objects = list.get(i);
                    HotZoneVO vo = new HotZoneVO();
                    int j = 5;
                    vo.zone4N=objects[j++]==null?0:Integer.parseInt(String.valueOf(objects[j-1]));
                    vo.zone4D=objects[j++]==null?0:Integer.parseInt(String.valueOf(objects[j-1]));
                    vo.zone10N=objects[j++]==null?0:Integer.parseInt(String.valueOf(objects[j-1]));
                    vo.zone10D=objects[j++]==null?0:Integer.parseInt(String.valueOf(objects[j-1]));
                    vo.zone13N=objects[j++]==null?0:Integer.parseInt(String.valueOf(objects[j-1]));
                    vo.zone13D=objects[j++]==null?0:Integer.parseInt(String.valueOf(objects[j-1]));
                    vo.zone8N=objects[j++]==null?0:Integer.parseInt(String.valueOf(objects[j-1]));
                    vo.zone8D=objects[j++]==null?0:Integer.parseInt(String.valueOf(objects[j-1]));
                    vo.zone2N=objects[j++]==null?0:Integer.parseInt(String.valueOf(objects[j-1]));
                    vo.zone2D=objects[j++]==null?0:Integer.parseInt(String.valueOf(objects[j-1]));
                    vo.zone1N=objects[j++]==null?0:Integer.parseInt(String.valueOf(objects[j-1]));
                    vo.zone1D=objects[j++]==null?0:Integer.parseInt(String.valueOf(objects[j-1]));
                    vo.zone3N=objects[j++]==null?0:Integer.parseInt(String.valueOf(objects[j-1]));
                    vo.zone3D=objects[j++]==null?0:Integer.parseInt(String.valueOf(objects[j-1]));
                    vo.zone9N=objects[j++]==null?0:Integer.parseInt(String.valueOf(objects[j-1]));
                    vo.zone9D=objects[j++]==null?0:Integer.parseInt(String.valueOf(objects[j-1]));
                    vo.zone12N=objects[j++]==null?0:Integer.parseInt(String.valueOf(objects[j-1]));
                    vo.zone12D=objects[j++]==null?0:Integer.parseInt(String.valueOf(objects[j-1]));
                    vo.zone6N=objects[j++]==null?0:Integer.parseInt(String.valueOf(objects[j-1]));
                    vo.zone6D=objects[j++]==null?0:Integer.parseInt(String.valueOf(objects[j-1]));
                    vo.zone7N=objects[j++]==null?0:Integer.parseInt(String.valueOf(objects[j-1]));
                    vo.zone7D=objects[j++]==null?0:Integer.parseInt(String.valueOf(objects[j-1]));
                    vo.zone5N=objects[j++]==null?0:Integer.parseInt(String.valueOf(objects[j-1]));
                    vo.zone5D=objects[j++]==null?0:Integer.parseInt(String.valueOf(objects[j-1]));
                    vo.zone11N=objects[j++]==null?0:Integer.parseInt(String.valueOf(objects[j-1]));
                    vo.zone11D=objects[j++]==null?0:Integer.parseInt(String.valueOf(objects[j-1]));
                    vo.zone14N=objects[j++]==null?0:Integer.parseInt(String.valueOf(objects[j-1]));
                    vo.zone14D=objects[j++]==null?0:Integer.parseInt(String.valueOf(objects[j-1]));
                    arrayList.add(vo);
                }
                return arrayList;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else {
            String sql = "select c08Made , c1624Made , c24PlusMade , c816Made , l1624Made , l24PlusMade , l816Made , lc1624Made  , lc24PlusMade  , r1624Made  , r24PlusMade  , r816Made  , rc1624Made  , rc24PlusMade ,c08Made + c1624Made + c24PlusMade + c816Made + l1624Made + l24PlusMade + l816Made + lc1624Made  + lc24PlusMade  + r1624Made  + r24PlusMade  + r816Made  + rc1624Made  + rc24PlusMade from hot_zone where seasonf=? and match_type=? and pid=?";
            QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
            ArrayList<HotZoneVO> arrayList = new ArrayList<>();
            try {
                List<Object[]> list =
                        queryRunner.query(sql,new ArrayListHandler(),season,matchType,playerId);
                for (int i = 0; i < list.size();i++){
                    Object[] objects = list.get(i);
                    HotZoneVO vo = new HotZoneVO();
                    int j = 0;
                    vo.zone4N=objects[14]==null?0:Integer.parseInt(String.valueOf(objects[14]));
                    vo.zone4D=objects[j++]==null?0:Integer.parseInt(String.valueOf(objects[j-1]));
                    vo.zone10N=vo.zone4N;
                    vo.zone10D=objects[j++]==null?0:Integer.parseInt(String.valueOf(objects[j-1]));
                    vo.zone13N=vo.zone4N;
                    vo.zone13D=objects[j++]==null?0:Integer.parseInt(String.valueOf(objects[j-1]));
                    vo.zone8N=vo.zone4N;
                    vo.zone8D=objects[j++]==null?0:Integer.parseInt(String.valueOf(objects[j-1]));
                    vo.zone2N=vo.zone4N;
                    vo.zone2D=objects[j++]==null?0:Integer.parseInt(String.valueOf(objects[j-1]));
                    vo.zone1N=vo.zone4N;
                    vo.zone1D=objects[j++]==null?0:Integer.parseInt(String.valueOf(objects[j-1]));
                    vo.zone3N=vo.zone4N;
                    vo.zone3D=objects[j++]==null?0:Integer.parseInt(String.valueOf(objects[j-1]));
                    vo.zone9N=vo.zone4N;
                    vo.zone9D=objects[j++]==null?0:Integer.parseInt(String.valueOf(objects[j-1]));
                    vo.zone12N=vo.zone4N;
                    vo.zone12D=objects[j++]==null?0:Integer.parseInt(String.valueOf(objects[j-1]));
                    vo.zone6N=vo.zone4N;
                    vo.zone6D=objects[j++]==null?0:Integer.parseInt(String.valueOf(objects[j-1]));
                    vo.zone7N=vo.zone4N;
                    vo.zone7D=objects[j++]==null?0:Integer.parseInt(String.valueOf(objects[j-1]));
                    vo.zone5N=vo.zone4N;
                    vo.zone5D=objects[j++]==null?0:Integer.parseInt(String.valueOf(objects[j-1]));
                    vo.zone11N=vo.zone4N;
                    vo.zone11D=objects[j++]==null?0:Integer.parseInt(String.valueOf(objects[j-1]));
                    vo.zone14N=vo.zone4N;
                    vo.zone14D=objects[j++]==null?0:Integer.parseInt(String.valueOf(objects[j-1]));
                    arrayList.add(vo);
                }
                return arrayList;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}
