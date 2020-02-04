package com.javatar.portafolio.application

import android.app.Application
import android.content.Context
import com.javatar.portafolio.di.*
import com.javatar.portafolio.utils.BASE_URL

class ApplicationContext : Application() {

    companion object {
        var ctx: Context? = null
        lateinit var apiComponent: APIComponent
    }

    override fun onCreate() {
        super.onCreate()
        ctx = applicationContext
        apiComponent = initDaggerComponent()

    }

    private fun initDaggerComponent(): APIComponent {
        apiComponent = DaggerAPIComponent
            .builder()
            .servicesApiModule(ServicesApiModule(BASE_URL))
            .experienceModule(ExperienceModule())
            .aptitudeModule(AptitudeModule())
            .projectModule(ProjectModule())
            .build()
        return apiComponent

    }
}