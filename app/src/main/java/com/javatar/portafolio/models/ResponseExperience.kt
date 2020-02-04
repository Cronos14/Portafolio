package com.javatar.portafolio.models

class ResponseExperience(
    code: String,
    val experience: List<Experience>
) : ResponseGeneral(code)