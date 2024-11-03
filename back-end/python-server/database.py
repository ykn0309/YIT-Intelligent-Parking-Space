import pymysql

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
