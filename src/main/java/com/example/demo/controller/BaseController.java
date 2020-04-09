package com.example.demo.controller;

import com.example.demo.service.WxService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@RestController
@RequestMapping("/wx")
public class BaseController extends HttpServlet {

    @Resource
    private WxService wxServiceImpl;

    @RequestMapping(value = "/test", method = {RequestMethod.GET, RequestMethod.POST})
    public String view(HttpServletRequest request ,HttpServletResponse response){
        response.setContentType("text/html;charset=utf-8");
        Boolean isGet = request.getMethod().toLowerCase().equals("get");
        ///验证
        if(isGet){
            System.out.println("get");
            /**
             * signature
             * 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
             * timestamp
             * 时间戳
             * nonce
             * 随机数
             * echostr
             * 随机字符串
             */

            String signature = request.getParameter("signature");
            String timestamp = request.getParameter("timestamp");
            String nonce = request.getParameter("nonce");
            String echostr = request.getParameter("echostr");
            System.out.println(signature);
            System.out.println(timestamp);
            System.out.println(nonce);
            System.out.println(echostr);
            //校验请求
            if(wxServiceImpl.check(timestamp,nonce,signature)){
                System.out.println("验证成功");
                PrintWriter writer = null;
                try {
                    writer = response.getWriter();
                    writer.print(echostr);
                    writer.flush();
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                writer.print(echostr);
            }else {
                System.out.println("验证失败");
            }


        }else {
            //处理消息和事件推送
            System.out.println("post");
//            try {
//                ServletInputStream inputStream = request.getInputStream();
//                StringBuilder s = new StringBuilder();
//                byte[] b=new byte[1024];
//                int len ;
//                while((len=inputStream.read(b))!=-1){
//                    s.append(new String(b,0,len));
//                }
//                System.out.println(s.toString());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }

            //处理消息和事件推送

            try {
                //接收消息转为map
                Map<String ,String>  map =  wxServiceImpl.parseRequest(request.getInputStream());
                System.out.println(map);

                //处理事件消息；回复数据
                String resXml = wxServiceImpl.getResponse(map);
                System.out.println("回复消息"+resXml);
                PrintWriter writer = response.getWriter();
                writer.print(resXml);
                writer.flush();;
                writer.close();

            } catch (IOException e) {
                e.printStackTrace();
            }


        }


        return  null;

    }


}
