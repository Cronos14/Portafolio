package com.javatar.portafolio.models

class Project(
    title: String,
    val type: Int = 0,
    val image: String,
    val projectDetail: ProjectDetail
) : BaseData(title)