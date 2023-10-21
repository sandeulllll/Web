package com.ccnuiot.xsystem01.gethuaweiiot;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Set;


public class httpGetHuaweiIOT {
    String token="";

    public httpGetHuaweiIOT()throws Exception {
        token = gettoken();
    }

    //获取token
    public static String gettoken() throws Exception
    {
        /*String postbody = "{" + "\"" + "auth" + "\"" + ": {" + "\"" + "identity" + "\"" + ": {" + "\"" + "methods" + "\"" + ": [" + "\"" + "password" + "\"" + "]," + "\"" + "password" + "\"" + ": {" + "\"" + "user" + "\"" + ":{" + "\"" + "domain\": {\"name\": \"fuxiaoiii\"},\"name\": \"fuxiaoiam\",\"password\": \"omtomc.1234\"}}},\"scope\": {\"project\": {\"name\": \"cn-north-4\"}}}}";
         */
        //TODO:请求体
        String postbody = "{\n" +
                " \"auth\": {\n" +
                "  \"identity\": {\n" +
                "   \"methods\": [\n" +
                "    \"password\"\n" +
                "   ],\n" +
                "   \"password\": {\n" +
                "    \"user\": {\n" +
                "     \"domain\": {\n" +
                "      \"name\": \"hid_erzprdq7inqlmp5\"\n" +
                "     },\n" +
                "     \"name\": \"x\",\n" +
                "     \"password\": \"hid_erzprdq7inqlmp5\"\n" +
                "    }\n" +
                "   }\n" +
                "  },\n" +
                "  \"scope\": {\n" +
                "   \"domain\": {\n" +
                "    \"id\": \"d0fc6d60e63a4ea58c0add516471e47f\",\n" +
                "    \"name\": \"hid_erzprdq7inqlmp5\"\n" +
                "   },\n" +
                "   \"project\": {\n" +
                "    \"id\": \"0e691b032b8090ea2f66c0169416cec9\",\n" +
                "    \"name\": \"cn-north-4\"\n" +
                "   }\n" +
                "  }\n" +
                " }\n" +
                "}";

        String strurl="https://iam.cn-north-4.myhuaweicloud.com"+"/v3/auth/tokens?nocatalog=false";

        URL url = new URL(strurl);
        HttpURLConnection urlCon = (HttpURLConnection)url.openConnection();
        urlCon.addRequestProperty("Content-Type", "application/json;charset=utf8");

        urlCon.setDoOutput(true);
        urlCon.setRequestMethod("POST");
        urlCon.setUseCaches(false);
        urlCon.setInstanceFollowRedirects(true);
        urlCon.connect();

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(urlCon.getOutputStream(),"UTF-8"));
        writer.write(postbody);
        writer.flush();
        writer.close();
        Map headers = urlCon.getHeaderFields();
        Set<String> keys = headers.keySet();
        for( String key : keys ){
            String val = urlCon.getHeaderField(key);
            //System.out.println(key+"    "+val);
        }

        String token = urlCon.getHeaderField("X-Subject-Token");
        //System.out.println("X-Subject-Token"+" : "+token);
        return token;
    }

    //getdev(): 获取设备信息
    /*params:
    1.device_id: 设备id
    2. mode:
    2.1 state:设备的在线状态
    2.2 shadow:设备的影子信息
    3. pro:如果选择的设备模式为state，则pro就是要查询、解析的设备属性
     */
    public String getdev(String device_id,String mode,String pro) throws Exception
    {
        String strurl="";
        if(mode.equals("shadow")) strurl="https://iotda.cn-north-4.myhuaweicloud.com"+"/v5/iot/%s/devices/%s/shadow";

        else if(mode.equals("status")) strurl="https://iotda.cn-north-4.myhuaweicloud.com"+"/v5/iot/%s/devices/%s";

        String project_id="0e691b032b8090ea2f66c0169416cec9";
        //String device_id="64aeb5a9ff79602237098522_AndroidAPP";
        //String device_id
        strurl = String.format(strurl, project_id,device_id);
        URL url = new URL(strurl);
        HttpURLConnection urlCon = (HttpURLConnection)url.openConnection();
        urlCon.addRequestProperty("Content-Type", "application/json");
        urlCon.addRequestProperty("X-Auth-Token",token);
        urlCon.connect();
        InputStreamReader is = new InputStreamReader(urlCon.getInputStream());
        BufferedReader bufferedReader = new BufferedReader(is);
        StringBuffer strBuffer = new StringBuffer();
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            strBuffer.append(line);
        }
        is.close();
        urlCon.disconnect();
        String result = strBuffer.toString();
        //System.out.println(result);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readValue(result, JsonNode.class);
        if(mode.equals("shadow"))
        {
            // 获取 radiaton 属性的值
            double radiatonValue = jsonNode.get("shadow").get(0).get("reported").get("properties").get("radiaton").asDouble();


            String radiatonString = Double.toString(radiatonValue);
            //System.out.println("radiaton = " + radiatonString);
            return radiatonString;

        }
        if(mode.equals("status"))
        {
            JsonNode statusNode = jsonNode.get("status");
            String statusstr = statusNode.asText();
            //System.out.println("status = " + statusstr);
            return statusstr;
        }
        return "something is error ";
    }


}
