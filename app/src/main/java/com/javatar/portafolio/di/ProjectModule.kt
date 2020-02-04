package com.javatar.portafolio.di

import com.javatar.portafolio.api.ServicesApi
import com.javatar.portafolio.features.projects.ProjectInteractor
import com.javatar.portafolio.features.projects.ProjectService
import dagger.Module
import dagger.Provides

@Module
class ProjectModule {

    @Provides
    fun provideProjectInteractor(projectService: ProjectService): ProjectInteractor {
        return ProjectInteractor(projectService)
    }

    @Provides
    fun provideProjectService(api: ServicesApi): ProjectService {
        return ProjectService(api)
    }

}