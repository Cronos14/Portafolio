package com.javatar.portafolio.models

class ResponseAptitude(
    code: String,
    val aptitude: List<Aptitude>
) : ResponseGeneral(code)