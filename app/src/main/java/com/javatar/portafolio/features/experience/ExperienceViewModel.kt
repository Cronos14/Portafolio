package com.javatar.portafolio.features.experience

import androidx.lifecycle.MutableLiveData
import com.javatar.portafolio.commons.viewmodels.BaseViewModel
import com.javatar.portafolio.models.Experience
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ExperienceViewModel(experienceInteractor: ExperienceInteractor) : BaseViewModel() {

    var experienceInteractor: ExperienceInteractor = experienceInteractor
    var experienceLiveData: MutableLiveData<List<Experience>> = MutableLiveData()

    fun loadExperience() {
        compositeDisposable.add(
            experienceInteractor.loadExperience()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(::showProgress)
                .doAfterTerminate(::hideProgress)
                .doOnSuccess(::setSuccess)
                .subscribe(::setData, ::setError)
        )
    }

    fun setData(experience: List<Experience>) {
        experienceLiveData.value = experience
    }
}