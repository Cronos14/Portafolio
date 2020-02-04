package com.javatar.portafolio.di

import com.javatar.portafolio.features.aptitude.AptitudeViewModelFactory
import com.javatar.portafolio.features.experience.ExperienceViewModelFactory
import com.javatar.portafolio.features.projects.ProjectViewModelFactory
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ServicesApiModule::class, ExperienceModule::class, AptitudeModule::class, ProjectModule::class])
interface APIComponent {

    fun inject(experienceViewModel: ExperienceViewModelFactory)
    fun inject(aptitudeViewModel: AptitudeViewModelFactory)
    fun inject(aptitudeViewModel: ProjectViewModelFactory)

}