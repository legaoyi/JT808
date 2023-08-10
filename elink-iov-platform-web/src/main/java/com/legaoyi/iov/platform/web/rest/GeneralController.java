package com.legaoyi.iov.platform.web.rest;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.legaoyi.iov.platform.service.DeviceService;
import com.legaoyi.iov.platform.service.GpsService;

@RestController("generalController")
@RequestMapping(value = "/common", produces = {"application/json"})
public class GeneralController extends BaseController {

    @Autowired
    @Qualifier("deviceService")
    private DeviceService deviceService;

    @Autowired
    @Qualifier("gpsService")
    private GpsService gpsService;

    @RequestMapping(value = {"/gps/last/{simCode}"}, method = {RequestMethod.GET})
    public Result get(@PathVariable String simCode) throws Exception {
        return new Result(gpsService.getLastGpsBySimCode(simCode));
    }
    
    
    @RequestMapping(value = {"/gps/last/all"}, method = {RequestMethod.GET})
    public Result getAll() throws Exception {
        return new Result(gpsService.getLastGps());
    }

    @RequestMapping(value = {"/device"}, method = {RequestMethod.POST})
    public Result post(@RequestBody Map<String, Object> form) throws Exception {
        return new Result(deviceService.save(form));
    }

    @RequestMapping(value = {"/device/{id}"}, method = {RequestMethod.PUT})
    public Result put(@PathVariable String id, @RequestBody Map<String, Object> form) throws Exception {
        return this.patch(id, form);
    }

    @RequestMapping(value = {"/device/{id}"}, method = {RequestMethod.PATCH})
    public Result patch(@PathVariable String id, @RequestBody Map<String, Object> form) throws Exception {
        form.put("id", id);
        return new Result(deviceService.save(form));
    }

    @RequestMapping(value = {"/device/{id}"}, method = {RequestMethod.DELETE})
    public Result delete(@PathVariable String id) throws Exception {
        deviceService.delete(id);
        return new Result();
    }
}
