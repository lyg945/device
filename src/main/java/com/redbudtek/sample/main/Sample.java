package com.redbudtek.sample.main;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.redbudtek.sample.tools.HttpTool;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: redbudx
 * Date: 15-1-16
 * Time: 上午9:33
 * To change this template use File | Settings | File Templates.
 */
public class Sample {

    private final String HASH = "test";
    private final String BASE_URL = "http://s1.anylinkcloud.com:8600";
    private String token = null;

    public Sample()
    {
        login();
    }

    /**
     * 登录，获取token
     */
    public void login()
    {
        Map<String,String> map=new HashMap<String,String>();

        map.put("name","user1");
        map.put("password","123456");
        map.put("hash",HASH);
        System.out.println(JSON.toJSONString(map));

        //获取数据
        String url= BASE_URL+"/user/login";

        String result= HttpTool.post(url, JSON.toJSONString(map));
        
        System.out.println(result);

        token=JSON.parseObject(result).getString("data");
    }

    /**
     * 获取实时数据
     */
    public void getCurrentData()
    {
        int deviceId=12; //设备id
        String url= BASE_URL+"/currentdata?token="+token+"&hash="+HASH+"&deviceid="+deviceId;
        String result=HttpTool.get(url);
        System.out.println(result);
    }

    /**
     * 获取历史数据
     */
    public void getHistoryData()
    {
        int deviceId = 12; //设备id
        String itemId = "11"; //数据项id
        long stime = 1417363200000l; //开始时间
        long etime = 1417449600000l; //结束时间

        //获取数据
        String url = BASE_URL + "/historydata?token=" + token + "&hash=" + HASH + "&deviceid=" + deviceId + "&dataitemid=" + itemId+"&stime="+stime+"&etime="+etime;

        String result = HttpTool.get(url);

        System.out.println(result);
    }

    /**
     * 控制和获取结果
     */
    public void control()
    {
        int devid = 12; //设备id

        String itemid = "11"; //数据项id

        int value = 1; //数据项值

        String url = BASE_URL + "/control?token=" + token + "&hash=" + HASH + "&devid=" + devid + "&itemid=" + itemid + "&value=" + value;

        String result = HttpTool.put(url, null);
        JSONObject obj=JSON.parseObject(result);
        System.out.println(result);
        String status = obj.getString("status");
        if ("100".equals(status))
        {
            String sign=obj.getString("data");

            //获取结果
            for (int i = 0; i < 60; i++)
            {
                String res= getResult(devid, itemid, sign);
                if ( res!= null)
                {
                    if(res.equals("0"))
                    {
                        System.out.println("控制成功");
                    }
                    else if(res.equals("3"))
                    {
                        System.out.println("请求超时");
                    }
                    else
                    {
                        System.out.println("请求失败");
                    }

                    break;
                }
                System.out.println("获取结果中...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        }

    }

    /**
     *  获取控制结果（get）
     * @param devid  设备id
     * @param itemid  数据项id
     * @param sign  控制标识符（发送控制命令时得到）
     * @return  控制结果
     */
    public String getResult(int devid,String itemid,String sign)
    {
        String url = BASE_URL + "/control/result?token="+token+"&hash=" + HASH + "&devid=" + devid + "&itemid=" + itemid + "&sign=test";
        String result = HttpTool.get(url);
        System.out.println(result);

        JSONObject obj=JSON.parseObject(result);

        return obj.get("data")==null?null:obj.getString("data") ;
    }


    /**
     * 添加用户（post）
     */
    public void addUser()
    {
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("token", token);
        map.put("hash", HASH);
        Map<String,Object> data=new HashMap<String,Object>();
        data.put("username", "test1");
        data.put("password", "123456");
        data.put("isAdmin", false);
        map.put("data", data);


        String jsonStr = JSON.toJSONString(map);
        System.out.println(jsonStr);
        String url = BASE_URL + "/tenant/users";
        String result = HttpTool.post(url, jsonStr);

        System.out.println(result);

    }

    /**
     * 获取用户信息（get）
     * @return
     */
    public int getUserInfo()
    {
        String name = "test1"; //用户名
        String url = BASE_URL + "/userlist?token=" + token + "&hash=" + HASH + "&name=" + name;
        String result = HttpTool.get(url);
        JSONObject obj=JSON.parseObject(result);
        return obj.getJSONArray("data").getJSONObject(0).getInteger("id");
    }

    /**
     * 删除用户
     * @param userIds 用户id列表
     */
    public void deleteUser(String userIds)
    {
        String url = BASE_URL + "/user?token=" + token + "&hash=" + HASH + "&userids=" + userIds;
        String result = HttpTool.delete(url);
        System.out.println(result);
    }
}
