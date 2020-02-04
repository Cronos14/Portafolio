package com.javatar.portafolio.features.projects

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.javatar.portafolio.application.ApplicationContext
import javax.inject.Inject

class ProjectViewModelFactory : ViewModelProvider.Factory {

    @Inject
    lateinit var projectInteractor: ProjectInteractor

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        ApplicationContext.apiComponent.inject(this)

        if (modelClass.isAssignableFrom(ProjectViewModel::class.java)) {
            return ProjectViewModel(projectInteractor) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}