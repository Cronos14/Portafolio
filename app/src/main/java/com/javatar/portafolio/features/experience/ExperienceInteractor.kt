package com.javatar.portafolio.features.experience

import com.javatar.portafolio.models.Experience
import io.reactivex.Single

class ExperienceInteractor(private val repository: ExperienceService) {

    fun loadExperience(): Single<List<Experience>> {

        return repository.getExperience()
    }

}