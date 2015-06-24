__author__ = 'chenghao'

from my_urllib import get_response
import json
import datetime
from mysql_connector import get_connector
import time
from multiprocessing import Process

games = set()
def game_live(conn,gameid):
    request_time = 0
    clean_flag = True
    init = False
    sql = 'update next_match set status = 2 where gameid = %s'
    cursor = conn.cursor()
    cursor.execute(sql,(gameid,))
    cursor.close()
    conn.commit()
    conn.close()
    while True:
        url_path = 'http://china.nba.com/wap/static/data/game/snapshot_'+str(gameid)+'.json'
        html = get_response(url_path)
        if not html :
            request_time += 1
            clean_flag = True
            if request_time < 30:
                continue
            else:
                break
        html = html.decode("utf-8-sig")
        payload = json.loads(html)
        payload = payload['payload']
        status = payload['boxscore']['status']
        status = int(status)
        if not init:
            if clean_flag:
                sql = 'delete from live where gameid = %s'
                cursor = conn.cursor()
                cursor.execute(sql,(gameid,))
                cursor.close()
                conn.commit()
                clean_flag = False
            period = payload['boxscore']['period']
            period = int(period)
            for i in range(1,period,1):
                data = []
                url_path = 'http://china.nba.com/wap/static/data/game/playbyplay_'+gameid+'_'+unicode(i)+'.json'
                html = get_response(url_path)
                if not html:
                    request_time += 1
                    clean_flag = True
                    if request_time < 10:
                        continue
                    else:
                        break
                html = html.decode("utf-8-sig")
                playbyplay = json.loads(html)
                payload = playbyplay['payload']
                events = payload['playByPlays'][0]['events']
                for j in range(len(events) - 1,-1,-1):
                    event = events[j]
                    awayScore = unicode(event['awayScore'])
                    homeScore = unicode(event['homeScore'])
                    gameClock = unicode(event['gameClock'])
                    description = unicode(event['description'])
                    playerId = unicode(event['playerId'])
                    teamId = unicode(event['teamId'])
                    m = (gameid,i,gameClock,awayScore,homeScore,description,teamId,playerId,len(events) - j)
                    print m
                    data.append(m)
                cursor = conn.cursor()
                sql = 'insert into live VALUES (%s,%s,%s,%s,%s,%s,%s,%s,%s)'
                cursor.executemany(sql,data)
                cursor.close()
                conn.commit()
            if clean_flag:
                continue
            init = True

        loop_time404 = 0
        while True:
            data = []
            url_path = 'http://china.nba.com/wap/static/data/game/playbyplay_'+gameid+'_'+unicode(period)+'.json'
            html = get_response(url_path)
            if not html:
                loop_time404 += 1
                if loop_time404 < 1000:
                    continue
                else:
                    break
            html = html.decode("utf-8-sig")
            playbyplay = json.loads(html)
            payload = playbyplay['payload']
            events = payload['playByPlays'][0]['events']
            sql = 'select * from live where gameid = %s and period = %s'
            cursor = conn.cursor()
            cursor.execute(sql,(gameid,period,))
            values = cursor.fetchall()
            for i in range(len(events) - len(values) -1,-1,-1):
                event = events[i]
                awayScore = unicode(event['awayScore'])
                homeScore = unicode(event['homeScore'])
                gameClock = unicode(event['gameClock'])
                description = unicode(event['description'])
                playerId = unicode(event['playerId'])
                teamId = unicode(event['teamId'])
                m = (gameid,period,gameClock,awayScore,homeScore,description,teamId,playerId,len(events)-i)
                print m
                data.append(m)
            cursor = conn.cursor()
            sql = 'insert into live VALUES (%s,%s,%s,%s,%s,%s,%s,%s,%s)'
            cursor.executemany(sql,data)
            cursor.close()
            conn.commit()
            now_period = payload['boxscore']['period']
            now_period = int(now_period)
            if now_period > period:
                period = now_period
                continue
            if status == 3:
                sql = 'update next_match set status = 3 where gameid = %s'
                cursor = conn.cursor()
                cursor.execute(sql,(gameid,))
                cursor.close()
                conn.commit()
                conn.close()
                return
    conn.close()

while True:
    conn = get_connector()
    sql = 'select * from next_match where status!=3'
    cursor = conn.cursor()
    cursor.execute(sql)
    values = cursor.fetchall()
    cursor.close()
    conn.close()
    for value in values:
        game_date_time = value[1]
        now_date_time = datetime.datetime.now()
        if now_date_time > game_date_time:
            if value[0] in games:
                pass
            else:
                p = Process(target=game_live, args=(get_connector(),value[0]))
                p.start()
                games.add(value[0])
    time.sleep(10)
