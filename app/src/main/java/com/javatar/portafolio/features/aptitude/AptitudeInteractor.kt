package com.javatar.portafolio.features.aptitude

import com.javatar.portafolio.models.Aptitude
import io.reactivex.Single

class AptitudeInteractor(private val repository: AptitudeService) {

    fun loadAptitude(): Single<List<Aptitude>> {

        return repository.getAptitude()
    }

}