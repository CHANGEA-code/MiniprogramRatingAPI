# 工程简介

## todoList

- 令牌桶算法限流 根据某个IP或者某个用户
- 搜索算法 使用 elasticsearch
- 推荐算法 考虑使用同协滤波算法 或者余弦原理？ 


- 比赛模块 redis结合 比赛的创建可以直接对数据库操作
  * 比赛的添加，修改，删除，搜索
  * 比赛的推荐
    
- 用户模块 redis结合+微信小程序local storage
  * 用户信息的增删改查（包含简历）
  * 用户发布动态说说
  * 用户收藏比赛  
  
- 队伍模块  redis结合
  * 队伍的创建 删除 队伍信息的修改
  * 用户查看队伍 申请进入队伍
  

    

- 广告模块 可以直接对数据库进行操作
  * 广告的增删改查
    
- 积分模块  目前不涉及兑换物品的话 可以考虑将信息录入到redis中  
  * 积分的增删改查

======================================================


用户 点击授权

openID在每个小程序中 对于每个用户来说 openID是唯一的

先查询一遍数据库 redis 

一个hashmap 存 用户信息 

一个hashmap存 用户ID和openID


如果 用户存在 则生成相对应的openID返回小程序

如果 用户不存在 则根据传递过来的用户信息 生成一个用户 之后返回openID

用户登录后访问接口

返回用户权限接口时 需要带上openID 和 用户ID

判断 openID和用户ID是不是属于同一个用户

如果属于 则进行后续操作

如果不属于 则提示操作失败并返回无权限401 前端根据状态码重新授权登录

================================================
用户缓存

redis使用的数据结构 hashmap


查询/缓存一个大用户 包含用户信息 简历信息 动态信息 关注信息 比赛信息 

map.put(用户id，大用户)

修改操作
map.remove 后 put

====================================

采用模板模式设计方法：

加入队伍有两种方式

1：自己申请加入队伍

2：队长邀请加入队伍                                      

之后执行入队逻辑


邀请加入队伍

查询 手机号码 -》用户

发送邀请 

后端接收请求 根据用户ID 缓存 被邀请人ID 设置有效期

同时给被邀请人发送邮件或者是 订阅消息 

被邀请人点击链接

判断在redis中是否有缓存了被邀请人ID，
如果没有（过期了或者其他） 则返回失败
如果有 执行 入队操作

===========================================


* 比赛 显示/根据比赛ID查询比赛:

redis 使用的数据结构：RedisKey : hashmap(key,value)

先将所有比赛缓存到redis中 后 分页获取
map.put(比赛id，比赛实体类)

添加比赛
map.put(比赛id，比赛实体类)

修改比赛
map.remove(比赛id)
map.put(****)

删除比赛
map.remove(比赛id)

* 比赛 热门点击和搜索top：

每次涉及 查询比赛获得的ID（获取比赛详细数据的接口）

使用zset数据结构

对对应的比赛ID进行 自增加一

然后查询zset中最大的前十个数据

* 比赛 热门参赛top：

 每次在创建队伍的时候 有个 比赛ID 同样采取前面的方法进行操作返回

* 比赛 根据比赛分类/比赛名称 查询比赛数据 可以采用elasticSearch

但这里elasticSearch在1核2g的服务器上运行 不是很明智

所以采取直接对数据库进行 操作

==============================================
用户动态说说缓存（待定）

redis使用的数据结构 hashMap （如果是根据时间排序呢？）

查询/缓存
map.put（用户id，）

添加
map.put

===============================================










# 延伸阅读

