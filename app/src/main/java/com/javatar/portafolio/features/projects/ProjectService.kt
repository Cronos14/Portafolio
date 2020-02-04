package com.javatar.portafolio.features.projects

import com.javatar.portafolio.api.ServicesApi
import com.javatar.portafolio.commons.services.Service
import com.javatar.portafolio.models.Project
import com.javatar.portafolio.models.ResponseProject
import io.reactivex.Single

class ProjectService(servicesApi: ServicesApi) : Service<ResponseProject>(servicesApi) {

    fun getProject(): Single<List<Project>> {

        val responseProjectService: Single<ResponseProject> = servicesApi.getProject()

        return getSingleWithoutResponseWrap(responseProjectService)
            .map(::mapToListProject)
    }

    private fun mapToListProject(responseProject: ResponseProject): List<Project> {
        return responseProject.projects
    }

}