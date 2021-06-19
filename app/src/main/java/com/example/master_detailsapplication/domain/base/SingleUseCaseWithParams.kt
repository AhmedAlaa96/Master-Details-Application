package com.example.master_detailsapplication.domain.base

import io.reactivex.Single


abstract class SingleUseCaseWithParams<in Params,T>(private val schedulers: IOSchedulers = MainIOSchedulers()) : UseCase() {


    internal abstract fun run(input: Params): Single<T>

    fun execute(
        input:Params,
        onSuccess: ((t: T) -> Unit),
        onError: ((t: Throwable) -> Unit),
        onFinished: () -> Unit = {}
    ) {
        disposeLast()
        lastDisposable = run(input)
            .subscribeOn(schedulers.io)
            .observeOn(schedulers.main)
            .doAfterTerminate(onFinished)
            .subscribe(onSuccess, onError)

        lastDisposable?.let {
            compositeDisposable.add(it)
        }
    }
}