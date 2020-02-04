package com.javatar.portafolio.features.aptitude

import com.javatar.portafolio.models.Aptitude
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class AptitudeInteractorTest {
    @Mock
    private lateinit var aptitudeService: AptitudeService

    private lateinit var aptitudeInteractor: AptitudeInteractor

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        aptitudeInteractor = AptitudeInteractor(aptitudeService)
    }

    @Test
    fun loadAptitude_Error_Response() {

        Mockito.`when`(aptitudeService.getAptitude()).thenReturn(Single.error(RuntimeException()))

        aptitudeInteractor.loadAptitude()
            .test()
            .assertError(RuntimeException::class.java)
            .assertTerminated()
    }

    @Test
    fun loadAptitude_Empty() {

        val aptitudeList: List<Aptitude> = arrayListOf()

        Mockito.`when`(aptitudeService.getAptitude()).thenReturn(Single.just(aptitudeList))

        aptitudeInteractor.loadAptitude()
            .test()
            .assertValue { it.isEmpty() }
            .assertNoErrors()
            .assertTerminated()

    }

    @Test
    fun loadAptitude_Done() {

        val aptitudeList: ArrayList<Aptitude> = arrayListOf()

        aptitudeList.add(Aptitude("Pruebas", 1))
        aptitudeList.add(Aptitude("Java", 0))
        aptitudeList.add(Aptitude("Kotlin", 0))

        Mockito.`when`(aptitudeService.getAptitude()).thenReturn(Single.just(aptitudeList))

        aptitudeInteractor.loadAptitude()
            .test()
            .assertNoErrors()
            .assertTerminated()

    }

    @Test
    fun loadAptitude_Done_Count() {

        val aptitudeList: ArrayList<Aptitude> = arrayListOf()

        aptitudeList.add(Aptitude("Pruebas", 1))
        aptitudeList.add(Aptitude("Java", 0))
        aptitudeList.add(Aptitude("Kotlin", 0))

        Mockito.`when`(aptitudeService.getAptitude()).thenReturn(Single.just(aptitudeList))

        aptitudeInteractor.loadAptitude()
            .test()
            .assertNoErrors()
            .assertTerminated()
            .assertValueCount(1)

    }
}