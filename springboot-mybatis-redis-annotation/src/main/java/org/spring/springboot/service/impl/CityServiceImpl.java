package org.spring.springboot.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.springboot.domain.City;
import org.spring.springboot.service.CityService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 城市业务逻辑实现类
 * <p>
 * Created by bysocket on 07/02/2017.
 */
@Service
public class CityServiceImpl implements CityService {


    // 模拟数据库存储
    private Map<String, City> cityMap = new HashMap<String, City>();
    /**
     * 添加单个缓存数据
     * @param city
     * @return
     */
    @Cacheable(value = "city",key = "'city_'+#city.cityName")
    public void saveCity(City city){
        // 模拟数据库插入操作
        cityMap.put(city.getCityName(), city);
    }

    @Cacheable(value = "city")
    public City getCityByName(String cityName){
        // 模拟数据库查询并返回
        return cityMap.get(cityName);
    }

    /**
     * 更新单个数据
     * @param cityName
     * @param description
     */
    @CachePut(value = "city",key = "'city_'+#city.cityName")
    public void updateCityDescription(String cityName, String description){
        City city = cityMap.get(cityName);
        city.setDescription(description);
        // 模拟更新数据库
        cityMap.put(cityName, city);
    }

    /**
     * 删除单个数据
     * @param city
     * @return
     */
    @CacheEvict(value = "city",key = "'city_'+#city.cityName")
    public boolean deleteCity(City city) {
        cityMap.remove(city.getCityName());
        return true;
    }

}
