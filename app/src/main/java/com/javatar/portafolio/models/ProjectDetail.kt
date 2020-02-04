package com.javatar.portafolio.models

import java.io.Serializable

data class ProjectDetail(
    val title: String,
    val icon: String,
    val smallIcon: String,
    val description: String,
    val features: List<Feature>?,
    val images: List<String>?
) : Serializable