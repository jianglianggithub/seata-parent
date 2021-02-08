#!/usr/bin/env python3
#  -*- coding: UTF-8 -*-

import http.client
import sys

headers = {
    'content-type': "application/x-www-form-urlencoded"
}

hasError = False
for line in open('../config.txt'):
    pair = line.split('=')
    if len(pair) < 2:
        continue
    print (line),


    conn = http.client.HTTPConnection("127.0.0.1:8848")

    url_postfix = '/nacos/v1/cs/configs?dataId={}&group=SEATA_GROUP&content={}'.format(
        str(pair[0]),
        str(   line[line.index('=')+1:]   )
        )
    conn.request("POST", url_postfix, headers=headers)
    res = conn.getresponse()
    data = res.read()
    if data.decode("utf-8") != "true":
        hasError = True
if hasError:
    print ("init nacos config fail.")
else:
    print ("init nacos config finished, please start seata-server.")
