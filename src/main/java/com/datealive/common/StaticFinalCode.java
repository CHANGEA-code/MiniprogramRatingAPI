package com.datealive.common;



/**
 * @ClassName: StaticFinalCode
 * @Description: TODO
 * @author: datealive
 * @date: 2021/2/2  10:09
 */
public class StaticFinalCode {

    /**
     * 每页显示5条数据
     */
    public static final int pageSize_5 = 5;
    /**
     * 每页显示10条数据
     */
    public static final int PageSize_10=10;
    /**
     * 每页显示20个数据
     */
    public static final int PageSize_20 = 20;

    /**
     * 定义"openid"的微信常量
     */
    public static final String OPEN_ID = "openid";
    /**
     * 定义"session_key"的微信常量
     */
    public static final String SESSION_KEY = "session_key";
    /**
     * 定义"access_token"的微信常量
     */
    public static final String ACCESS_TOKEN="access_token";

    /**
     * 定义“ACCESS_TOKEN_EXPIRE”的微信常量 表示2个小时
     */
    public static final long ACCESS_TOKEN_EXPIRE=7200;

    /**
     * 定义redis分布式锁名称
     */
    public static final String RedisLockKey="RedisLockKey";

    /**
     * 定义redis分布式锁时间
     */
    public static final long LOCK_TIMEOUT = 3000;

    public static final String ALL_TREE_HOLE_KEY="all_tree_hole";

    /**
     * 定义redis存放用户id和openid的key
     */
    public static final String UidAndOpenidKey="UidAndOpenidKey";
    /**
     * 定义redis存放用户以及用户简历的信息的key
     */
    public static final String UserAndResumeKey="UserAndResumeKey";

    /**
     * 定义redis存放用户top排行榜key
     */
    public static final String UserTop10ByRateCountKey="UserTop10ByRateCountKey";

    /**
     * 定义redis存放热点数据的时间 这里设置为1天 即 一天更新一次
     */
    public static final long topKey_TIMEOUT=86400;

    /**
     * 定义redis存放队长邀请队员信息 的时间 这里为2天
     */
    public static final long INVITE_TIMEOUT=172800;

    /**
     * 定义redis存放用户申请加入团队信息 的时间 这里为1天
     */
    public static final long APPLY_TIMEOUT=86400;


    /**
     * 定义redis存放队长邀请队员的 邀请dto信息
     */
    public static final String Captain_INVITE_Key="Captain_INVITE_Key";

    /**
     * 定义redis存放用户申请加入团队信息
     */
    public static final String User_APPLY_TEAM_Key="User_APPLY_TEAM_Key";

    /**
     * 定义redis存放某个用户的被邀请信息
     */
    public static final String User_INVITE_MESSAGE_Key="User_INVITE_MESSAGE";

    /**
     * 定义redis存放某个用户的入队申请列表信息
     */
    public static final String User_APPLY_MESSAGE_Key="User_APPLY_MESSAGE";
    /**
     * 定义redis存放比赛热点top10的key
     */
    public static final String RATE_TOP_10_INCY_key ="RATE_TOP_10_INCY_key";


    /**
     * 定义redis存放比赛详细信息的key
     */
    public static final String Rate_DEATIL_BYID_Key="Rate_DEATIL_BYID_Key";

    /**
     * 定义redis存放热点比赛list的key
     */
    public static final String Rate_TOP_List_Key="Rate_TOP_List_Key";
    /**
     * 定义redis存放热点比赛list的缓存时间
     */
    public static final long Rate_Top_TIMEOUT=3600;


}
