#!python3
# -*- coding:utf-8 -*-
# @file i_get_appver.py
# @author root(chenliang@cslc.com.cn)
# @date 2018/03/28 15:24:04
# @version $Revision$
# @brief
# 自动发布过程中标准的获取应用版本号的py接口文件。由各自产品实现。
# 文件名称不可修改

import xml.etree.ElementTree as et

if __name__ == '__main__':
    #myroot = et.parse('pom.xml').getroot()
    #appver = myroot.find('{http://maven.apache.org/POM/4.0.0}version').text
    #print(appver,end ='')
    print('1.0.0',end ='')
