package com.javatar.portafolio.api

import com.javatar.portafolio.models.ResponseAptitude
import com.javatar.portafolio.models.ResponseExperience
import com.javatar.portafolio.models.ResponseProject
import io.reactivex.Single
import retrofit2.http.GET

interface ServicesApi {

    @GET("233ae05ae5831d4b9f125e9287436dec/raw/2ba856545aefdb45206eee7a09ceb868211a5afd/experience")
    fun getExperience(): Single<ResponseExperience>

    @GET("39484a993ae69a4dd1fa227dbcfec5cb/raw/5fb4aeeab49673267c804046dda9cf5aeb836f4f/aptitude")
    fun getAptitude(): Single<ResponseAptitude>

    @GET("3d93f24badcd8f4938168013576610e2/raw/bcf42a344de5094a789c539b2bc96409fc6dd56d/projects")
    fun getProject(): Single<ResponseProject>
}