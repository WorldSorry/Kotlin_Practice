package com.hykj.datademo.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.hykj.datademo.R
import com.hykj.datademo.data.Book
import com.hykj.datademo.data.User
import com.hykj.datademo.lifecycles.MyObserver
import com.hykj.datademo.mvvm.ThirdViewModel
import com.hykj.datademo.mvvm.ThirdViewModelFactory
import com.hykj.datademo.room.AppDatabase
import kotlinx.android.synthetic.main.activity_third.*

/**
 * map switchMap
 * lifecycles组件
 */
class ThirdActivity : AppCompatActivity() {
    lateinit var viewModel: ThirdViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)
        lifecycle.addObserver(MyObserver())

//        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application)
//            .create(ThirdViewModel::class.java)
//        ViewModelProvider.AndroidViewModelFactory.getInstance()

        //带参数的viewModel
        viewModel =
            ViewModelProvider(this,
                ThirdViewModelFactory(3)
            ).get(ThirdViewModel::class.java)

//        viewModel = ThirdViewModelFactory(3).create(ThirdViewModel::class.java)

        plusOneBtn.setOnClickListener {
            viewModel.plusOne()
        }
        clearBtn.setOnClickListener {
            viewModel.clear()
        }
        getUser.setOnClickListener {
            viewModel.getUserName("100")
        }
        viewModel.counter.observe(this, Observer { count -> infoText.text = count.toString() })
        viewModel.userName.observe(this, Observer { name -> infoText.text = name })

        val database = AppDatabase.getDatabase(this)
        val user1 = User("zhao", "YaChao")
        val user2 = User("zhang", "San")

        val book1 = Book("三国演义", 90, "罗贯中")
        val book2 = Book("红楼梦", 90, "曹雪芹")
        insert.setOnClickListener {
//            database.userDao().insertUser(user1)
//            database.userDao().insertUser(user2)
            database.bookDao().insertBook(book1)
            database.bookDao().insertBook(book2)

        }
        queryAllUser.setOnClickListener {
//            var userList = database.userDao().loadAllUser()
//            for (user in userList) {
//                Log.e("ThirdActivity", "lastName:${user.lastName},fistName:${user.fastName}")
//            }
            val loadAllBooks = database.bookDao().loadAllBooks()
            for (book in loadAllBooks) {
                Log.e(
                    "ThirdActivity", "bookName:${book.name}," +
                            "fistName:${book.pages}，author:${book.author}"
                )
            }
        }


    }
}

