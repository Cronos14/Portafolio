package com.javatar.portafolio.features.experience

import com.javatar.portafolio.models.Experience
import com.javatar.portafolio.models.ExperienceDetail
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.Mockito.`when` as whenever

class ExperienceInteractorTest {

    @Mock
    private lateinit var experienceService: ExperienceService

    private lateinit var experienceInteractor: ExperienceInteractor

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        experienceInteractor = ExperienceInteractor(experienceService)
    }

    @Test
    fun loadExperience_Error_Response() {

        whenever(experienceService.getExperience()).thenReturn(Single.error(RuntimeException()))

        experienceInteractor.loadExperience()
            .test()
            .assertError(RuntimeException::class.java)
            .assertTerminated()
    }

    @Test
    fun loadExperience_Empty() {

        val experienceList: List<Experience> = arrayListOf()

        whenever(experienceService.getExperience()).thenReturn(Single.just(experienceList))

        experienceInteractor.loadExperience()
            .test()
            .assertValue { it.isEmpty() }
            .assertNoErrors()
            .assertTerminated()

    }

    @Test
    fun loadExperience_Done() {

        val experienceList: ArrayList<Experience> = arrayListOf()

        experienceList.add(Experience("", "", "", 0, "", ExperienceDetail("", "", "", null)))

        whenever(experienceService.getExperience()).thenReturn(Single.just(experienceList))

        experienceInteractor.loadExperience()
            .test()
            .assertNoErrors()
            .assertTerminated()

    }

}