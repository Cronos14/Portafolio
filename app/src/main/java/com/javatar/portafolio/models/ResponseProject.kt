package com.javatar.portafolio.models

class ResponseProject(
    code: String,
    val projects: List<Project>
) : ResponseGeneral(code)