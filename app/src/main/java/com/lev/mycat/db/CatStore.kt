package com.lev.mycat.db

import android.content.Context
import android.os.Handler
import android.os.HandlerThread
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CatStore
@Inject constructor(context: Context) {

    companion object {
        const val WORKER_NAME = "CatStore"
    }

    private var dbHandler: Handler? = null

    private val dao: CatDao
    private val handler = Handler()
    private val taskQueue: MutableList<Runnable> = mutableListOf()
    private val worker = object : HandlerThread(WORKER_NAME) {
        override fun onLooperPrepared() {
            super.onLooperPrepared()
            dbHandler = Handler(looper)
        }
    }

    init {
        worker.start()
        dao = CatDatabase.getInstance(context)!!.catDao()
    }

    fun save(cat: Cat,callback: () -> Unit) = postTask(Runnable {
        dao.save(CatEntity(cat))
        callback()
    })

    fun load(callback: (Cat?) -> Unit) = postTask(Runnable {
        val cat = dao.load().firstOrNull()
        handler.post { callback(cat) }
    })

    fun clean() = postTask(Runnable {
        dao.clear()
    })

    private fun postTask(task: Runnable) {
        if (handlerIsReady()) {
            purgeTaskQueue()
            dbHandler!!.post(task)
            return
        }
        taskQueue.add(task)
    }

    private fun handlerIsReady(): Boolean {
        return dbHandler != null
    }

    private fun purgeTaskQueue() {
        if (taskQueue.isEmpty()) {
            return
        }
        taskQueue.forEach { dbHandler!!.post(it) }
        taskQueue.clear()
    }
}