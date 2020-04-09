package com.example.demo.service.serviceImpl;

import com.example.demo.entity.message.*;
import com.example.demo.service.WxService;
import com.example.demo.util.AutoChatUtil;
import com.example.demo.util.GetTokenUtil;
import com.thoughtworks.xstream.XStream;
import net.sf.json.JSONObject;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.servlet.ServletInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.*;

@Service
public class WxServiceImpl implements WxService {

    @Value("${WEIXIN_TOKEN}")    //  @Value("${bpm.webservice.employee.apply}")
    private  String  TOKEN;

    @Value("${APPKEY}")
    private String APPKEY;



    /**
     * 校验验证签名
     *
     * @param timestamp
     * @param nonce
     * @param signature
     * @return
     */
    @Override
    public boolean check(String timestamp, String nonce, String signature) {

//1）将token、timestamp、nonce三个参数进行字典序排序
// 2）将三个参数字符串拼接成一个字符串进行sha1加密
// 3）开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
        String[] str = new String[]{TOKEN,timestamp,nonce};
        Arrays.sort(str);

        String s = str[0]+str[1]+str[2];
        String sig = sha1(s); //sha1加密

        System.out.println("加密后的字符："+sig);
        System.out.println("加密前传进的字符："+signature);

        return sig.equalsIgnoreCase(signature);
    }

    /**
     * 解析传来的xml数据包,返回map
     *
     * @param inputStream
     * @return
     */
    @Override
    public Map<String, String> parseRequest(ServletInputStream inputStream) {

        Map<String,String> map = new HashMap<>();
        SAXReader reader = new SAXReader();
        try {
            Document read = reader.read(inputStream);
            Element rootElement = read.getRootElement();
            List<Element> elements = rootElement.elements();
            for (Element e :elements
                 ) {
                map.put(e.getName(),e.getStringValue());
            }

        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return map;
    }

    /**
     * c处理事件和消息，返回xml包
     * @param map
     * @return
     */
    @Override
    public String getResponse(Map<String, String> map) {
        BaseMessage msg = new BaseMessage();
        String msgType = map.get("MsgType");//类型
        switch (msgType){
            case "text":
               msg = dealText(map);//处理文本消息
                break;

            case "image"://图像消息

                //todo
                //图像识别
                msg = dealImage(map);
                break;
            case "video"://视频信息
                break;
            case "shortvideo"://短视频
                break;
            case "voice"://语音
                break;
            case "link"://连接
                break;
            case "location"://位置
                break;
            case "event":

                //处理事件
                msg = dealEvent(map);
                break;
            default:
                break;
        }

        //将消息对象返回一个xml
        if(msg!=null){
            String s =  beanToXml(msg);
            return s;
        }

        return null;
    }




    /**
     * 处理图像消息
     * （
     *     解析图片中文字信息
     * ）
     * @param map
     * @return
     */
    private BaseMessage dealImage(Map<String, String> map) {

        //1.取出图片地址
        String path = map.get("PicUrl");

        //2.调用接口返回解析出数据
        //ai.baidu
        //3.返回


        /*
        *   //百度AI
            public static final String APP_ID = "";
            public static final String API_KEY = "";
            public static final String SECRET_KEY = "";

        <dependency>
            <groupId>com.baidu.aip</groupId>
            <artifactId>java-sdk</artifactId>
            <version></version>
        </dependency>

            // 初始化一个AipOcr
            AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);
            // 可选：设置网络连接参数
            client.setConnectionTimeoutInMillis(2000);
            client.setSocketTimeoutInMillis(60000);
            // 调用接口
            String path = requestMap.get("PicUrl");

            //进行网络图片的识别
            org.json.JSONObject res = client.generalUrl(path, new HashMap<String,String>());
            String json = res.toString();
            //转为jsonObject
            JSONObject jsonObject = JSONObject.fromObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("words_result");
            Iterator<JSONObject> it = jsonArray.iterator();
            StringBuilder sb = new StringBuilder();
            while(it.hasNext()) {
                JSONObject next = it.next();
                sb.append(next.getString("words"));
            }
            return new TextMessage(requestMap, sb.toString());
            * */

        return new TextMessage(map, path);

    }


    /**
     * 处理事件(
     * 有多种
     * 1.关注取消事件
     * 2.自定义菜单事件  ： click   view
     * 3.地理位置
     * 等等
     * )
     *
     * @param map
     * @return
     */
    private BaseMessage dealEvent(Map<String, String> map) {
        BaseMessage msg = new BaseMessage();;
        String event = map.get("Event");

        switch (event){
            case "CLICK":
                //自定义菜单的
                //点击按钮

               msg =  dealClickMenu(map);
                break;
            case "VIEW":
                //自定义菜单
                //view按钮

                break;
            case "LOCATION":
                //位置
                break;
            case "SCAN":
                //扫描
                break;
            default:
                break;
        }
        return msg;
    }

    /**
     * 处理自定义菜单下点击按钮操作
     *
     * @param map
     */
    private BaseMessage dealClickMenu(Map<String, String> map) {

        //EventKey<-对应->自定义菜单时写的 key

        String eventKey = map.get("EventKey");

        switch (eventKey){
            case "1":
                //点击一级菜单点击

                //do something{}

                return new TextMessage(map,"你点击了一级菜单。");

            case"31":
                //点击二级菜单点击

                //生成二维码
                String qrCodeTicket = GetTokenUtil.getQrCodeTicket();

                List<Article> articles  = new ArrayList<>();
                String erweima = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket="+qrCodeTicket;
                try {
                    articles.add(
                            new Article("二维码",
                                    "description",
                                    erweima, erweima));
                } catch (Exception e) {
                    e.printStackTrace();
                }

                NewsMessage news = new NewsMessage(map, articles);
                return news;

                //do something{}
        }
        return null;
    }


    /**
     * 把信息对象转化为xml包数据
     * @param msg
     * @return
     */
    private String beanToXml(BaseMessage msg) {
        XStream stream = new XStream();
        //转类到xml
        stream.processAnnotations(TextMessage.class);
        stream.processAnnotations(ImageMessage.class);
        stream.processAnnotations(VideoMessage.class);
        stream.processAnnotations(VoiceMessage.class);
        stream.processAnnotations(NewsMessage.class);
        stream.processAnnotations(MusicMessage.class);
        String s = stream.toXML(msg);
        return s;
    }

    /**
     * 处理文本消息
     * @param map
     * @return
     */
    private BaseMessage dealText(Map<String, String> map) {

        //聊天机器人（文本信息）
        String content = map.get("Content");//用户发来的信息


        //测试返回图文消息
        if(content.equals("测试返回图文")){
            List<Article> articles  = new ArrayList<>();
            articles.add(
                    new Article("title",
                            "description",
                    "http://mmbiz.qpic.cn/mmbiz_jpg/GnaTLFBJttSjicwBtXcHCNVkKGg0I52J4D8ao126Ibw86wR6bVfsoe4us2ESs5h9kKMx88c1PJp52cBnJGrbQAQ/0", "www.baidu.com"));

            NewsMessage news = new NewsMessage(map, articles);
            return news;
        }



        //String  reply = chat(content);//调用图灵机器人聊天返回消息
        //todo  报错，后续重写
        //TextMessage text = new TextMessage(map,reply);

        TextMessage text = new TextMessage(map,content);
        return text;
    }


    /**
     * 调用图灵机器人聊天
     *
     * @param content 用户发的消息
     * @return
     */
    private String chat(String content) {
        String result =null;
        String url ="http://op.juhe.cn/robot/index";//请求接口地址
        Map params = new HashMap();//请求参数
        params.put("key",APPKEY);//您申请到的本接口专用的APPKEY
        params.put("info",content);//要发送给机器人的内容，不要超过30个字符
        params.put("dtype","");//返回的数据的格式，json或xml，默认为json
        params.put("loc","");//地点，如北京中关村
        params.put("lon","");//经度，东经116.234632（小数点后保留6位），需要写为116234632
        params.put("lat","");//纬度，北纬40.234632（小数点后保留6位），需要写为40234632
        params.put("userid","");//1~32位，此userid针对您自己的每一个用户，用于上下文的关联
        try {
            result = AutoChatUtil.net(url, params, "GET");
            //解析json
            JSONObject jsonObject = JSONObject.fromObject(result);
            //取出error_code
            int code = jsonObject.getInt("error_code");
            if(code!=0) {
                return null;
            }
            //取出返回的消息的内容
            String resp = jsonObject.getJSONObject("result").getString("text");
            return resp;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 加密
     * @param s
     * @return
     */
    private String sha1(String s) {

        try {
            MessageDigest md = MessageDigest.getInstance("sha1");//md5

            //加密
            byte[] digest = md.digest(s.getBytes());

            //处理

            //高四位右移动四位
            // 0 0 0 0 1 1 1 1   & 与运算  （15）

            //低四位处理   直接& 与运算  （15）


            char [] chars ={'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
            StringBuilder stringBuilder = new StringBuilder();

            for(Byte b : digest){
                 stringBuilder.append(chars[(b>>4)&15]);//得到0-15之间数，对应char数组
                stringBuilder.append(chars[b&15]);
            }
            return stringBuilder.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取token
     */
    @Override
    public String getToken() {
        String token = GetTokenUtil.getToken();
        return token;
    }


    /**
     * 获取已关注的用户的基本信息
     *
     * */
    public  String getUserInfo(String openid) {
        String url="https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
        url = url.replace("ACCESS_TOKEN", (getToken())).replace("OPENID", openid);
        String result = GetTokenUtil.getFromUrl(url);
        return result;
    }


    /**获取未关注用户信息，网页授权
     *
     *
     *1 .引导用户授权
     *
     *if(msg.equals("登录")) {
     * 			String url="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxb6777fffdf5b64a4&redirect_uri=http://www.6sdd.com/weixin/GetUserInfo&response_type=code&scope=snsapi_userinfo#wechat_redirect";
     * 			TextMessage tm = new TextMessage(requestMap, "点击<a href=\""+url+"\">这里</a>登录");
     * 			return tm;
     *                }
     *
     *
     *
     *2 .获取code
     * 		String code = request.getParameter("code");
     * 		//换取accesstoken的地址
     * 		String url="https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
     * 		url=url.replace("APPID", "wxb6777fffdf5b64a4").replace("SECRET", "6b55d3bb4d9c5373c8a30915d900ca13").replace("CODE", code);
     * 		String result = Util.get(url);
     * 		String at = JSONObject.fromObject(result).getString("access_token");
     * 		String openid = JSONObject.fromObject(result).getString("openid");
     * 3 .拉取用户的基本信息
     * 		url="https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
     * 		url=url.replace("ACCESS_TOKEN", at).replace("OPENID", openid);
     * 		result = Util.get(url);
     * 		System.out.println(result);
     */

}
