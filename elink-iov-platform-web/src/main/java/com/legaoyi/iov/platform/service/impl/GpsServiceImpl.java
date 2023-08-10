package com.legaoyi.iov.platform.service.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Service;

import com.legaoyi.iov.platform.service.DeviceService;
import com.legaoyi.iov.platform.service.GpsService;
import com.legaoyi.iov.platform.util.Constants;
import com.legaoyi.iov.platform.util.JsonUtil;

@Service("gpsService")
public class GpsServiceImpl implements GpsService {

    @Autowired
    @Qualifier("deviceService")
    private DeviceService deviceService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void save(Map<?, ?> data) throws Exception {
        String sql = "insert into gps(id,sim_code,gps_time,gps_info,create_time) values(?,?,?,?,?)";
        jdbcTemplate.update(sql, new PreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                Map<?, ?> messageBody = (Map<?, ?>) data.get(Constants.MAP_KEY_MESSAGE_MESSAGE_BODY);
                ps.setString(1, UUID.randomUUID().toString().replaceAll("-", ""));
                ps.setString(2, (String) data.get("simCode"));
                ps.setLong(3, (Long) messageBody.get("time"));
                ps.setString(4, JsonUtil.covertObjectToStringWithoutEmpty(messageBody));
                ps.setTimestamp(5, new java.sql.Timestamp(System.currentTimeMillis()));
            }
        });
    }

    @Override
    public Map<?, ?> getLastGpsBySimCode(String simCode) throws Exception {
        try {
            String sql = "select id,sim_code as simCode,gps_time as gpsTime,gps_info as gpsInfo ,date_format(create_time,'%Y-%m-%d %H:%I:%s') as createTime from gps where sim_code = ?  order by gps_time desc limit 1 ";
            return jdbcTemplate.queryForMap(sql, simCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<?> getLastGps() throws Exception {
        List<Object> list = new ArrayList<Object>();
        List<?> devices = deviceService.findAll();
        if (devices != null) {
            for (Object o : devices) {
                Map<?, ?> device = (Map<?, ?>) o;
                String simCode = (String) device.get("simCode");
                Map<?, ?> gps = this.getLastGpsBySimCode(simCode);
                if (gps != null) {
                    list.add(gps);
                }
            }
        }
        return list;
    }

    @Override
    public List<?> find(String simCode, long startTime, long endTime) throws Exception {
        String sql = "select id,sim_code as simCode,gps_time as gpsTime,gps_info as gpsInfo ,date_format(create_time,'%Y-%m-%d %H:%I:%s') as createTime from gps where sim_code = ? and gps_time >= ? and gps_time <= ? ";
        return jdbcTemplate.queryForList(sql, simCode, startTime, endTime);
    }

}
