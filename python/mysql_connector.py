__author__ = 'chenghao'

import mysql.connector


def get_connector():
    conn = mysql.connector.connect(
        user='root',
        password='',
        database='nba',
        use_unicode=True
    )
    return conn
