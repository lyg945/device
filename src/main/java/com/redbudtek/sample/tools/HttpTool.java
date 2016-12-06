package com.redbudtek.sample.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpTool {


    public static String post(String urlStr,String messageOut) {
        HttpURLConnection httpConn = null;
        URL url = null;
        String messageIn="";
        try {
            url = new URL(urlStr);
            httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setRequestMethod("POST");
            httpConn.setDoInput(true);
            httpConn.setDoOutput(true);
            httpConn.setRequestProperty("Accept-Charset", "UTF-8");
            httpConn.setRequestProperty("Content-Type", "application/json");
            PrintWriter out = new PrintWriter(httpConn.getOutputStream());
            // out.println(URLEncoder.encode(this.messageOut, "utf-8"));
            out.println(messageOut);
            out.flush();
            /*
             * DataOutputStream out = new DataOutputStream(
             * httpConn.getOutputStream()); JSONObject obj = new JSONObject();
             * obj.put("deviceid", 26); System.out.println(obj.toString());
             * out.writeBytes(obj.toString()); out.flush();
             */

            // DataInputStream bin = new
            // DataInputStream(httpConn.getInputStream());
            BufferedReader bin = new BufferedReader(new InputStreamReader(
                    httpConn.getInputStream(),"utf-8"));
            StringBuffer buff = new StringBuffer();
            String line;
            while ((line = bin.readLine()) != null) {
                buff.append(line);
            }
            messageIn = buff.toString();
            out.close();
            bin.close();
            httpConn.disconnect();
        } catch (ConnectException ce) {
            ce.printStackTrace();
        } catch (IOException ie) {
            ie.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return messageIn;
    }

    public static String put(String urlStr,String messageOut) {
        HttpURLConnection httpConn = null;
        URL url = null;
        String messageIn="";
        try {
            url = new URL(urlStr);
            httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setRequestMethod("PUT");
            httpConn.setDoInput(true);
            httpConn.setDoOutput(true);
            httpConn.setRequestProperty("Accept-Charset", "UTF-8");
            httpConn.setRequestProperty("Content-Type", "application/json");
            PrintWriter out = new PrintWriter(httpConn.getOutputStream());
            // out.println(URLEncoder.encode(this.messageOut, "utf-8"));
            out.println(messageOut);
            out.flush();
            /*
             * DataOutputStream out = new DataOutputStream(
             * httpConn.getOutputStream()); JSONObject obj = new JSONObject();
             * obj.put("deviceid", 26); System.out.println(obj.toString());
             * out.writeBytes(obj.toString()); out.flush();
             */

            // DataInputStream bin = new
            // DataInputStream(httpConn.getInputStream());
            BufferedReader bin = new BufferedReader(new InputStreamReader(
                    httpConn.getInputStream(),"utf-8"));
            StringBuffer buff = new StringBuffer();
            String line;
            while ((line = bin.readLine()) != null) {
                buff.append(line);
            }
            messageIn = buff.toString();
            out.close();
            bin.close();
            httpConn.disconnect();
        } catch (ConnectException ce) {
            ce.printStackTrace();
        } catch (IOException ie) {
            ie.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return messageIn;
    }

    public static String delete(String urlStr) {
        HttpURLConnection httpConn = null;
        URL url = null;
        String messageIn="";
        try {
            url = new URL(urlStr);
            httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setRequestMethod("DELETE");
            httpConn.setDoInput(true);
            httpConn.setDoOutput(true);
            httpConn.setRequestProperty("Accept-Charset", "UTF-8");
            httpConn.setRequestProperty("Content-Type", "application/json");


            // DataInputStream bin = new
            // DataInputStream(httpConn.getInputStream());
            BufferedReader bin = new BufferedReader(new InputStreamReader(
                    httpConn.getInputStream(),"utf-8"));
            StringBuffer buff = new StringBuffer();
            String line;
            while ((line = bin.readLine()) != null) {
                buff.append(line);
            }
            messageIn = buff.toString();

            bin.close();
            httpConn.disconnect();
        } catch (ConnectException ce) {
            ce.printStackTrace();
        } catch (IOException ie) {
            ie.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return messageIn;
    }

    public static String get(String urlStr) {
        HttpURLConnection httpConn = null;
        URL url = null;
        String messageIn="";
        try {
            url = new URL(urlStr);
            httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setRequestMethod("GET");
            httpConn.setRequestProperty("Accept-Charset", "utf-8");
            httpConn.setRequestProperty("Content-Type", "application/json");

            // DataInputStream bin = new
            // DataInputStream(httpConn.getInputStream());
            BufferedReader bin = new BufferedReader(new InputStreamReader(
                    httpConn.getInputStream(),"utf-8"));
            StringBuffer buff = new StringBuffer();
            String line;
            while ((line = bin.readLine()) != null) {
                buff.append(line);
            }
            messageIn = buff.toString();
            bin.close();
            httpConn.disconnect();
        } catch (ConnectException ce) {
        } catch (IOException ie) {
        } catch (Exception e) {
        }
        return messageIn;
    }

}
