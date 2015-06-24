# -*- coding: utf-8 -*-
import os
import urllib2

__author__ = 'chenghao'
import types
from bs4 import BeautifulSoup
from my_urllib import get_response
from log import log
from player_info import get_player_name
from mysql_connector import get_connector
import re


def get_season(match_date):
    month = int(match_date[5: 7])
    year = int(match_date[0: 4])
    if month >= 10:
        return str(year) + "-" + str(year + 1)
    else:
        return str(year - 1) + "-" + str(year)


def get_tr_score(player_node):
    if len(player_node.select('td')) > 0:
        tds = player_node.select('td')
        length = len(tds)
        player_name = str(tds[0].string).strip()
        if player_name == 'Total':
            return 'Total'
        if type(tds[0]) != types.NoneType:
            if type(tds[0].find('a')) != types.NoneType:
                if tds[0].find('a').has_attr('href'):
                    player_name_temp = get_player_name(tds[0].find('a')['href'])
                    if player_name_temp:
                        player_name = player_name_temp
                pass
        else:
            pass
        player_box_score = '' + player_name + ';'
        index = 1
        if length > index:
            if tds[index].has_attr('class'):
                if tds[index]['class'][0].strip() == 'nbaGIdnp':
                    return player_box_score
                else:
                    if type(tds[index].string) != types.NoneType:
                        player_box_score += tds[index].string + ';'
                        index += 1
                    else:
                        player_box_score += 'null;'
                        index += 1
            else:
                pass
        else:
            player_box_score += 'null;'
            index += 1

        for i in range(2, 17, 1):
            if length > index:
                if type(tds[index].string) != types.NoneType:
                    player_box_score += tds[index].string + ';'
                    index += 1
                else:
                    player_box_score += 'null;'
                    index += 1
            else:
                player_box_score += 'null;'
                index += 1
        return player_box_score


def format_time(time):
    print time
    com = re.compile(r"[0-9]{1,2}:[0-9]{1,2}:[0-9]{1,2}");
    match = com.match(time)
    if not match:
        return None
    else:
        ss = time.split(':')
        s = int(ss[2])
        min = int(ss[1])
        h = int(ss[0])
        if s == 60:
            s = s - 60
            min = min + 1

        if min >= 60:
            min = min - 60
            h = h + 1

        return '%02d' % h + ":" + '%02d' % min + ":" + '%02d' % s


def get_player_score(player_point):
    ss = player_point[0:-1].split(';')
    print len(ss),ss
    res = []
    for i in range(0, len(ss), 1):
        if i == 1:
            continue
        elif i == 2:
            res.append(format_time('00:'+ss[i].strip()))
            pass
        elif i == 3:
            print ss[i]
            nums = ss[i].strip().split('-')
            if len(nums) < 2:
                res.append(None)
                res.append(None)
            else:
                res.append(nums[0].strip())
                res.append(nums[1].strip())
        elif i == 4:
            nums = ss[i].strip().split('-')
            if len(nums) < 2:
                res.append(None)
                res.append(None)
            else:
                res.append(nums[0].strip())
                res.append(nums[1].strip())
        elif i == 5:
            nums = ss[i].strip().split('-')
            if len(nums) < 2:
                res.append(None)
                res.append(None)
            else:
                res.append(nums[0].strip())
                res.append(nums[1].strip())
        else:
            temp = ss[i].strip()
            if temp == '':
                res.append(None)
            else:
                res.append(temp)
    for i in range(len(ss), 17, 1):
        res.append(None)
    return res


def get_season(match_date):
    ss = match_date.split('-')
    year = int(ss[0])
    month = int(ss[1])
    if month > 9:
        return year
    else:
        return year - 1


def get_box_score(html, url_path, match_date):
    conn = get_connector()
    soup = BeautifulSoup(html)
    away_team = ''
    home_team = ''
    away_team_point = []
    home_team_point = []
    nbaGIGmeLve = soup.find(id='nbaGIGmeLve')
    if type(nbaGIGmeLve) != types.NoneType:
        nbaGITmeQtr = soup.find(id='nbaGITmeQtr')
        if type(nbaGITmeQtr) != types.NoneType:
            table = nbaGITmeQtr.find('table')
            if type(table) != types.NoneType:
                if len(table.select('tr')) == 2:
                    away_team = table.select('tr')[0].td.string
                    away_team = unicode(away_team).strip()
                    home_team = table.select('tr')[1].td.string
                    home_team = unicode(home_team).strip()
                    sql = 'select * from matchinfo,team a,team b where matchtime=%s and a.abbr=%s and b.abbr=%s and matchinfo.teamf=a.tid and matchinfo.teaml=b.tid'
                    cursor = conn.cursor()
                    cursor.execute(sql, (match_date, away_team, home_team))
                    values = cursor.fetchall()
                    print values
                    if len(values) != 0:
                        return
            else:
                log(url_path + 'CANNOT FOUND TEAM INFO')
        else:
            log(url_path + 'CANNOT FOUND TEAM INFO')
        nbaGIQtrScrs = soup.find(id='nbaGIQtrScrs')
        if type(nbaGIQtrScrs) != types.NoneType:
            table = nbaGIQtrScrs.find('table')
            if type(table) != types.NoneType:
                if len(table.select('tr')) == 3:
                    td_score = table.select('tr')[0].select('td')
                    for td in td_score:
                        point = td.string
                        away_team_point.append(point)
                    td_score = table.select('tr')[2].select('td')
                    for td in td_score:
                        point = td.string
                        home_team_point.append(point)
            else:
                log(url_path + 'CANNOT FOUND TEAM INFO')
        else:
            log(url_path + 'CANNOT FOUND TEAM INFO')
    else:
        log(url_path + 'CANNOT FOUND TEAM INFO')
    nbaGIboxscore = soup.find(id='nbaGIboxscore')
    away_player_point = []
    home_player_point = []
    if type(nbaGIboxscore) != types.NoneType:
        nodes = nbaGIboxscore.find_all(id='nbaGITeamStats')
        if len(nodes) == 2:
            node = nodes[0]
            print away_team
            print away_team_point
            playerNodes = node.select('tr')
            for player_node in playerNodes:
                if player_node.attrs.has_key('class'):
                    if player_node['class'][0] == 'odd' or player_node['class'][0] == 'even':
                        tr_score = get_tr_score(player_node)
                        if tr_score != 'Total':
                            away_player_point.append(tr_score)
            node = nodes[1]
            print home_team
            print home_team_point
            playerNodes = node.select('tr')
            for player_node in playerNodes:
                if player_node.attrs.has_key('class'):
                    if player_node['class'][0] == 'odd' or player_node['class'][0] == 'even':
                        tr_score = get_tr_score(player_node)
                        if tr_score != 'Total':
                            home_player_point.append(tr_score)
        else:
            log(url_path + 'CANNOT FOUND TWO TEAM')
            return
    else:
        log(url_path + 'NOT FOUND BOXSCORE')
        return
    cursor = conn.cursor()
    sql = 'select tid from team where abbr=%s'
    cursor.execute(sql, (away_team,))
    teamf = cursor.fetchall()[0][0]
    cursor.execute(sql, (home_team,))
    teaml = cursor.fetchall()[0][0]
    sql = 'insert into matchinfo VALUES (NULL ,%s, %s, %s, %s,%s,%s,%s)'
    season = get_season(match_date)
    print season, teamf, teaml
    data1 = (
        season, season + 1, match_date, teamf, teaml, str(away_team_point[-1]).strip(), str(home_team_point[-1]).strip())
    cursor.close()
    cursor = conn.cursor()
    cursor.execute(sql, data1)
    sql = 'SELECT LAST_INSERT_ID()'
    cursor.close()
    cursor = conn.cursor()
    cursor.execute(sql)
    values = cursor.fetchall()
    mid = int(values[0][0])
    sql = 'insert into matchscore VALUES (%s,%s,%s,%s)'
    data_scores = []
    for i in range(0, len(away_team_point) - 1, 1):
        data_scores.append((mid, i + 1, str(away_team_point[i]).strip(), str(home_team_point[i]).strip()))
    cursor.close()
    cursor = conn.cursor()
    cursor.executemany(sql, data_scores)
    cursor.close()
    # write file
    # if not (os.path.exists('./data/' + get_season(match_date))):
    # os.mkdir('./data/' + get_season(match_date))
    # file_name = match_date[5:10] + '_' + away_team + '-' + home_team
    # data_file = open('./data/' + get_season(match_date) + '/' + file_name, 'w')
    # line1 = match_date[5:10] + ';' + away_team + '-' + home_team + ';' + away_team_point[-1] + '-' + home_team_point[
    #     -1] + ';'
    # data_file.write(line1 + '\n')
    # line2 = ''
    # length = len(away_team_point)
    # for i in range(0, length - 1, 1):
    #     line2 += away_team_point[i] + '-' + home_team_point[i] + ';'
    # data_file.write(line2 + '\n')
    # data_file.write(away_team + '\n')
    players = []
    for player_point in away_player_point:
        print player_point
        sql = 'select pid from player where display_name_en=%s order by birthday'
        player = get_player_score(player_point)
        cursor = conn.cursor()
        cursor.execute(sql,(player[0],))
        cursor.close()
        pid = cursor.fetchall()
        if len(pid) == 0:
            sql = 'insert into player (pid,display_name_en) VALUES (NULL,%s)'
            cursor = conn.cursor()
            cursor.execute(sql,(player[0],))
            cursor.close()
            sql = 'SELECT LAST_INSERT_ID()'
            cursor = conn.cursor()
            cursor.execute(sql)
            pid = cursor.fetchall()
            cursor.close()
        pid = pid[0][0]
        player[0] = pid[0][0]
        data = []
        data.append(mid)
        data.append(teamf)
        data.append(pid)
        for i in range(0,len(player),1):
            data.append(player[i])
        players.append(player)

    sql = 'insert into playerscore VALUES (%s,)'
        # data_file.write(home_team + '\n')
        # for player_point in home_player_point:
        #     data_file.write(player_point.encode('UTF-8') + '' + '\n')
        # data_file.flush()
        # data_file.close()


def get_game_info(url_path, match_date):
    url_path = 'http://www.nba.com' + url_path
    print url_path
    html = get_response(url_path)
    get_box_score(html, url_path, match_date)
    pass


    # get_game_info('http://www.nba.com/games/20111220/DETCLE/gameinfo.html', '2011-12-30')