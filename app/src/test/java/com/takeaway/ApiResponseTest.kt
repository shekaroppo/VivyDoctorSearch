package com.takeaway

import com.takeaway.data.services.ApiService
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
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
        enqueueResponse("restaurants.json")

//        val testSubscriber = service.getRestaurants()
//        testSubscriber.assertNoErrors()
//        val response = testSubscriber.values()[0] as SearchResultResponse
//        assertThat(response, notNullValue())
//        assertThat(response.lastKey, notNullValue())
//        val doctor = response.doctors[0]
//        assertThat(doctor, notNullValue())
//        assertThat(doctor.address, `is`("Roßmarkt 5, 601 Frankfurt am Main, Germany"))
//        assertThat(doctor.name, `is`("Dr. med. Christiane Stephan-Seffer"))
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
