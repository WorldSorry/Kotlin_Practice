package com.hykj.datademo.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.work.OneTimeWorkRequest
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.hykj.datademo.R
import com.hykj.datademo.workmanager.SimpleWorker
import kotlinx.android.synthetic.main.activity_work_manager.*
import java.util.concurrent.TimeUnit

class WorkManagerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_manager)
        //单此运行的后台任务请求
//        var request = OneTimeWorkRequest.Builder(SimpleWorker::class.java).build()
        //构建周期行的后台任务，间隔不能少于15分钟
        var request1 =
            PeriodicWorkRequest
                .Builder(SimpleWorker::class.java, 15, TimeUnit.SECONDS).build()

        doWorkBtn.setOnClickListener {

            var request = OneTimeWorkRequest.Builder(SimpleWorker::class.java).build()
            WorkManager.getInstance(this).enqueue(request)
        }
    }
}