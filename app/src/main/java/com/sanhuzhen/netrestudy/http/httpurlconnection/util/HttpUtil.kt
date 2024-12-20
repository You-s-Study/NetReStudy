package com.sanhuzhen.netrestudy.http.httpurlconnection.util

import java.net.HttpURLConnection
import java.net.URL

/**
 * description: 对HttpURLConnection进行封装，目前只封装了GET方法
 * author: sanhuzhen
 * date: 2024/12/20 23:13
 */
object HttpUtil {

    /**
     * 获取HttpURLConnection
     * @param url 请求地址
     * @param method 请求方式
     * @return HttpURLConnection
     */
    private fun getHttpURLConnection(url: String, method: String): HttpURLConnection{
        val conn = URL(url).openConnection() as HttpURLConnection
        try {
            conn.apply {
                requestMethod = method // 请求方式
                readTimeout = 5000// 读取超时 单位 毫秒
                connectTimeout = 5000// 连接超时 单位 毫秒
                setRequestProperty("Content-Type", "application/json") // 设置请求头格式为json格式
                setRequestProperty("Connection", "close") // 设置长连接
                doInput = true
                doOutput = if (method == "POST") true else false
            }
        }catch (e: Exception){
            e.printStackTrace()
        }
        return conn
    }

    /**
     * 发送Get请求
     *
     * @param url 请求地址
     * @return 请求结果
     */
    fun sendGet(url: String): String {
        val conn = getHttpURLConnection(url, "GET")
        return try {
            conn.connect()
            val responseCode = conn.responseCode
            if (responseCode == 200) {
                val response = conn.inputStream.bufferedReader().readText()
                response
            } else {
                "请求失败，错误码：$responseCode"
            }
        } catch (e: Exception) {
            e.printStackTrace()
            "请求失败，错误信息：${e.message}"
        }
    }

    /**
     * 发送Get请求，带有回调函数
     *
     * @param url 请求地址
     * @param callBack 回调函数
     * @return
     */
    fun sendGet(url: String, callBack: HttpCallBack) {
        val conn = getHttpURLConnection(url, "GET")
        return try {
            conn.connect()
            val responseCode = conn.responseCode
            if (responseCode == 200) {
                val response = conn.inputStream.bufferedReader().readText()
                callBack.onSuccess(response)
            } else {
                callBack.onFailure(conn.responseCode, Exception("请求失败，错误码：$responseCode"))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            callBack.onFailure(conn.responseCode,e)
        }
    }
}