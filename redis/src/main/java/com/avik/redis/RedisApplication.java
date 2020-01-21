package com.avik.redis;

import java.net.URI;
import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.avik.redis.resourse.MovieCache;
import com.avik.redis.vo.Movie;
import com.netflix.discovery.EurekaClient;

@SpringBootApplication
@EnableCaching
@EnableEurekaClient
@EntityScan("com.avik.redis.db")
public class RedisApplication {


	@Autowired
	MovieCache movieCache;

	 @Autowired
	private EurekaClient discoveryClient;
	 
	@Autowired
	private LoadBalancerClient loadBalancer;

	@LoadBalanced
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(RedisApplication.class, args);
	}

	@PostConstruct
	public void init(){
		 ServiceInstance instance = loadBalancer.choose("movie-avikdb-service");
		 URI uri = instance.getUri();
		String url = discoveryClient.getNextServerFromEureka("movie-avikdb-service", false).getHomePageUrl();
		ResponseEntity<Movie[]> response = getRestTemplate().getForEntity(uri+"/movie-db/allMovie",Movie[].class);
		//ResponseEntity<Movie[]> response = getRestTemplate().getForEntity(url+"/movie-db/allMovie",Movie[].class);
		Arrays.asList(response.getBody()).stream().forEach((item)->movieCache.save(item));

	}

	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		JedisConnectionFactory jedisConFactory
		= new JedisConnectionFactory();
		jedisConFactory.getPoolConfig().setMaxIdle(30);
		jedisConFactory.getPoolConfig().setMinIdle(10);
		return jedisConFactory;
	}

	@Bean
	public RedisTemplate<String, Movie> redisTemplate() {
		final RedisTemplate<String, Movie> template = new RedisTemplate<>();
		template.setConnectionFactory(jedisConnectionFactory());
		template.setValueSerializer(new GenericToStringSerializer<Movie>(Movie.class));
		return template;
	}



	/*
	 * @Bean MessageListenerAdapter messageListener() { return new
	 * MessageListenerAdapter(new RedisMessageSubscriber()); }
	 */

	/*
	 * @Bean RedisMessageListenerContainer redisContainer() { final
	 * RedisMessageListenerContainer container = new
	 * RedisMessageListenerContainer();
	 * container.setConnectionFactory(jedisConnectionFactory());
	 * container.addMessageListener(messageListener(), topic()); return container; }
	 */

	/*
	 * @Bean MessagePublisher redisPublisher() { return new
	 * RedisMessagePublisher(redisTemplate(), topic()); }
	 */

	/*
	 * @Bean ChannelTopic topic() { return new ChannelTopic("pubsub:queue"); }
	 * 
	 **/ 


}
