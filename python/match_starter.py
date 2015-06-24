__author__ = 'chenghao'

from my_urllib import get_response
from mysql_connector import get_connector
from bs4 import BeautifulSoup
import re
import datetime
from match_info import get_game_info


def print_url(match_date, format_match_date):
    url_path = 'http://www.nba.com/gameline/' + match_date + '/'
    print url_path
    html = get_response(url_path)
    if html:
        soup = BeautifulSoup(html)
        game_set = set()
        for tag in soup.find_all(href=re.compile("^/games/" + match_date + "/")):
            if tag.string == 'recap':
                game_set.add(tag['href'])
        for tag in game_set:
            get_game_info(tag, format_match_date)
            pass


sql = 'select max(matchtime) from matchinfo'
conn = get_connector()
cursor = conn.cursor()
cursor.execute(sql)
values = cursor.fetchall()
date = values[0][0]
step = datetime.date(2014, 1, 2) - datetime.date(2014, 1, 1)
today = datetime.date.today()

while date < today:
    print_url(date.strftime("%Y%m%d"), date.isoformat())
    date = date + step


