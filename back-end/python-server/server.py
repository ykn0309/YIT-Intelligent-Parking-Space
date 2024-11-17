from flask import Flask, request, jsonify
from flask_cors import CORS
import os
from datetime import datetime
import json
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

    wallet = get_wallet(userid)['wallet']
    if wallet < cost:
        return jsonify({'message': 'nomoney', 'userid': userid, 'carid': carid, 'parkId' : parkId, 'startTime' : startTime ,'endTime' : endTime, 'parkingTime' : minutes, 'cost' : cost}), 200

    return jsonify({'message': 'success', 'userid': userid, 'carid': carid, 'parkId' : parkId, 'startTime' : startTime ,'endTime' : endTime, 'parkingTime' : minutes, 'cost' : cost}), 200

@app.route('/api/getPath', methods=['GET'])
def get_path():
    pageid = request.args.get('pageid')
    blob_data = load_parkid_path_from_database(pageid)
    decoded_data = blob_data['data'].decode('utf-8')
    decoded_data = json.loads(decoded_data)
    parkId = decoded_data['parkId']
    road = decoded_data['road']
    return jsonify({'parkId' : parkId, 'road': road})

if __name__ == '__main__':
    app.run(debug=True)
