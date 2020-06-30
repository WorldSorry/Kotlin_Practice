package com.hykj.datademo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

    }

    override fun onClick(p0: View?) {

        when(p0?.id){
        }
    }
}