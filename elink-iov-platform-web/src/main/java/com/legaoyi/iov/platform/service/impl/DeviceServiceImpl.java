package com.legaoyi.iov.platform.service.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Service;

import com.legaoyi.iov.platform.service.DeviceService;

@Service("deviceService")
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Map<?, ?> getBySimCode(String simCode) throws Exception {
        String sql = "select id,name,auth_code as authCode from device where sim_code=? limit 1";
        return jdbcTemplate.queryForMap(sql, simCode);
    }

    @Override
    public Map<?, ?> get(String id) throws Exception {
        String sql = "select id,name,auth_code as authCode,state,last_online_time as lastOnlineTime,last_offline_time as lastOfflineTime,date_format(create_time,'%Y-%m-%d %H:%I:%s') as createTime  from device where id=? limit 1";
        return jdbcTemplate.queryForMap(sql, id);
    }

    @Override
    public void delete(String id) throws Exception {
        String sql = "delete from device where id=?";
        jdbcTemplate.update(sql, id);

    }

    @Override
    public Map<?, ?> save(Map<?, ?> data) throws Exception {
        String id = (String) data.get("id");
        if (StringUtils.isNotBlank(id)) {
            String sql = "update device set name= ?,sim_code = ?,auth_code=? where id = ?";
            jdbcTemplate.update(sql, (String) data.get("name"), (String) data.get("simCode"), (String) data.get("authCode"), id);
        } else {
            String sql = "insert into device(id,name,sim_code,auth_code,create_time) values(?,?,?,?,?)";
            jdbcTemplate.update(sql, new PreparedStatementSetter() {

                @Override
                public void setValues(PreparedStatement ps) throws SQLException {
                    ps.setString(1, UUID.randomUUID().toString().replaceAll("-", ""));
                    ps.setString(2, (String) data.get("name"));
                    ps.setString(3, (String) data.get("simCode"));
                    ps.setString(4, (String) data.get("authCode"));
                    ps.setTimestamp(5, new java.sql.Timestamp(System.currentTimeMillis()));
                }
            });

        }
        return data;
    }

    @Override
    public List<?> findAll() throws Exception {
        String sql = "select id,name,sim_code as simCode,auth_code as authCode,state,last_online_time as lastOnlineTime,last_offline_time as lastOfflineTime,date_format(create_time,'%Y-%m-%d %H:%I:%s') as createTime  from device";
        return jdbcTemplate.queryForList(sql);
    }

    @Override
    public void setOffline(String simCode) throws Exception {
        String sql = "update device set state= 0,last_offline_time = ? where state=1 and sim_code=? and last_online_time < ?";
        long now = System.currentTimeMillis();
        jdbcTemplate.update(sql, now, simCode, now);
    }

    @Override
    public void setOnline(String simCode) throws Exception {
        String sql = "update device set state= 1,last_online_time = ? where sim_code=?  and (last_offline_time < ? or last_offline_time is null)";
        long now = System.currentTimeMillis();
        jdbcTemplate.update(sql, now, simCode, now);
    }

}
