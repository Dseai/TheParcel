package com.syn.theparcel.enty;




import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
/**
 * Created by 孙亚楠 on 2016/11/21.
 */
public class GetContext {
    private StringBuilder response;
    public GetContext(String url){//给定访问的网址,返回网址内容
         try{
                    URL myurl = new URL(url);
                    HttpURLConnection connection =(HttpURLConnection) myurl.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    InputStream in=connection.getInputStream();
                    BufferedReader reader= new BufferedReader(new InputStreamReader(in));
                    response=new StringBuilder();
                    String line;
                    while((line=reader.readLine())!=null){
                        response.append(line);
                    }
             }catch (MalformedURLException e) {
                    e.printStackTrace();
         } catch (IOException e) {
                    e.printStackTrace();
                }
            }
    public String getResponse() {
        return response.toString();
    }
}
