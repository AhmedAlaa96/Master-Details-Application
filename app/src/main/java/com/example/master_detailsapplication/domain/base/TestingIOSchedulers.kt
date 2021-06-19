package com.example.master_detailsapplication.domain.base

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class TestingIOSchedulers: IOSchedulers{
    override val io: Scheduler
        get() = Schedulers.trampoline()
    override val main: Scheduler
        get() = Schedulers.trampoline()

}