package data;

import data.database.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.SQLException;

/**
 * Created by chenghao on 15/6/14.
 */
public class Stub {
    public static void stub(){
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "insert into live values(?,?,?,?,?,?,?,?,?)";
        int away = 0;
        for (int i = 0; i < 4;i++){
            for (int j = 0; j < 10;j++){
                try {
                    queryRunner.update(sql,1,i+1,"11:11",away++,0,"程昊三分！！！！","1610612739",0,j+1);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                queryRunner.update(sql,1,i+1,"11:11",away++,0,"本场比赛结束","1610612739",0,11);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args){
        stub();
    }
}
