package com.javatar.portafolio.utils

import okhttp3.*

class InterceptorRepositoryDummy(val content: String, val codeHttp: Int = 200) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        return Response.Builder()
            .code(codeHttp)
            .message(content)
            .request(chain.request())
            .protocol(Protocol.HTTP_1_0)
            .body(ResponseBody.create(MediaType.parse("application/json"), content.toByteArray()))
            .addHeader("content-type", "application/json")
            .build();

    }
}