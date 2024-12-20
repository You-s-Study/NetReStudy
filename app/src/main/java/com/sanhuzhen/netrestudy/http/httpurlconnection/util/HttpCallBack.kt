package com.sanhuzhen.netrestudy.http.httpurlconnection.util

/**
 * description: 利用接口回调处理网络请求结果
 * author: sanhuzhen
 * date: 2024/12/20 23:28
 */
interface HttpCallBack {
    fun onSuccess(result: String)
    fun onFailure(responseCode: Int, e: Exception)
}