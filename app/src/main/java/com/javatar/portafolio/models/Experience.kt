package com.javatar.portafolio.models

class Experience(
    title: String,
    val enterprise: String,
    val date: String,
    val type: Int = 0,
    val image: String,
    val experienceDetail: ExperienceDetail
) : BaseData(title)