package com.example.demo.util;

/**
 * com.example.demo.util
 *
 * @author ypl
 * @create 2020-04-09 18:18
 */

//新增临时素材
public class UploadFileUtil {


    /**
     * 上传临时素材
     * @param path	上传的文件的路径
     * @param type	上传的文件类型
     * @return
     */
//    public static String upload(String path,String type) {
//        File file = new File(path);
//        //地址
//        String url="https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";
//        url = url.replace("ACCESS_TOKEN", getAccessToken()).replace("TYPE", type);
//        try {
//            URL urlObj = new URL(url);
//            //强转为案例连接
//            HttpsURLConnection conn = (HttpsURLConnection) urlObj.openConnection();
//            //设置连接的信息
//            conn.setDoInput(true);
//            conn.setDoOutput(true);
//            conn.setUseCaches(false);
//            //设置请求头信息
//            conn.setRequestProperty("Connection", "Keep-Alive");
//            conn.setRequestProperty("Charset", "utf8");
//            //数据的边界
//            String boundary = "-----"+System.currentTimeMillis();
//            conn.setRequestProperty("Content-Type", "multipart/form-data;boundary="+boundary);
//            //获取输出流
//            OutputStream out = conn.getOutputStream();
//            //创建文件的输入流
//            InputStream is = new FileInputStream(file);
//            //第一部分：头部信息
//            //准备头部信息
//            StringBuilder sb = new StringBuilder();
//            sb.append("--");
//            sb.append(boundary);
//            sb.append("\r\n");
//            sb.append("Content-Disposition:form-data;name=\"media\";filename=\""+file.getName()+"\"\r\n");
//            sb.append("Content-Type:application/octet-stream\r\n\r\n");
//            out.write(sb.toString().getBytes());
//            System.out.println(sb.toString());
//            //第二部分：文件内容
//            byte[] b = new byte[1024];
//            int len;
//            while((len=is.read(b))!=-1) {
//                out.write(b, 0, len);
//            }
//            is.close();
//            //第三部分：尾部信息
//            String foot = "\r\n--"+boundary+"--\r\n";
//            out.write(foot.getBytes());
//            out.flush();
//            out.close();
//            //读取数据
//            InputStream is2 = conn.getInputStream();
//            StringBuilder resp = new StringBuilder();
//            while((len=is2.read(b))!=-1) {
//                resp.append(new String(b,0,len));
//            }
//            return resp.toString();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
}
