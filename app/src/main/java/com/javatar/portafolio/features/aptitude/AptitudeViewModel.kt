package com.javatar.portafolio.features.aptitude

import androidx.lifecycle.MutableLiveData
import com.javatar.portafolio.commons.viewmodels.BaseViewModel
import com.javatar.portafolio.models.Aptitude
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AptitudeViewModel(aptitudeInteractor: AptitudeInteractor) : BaseViewModel() {

    var aptitudeInteractor: AptitudeInteractor = aptitudeInteractor
    var aptitudeLiveData: MutableLiveData<List<Aptitude>> = MutableLiveData()

    fun loadAptitude() {
        compositeDisposable.add(
            aptitudeInteractor.loadAptitude()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(::showProgress)
                .doAfterTerminate(::hideProgress)
                .doOnSuccess(::setSuccess)
                .subscribe(::setData, ::setError)
        )
    }

    fun setData(aptitude: List<Aptitude>) {
        aptitudeLiveData.value = aptitude
    }
}