package com.javatar.portafolio.di

import com.javatar.portafolio.api.ServicesApi
import com.javatar.portafolio.features.experience.ExperienceInteractor
import com.javatar.portafolio.features.experience.ExperienceService
import dagger.Module
import dagger.Provides

@Module
class ExperienceModule {

    @Provides
    fun provideExperienceInteractor(experienceService: ExperienceService): ExperienceInteractor {
        return ExperienceInteractor(experienceService)
    }

    @Provides
    fun provideExperienceService(api: ServicesApi): ExperienceService {
        return ExperienceService(api)
    }

}