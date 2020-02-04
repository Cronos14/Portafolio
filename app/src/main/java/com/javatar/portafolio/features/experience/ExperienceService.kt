package com.javatar.portafolio.features.experience

import com.javatar.portafolio.api.ServicesApi
import com.javatar.portafolio.commons.services.Service
import com.javatar.portafolio.models.Experience
import com.javatar.portafolio.models.ResponseExperience
import io.reactivex.Single

class ExperienceService(servicesApi: ServicesApi) : Service<ResponseExperience>(servicesApi) {

    fun getExperience(): Single<List<Experience>> {

        val responseExperienceService: Single<ResponseExperience> = servicesApi.getExperience()

        return getSingleWithoutResponseWrap(responseExperienceService)
            .map(::mapToListExperience)
    }

    private fun mapToListExperience(responseExperience: ResponseExperience): List<Experience> {
        return responseExperience.experience
    }

}