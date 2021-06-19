package com.example.master_detailsapplication.domain.base

import io.reactivex.Single


abstract class SingleUseCase<T>(private val schedulers: IOSchedulers = MainIOSchedulers()) : UseCase() {

    internal abstract fun run(): Single<T>



    fun execute(
        onSuccess: ((t: T) -> Unit),
        onError: ((t: Throwable) -> Unit),
        onFinished: () -> Unit = {}
    ) {
        disposeLast()
        lastDisposable = run()
            .subscribeOn(schedulers.io)
            .observeOn(schedulers.main)
            .doAfterTerminate(onFinished)
            .subscribe(onSuccess, onError)

        lastDisposable?.let {
            compositeDisposable.add(it)
        }
    }
}