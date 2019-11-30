package com.lyh.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author shenhj
 * @version 1.0
 * @date 2019/11/22 0:53
 */
@Configuration
@Data
public class RedisClusterConfig {

  private LettuceConnectionFactory lettuceConnectionFactory;

  @Autowired
  public RedisClusterConfig(LettuceConnectionFactory lettuceConnectionFactory) {
    // 依赖注入LettuceConnectionFactory工厂类
    // LettuceConnectionConfiguration自动注入了含lettuce Pool连接池和集群Cluster的LettuceConnectionFactory连接工厂类
    this.lettuceConnectionFactory = lettuceConnectionFactory;
  }


  @Bean
  RedisTemplate<String, Object> lettuceRedisTemplate() {
    // 设置序列化
    Jackson2JsonRedisSerializer<String> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<String>(String.class);
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
    jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

    // 配置redisTemplate
    RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
    redisTemplate.setConnectionFactory(lettuceConnectionFactory);
    RedisSerializer<?> stringSerializer = new StringRedisSerializer();
    // key 序列化
    redisTemplate.setKeySerializer(stringSerializer);
    // value 序列化
    redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);

    // Hash Key序列化
    redisTemplate.setHashKeySerializer(stringSerializer);
    // Hash Value 序列化
    redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
    // 初始化赋值
    redisTemplate.afterPropertiesSet();
    return redisTemplate;
  }
}