from flask import Flask, request, jsonify
from flask_cors import CORS
import os
from datetime import datetime
from detect_plate import run
from database import *

app = Flask(__name__)
CORS(app)

# 设置文件上传的保存路径
UPLOAD_FOLDER = 'uploads'
os.makedirs(UPLOAD_FOLDER, exist_ok=True)

@app.route('/api/entrance', methods=['POST'])
def park_entrance():
    # 检查是否有文件在请求中
    if 'file' not in request.files:
        return jsonify({'error': '没有文件被上传'}), 400

    file = request.files['file']

    # 检查文件是否存在
    if file.filename == '':
        return jsonify({'error': '没有选择文件'}), 400

    # 保存文件
    file_path = os.path.join(UPLOAD_FOLDER, file.filename)
    file.save(file_path)

    print(file_path)
    carid = run(file_path)
    print(carid)

    user_info = find_user(carid)
    if user_info != 0:
        userid = user_info['user_id']
    else:
        print('no this user')
        return jsonify({'message' : 'no this user'}), 200

    parkid_path = get_parkid_path()
    if parkid_path == False:
        return jsonify({'message' : 'get_parkid_path failed'}), 200
    else:
        parkId = parkid_path['parkId']
        pageid = save_parkid_path_to_database(parkid_path)

    startTime = datetime.now().strftime("%Y-%m-%d %H:%M:%S")

    return jsonify({'message': 'success', 'userid' : userid, 'carid': carid, 'startTime' : startTime, 'parkId' : parkId, 'pageId' : pageid}), 200

@app.route('/api/exit', methods=['POST'])
def park_exit():
    # 检查是否有文件在请求中
    if 'file' not in request.files:
        return jsonify({'error': '没有文件被上传'}), 400

    file = request.files['file']

    # 检查文件是否存在
    if file.filename == '':
        return jsonify({'error': '没有选择文件'}), 400

    # 保存文件
    file_path = os.path.join(UPLOAD_FOLDER, file.filename)
    file.save(file_path)

    print(file_path)
    carid = run(file_path)
    print(carid)

    user_info = find_occupied(carid)
    if user_info != 0:
        userid = user_info['user_id']
        startTime = user_info['start_time']
        parkId = user_info['park_id']
    else:
        print('no this user')

    endTime = datetime.now().strftime("%Y-%m-%d %H:%M:%S")

    start_time = datetime.strptime(startTime, "%Y-%m-%d %H:%M:%S")
    end_time = datetime.strptime(endTime, "%Y-%m-%d %H:%M:%S")
    parking_time = end_time - start_time
    minutes = int(parking_time.total_seconds() / 60)
    cost = minutes * 0.1

    return jsonify({'message': '文件上传成功', 'userid': userid, 'carid': carid, 'parkId' : parkId, 'startTime' : startTime ,'endTime' : endTime, 'parkingTime' : minutes, 'cost' : cost}), 200

if __name__ == '__main__':
    app.run(debug=True)
