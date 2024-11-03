import pymysql
import requests
import time
import json

# 配置数据库连接   
def get_db_connection():
    connection = pymysql.connect(
        host='localhost',
        user='root',
        password='123456',
        database='db_example',
        cursorclass=pymysql.cursors.DictCursor
    )
    return connection

def find_user(carid):
    connection = get_db_connection()
    try:
        with connection.cursor() as cursor:
            sql = f"select * from users where car_id = '{carid}';"
            cursor.execute(sql)
            result = cursor.fetchone()
            if result:
                return result
            else:
                return 0
    finally:
        connection.close()

def find_occupied(carid):
    connection = get_db_connection()
    try:
        with connection.cursor() as cursor:
            sql = f"select * from occupiedparks where car_id = '{carid}';"
            cursor.execute(sql)
            result = cursor.fetchone()
            if result:
                return result
            else:
                return 0
    finally:
        connection.close()

def get_parkid_path():
    url = "http://localhost:8080/api/getRoad"
    params = {'xlabel' : 0, 'ylabel' : 0}

    response  = requests.get(url, params=params)
    if response.status_code == 200:
        data = response.json()
        return data
    else:
        print(f'请求失败，状态码：{response.status_code}')
        return False

def save_parkid_path_to_database(data):
    connection = get_db_connection()
    try:
        with connection.cursor() as cursor:
            id = generate_timestamp_id()
            json_str = json.dumps(data)
            blob_data = json_str.encode('utf-8')
            sql = """ insert into dynamic_map values (%s, %s)"""
            cursor.execute(sql, (id, blob_data))
            connection.commit()
    finally:
        connection.close()

def generate_timestamp_id():
    return str(int(time.time() * 1000))  # 毫秒级时间戳