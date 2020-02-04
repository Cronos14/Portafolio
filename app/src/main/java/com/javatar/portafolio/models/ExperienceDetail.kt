package com.javatar.portafolio.models

import java.io.Serializable

data class ExperienceDetail(
    val title: String,
    val icon: String,
    val description: String,
    val aptitude: List<Aptitude>?
) : Serializable