package com.javatar.portafolio.features.projects

import androidx.lifecycle.MutableLiveData
import com.javatar.portafolio.commons.viewmodels.BaseViewModel
import com.javatar.portafolio.models.Project
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ProjectViewModel(projectInteractor: ProjectInteractor) : BaseViewModel() {

    var projectInteractor: ProjectInteractor = projectInteractor

    var projectLiveData: MutableLiveData<List<Project>> = MutableLiveData()

    fun loadProject() {
        compositeDisposable.add(
            projectInteractor.loadProject()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(::showProgress)
                .doAfterTerminate(::hideProgress)
                .doOnSuccess(::setSuccess)
                .subscribe(::setData, ::setError)
        )
    }

    fun setData(project: List<Project>) {
        projectLiveData.value = project
    }

}