package com.javatar.portafolio.commons.services

import com.javatar.portafolio.api.ServicesApi
import com.javatar.portafolio.models.ResponseGeneral
import io.reactivex.Single

open class Service<T : ResponseGeneral>(servicesApi: ServicesApi) {

    var servicesApi: ServicesApi = servicesApi

    fun getSingleWithoutResponseWrap(response: Single<T>): Single<T> {
        return response
            .filter(::filterCodeError)
            .toSingle()
    }

    fun filterCodeError(responseExperience: T): Boolean {
        if (responseExperience.code == "0")
            return true

        throw RuntimeException("Codigo de error")
    }


}