__author__ = 'chenghao'

from my_urllib import get_response
import json
import time
import datetime
from mysql_connector import get_connector
import mysql.connector

conn = get_connector()
while True:
    url_path = 'http://china.nba.com/wap/static/data/season/schedule_7.json'
    html = get_response(url_path)
    if html:
        dates = json.loads(html)['payload']['dates']
        for date in dates:
            utcMillis = date['utcMillis']
            utcMillis = utcMillis.strip()
            utcMillis = int(utcMillis)
            game_date = datetime.date.fromtimestamp(utcMillis / 1000).isoformat()
            games = date['games']
            for game in games:
                status = game['boxscore']['status']
                status = status.strip()
                status = int(status)
                if status != 1:
                    sql = 'update next_match set status = %s'
                    cursor = conn.cursor()
                    cursor.execute(sql, (status,))
                    cursor.close()
                    conn.commit()
                    continue
                game_utcMillis = game['profile']['utcMillis']
                game_utcMillis = game_utcMillis.strip()
                game_utcMillis = int(game_utcMillis)
                game_date_time = datetime.datetime.fromtimestamp(game_utcMillis / 1000).strftime('%Y-%m-%d %H:%M:%S')
                gameId = game['profile']['gameId']
                awayTeamId = game['profile']['awayTeamId']
                homeTeamId = game['profile']['homeTeamId']
                sql = 'insert into next_match VALUES (%s,%s,%s,%s,%s)'
                try:
                    cursor = conn.cursor()
                    cursor.execute(sql, (gameId, game_date_time, awayTeamId, homeTeamId, status))
                    cursor.close()
                    conn.commit()
                except mysql.connector.errors.IntegrityError, e:
                    continue
    time.sleep(10)
conn.close()