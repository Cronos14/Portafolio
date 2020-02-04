package com.javatar.portafolio.di

import com.javatar.portafolio.api.ServicesApi
import com.javatar.portafolio.features.aptitude.AptitudeInteractor
import com.javatar.portafolio.features.aptitude.AptitudeService
import dagger.Module
import dagger.Provides

@Module
class AptitudeModule {

    @Provides
    fun provideAptitudeInteractor(aptitudeService: AptitudeService): AptitudeInteractor {
        return AptitudeInteractor(aptitudeService)
    }

    @Provides
    fun provideAptitudeService(api: ServicesApi): AptitudeService {
        return AptitudeService(api)
    }

}