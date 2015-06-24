package data.update;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.sql.DataSource;

import data.database.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

public class MatchInfo {
	public static void main(String[] args) {
		DataSource dataSource = JDBCUtils.getDataSource();
		QueryRunner queryRunner = new QueryRunner();
		try {
			Connection connection = JDBCUtils.getConnection();
			File file = new File("/Users/chenghao/Documents/nba_spyder/data");
			File[] files = file.listFiles();
			for (int m = 0; m < files.length; m++) {
				if (files[m].isDirectory()) {
					String years = files[m].getName();
					String[] ss = years.split("-");
					int seasonFrom = Integer.parseInt(ss[0].trim());
					int seasonTo = Integer.parseInt(ss[1].trim());
					handle(queryRunner,connection,files[m],seasonFrom,seasonTo);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void handle(QueryRunner queryRunner, Connection connection,File file, int seasonFrom, int seasonTo){
		File[] files = file.listFiles();
		for (int i = 0; i < files.length; i++){
			if (!files[i].isDirectory()){
				insert(queryRunner,connection,files[i],seasonFrom,seasonTo);
			}
		}
	}

	public static void insert(QueryRunner queryRunner, Connection connection,
			File file, int seasonFrom, int seasonTo) {
		try {
			connection.setAutoCommit(false);
			String title = file.getName();
			if(title.equals(".DS_Store")){
				return;
			}
			String input = null;
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream(file),"UTF-8"));
			input = br.readLine();
			Object[] matchinfo = getMatchInfo(queryRunner,connection,title, input,seasonFrom,seasonTo);
			String sqlInsert1 = "insert into matchinfo values(null,?,?,?,?,?,?,?);";
			queryRunner.update(connection, sqlInsert1, matchinfo);
			Object scalar = queryRunner.query(connection,
					"SELECT LAST_INSERT_ID()", new ScalarHandler());
			String mid = String.valueOf(scalar);

			String sqlInsert2 = "insert into matchscore values(?,?,?,?)";

			input = br.readLine();
			String[] scores = input.split(";");
			for (int i = 0; i < scores.length; i++) {
				Object[] scoresinfo = getMatchScore(mid, i + 1, scores[i]);
				queryRunner.update(connection, sqlInsert2, scoresinfo);
			}

			String sqlInsert3 = "insert into playerscore values"
					+ " (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			String tid = null;
			int serialId = 0;
			while ((input = br.readLine()) != null) {
				if (input.trim().length() <= 3) {
					tid = String.valueOf(getTid(queryRunner,connection,input.trim(),seasonFrom));
					serialId = 1;
					continue;
				}

				Object[] playerInfos = getPlayerInfo(mid, tid, input,serialId);
				if (playerInfos == null){
					continue;
				}
				Object pid = queryRunner.query(connection,
						"select pid from player where display_name_en = ?",
						new ScalarHandler(), String.valueOf(playerInfos[2]).trim());
				if (pid == null) {
					System.out.println("发现了:" + playerInfos[2]);
					queryRunner.update(connection,
							"insert into player (pid,display_name_en) values(null,?)",
							playerInfos[2]);
					playerInfos[2] = queryRunner.query(connection,
							"SELECT LAST_INSERT_ID()", new ScalarHandler());
					System.out.println("new pid:" + playerInfos[2]);
				} else {
					playerInfos[2] = pid;
				}
				queryRunner.update(connection, sqlInsert3, playerInfos);
				serialId++;
			}

			connection.commit();
			System.out.println(seasonFrom+" "+seasonTo + " "+file.getName().trim());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
//			try {
////				connection.rollback();
//			} catch (SQLException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
		}

	}

	public static Object[] getMatchInfo(QueryRunner queryRunner, Connection connection,String title, String s, int seasonFrom, int seasonTo) throws SQLException {
		String[] ss = s.split(";");
		String[] titles = title.split("_");
		Object[] objects = new Object[7];
//		String[] seasons = titles[0].split("-");
		objects[0] = seasonFrom;
		objects[1] = seasonTo;
		String[] teams = titles[1].split("-");
		objects[3] = getTid(queryRunner,connection,teams[0],seasonFrom);

		objects[4] = getTid(queryRunner,connection,teams[1],seasonFrom);

		String[] scores = ss[2].split("-");
		objects[5] = scores[0];
		objects[6] = scores[1];
		String[] times = titles[0].split("-");
		int month = Integer.parseInt(times[0]);
		if (month < 9) {
			objects[2] = seasonTo + "-" + titles[0];
		} else {
			objects[2] = seasonFrom + "-" + titles[0];
		}
		if(getTid(queryRunner,connection,teams[0],seasonFrom) == -1){
			printArray2(objects);
		}
		if(getTid(queryRunner,connection,teams[1],seasonFrom) == -1){
			printArray2(objects);
		}
		return objects;
	}

	public static Object[] getMatchScore(String mid, int index, String score) {
		Object[] objects = new Object[4];
		objects[0] = mid;
		objects[1] = index;
		String[] scores = score.split("-");
		objects[2] = scores[0];
		objects[3] = scores[1];
		return objects;
	}

	public static Object[] getPlayerInfo(String mid, String tid, String info,int serialId) {
		String[] infos = info.split(";");
		Object[] objects = null;
		if (infos.length <= 2){
			return null;
		}

		if (infos.length <= 16){
			return null;
		}

		objects = new Object[22];
		objects[0] = mid;
		objects[1] = tid;
		objects[2] = infos[0];
		objects[3] = infos[2];
		String[] FGM = infos[3].split("-");
		if(FGM.length < 2){
			FGM = new String[]{"0", "0"};
		}
		objects[4] = FGM[0].trim();
		objects[5] = FGM[1].trim();
		String[] TPM = infos[4].split("-");
		if(TPM.length < 2){
			TPM = new String[]{"0", "0"};
		}
		objects[6] = TPM[0].trim();
		objects[7] = TPM[1].trim();
		String[] FTM = infos[5].split("-");
		if(FTM.length < 2){
			FTM = new String[]{"0", "0"};
		}
		objects[8] = FTM[0].trim();
		objects[9] = FTM[1].trim();
		objects[10] = infos[7].trim();
		objects[11] = infos[8].trim();
		objects[12] = infos[9].trim();
		objects[13] = infos[10].trim();
		objects[14] = infos[12].trim();
		objects[15] = infos[14].trim();
		objects[16] = infos[13].trim();
		objects[17] = infos[11].trim();
		objects[18] = infos[16].trim();
		String inPlaceTime = null;
		inPlaceTime = "00:" + infos[2];
		objects[3] = format(inPlaceTime);
		objects[19] = serialId;
		objects[20] = infos[6].trim();
		if (String.valueOf(objects[20]).trim().equals("-")){
			objects[20] = null;
		}
		objects[21] = infos[15].trim();
		for (int i = 0; i < objects.length;i++){
			if (String.valueOf(objects[i]).trim().equals("null")){
				objects[i] = null;
			}
		}
		return objects;
	}

	public static String format(String time) {
		Pattern pattern = Pattern.compile("[0-9]{1,2}:[0-9]{1,2}:[0-9]{1,2}");
		Matcher matcher = pattern.matcher(time);
		boolean b = matcher.matches();
		if (b) {
			String[] times = time.split(":");
			int s = Integer.parseInt(times[2]);
			int min = Integer.parseInt(times[1]);
			int h = Integer.parseInt(times[0]);
			if (s == 60) {
				s = s - 60;
				min = min + 1;
			}

			if (min >= 60) {
				min = min - 60;
				h = h + 1;
			}

			return "" + h + ":" + min + ":" + s;
		}
		return null;
	}


	public static int getTid(QueryRunner queryRunner, Connection connection,String abbr,int year) throws SQLException {
		String sql = "select tid from team where abbr = ?";
		Object res = queryRunner.query(connection,sql, new ScalarHandler("tid"),abbr);
		if (res == null){
			sql = "select tid from team_history where abbr = ? and year_from <= ? and year_to >= ?";
			res = queryRunner.query(connection,sql, new ScalarHandler("tid"),abbr,year,year);
			if(res == null){
				println("NOT FOUND " + abbr + " " + year);
				sql = "insert into team(tid,abbr) values (null,?)";
				queryRunner.update(connection,sql,abbr);
				res = queryRunner.query(connection,
						"SELECT LAST_INSERT_ID()", new ScalarHandler());
				return Integer.parseInt(String.valueOf(res).trim());
			}
			return Integer.parseInt(String.valueOf(res).trim());
		}else {

		}
		return Integer.parseInt(String.valueOf(res).trim());
	}
	public static BufferedWriter bw;
	private static void println(String s) {
		if (bw == null){
			try {
				bw = new BufferedWriter(new OutputStreamWriter( new FileOutputStream("src/log.txt")));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

		}
		try {
			bw.write(s);
			bw.newLine();
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void printArray1(Object[] objects){
		if (objects.length > 0){
			for (int i = 0; i < objects.length - 1; i++){
				System.out.print(objects[i]+" ");
			}
			System.out.println(objects[objects.length - 1]);
		}
	}

	public static void printArray2(Object[] objects){

		if (objects.length > 0){
			try {
				for (int i = 0; i < objects.length - 1; i++){
					bw.write(objects[i]+" ");
				}
				bw.write(objects[objects.length - 1] + "");
				bw.newLine();
				bw.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
}
