package com.sanhuzhen.netrestudy.bean

/**
 * description:
 * author: sanhuzhen
 * date: 2024/12/18 23:22
 */
data class BannerData(
    val data: List<Data>,
    val errorCode: Int,
    val errorMsg: String
)

data class Data(
    val desc: String,
    val id: Int,
    val imagePath: String,
    val isVisible: Int,
    val order: Int,
    val title: String,
    val type: Int,
    val url: String
)