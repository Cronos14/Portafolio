package com.javatar.portafolio.features.aptitude

import com.javatar.portafolio.api.ServicesApi
import com.javatar.portafolio.commons.services.Service
import com.javatar.portafolio.models.Aptitude
import com.javatar.portafolio.models.ResponseAptitude
import io.reactivex.Single

class AptitudeService(servicesApi: ServicesApi) : Service<ResponseAptitude>(servicesApi) {

    fun getAptitude(): Single<List<Aptitude>> {

        val responseAptitudeService: Single<ResponseAptitude> = servicesApi.getAptitude()

        return getSingleWithoutResponseWrap(responseAptitudeService)
            .map(::mapToListAptitude)
    }

    private fun mapToListAptitude(responseAptitude: ResponseAptitude): List<Aptitude> {
        return responseAptitude.aptitude
    }

}