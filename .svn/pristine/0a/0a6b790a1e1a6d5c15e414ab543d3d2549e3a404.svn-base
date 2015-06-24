__author__ = 'chenghao'

import urllib2
from log import log
import zlib
import socket


class RedirctHandler(urllib2.HTTPRedirectHandler):
    def __init__(self):
        pass

    def http_error_301(self, req, fp, code, msg, headers):
        return

    def http_error_302(self, req, fp, code, msg, headers):
        return


def get_response(url_path):
    url = url_path
    try:
        request = urllib2.Request(url)
        debug_handler = urllib2.HTTPHandler(debuglevel=0)
        opener = urllib2.build_opener(debug_handler, RedirctHandler())
        response = opener.open(request, timeout=180)
        print response.code
        html = response.read()
        gzipped = response.headers.get('Content-Encoding')
        if gzipped:
            print "gzipped"
            html = zlib.decompress(html, 16 + zlib.MAX_WBITS)
        return html
    except urllib2.HTTPError, e:
        print e
        log(str(e.code) + ' ' + str(e.reason) + ' ' + url)
        return False
    except socket.timeout, e:
        print "socket timeout"
        print e
        log(url)
        return False
