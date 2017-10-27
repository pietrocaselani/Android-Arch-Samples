package io.github.pietrocaselani.searchrepos

import io.github.pietrocaselani.searchrepos.organization.GithubService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Environment {
	val githubService: GithubService

	init {
		val loggingInterceptor = HttpLoggingInterceptor()
		loggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC

		val okHttpClient = OkHttpClient.Builder()
				.addInterceptor(GithubInterceptor())
				.addInterceptor(loggingInterceptor)
				.build()


		val retrofit = Retrofit.Builder()
				.baseUrl("https://api.github.com")
				.client(okHttpClient)
				.addConverterFactory(GsonConverterFactory.create())
				.build()

		githubService = retrofit.create(GithubService::class.java)
	}
}

private class GithubInterceptor: Interceptor {
	override fun intercept(chain: Interceptor.Chain?): Response {
		val request = chain!!.request()

		val newRequest = request.newBuilder()
				.header("Accept", "application/vnd.github.v3+json")
				.build()

		return chain.proceed(newRequest)
	}

}