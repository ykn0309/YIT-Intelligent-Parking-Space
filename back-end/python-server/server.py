from flask import Flask, request, jsonify
from flask_cors import CORS
import os
from datetime import datetime
from detect_plate import run
from database import find_user

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
        userid = user_info['userid']
        parkid = user_info['parkid']
    else:
        print('no this user')


    startTime = datetime.now().strftime("%Y-%m-%d %H:%M:%S")

    return jsonify({'message': '文件上传成功', 'filename': file.filename, 'userid' : userid, 'parkid' : parkid, 'carid': carid, 'startTime' : startTime}), 200

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

    endTime = datetime.now().strftime("%Y-%m-%d %H:%M:%S")

    return jsonify({'message': '文件上传成功', 'filename': file.filename, 'carid': carid, 'endTime' : endTime}), 200

if __name__ == '__main__':
    app.run(debug=True)
