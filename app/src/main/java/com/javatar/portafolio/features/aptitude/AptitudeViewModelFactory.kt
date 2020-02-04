package com.javatar.portafolio.features.aptitude

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.javatar.portafolio.application.ApplicationContext
import javax.inject.Inject

class AptitudeViewModelFactory : ViewModelProvider.Factory {

    @Inject
    lateinit var aptitudeInteractor: AptitudeInteractor

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        ApplicationContext.apiComponent.inject(this)

        if (modelClass.isAssignableFrom(AptitudeViewModel::class.java)) {
            return AptitudeViewModel(aptitudeInteractor) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}