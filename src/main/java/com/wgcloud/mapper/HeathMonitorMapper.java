package com.wgcloud.mapper;

import org.springframework.stereotype.Repository;

import com.wgcloud.entity.HeathMonitor;

import java.util.List;
import java.util.Map;

/**
 * @version v2.3
 * @ClassName:HeathMonitorMapper.java
 * @author: http://www.wgstart.com
 * @date: November 16, 2019
 * @Description: HeathMonitorDao.java
 * @Copyright: 2017-2022 wgcloud. All rights reserved.
 */
@Repository
public interface HeathMonitorMapper {

    public List<HeathMonitor> selectAllByParams(Map<String, Object> map) throws Exception;

    public List<HeathMonitor> selectByParams(Map<String, Object> params) throws Exception;

    public HeathMonitor selectById(String id) throws Exception;

    public void save(HeathMonitor HeathMonitor) throws Exception;

    public void insertList(List<HeathMonitor> recordList) throws Exception;

    public int deleteById(String[] id) throws Exception;

    public int deleteByDate(Map<String, Object> map) throws Exception;

    public int countByParams(Map<String, Object> params) throws Exception;

    public void updateList(List<HeathMonitor> recordList) throws Exception;

    public void updateById(HeathMonitor HeathMonitor) throws Exception;
}
