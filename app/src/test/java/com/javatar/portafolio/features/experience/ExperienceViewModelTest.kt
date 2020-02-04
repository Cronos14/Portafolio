package com.javatar.portafolio.features.experience

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.javatar.portafolio.features.rules.RxImmediateSchedulerRule
import com.javatar.portafolio.models.Experience
import io.reactivex.Single
import org.junit.Before
import org.junit.ClassRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.never
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.Mockito.`when` as whenever



@RunWith(MockitoJUnitRunner::class)
class ExperienceViewModelTest {

    companion object {
        @ClassRule
        @JvmField
        val schedulers = RxImmediateSchedulerRule()
    }

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var experienceInteractor: ExperienceInteractor

    @Mock
    private lateinit var observerSuccess : Observer<Any>

    @Mock
    private lateinit var observerError : Observer<Throwable>

    @Mock
    private lateinit var observerData : Observer<List<Experience>>

    private lateinit var viewModel: ExperienceViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = ExperienceViewModel(experienceInteractor)
        viewModel.successLiveData.observeForever(observerSuccess)
        viewModel.errorLiveData.observeForever(observerError)
        viewModel.experienceLiveData.observeForever(observerData)
    }

    @Test
    fun loadExperience_Done() {

        val experienceList: List<Experience> = arrayListOf()

        whenever(experienceInteractor.loadExperience()).thenReturn(Single.just(experienceList))

        viewModel.loadExperience()

        verify(observerSuccess).onChanged(ArgumentMatchers.any())
        verify(observerData).onChanged(ArgumentMatchers.any())
        verify(observerError, never()).onChanged(ArgumentMatchers.any())

    }

    @Test
    fun loadExperience_Error() {

        whenever(experienceInteractor.loadExperience()).thenReturn(Single.error(RuntimeException()))

        viewModel.loadExperience()

        verify(observerSuccess, never()).onChanged(ArgumentMatchers.any())
        verify(observerData, never()).onChanged(ArgumentMatchers.any())
        verify(observerError).onChanged(ArgumentMatchers.any())

    }


}