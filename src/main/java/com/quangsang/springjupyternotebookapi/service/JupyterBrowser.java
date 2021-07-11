package com.quangsang.springjupyternotebookapi.service;

import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.config.SocketConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Service
public class JupyterBrowser {

    @Value("${jupyter.server}")
    private String server;

    @Value("${jupyter.password}")
    private String password;

    public String getToken(){
        String url = server;

        SocketConfig socketConfig = SocketConfig.custom().setSoKeepAlive(true).setSoTimeout(8640000).build();
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(86400000).build();
        CloseableHttpClient client = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig)
                .setDefaultSocketConfig(socketConfig).build();

        HttpPost httpPost = new HttpPost(url);
        //httpPost.setHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");

        List<NameValuePair> params = new ArrayList<>();
        //params.add(new BasicNameValuePair("userName",""));
        //params.add(new BasicNameValuePair("password",password));
        params.add(new BasicNameValuePair("token","8835b488aa72f6658f8e869c9e7b5d62f78663c4345590bf"));
        CloseableHttpResponse response = null;
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
            response = client.execute(httpPost);
            response.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return parseSessionID(response);
    }

    //Create a Jupyter Notebook or Directory
    public void createNoteBook(){

    }
    //Read a Jupyter Notebook or Directory
    public void readnoteBook(){
        String url = server;
       CloseableHttpClient client = HttpClients.createDefault();
        HttpGet request = new HttpGet(url);
    }
    //Rename a Jupyter Notebook or Directory
    public void renameNoteBook(){

    }
    //Update a Jupyter Notebook
    public void updateNoteBook(){

    }
    //Upload a Jupyter NoteBook
    public void UploadNoteBook(){

    }
    //Clone a jupyter NoteBook
    public void cloneNoteBook(){

    }

    //Delete a Jupyter NoteBook or Directory
    public void deletenoteBook(){

    }

    //Run a Jupyter NoteBook
    public void runNoteBook(){

    }

    //Schedule a Jupyter NoteBook
    public void scheduleNoteBook(){

    }

    private String parseSessionID(CloseableHttpResponse response) {
        String jSessionId = "";
        try {
            Header[] header = response.getAllHeaders();
            for (int i = 0; i < header.length; i++) {
                String value = header[i].getValue();
                if (value.contains("JSESSIONID")) {
                    int index = value.indexOf("JSESSIONID =");
                    int endIndex = value.indexOf(";", index);
                    String sessionID = value.substring(index + "JSESSIONID =".length(), endIndex);
                    jSessionId = sessionID;

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jSessionId;
    }
}
