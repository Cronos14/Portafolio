package com.javatar.portafolio.commons.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel : ViewModel() {

    var compositeDisposable: CompositeDisposable = CompositeDisposable()

    var successLiveData: MutableLiveData<Any> = MutableLiveData()
    var errorLiveData: MutableLiveData<Throwable> = MutableLiveData()

    var showProgressLiveData: MutableLiveData<Any> = MutableLiveData()
    var hideProgressLiveData: MutableLiveData<Any> = MutableLiveData()

    open fun setSuccess(any: Any) {
        successLiveData.value = Any()
    }

    open fun setError(throwable: Throwable) {
        errorLiveData.value = throwable
    }

    open fun showProgress(disposable: Disposable) {
        showProgressLiveData.value = Any()
    }

    open fun hideProgress() {
        hideProgressLiveData.value = Any()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}