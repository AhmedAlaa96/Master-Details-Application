package com.example.master_detailsapplication.domain.base

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

interface IOSchedulers {
    val io: Scheduler
    val main: Scheduler
}


