package com.javatar.portafolio.features.aptitude

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.javatar.portafolio.features.rules.RxImmediateSchedulerRule
import com.javatar.portafolio.models.Aptitude
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
class AptitudeViewModelTest {

    companion object {
        @ClassRule
        @JvmField
        val schedulers = RxImmediateSchedulerRule()
    }

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var aptitudeInteractor: AptitudeInteractor

    @Mock
    private lateinit var observerSuccess : Observer<Any>

    @Mock
    private lateinit var observerError : Observer<Throwable>

    @Mock
    private lateinit var observerData : Observer<List<Aptitude>>

    private lateinit var viewModel : AptitudeViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = AptitudeViewModel(aptitudeInteractor)
        viewModel.successLiveData.observeForever(observerSuccess)
        viewModel.errorLiveData.observeForever(observerError)
        viewModel.aptitudeLiveData.observeForever(observerData)
    }

    @Test
    fun loadAptitude_Done() {

        val aptitudeList: List<Aptitude> = arrayListOf()

        whenever(aptitudeInteractor.loadAptitude()).thenReturn(Single.just(aptitudeList))

        viewModel.loadAptitude()

        Mockito.verify(observerSuccess).onChanged(ArgumentMatchers.any())
        Mockito.verify(observerData).onChanged(ArgumentMatchers.any())
        Mockito.verify(observerError, never()).onChanged(ArgumentMatchers.any())

    }

    @Test
    fun loadAptitude_Error() {

        whenever(aptitudeInteractor.loadAptitude()).thenReturn(Single.error(RuntimeException()))

        viewModel.loadAptitude()

        Mockito.verify(observerSuccess,never()).onChanged(ArgumentMatchers.any())
        Mockito.verify(observerData, never()).onChanged(ArgumentMatchers.any())
        Mockito.verify(observerError).onChanged(ArgumentMatchers.any())

    }
}