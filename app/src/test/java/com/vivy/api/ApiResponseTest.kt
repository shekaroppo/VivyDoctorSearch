package com.vivy.api

import com.vivy.data.model.SearchResultResponse
import com.vivy.data.services.ApiService
import com.vivy.utils.Constants
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsNull.notNullValue
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.nio.charset.StandardCharsets

@RunWith(JUnit4::class)
class ApiResponseTest {

    private lateinit var service: ApiService
    private lateinit var mockWebServer: MockWebServer

    @Throws(IOException::class)
    @Before
    fun createService() {
        mockWebServer = MockWebServer()
        service = Retrofit.Builder()
                .baseUrl(mockWebServer.url("/"))
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(ApiService::class.java)
    }

    @Throws(IOException::class)
    @After
    fun stopService() {
        mockWebServer.shutdown()
    }

    @Test
    fun searchDoctorsTest() {
        enqueueResponse("doctors.json")

        val testSubscriber = service.searchDoctors(hashMapOf("search" to "Frau", "lat" to Constants.LATITUDE, "lng" to Constants.LONGITUDE),
                hashMapOf("Authorization" to "Bearer ${Constants.ACCESS_TOKEN}")).test()
        testSubscriber.assertNoErrors()
        val response = testSubscriber.values()[0] as SearchResultResponse
        assertThat(response, notNullValue())
        assertThat(response.lastKey, notNullValue())
        val doctor = response.doctors[0]
        assertThat(doctor, notNullValue())
        assertThat(doctor.address, `is`("Roßmarkt 5, 601 Frankfurt am Main, Germany"))
        assertThat(doctor.name, `is`("Dr. med. Christiane Stephan-Seffer"))
    }

    @Throws(IOException::class)
    private fun enqueueResponse(fileName: String) {
        enqueueResponse(fileName, emptyMap())
    }

    @Throws(IOException::class)
    private fun enqueueResponse(fileName: String, headers: Map<String, String>) {
        val inputStream = javaClass.classLoader!!.getResourceAsStream("api-response/$fileName")
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        for ((key, value) in headers) {
            mockResponse.addHeader(key, value)
        }
        mockWebServer.enqueue(mockResponse.setBody(source.readString(StandardCharsets.UTF_8)))
    }
}
