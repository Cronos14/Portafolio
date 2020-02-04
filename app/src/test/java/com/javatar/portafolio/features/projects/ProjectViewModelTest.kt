package com.javatar.portafolio.features.projects

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.javatar.portafolio.features.rules.RxImmediateSchedulerRule
import com.javatar.portafolio.models.Project
import io.reactivex.Single
import org.junit.Before
import org.junit.ClassRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.never
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.Mockito.`when` as whenever

@RunWith(MockitoJUnitRunner::class)
class ProjectViewModelTest {

    companion object {
        @ClassRule
        @JvmField
        val schedulers = RxImmediateSchedulerRule()
    }

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var projectInteractor: ProjectInteractor

    @Mock
    private lateinit var observerSuccess : Observer<Any>

    @Mock
    private lateinit var observerError : Observer<Throwable>

    @Mock
    private lateinit var observerData : Observer<List<Project>>

    private lateinit var viewModel : ProjectViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = ProjectViewModel(projectInteractor)
        viewModel.successLiveData.observeForever(observerSuccess)
        viewModel.errorLiveData.observeForever(observerError)
        viewModel.projectLiveData.observeForever(observerData)
    }

    @Test
    fun loadProject_Done() {

        val projectList: List<Project> = arrayListOf()

        whenever(projectInteractor.loadProject()).thenReturn(Single.just(projectList))

        viewModel.loadProject()

        Mockito.verify(observerSuccess).onChanged(ArgumentMatchers.any())
        Mockito.verify(observerData).onChanged(ArgumentMatchers.any())
        Mockito.verify(observerError, never()).onChanged(ArgumentMatchers.any())

    }

    @Test
    fun loadProject_Error() {

        whenever(projectInteractor.loadProject()).thenReturn(Single.error(RuntimeException()))

        viewModel.loadProject()

        Mockito.verify(observerSuccess,never()).onChanged(ArgumentMatchers.any())
        Mockito.verify(observerData, never()).onChanged(ArgumentMatchers.any())
        Mockito.verify(observerError).onChanged(ArgumentMatchers.any())

    }
}