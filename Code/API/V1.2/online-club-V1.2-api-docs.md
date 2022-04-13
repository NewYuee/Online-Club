> This documentation is generated by [JApiDocs](https://japidocs.agilestudio.cn/).
---
# ActiveController
## 动态发布-一般说说

**

**请求URL**

active/short/insertOne `POST` 

**请求参数**

参数名|类型|必须|描述
--:|:--:|:--:|:--
inputStr|string|否|内容

**返回结果**

```json
{
	"code":"int",
	"msg":"string",
	"data":{}
}
```
## 动态发布-文章

**

**请求URL**

active/article/insertOne `POST` 

**请求参数**

参数名|类型|必须|描述
--:|:--:|:--:|:--
inputStr|string|否|内容
title|string|是|标题

**返回结果**

```json
{
	"code":"int",
	"msg":"string",
	"data":{}
}
```
## 根据资源id对活动进行点赞

**

**请求URL**

active/like/{sourceId} `POST` 

**请求参数**

参数名|类型|必须|描述
--:|:--:|:--:|:--
sourceId|int|否|活动id

**返回结果**

```json
{
	"code":"int",
	"msg":"string",
	"data":{}
}
```
## 根据资源id对活动取消点赞

**

**请求URL**

active/unlike/{sourceId} `POST` 

**请求参数**

参数名|类型|必须|描述
--:|:--:|:--:|:--
sourceId|int|否|活动id

**返回结果**

```json
{
	"code":"int",
	"msg":"string",
	"data":{}
}
```
## 根据活动id获取点赞数，以及是否点赞

**

**请求URL**

active/getLikeNum/{sourceId} `GET` `POST` 

**请求参数**

参数名|类型|必须|描述
--:|:--:|:--:|:--
sourceId|string|否|活动id

**返回结果**

```json
{
	"code":"int",
	"msg":"string",
	"data":{}
}
```
## 根据id删除动态

**

**请求URL**

active/delete/{id} `GET` `POST` 


**返回结果**

```json
{
	"code":"int",
	"msg":"string",
	"data":{}
}
```
## 根据活动id删除活动及其关联数据

**

**请求URL**

active/admin/deleteActive `POST` 

**请求参数**

参数名|类型|必须|描述
--:|:--:|:--:|:--
aid|int|否|活动id

**返回结果**

```json
{
	"code":"int",
	"msg":"string",
	"data":{}
}
```
## 获取全部活动信息

**

**请求URL**

active/getAll `GET` `POST` 

**请求参数**

参数名|类型|必须|描述
--:|:--:|:--:|:--
page|int|否|页码
limit|int|否|分页大小

**返回结果**

```json
{
	"code":"int",
	"msg":"string",
	"data":[{
		"a_id":"int",
		"a_type":"string",
		"u_id":"int",
		"u_name":"string",
		"updateTime":"date",
		"omitContent":"string",
		"comment_count":"int"
	}],
	"count":"int"
}
```
# ApplicationController
## 获取用户所有申请

**

**请求URL**

/application/getAll `GET` `POST` 

**请求参数**

参数名|类型|必须|描述
--:|:--:|:--:|:--
uid|string|否|用户id

**返回结果**

```json
{
	"code":"int",
	"msg":"string",
	"data":{}
}
```
## 根据申请单id删除申请

**

**请求URL**

/application/delete/{appId} `GET` `POST` 

**请求参数**

参数名|类型|必须|描述
--:|:--:|:--:|:--
appId|string|否|申请单id

**返回结果**

```json
{
	"code":"int",
	"msg":"string",
	"data":{}
}
```
## getApplication

**

**请求URL**

/application/get/{appId} `GET` `POST` 


**返回结果**

```json
{
	"code":"int",
	"msg":"string",
	"data":{}
}
```
# ChatController
## 根据用户id和聊天对象id获取最近聊天记录，默认5条

**

**请求URL**

/get5ChatMsgs/{uId}/{toUid} `GET` `POST` 

**请求参数**

参数名|类型|必须|描述
--:|:--:|:--:|:--
uId|string|否|当前用户id
toUid|string|否|聊天对象id

**返回结果**

```json
{
	"code":"int",
	"msg":"string",
	"data":{}
}
```
## 根据用户id和聊天对象id获取全部聊天记录

**

**请求URL**

/getAllMsgs/{uId}/{toUid} `GET` `POST` 

**请求参数**

参数名|类型|必须|描述
--:|:--:|:--:|:--
uId|string|否|当前用户id
toUid|string|否|聊天对象id

**返回结果**

```json
{
	"code":"int",
	"msg":"string",
	"data":{}
}
```
## 根据用户id获取未读消息，由未读消息列表解析

**

**请求URL**

/getNotReadMsgs/{uid} `GET` `POST` 

**请求参数**

参数名|类型|必须|描述
--:|:--:|:--:|:--
uid|string|否|当前用户id

**返回结果**

```json
{
	"code":"int",
	"msg":"string",
	"data":{}
}
```
# ClubContactController
## 根据社团id获取社团联系人

**

**请求URL**

/club/getContact `GET` `POST` 

**请求参数**

参数名|类型|必须|描述
--:|:--:|:--:|:--
clubId|int|否|

**返回结果**

```json
{
	"code":"int",
	"msg":"string",
	"data":[{
		"id":"int",
		"clubId":"int",
		"realName":"string",
		"telNum":"string",
		"email":"string",
		"departmentInClub":"string",
		"positionInClub":"string",
		"other":"string"
	}],
	"count":"int"
}
```
## 获取全部社团联系人

**

**请求URL**

/club/getContact/all `GET` `POST` 

**请求参数**

参数名|类型|必须|描述
--:|:--:|:--:|:--
page|int|否|
limit|int|否|
keyword|string|否|

**返回结果**

```json
{
	"code":"int",
	"msg":"string",
	"data":[{
		"id":"int",
		"clubId":"int",
		"realName":"string",
		"telNum":"string",
		"email":"string",
		"departmentInClub":"string",
		"positionInClub":"string",
		"other":"string"
	}],
	"count":"int"
}
```
# ClubController
## 根据用户id查找其加入的社团数

**

**请求URL**

/club/getCount/{uid} `GET` `POST` 

**请求参数**

参数名|类型|必须|描述
--:|:--:|:--:|:--
uid|string|否|用户id

**返回结果**

```json
{
	"code":"int",
	"msg":"string",
	"data":{}
}
```
## 得到社团列表

**

**请求URL**

/club/getAll `GET` `POST` 

**请求参数**

参数名|类型|必须|描述
--:|:--:|:--:|:--
page|int|否|页数
limit|int|否|分页大小
keyword|string|否|搜索的关键词

**返回结果**

```json
{
	"code":"int",
	"msg":"string",
	"data":[{
		"clubId":"int",
		"clubName":"string",
		"activeCount":"int",
		"memberships":"int"
	}],
	"count":"int"
}
```
## 更新社团信息

**

**请求URL**

/club/updateInfo `POST` 

**请求参数**

参数名|类型|必须|描述
--:|:--:|:--:|:--
username|string|否|社团名
email|string|否|邮箱地址
profile|string|否|简介

**返回结果**

```json
{
	"code":"int",
	"msg":"string",
	"data":{}
}
```
## 获取管理端首页统计数据

**

**请求URL**

/getData/club/index `GET` `POST` 


**返回结果**

```json
{
	"code":"int",
	"msg":"string",
	"data":{}
}
```
## 根据当前session获取事务申请

**

**请求URL**

/club/getApply/all `GET` `POST` 

**请求参数**

参数名|类型|必须|描述
--:|:--:|:--:|:--
page|int|否|页码
limit|int|否|分页大小

**返回结果**

```json
{
	"code":"int",
	"msg":"string",
	"data":[{
		"appId":"int",
		"type":"string",
		"name":"string",
		"gender":"string",
		"telNum":"string",
		"detailInfo":"string",
		"reason":"string",
		"status":"string",
		"dealResult":"string",
		"time":"date"
	}],
	"count":"int"
}
```
## 获取社团成员

**

**请求URL**

/club/getMember `GET` `POST` 

**请求参数**

参数名|类型|必须|描述
--:|:--:|:--:|:--
page|int|否|页码
limit|int|否|页大小
keyword|string|否|用户名关键词

**返回结果**

```json
{
	"code":"int",
	"msg":"string",
	"data":[{
		"id":"int",
		"clubId":"int",
		"userId":"int",
		"memName":"string",
		"gender":"string",
		"memTelNum":"string",
		"memDetailInfo":"string",
		"memJoinTime":"date"
	}],
	"count":"int"
}
```
## 根据id删除社团成员

**

**请求URL**

/club/delete/member `POST` 

**请求参数**

参数名|类型|必须|描述
--:|:--:|:--:|:--
memberId|int|否|成员id

**返回结果**

```json
{
	"code":"int",
	"msg":"string",
	"data":{}
}
```
## 添加社团成员

**

**请求URL**

/club/addMember `POST` 

**请求参数**

参数名|类型|必须|描述
--:|:--:|:--:|:--
id|int|否|
clubId|int|否|
userId|int|否|
memName|string|否|
gender|string|否|
memTelNum|string|否|
memDetailInfo|string|否|
memJoinTime|date|否|

**返回结果**

```json
{
	"code":"int",
	"msg":"string",
	"data":{}
}
```
## 根据申请id处理事务

**

**请求URL**

/club/dealApply `POST` 

**请求参数**

参数名|类型|必须|描述
--:|:--:|:--:|:--
appId|int|否|申请id
type|int|否|处理结果(成功/失败)
other|string|否|处理意见

**返回结果**

```json
{
	"code":"int",
	"msg":"string",
	"data":{}
}
```
# CommentController
## 根据活动Id获取评论

**

**请求URL**

/get/comments `GET` `POST` 

**请求参数**

参数名|类型|必须|描述
--:|:--:|:--:|:--
activeId|string|否|活动Id

**返回结果**

```json
{
	"code":"int",
	"msg":"string",
	"data":{}
}
```
## 根据用户id获取用自己的评论信息

**

**请求URL**

/get/comments/{uid} `GET` `POST` 


**返回结果**

```json
{
	"code":"int",
	"msg":"string",
	"data":{}
}
```
## 根据id删除评论

**

**请求URL**

/comment/admin/delete `GET` `POST` 

**请求参数**

参数名|类型|必须|描述
--:|:--:|:--:|:--
cid|int|否|评论id

**返回结果**

```json
{
	"code":"int",
	"msg":"string",
	"data":{}
}
```
## 根据id删除评论

**

**请求URL**

/comment/delete/{commentId} `GET` `POST` 

**请求参数**

参数名|类型|必须|描述
--:|:--:|:--:|:--
commentId|string|否|评论id

**返回结果**

```json
{
	"code":"int",
	"msg":"string",
	"data":{}
}
```
# LoginController
## 返回登录注册页面

**

**请求URL**

/login `GET` `POST` 


**返回结果**

```json
string{}
```
## 获取验证码图片

**

**请求URL**

/login/getVerify `GET` `POST` 


**返回结果**

```json
{}
```
## 登录的验证码校验

**

**请求URL**

/login/checkVerify `GET` `POST` 

**请求参数**

参数名|类型|必须|描述
--:|:--:|:--:|:--
verificationCode|string|是|

**返回结果**

```json
{
	"valid":"boolean"
}
```
## 登录检查用户名是否存在

**

**请求URL**

/login/checkname `GET` `POST` 

**请求参数**

参数名|类型|必须|描述
--:|:--:|:--:|:--
r_uName|string|是|用户名

**返回结果**

```json
{
	"valid":"boolean"
}
```
## 登录检查使用邮箱是否绑定其他用户

**

**请求URL**

/login/checkemail `GET` `POST` 

**请求参数**

参数名|类型|必须|描述
--:|:--:|:--:|:--
r_email|string|是|所用邮箱

**返回结果**

```json
{
	"valid":"boolean"
}
```
## 用户注册

**

**请求URL**

/register/insert `POST` 

**请求参数**

参数名|类型|必须|描述
--:|:--:|:--:|:--
emailcode|string|是|输入收到的邮箱验证码
r_email|string|是|注册使用的邮箱
r_uName|string|是|注册的用户名
uPassword|string|是|用户密码

**返回结果**

```json
{
	"code":"int",
	"msg":"string",
	"data":{}
}
```
## 发送验证码到邮箱

**

**请求URL**

/register/sendmail `POST` 

**请求参数**

参数名|类型|必须|描述
--:|:--:|:--:|:--
r_uName|string|是|用户名
r_email|string|是|邮箱地址

**返回结果**

```json
{
	"code":"int",
	"msg":"string",
	"data":{}
}
```
## 用户登录

**

**请求URL**

/user/login `GET` `POST` 


**返回结果**

```json
{
	"code":"int",
	"msg":"string",
	"data":{}
}
```
## 后台登录处理,成功返回对应管理界面

**

**请求URL**

/admin/login `GET` `POST` 

**请求参数**

参数名|类型|必须|描述
--:|:--:|:--:|:--
uId|int|否|
uName|string|否|
uPassword|string|否|
uMailAdd|string|否|
uAuthNo|int|否|
uProfile|string|否|
uProfilePhotoName|string|否|
uProfileBackgroundimgName|string|否|

**返回结果**

```json
{
	"code":"int",
	"msg":"string",
	"data":{}
}
```
# NoticeController
## 根据用户id查找点赞消息

**

**请求URL**

/get/notice/like/{uid} `GET` `POST` 

**请求参数**

参数名|类型|必须|描述
--:|:--:|:--:|:--
uid|string|否|用户id

**返回结果**

```json
{
	"code":"int",
	"msg":"string",
	"data":{}
}
```
## 根据id获取评论通知

**

**请求URL**

/get/notice/comment/{uid} `GET` `POST` 

**请求参数**

参数名|类型|必须|描述
--:|:--:|:--:|:--
uid|string|否|用户id

**返回结果**

```json
{
	"code":"int",
	"msg":"string",
	"data":{}
}
```
## 根据id获取申请通知列表

**

**请求URL**

/get/notice/apply/{uid} `GET` `POST` 

**请求参数**

参数名|类型|必须|描述
--:|:--:|:--:|:--
uid|string|否|用户id

**返回结果**

```json
{
	"code":"int",
	"msg":"string",
	"data":{}
}
```
# UserController
## updatePasswordInEmail

**

**请求URL**

/user/updatePassword `POST` 


**返回结果**

```json
{
	"code":"int",
	"msg":"string",
	"data":{}
}
```
## 更新个人简介

**

**请求URL**

/updateInfo/intro `POST` 

**请求参数**

参数名|类型|必须|描述
--:|:--:|:--:|:--
content|string|否|

**返回结果**

```json
{
	"code":"int",
	"msg":"string",
	"data":{}
}
```
## 得到用户列表

**

**请求URL**

/user/getAll `GET` `POST` 

**请求参数**

参数名|类型|必须|描述
--:|:--:|:--:|:--
page|int|否|页码
limit|int|否|分页大小
keyword|string|否|搜索的关键词

**返回结果**

```json
{
	"code":"int",
	"msg":"string",
	"data":[{
		"uId":"int",
		"uName":"string",
		"uPassword":"string",
		"uMailAdd":"string",
		"uAuthNo":"int",
		"uProfile":"string",
		"uProfilePhotoName":"string",
		"uProfileBackgroundimgName":"string"
	}],
	"count":"int"
}
```
## 得到管理端首页系统数据统计

**

**请求URL**

/getData/admin/index `GET` `POST` 


**返回结果**

```json
{
	"code":"int",
	"msg":"string",
	"data":{}
}
```
## 根据id删除用户

**

**请求URL**

/user/delete `POST` 

**请求参数**

参数名|类型|必须|描述
--:|:--:|:--:|:--
uid|string|否|用户id

**返回结果**

```json
{
	"code":"int",
	"msg":"string",
	"data":{}
}
```
## 新增社团用户

**

**请求URL**

/insert/club `POST` 

**请求参数**

参数名|类型|必须|描述
--:|:--:|:--:|:--
username|string|否|社团名
password|string|否|密码
email|string|否|邮箱

**返回结果**

```json
{
	"code":"int",
	"msg":"string",
	"data":{}
}
```
## 新增用户

**

**请求URL**

/insert/user `GET` `POST` 

**请求参数**

参数名|类型|必须|描述
--:|:--:|:--:|:--
username|string|否|用户名
password|string|否|密码
email|string|否|邮箱

**返回结果**

```json
{
	"code":"int",
	"msg":"string",
	"data":{}
}
```
## updateClubInfo

**

**请求URL**

/user/updateClubInfo `POST` 


**返回结果**

```json
{
	"code":"int",
	"msg":"string",
	"data":{}
}
```