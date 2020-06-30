package com.hykj.datademo.activity

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import com.hykj.datademo.R
import com.hykj.datademo.sql.MySQLiteOpenHelper
import kotlinx.android.synthetic.main.activity_main.*
import java.io.*

@RequiresApi(Build.VERSION_CODES.P)
class MainActivity : AppCompatActivity(), View.OnClickListener {

    val helper by lazy { MySQLiteOpenHelper(this@MainActivity, "BookStore.db", null, 1) }
    val manager by lazy { getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager }
    val channerlId: String = "01111"
    val pendingIntent by lazy {
        PendingIntent.getActivity(
            this, 0,
            Intent(this, LoginActivity::class.java), 0
        )
    }

    @SuppressLint("ServiceCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        send_notification.setOnClickListener(this)
        tv_read.setOnClickListener(this)
        btn_save.setOnClickListener(this)
        create_database.setOnClickListener(this)
        go_second.setOnClickListener(this)
        but_big_text.setOnClickListener(this)
        but_big_picture.setOnClickListener(this)
        thirdActivity.setOnClickListener(this)

        manager.createNotificationChannel(
            NotificationChannel(
                channerlId,
                "热门推荐",
                NotificationManager.IMPORTANCE_DEFAULT
            )
        )
    }

    fun save(inputText: String) {
        try {
            val output = openFileOutput("data", Context.MODE_PRIVATE)
            val bufferedWriter = BufferedWriter(OutputStreamWriter(output))
            bufferedWriter.use {
                it.write(inputText)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun read(): String {
        val content = StringBuilder()
        val input = openFileInput("data")
        val bufferedReader = BufferedReader(InputStreamReader(input))
        bufferedReader.use {
            bufferedReader.forEachLine {
                content.append(it)
            }
        }
        return content.toString()
    }

    fun spSave(data: String) {
        getSharedPreferences("data", Context.MODE_PRIVATE).edit().apply {
            putString("data", data)
            apply()
        }
    }


    fun spRead(key: String): String {
        val sp = getSharedPreferences("data", Context.MODE_PRIVATE)
        return sp.getString(key, "").toString()
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btn_save -> spSave(et_input_text.text.toString())
            R.id.btn_read -> tv_read.text = spRead("data")
            R.id.create_database -> helper.readableDatabase
            R.id.go_second -> startActivity(Intent(this, SecondActivity::class.java))
            R.id.thirdActivity -> startActivity(Intent(this, ThirdActivity::class.java))
            R.id.send_notification ->
                manager.notify(
                    1, NotificationCompat.Builder(this, channerlId)
                        .setContentText("标题")
                        .setContentText("内容")
                        .setContentIntent(pendingIntent)
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setAutoCancel(true)//点击通知取消
                        .build()
                )
            R.id.but_big_text ->
                manager.notify(
                    2, NotificationCompat.Builder(this, channerlId)
                        .setContentText("相互支持 合作共赢")
                        .setStyle(
                            NotificationCompat.BigTextStyle()
                                .bigText(
                                    getString(R.string.context)
                                )
                        )
                        .setContentIntent(pendingIntent)
                        .setSmallIcon(R.drawable.touxiang)
                        .setAutoCancel(true)//点击通知取消
                        .build()
                )
            R.id.but_big_picture ->
                manager.notify(
                    2, NotificationCompat.Builder(this, channerlId)
                        .setContentText("相互支持 合作共赢")
                        .setStyle(
                            NotificationCompat.BigPictureStyle()
                                .bigPicture(BitmapFactory.decodeResource(resources,
                                    R.drawable.bg3
                                ))
                        )
                        .setContentIntent(pendingIntent)
                        .setSmallIcon(R.drawable.touxiang)
                        .setAutoCancel(true)//点击通知取消
                        .build()
                )
        }

    }


}