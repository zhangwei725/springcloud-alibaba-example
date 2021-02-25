package com.sb.redis.example.utils;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;

/**
 * @author zhangwei
 */
@Component
@Slf4j
public class RedisHelperImpl implements RedisHelper {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Resource
    private ValueOperations<String, Object> valueOperations;
    @Resource
    private HashOperations<String, String, Object> hashOperations;
    @Resource
    private ListOperations<String, Object> listOperations;
    @Resource
    private SetOperations<String, Object> setOperations;
    @Resource
    private ZSetOperations<String, Object> zSetOperations;

    @Override
    public void expire(String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public long getExpire(String key) {
        Long expire = redisTemplate.getExpire(key, TimeUnit.SECONDS);
        return expire != null && expire > 0 ? expire : 0;
    }

    @Override
    public boolean hasKey(String key) {
        try {
            return redisTemplate.hasKey(key) != null;
        } catch (NullPointerException e) {
            return false;
        }
    }

    @Override
    public void del(String... keys) {
        if (keys != null && keys.length > 0) {
            if (keys.length == 1) {
                redisTemplate.delete(keys[0]);
            } else {
                List list = CollectionUtils.arrayToList(keys);
                redisTemplate.delete(list);
            }
        }
    }

    @Override
    public Object get(String key) {
        return key == null ? null : valueOperations.get(key);
    }


    @Override
    public void set(String key, Object value) {
        try {
            valueOperations.set(key, value);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public boolean set(String key, Object value, long time) {
        try {
            if (time > 0) {
                valueOperations.set(key, value, time, TimeUnit.SECONDS);
            } else {
                set(key, value);
            }
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }


    @Override
    public long incr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递增因子必须大于0");
        }
        Long increment = valueOperations.increment(key, delta);
        return increment != null && increment > 0 ? increment : 0;
    }


    @Override
    public long decr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递减因子必须大于0");
        }
        Long decrement = valueOperations.decrement(key, -delta);
        return decrement != null && decrement > 0 ? decrement : 0;
    }


    @Override
    public boolean setBit(String key, Integer index, Boolean tag) {
        return redisTemplate.execute((RedisCallback<Boolean>) con -> con.setBit(key.getBytes(), index, tag));
    }

    @Override
    public boolean getBit(String key, Integer index) {
        return redisTemplate.execute((RedisCallback<Boolean>) con -> con.getBit(key.getBytes(), index));
    }

    /**
     * 统计bitmap中，value为1的个数，非常适用于统计网站的每日活跃用户数等类似的场景
     *
     * @param key
     * @return
     */
    @Override
    public Long bitCount(String key) {
        return redisTemplate.execute((RedisCallback<Long>) con -> con.bitCount(key.getBytes()));
    }

    @Override
    public Long bitCount(String key, int start, int end) {
        return redisTemplate.execute((RedisCallback<Long>) con -> con.bitCount(key.getBytes(), start, end));
    }

    @Override
    public Long bitOp(RedisStringCommands.BitOperation op, String saveKey, String... desKey) {
        byte[][] bytes = new byte[desKey.length][];
        for (int i = 0; i < desKey.length; i++) {
            bytes[i] = desKey[i].getBytes();
        }
        return redisTemplate.execute((RedisCallback<Long>) con -> con.bitOp(op, saveKey.getBytes(), bytes));
    }




    @Override
    public Object hget(String key, String item) {
        return hashOperations.get(key, item);
    }

    @Override
    public Map<String, Object> hmget(String key) {
        return hashOperations.entries(key);
    }

    @Override
    public boolean hmset(String key, Map<String, Object> map) {
        try {
            hashOperations.putAll(key, map);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }


    @Override
    public boolean hmset(String key, Map<String, Object> map, long time) {
        try {
            hashOperations.putAll(key, map);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }


    @Override
    public boolean hset(String key, String item, Object value) {
        try {
            hashOperations.put(key, item, value);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }


    @Override
    public boolean hset(String key, String item, Object value, long time) {
        try {
            hashOperations.put(key, item, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }


    @Override
    public void hdel(String key, Object... item) {
        hashOperations.delete(key, item);
    }


    @Override
    public boolean hHasKey(String key, String item) {
        return hashOperations.hasKey(key, item);
    }

    @Override
    public double hincr(String key, String item, double by) {
        return hashOperations.increment(key, item, by);
    }


    @Override
    public double hdecr(String key, String item, double by) {
        return hashOperations.increment(key, item, -by);
    }

    @Override
    public Set<Object> sGet(String key) {
        try {
            return setOperations.members(key);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @Override
    public boolean sHasKey(String key, Object value) {
        try {
            Boolean member = setOperations.isMember(key, value);
            return member != null ? member : false;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    @Override
    public long sSet(String key, Object... values) {
        try {
            Long add = setOperations.add(key, values);
            return add != null && add > 0 ? add : 0;
        } catch (Exception e) {
            log.error(e.getMessage());
            return 0;
        }
    }

    @Override
    public long sSetAndTime(String key, long time, Object... values) {
        try {
            Long count = setOperations.add(key, values);
            if (time > 0) {
                expire(key, time);
            }
            return count != null && count > 0 ? count : 0;
        } catch (Exception e) {
            log.error(e.getMessage());
            return 0;
        }
    }

    @Override
    public long sGetSetSize(String key) {
        try {
            Long size = setOperations.size(key);
            return size != null && size > 0 ? size : 0;
        } catch (Exception e) {
            log.error(e.getMessage());
            return 0;
        }
    }

    @Override
    public long setRemove(String key, Object... values) {
        try {
            Long count = setOperations.remove(key, values);
            return count != null && count > 0 ? count : 0;
        } catch (Exception e) {
            log.error(e.getMessage());
            return 0;
        }
    }


    @Override
    public List<Object> lGet(String key, long start, long end) {
        try {
            return listOperations.range(key, start, end);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Object> lGetAll(String key) {
        return lGet(key, 0, -1);
    }


    @Override
    public long lGetListSize(String key) {
        try {
            Long size = listOperations.size(key);
            return size != null && size > 0 ? size : 0;
        } catch (Exception e) {
            log.error(e.getMessage());
            return 0;
        }
    }

    @Override
    public Object lGetIndex(String key, long index) {
        try {
            return listOperations.index(key, index);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @Override
    public boolean lSet(String key, Object value) {
        try {
            listOperations.rightPush(key, value);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }


    @Override
    public boolean lSet(String key, Object value, long time) {
        try {
            listOperations.rightPush(key, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }


    @Override
    public boolean lSet(String key, List<Object> value) {
        try {
            listOperations.rightPushAll(key, value);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }


    @Override
    public boolean lSet(String key, List<Object> value, long time) {
        try {
            listOperations.rightPushAll(key, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }


    @Override
    public boolean lUpdateIndex(String key, long index, Object value) {
        try {
            listOperations.set(key, index, value);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }


    @Override
    public long lRemove(String key, long count, Object value) {
        try {
            Long remove = listOperations.remove(key, count, value);
            return remove != null && remove > 0 ? remove : 0;
        } catch (Exception e) {
            log.error(e.getMessage());
            return 0;
        }
    }

    @Override
    public Boolean zAdd(String key, String value, double score) {
        return null;
    }

    @Override
    public Long zAdd(String key, Set<ZSetOperations.TypedTuple<String>> values) {
        return null;
    }

    @Override
    public Set<String> zRange(String key, long start, long end) {
        return null;
    }

    @Override
    public Set<ZSetOperations.TypedTuple<String>> zRangeWithScores(String key, long start, long end) {
        return null;
    }

    @Override
    public Set<String> zRangeByScore(String key, double min, double max) {
        return null;
    }

    @Override
    public Set<ZSetOperations.TypedTuple<String>> zRangeByScoreWithScores(String key, double min, double max) {
        return null;
    }

    @Override
    public Set<ZSetOperations.TypedTuple<String>> zRangeByScoreWithScores(String key, double min, double max, long start, long end) {
        return null;
    }

    @Override
    public Set<String> zReverseRange(String key, long start, long end) {
        return null;
    }

    @Override
    public Set<ZSetOperations.TypedTuple<String>> zReverseRangeWithScores(String key, long start, long end) {
        return null;
    }

    @Override
    public Set<String> zReverseRangeByScore(String key, double min, double max) {
        return null;
    }

    @Override
    public Set<ZSetOperations.TypedTuple<String>> zReverseRangeByScoreWithScores(String key, double min, double max) {
        return null;
    }

    @Override
    public Set<String> zReverseRangeByScore(String key, double min, double max, long start, long end) {
        return null;
    }

    @Override
    public Long zRank(String key, Object value) {
        return null;
    }

    @Override
    public Long zReverseRank(String key, Object value) {
        return null;
    }

    @Override
    public Long zCount(String key, double min, double max) {
        return null;
    }

    @Override
    public Long zSize(String key) {
        return null;
    }

    @Override
    public Long zZCard(String key) {
        return null;
    }

    @Override
    public Double zScore(String key, Object value) {
        return null;
    }

    @Override
    public Long zUnionAndStore(String key, String otherKey, String destKey) {
        return null;
    }

    @Override
    public Long zUnionAndStore(String key, Collection<String> otherKeys, String destKey) {
        return null;
    }

    @Override
    public Long zIntersectAndStore(String key, String otherKey, String destKey) {
        return null;
    }

    @Override
    public Long zIntersectAndStore(String key, Collection<String> otherKeys, String destKey) {
        return null;
    }

    @Override
    public Cursor<ZSetOperations.TypedTuple<String>> zScan(String key, ScanOptions options) {
        return null;
    }

    @Override
    public Long zRemove(String key, Object... values) {
        return null;
    }

    @Override
    public Double zIncrementScore(String key, String value, double delta) {
        return null;
    }

    @Override
    public Long zRemoveRange(String key, long start, long end) {
        return null;
    }

    @Override
    public Long zRemoveRangeByScore(String key, double min, double max) {
        return null;
    }

}