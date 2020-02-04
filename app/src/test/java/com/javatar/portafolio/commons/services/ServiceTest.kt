package com.javatar.portafolio.commons.services

import com.javatar.portafolio.api.ServicesApi
import com.javatar.portafolio.models.ResponseGeneral
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class ServiceTest {

    @Mock
    private lateinit var serviceApi: ServicesApi

    private lateinit var service: Service<ResponseGeneral>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        service = Service(serviceApi)
    }

    @Test
    fun filterCodeError_Code_Error() {

        val responseGeneral = ResponseGeneral("-1")

        service.getSingleWithoutResponseWrap(Single.just(responseGeneral))
            .test()
            .assertError(RuntimeException::class.java)

    }

    @Test
    fun filterCodeError_Code_Done() {

        val responseGeneral = ResponseGeneral("0")

        service.getSingleWithoutResponseWrap(Single.just(responseGeneral))
            .test()
            .assertNoErrors()

    }

}