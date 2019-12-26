package com.lyh.security.app.social;

import com.lyh.security.app.exception.AppSecretException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.social.connect.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.concurrent.TimeUnit;

/**
 * @author shenhj
 * @version 1.0
 * Description:
 * @date 2019/12/26 0:04
 */
@Component
public class AppSignUpUtils {

  @Autowired
  private RedisTemplate<Object, Object> redisTemplate;

  @Autowired
  private UsersConnectionRepository usersConnectionRepository;

  @Autowired
  private ConnectionFactoryLocator connectionFactoryLocator;

  /***
   * 将社交账户信息存到redis
   * @param request
   * @param connectionData
   */
  public void saveConnectionData(WebRequest request, ConnectionData connectionData){
    redisTemplate.opsForValue().set(getKey(request), connectionData, 10, TimeUnit.MINUTES);
  }

  /***
   * 从redis取出社交账户信息，并将其与本系统账户建立关联，并将关联关系存到数据库---userconnection表
   * @param request
   * @param userId
   */
  public void doPostSignUp(WebRequest request, String userId){
    String key = getKey(request);
    if(!redisTemplate.hasKey(key)){
      throw new AppSecretException("无法找到缓存的用户社交账号信息");
    }
    ConnectionData connectionData = (ConnectionData) redisTemplate.opsForValue().get(key);
    Connection<?> connection = connectionFactoryLocator.getConnectionFactory(connectionData.getProviderId())
            .createConnection(connectionData);
    usersConnectionRepository.createConnectionRepository(userId).addConnection(connection);

    redisTemplate.delete(key);

  }


  private String getKey(WebRequest request){
    String deviceId = request.getHeader("deviceId");
    if(StringUtils.isBlank(deviceId)){
      throw new AppSecretException("设备id参数不能为空");
    }
    return "lyh:security:social.connect." + deviceId;
  }

}
