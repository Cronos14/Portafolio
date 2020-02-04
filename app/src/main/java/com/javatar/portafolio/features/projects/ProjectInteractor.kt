package com.javatar.portafolio.features.projects

import com.javatar.portafolio.models.Project
import io.reactivex.Single

class ProjectInteractor(private val repository: ProjectService) {

    fun loadProject(): Single<List<Project>> {

        return repository.getProject()
    }

}