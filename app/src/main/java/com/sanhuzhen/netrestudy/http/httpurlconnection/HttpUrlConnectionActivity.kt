package com.sanhuzhen.netrestudy.http.httpurlconnection

import android.os.Bundle
import com.sanhuzhen.netrestudy.base.BaseActivity
import com.sanhuzhen.netrestudy.databinding.ActivityHttpurlconnectionBinding
import java.net.URL

/**
 * description: 回顾HttpUrlConnection进行网络请求
 * author: sanhuzhen
 * date: 2024/12/19 21:58
 */
class HttpUrlConnectionActivity: BaseActivity<ActivityHttpurlconnectionBinding>() {
    override fun getViewBinding() = ActivityHttpurlconnectionBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.btHttpUrlConnection.setOnClickListener {
            getData()
        }
    }
    private fun getData() {
        //开启一个线程进行网络请求，防止主线程堵塞
        Thread{

            //使用 URL 对象的 openConnection（）方法获取到 HttpUrlConnection 对象
            val url = URL("https://www.wanandroid.com/banner/json")
            val httpUrlConnection = url.openConnection()

            //进行全局的网络设置并建立 Http 连接
        }
    }
}