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
	"carsList":[
        {
            "parkId":"车位ID（1）",
            "userId":"用户ID（123）",
            "carId":"车牌号（鄂A12345）",
            "startTime":"开始时间（2024-10-01 14:00:56）"
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
	"carsMapList":[
        {
            "parkId":"车位ID（1）",
            "occupied":"是否被占用（true/false）",
            "userId":"用户ID（123）",
            "carId":"车牌号（鄂A12345）"
        },
        {···},
        {···},
        ···
    ]
}
```



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
          "password": "114514"，
        }
}
```



**4、获取所有用户信息(不区分普通用户和管理员版本)**

**请求**

- 方法：GET

- URL：/api/getAllUsers

- 请求参数

  无

**响应**

```json
{
    "usersList":[
        {
          "username": "圆角",
          "password": "114514"，
        },
    	{···},
		{···},
		···
    ]
}
```





