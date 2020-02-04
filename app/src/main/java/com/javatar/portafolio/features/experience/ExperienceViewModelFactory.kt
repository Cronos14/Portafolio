package com.javatar.portafolio.features.experience

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.javatar.portafolio.application.ApplicationContext
import javax.inject.Inject

class ExperienceViewModelFactory : ViewModelProvider.Factory {

    @Inject
    lateinit var experienceInteractor: ExperienceInteractor

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        ApplicationContext.apiComponent.inject(this)

        if (modelClass.isAssignableFrom(ExperienceViewModel::class.java)) {
            return ExperienceViewModel(experienceInteractor) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}