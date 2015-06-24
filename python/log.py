# -*- coding: utf-8 -*-

__author__ = 'chenghao'
import time


def log(msg):
    log_file = open('log.txt', 'a+')
    log_file.write(get_current_time() + '----------' + msg + '\n')
    log_file.flush()
    log_file.close()


def get_current_time():
    ISOTIMEFORMAT = '%Y-%m-%d %X'
    return time.strftime(ISOTIMEFORMAT, time.localtime(time.time()))