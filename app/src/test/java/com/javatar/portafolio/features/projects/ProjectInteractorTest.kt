package com.javatar.portafolio.features.projects

import com.javatar.portafolio.models.Project
import com.javatar.portafolio.models.ProjectDetail
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class ProjectInteractorTest {
    @Mock
    private lateinit var projectService: ProjectService

    private lateinit var projectInteractor: ProjectInteractor

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        projectInteractor = ProjectInteractor(projectService)
    }

    @Test
    fun loadProject_Error_Response() {

        Mockito.`when`(projectService.getProject()).thenReturn(Single.error(RuntimeException()))

        projectInteractor.loadProject()
            .test()
            .assertError(RuntimeException::class.java)
            .assertTerminated()
    }

    @Test
    fun loadProject_Empty() {

        val projectList: List<Project> = arrayListOf()

        Mockito.`when`(projectService.getProject()).thenReturn(Single.just(projectList))

        projectInteractor.loadProject()
            .test()
            .assertValue { it.isEmpty() }
            .assertNoErrors()
            .assertTerminated()

    }

    @Test
    fun loadProject_Done() {

        val projectList: ArrayList<Project> = arrayListOf()

        projectList.add(Project("", 0, "", ProjectDetail("", "", "", "", null, null)))

        Mockito.`when`(projectService.getProject()).thenReturn(Single.just(projectList))

        projectInteractor.loadProject()
            .test()
            .assertNoErrors()
            .assertTerminated()

    }
}