**前后端交互API**



**1、获取当前停车场内的所有车辆信息的列表**

**请求**

* 方法：GET
* URL：/api/getAllCars
* 请求参数：
  * 无

**响应**

```json
{
	"data":[
        {
            "parkId":"车位ID（1）",
            "userId":"用户ID（123）",
            "carId":"车牌号（鄂A12345）",
            "startTime":"开始时间（2024-10-01 14:00:56）",
            "occupied": "是否被占用（true/false）"
        },
        {···},
        {···},
        ···
    ]
}
```

**2、获取停车场地图信息**

**请求**

* 方法：GET
* URL：/api/getCarsMap
* 请求参数：
  * 无

**响应**

```json
{
	"data":[
        {
            "parkId":"车位ID（1）",
            "userId":"用户ID（123）",
            "carId":"车牌号（鄂A12345）",
            "startTime":"开始时间（2024-10-01 14:00:56）",
            "occupied": "是否被占用（true/false）"
        },
        {···},
        {···},
        ···
    ]
}
```


**3、登记车辆**

**请求**

* 方法：POST
* URL：/api/addCar
* 请求参数：
  * [
        {
            "parkId":"车位ID（1）",
            "userId":"用户ID（123）",
            "carId":"车牌号（鄂A12345）",
            "startTime":"开始时间（2024-10-01 14:00:56）",
            "occupied": "是否被占用（true/false）"
        },
        {···},
        {···},
        ···
    ]

**响应**

null

**4、登出车辆**

**请求**

* 方法：GET
* URL：/api/addCar
* 请求参数：
  * 无

**响应**

null

**3、存储注册信息**

**请求**

- 方法：POST

- URL：/api/postUserInformation

- 请求参数

  无

**响应**

```json
{
    "userInformation":
        {
          "username": "圆角",
          "password": "114514",
        }
}
```



**4、验证登录是否成功(不区分管理员和普通用户的版本，需要后端判断是否存在已注册用户并返回结果)**

**请求**

- 方法：GET

- URL：/api/getLoginResualt

- 请求参数

  {
  
  "loginResualt": "登录结果（0/1）",
  
  }

**响应**

```json
{
    "username": "圆角",
	"password": "114514",
}
```





