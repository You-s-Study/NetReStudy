package com.sanhuzhen.netrestudy.http.httpurlconnection

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import com.sanhuzhen.netrestudy.base.BaseActivity
import com.sanhuzhen.netrestudy.databinding.ActivityHttpurlconnectionBinding
import com.sanhuzhen.netrestudy.http.httpurlconnection.util.HttpCallBack
import com.sanhuzhen.netrestudy.http.httpurlconnection.util.HttpUtil
import java.net.HttpURLConnection
import java.net.URL

/**
 * description: 回顾HttpUrlConnection进行网络请求
 * author: sanhuzhen
 * date: 2024/12/19 21:58
 */
class HttpUrlConnectionActivity : BaseActivity<ActivityHttpurlconnectionBinding>() {
    override fun getViewBinding() = ActivityHttpurlconnectionBinding.inflate(layoutInflater)


    private val mHandler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                1 -> {
                    val bundle = msg.obj as Bundle
                    val data = bundle.getString("data")
                    binding.tvResult.text = data
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.btHttpUrlConnection.setOnClickListener {
//            getData()
            Thread {
                HttpUtil.sendGet("https://www.wanandroid.com/banner/json", object : HttpCallBack {
                    override fun onSuccess(result: String) {
                        runOnUiThread {
                            binding.tvResult.text = result
                        }
                        Log.e("TAG", "onSuccess: $result")
                    }

                    override fun onFailure(responseCode: Int, e: Exception) {
                    }
                })
            }.start()
        }
    }

    private fun getData() {
        //开启一个线程进行网络请求，防止主线程堵塞
        Thread {
            //使用 URL 对象的 openConnection（）方法获取到 HttpUrlConnection 对象
            val url = URL("https://www.wanandroid.com/banner/json")
            val httpUrlConnection = url.openConnection() as HttpURLConnection
            //进行全局的网络设置并建立 Http 连接

            httpUrlConnection.apply {
                requestMethod = "GET" //设置请求方式
                connectTimeout = 5000 //设置连接超时时间
                readTimeout = 5000 //设置读取超时时间
                doInput = true //设置是否允许输入流，默认情况下是true
                connect() //建立连接
            }
            val input = httpUrlConnection.inputStream

            val result = input.bufferedReader().use { it.readText() }
            //在主线程更新 UI 或者使用Handler
            runOnUiThread {
                binding.tvResult.text = result
            }
            //用Handler 更新 UI
            //1.用post()
//            mHandler.post {
//                binding.tvResult.text = result
//            }
            //2.用Message
            // 优化后的发送消息
//            val message = mHandler.obtainMessage(1).apply {
//                obj = Bundle().apply {
//                    putString("data", result)
//                }
//            }
//            mHandler.sendMessage(message)
        }.start()
    }
}