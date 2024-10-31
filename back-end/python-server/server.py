from flask import Flask, request, jsonify
from flask_cors import CORS
import os
from detect_plate import run

app = Flask(__name__)
CORS(app)

# 设置文件上传的保存路径
UPLOAD_FOLDER = 'uploads'
os.makedirs(UPLOAD_FOLDER, exist_ok=True)

@app.route('/upload', methods=['POST'])
def upload_file():
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

    return jsonify({'message': '文件上传成功', 'filename': file.filename, 'carid': carid}), 200

if __name__ == '__main__':
    app.run(debug=True)
