package com.ccnuiot.xsystem01.controller;

import com.ccnuiot.xsystem01.gethuaweiiot.httpGetHuaweiIOT;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TemperatureController {

    @GetMapping("/getTemperature")
    public String getTemperature(){
        /*double temperature = 0.255;*/
        String status = "";
        String sensor_id = "6500372502c946447751ea00_sensor";
        String temperature = "";
        httpGetHuaweiIOT iotController = null;


        try {
            iotController = new httpGetHuaweiIOT();
            temperature = iotController.getdev(sensor_id,"shadow","radiation");
            System.out.println(temperature);

        } catch (Exception e) {
            e.printStackTrace();
        }



       /* return String.valueOf(temperature);*/

        return temperature;
    }
}
