package com.sb.redis.example.utils;

import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.*;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 *
 */

public interface RedisHelper {

    /*=============================Key操作============================  */

    /**
     * 指定缓存失效时间
     *
     * @param key  键
     * @param time 时间(秒)
     */
    void expire(String key, long time);

    /**
     * 根据key 获取过期时间
     *
     * @param key 键 不能为null
     * @return 时间(秒) 返回0代表为永久有效
     */
    long getExpire(String key);

    /**
     * 判断key是否存在
     *
     * @param key 键
     * @return true 存在 false不存在
     */
    boolean hasKey(String key);

    /**
     * 删除缓存
     *
     * @param keys 可以传一个值 或多个
     */
    public void del(String... keys);

    //============================String=============================

    /**
     * 普通缓存获取
     *
     * @param key 键
     * @return 值
     */
    public Object get(String key);


    /**
     * 普通缓存放入
     *
     * @param key   键
     * @param value 值
     * @return true成功 false失败
     */
    public void set(String key, Object value);

    /**
     * 普通缓存放入并设置时间
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒) time要大于0 如果time小于等于0 将设置无限期
     * @return true成功 false 失败
     */
    public boolean set(String key, Object value, long time);

    /**
     * 递增
     *
     * @param key   键
     * @param delta 要增加几(大于0)
     * @return
     */
    public long incr(String key, long delta);

    /**
     * 递减
     *
     * @param key   键
     * @param delta 要减少几(小于0)
     * @return
     */
    public long decr(String key, long delta);

    //================================Map=================================

    /**
     * HashGet
     *
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return 值
     */
    Object hget(String key, String item);

    /**
     * 获取hashKey对应的所有键值
     *
     * @param key 键
     * @return 对应的多个键值
     */
    Map<String, Object> hmget(String key);

    /**
     * HashSet
     *
     * @param key 键
     * @param map 对应多个键值
     * @return true 成功 false 失败
     */
    boolean hmset(String key, Map<String, Object> map);

    /**
     * HashSet 并设置时间
     *
     * @param key  键
     * @param map  对应多个键值
     * @param time 时间(秒)
     * @return true成功 false失败
     */
    boolean hmset(String key, Map<String, Object> map, long time);

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @return true 成功 false失败
     */
    boolean hset(String key, String item, Object value);

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @param time  时间(秒)  注意:如果已存在的hash表有时间,这里将会替换原有的时间
     * @return true 成功 false失败
     */
    boolean hset(String key, String item, Object value, long time);

    /**
     * 删除hash表中的值
     *
     * @param key  键 不能为null
     * @param item 项 可以使多个 不能为null
     */
    void hdel(String key, Object... item);

    /**
     * 判断hash表中是否有该项的值
     *
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return true 存在 false不存在
     */
    boolean hHasKey(String key, String item);

    /**
     * hash递增 如果不存在,就会创建一个 并把新增后的值返回
     *
     * @param key  键
     * @param item 项
     * @param by   要增加几(大于0)
     * @return
     */
    double hincr(String key, String item, double by);

    /**
     * hash递减
     *
     * @param key  键
     * @param item 项
     * @param by   要减少记(小于0)
     * @return
     */
    double hdecr(String key, String item, double by);

    //============================set=============================

    /**
     * 根据key获取Set中的所有值
     *
     * @param key 键
     * @return
     */
    Set<Object> sGet(String key);

    /**
     * 根据value从一个set中查询,是否存在
     *
     * @param key   键
     * @param value 值
     * @return true 存在 false不存在
     */
    boolean sHasKey(String key, Object value);

    /**
     * 将数据放入set缓存
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 成功个数
     */
    long sSet(String key, Object... values);

    /**
     * 将set数据放入缓存
     *
     * @param key    键
     * @param time   时间(秒)
     * @param values 值 可以是多个
     * @return 成功个数
     */
    long sSetAndTime(String key, long time, Object... values);

    /**
     * 获取set缓存的长度
     *
     * @param key 键
     * @return
     */
    long sGetSetSize(String key);

    /**
     * 移除值为value的
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 移除的个数
     */
    long setRemove(String key, Object... values);


    public boolean setBit(String key, Integer index, Boolean tag);

    public boolean getBit(String key, Integer index);

    /**
     * 统计bitmap中，value为1的个数，非常适用于统计网站的每日活跃用户数等类似的场景
     *
     * @param key
     * @return
     */
    public Long bitCount(String key);

    public Long bitCount(String key, int start, int end);

    public Long bitOp(RedisStringCommands.BitOperation op, String saveKey, String... desKey);

    //===============================list=================================

    /**
     * 获取list缓存的内容
     *
     * @param key   键
     * @param start 开始
     * @param end   结束  0 到 -1代表所有值
     * @return
     */
    List<Object> lGet(String key, long start, long end);

    /**
     * 获取list缓存的所有内容
     *
     * @param key
     * @return
     */
    List<Object> lGetAll(String key);

    /**
     * 获取list缓存的长度
     *
     * @param key 键
     * @return
     */
    long lGetListSize(String key);

    /**
     * 通过索引 获取list中的值
     *
     * @param key   键
     * @param index 索引  index>=0时， 0 表头，1 第二个元素，依次类推；index<0时，-1，表尾，-2倒数第二个元素，依次类推
     * @return
     */
    Object lGetIndex(String key, long index);

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @return
     */
    boolean lSet(String key, Object value);

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     * @return
     */
    boolean lSet(String key, Object value, long time);

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @return
     */
    boolean lSet(String key, List<Object> value);

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     * @return
     */
    boolean lSet(String key, List<Object> value, long time);

    /**
     * 根据索引修改list中的某条数据
     *
     * @param key   键
     * @param index 索引
     * @param value 值
     * @return
     */
    boolean lUpdateIndex(String key, long index, Object value);

    /**
     * 移除N个值为value
     *
     * @param key   键
     * @param count 移除多少个
     * @param value 值
     * @return 移除的个数
     */
    long lRemove(String key, long count, Object value);

    /**
     * 添加元素,有序集合是按照元素的score值由小到大排列
     *
     * @param key
     * @param value
     * @param score
     * @return
     */
    Boolean zAdd(String key, String value, double score);

    /**
     * 批量添加
     *
     * @param key
     * @param values
     * @return 使用
     * ZSetOperations.TypedTuple<String> objectTypedTuple1 = new DefaultTypedTuple<String>(value, score);
     */
    Long zAdd(String key, Set<ZSetOperations.TypedTuple<String>> values);

    //获取集合的元素, 从小到大排序, start开始位置, end结束位置
    Set<String> zRange(String key, long start, long end);

    //获取集合元素, 并且把score值也获取
    Set<ZSetOperations.TypedTuple<String>> zRangeWithScores(String key, long start, long end);

    //根据Score值查询集合元素的值, 从小到大排序
    Set<String> zRangeByScore(String key, double min, double max);

    //根据Score值查询集合元素, 从小到大排序
    Set<ZSetOperations.TypedTuple<String>> zRangeByScoreWithScores(String key, double min, double max);

    //根据Score值查询集合元素, 从小到大排序
    Set<ZSetOperations.TypedTuple<String>> zRangeByScoreWithScores(String key, double min, double max, long start, long end);

    //获取集合的元素, 从大到小排序
    Set<String> zReverseRange(String key, long start, long end);

    //获取集合的元素, 从大到小排序, 并返回score值
    Set<ZSetOperations.TypedTuple<String>> zReverseRangeWithScores(String key, long start, long end);

    //根据Score值查询集合元素, 从大到小排序
    Set<String> zReverseRangeByScore(String key, double min, double max);

    //根据Score值查询集合元素, 从大到小排序
    Set<ZSetOperations.TypedTuple<String>> zReverseRangeByScoreWithScores(String key, double min, double max);

    Set<String> zReverseRangeByScore(String key, double min, double max, long start, long end);

    //返回元素在集合的排名,有序集合是按照元素的score值由小到大排列
    Long zRank(String key, Object value);

    //返回元素在集合的排名,按元素的score值由大到小排列
    Long zReverseRank(String key, Object value);

    //根据score值获取集合元素数量
    Long zCount(String key, double min, double max);

    //获取集合大小
    Long zSize(String key);

    //获取集合大小
    Long zZCard(String key);

    //获取集合中value元素的score值
    Double zScore(String key, Object value);

    //获取key和otherKey的并集并存储在destKey中
    Long zUnionAndStore(String key, String otherKey, String destKey);

    //获取key和多个集合的并集并存储在destKey中
    Long zUnionAndStore(String key, Collection<String> otherKeys, String destKey);

    //获取key和otherKey的交集并存储在destKey中
    Long zIntersectAndStore(String key, String otherKey, String destKey);

    //获取key和多个集合的交集并存储在destKey中
    Long zIntersectAndStore(String key, Collection<String> otherKeys, String destKey);

    //使用迭代器获取
    Cursor<ZSetOperations.TypedTuple<String>> zScan(String key, ScanOptions options);
    //移除
    Long zRemove(String key, Object... values);
    //增加元素的score值，并返回增加后的值
    Double zIncrementScore(String key, String value, double delta);
    //移除指定索引位置的成员
    Long zRemoveRange(String key, long start, long end);

    //根据指定的score值的范围来移除成员
    Long zRemoveRangeByScore(String key, double min, double max);
}
