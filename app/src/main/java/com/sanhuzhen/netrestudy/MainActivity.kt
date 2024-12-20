package com.sanhuzhen.netrestudy

import android.content.Intent
import android.os.Bundle
import com.sanhuzhen.netrestudy.base.BaseActivity
import com.sanhuzhen.netrestudy.databinding.ActivityMainBinding
import com.sanhuzhen.netrestudy.http.httpurlconnection.HttpUrlConnectionActivity

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply {
            btHttpUrlConnection.setOnClickListener {
                startActivity(Intent(this@MainActivity, HttpUrlConnectionActivity::class.java))
            }
            btOkhttp.setOnClickListener { }
        }

    }
}