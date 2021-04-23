package com.sousajrps.covid19pt.remote

import com.github.doyaaaaaken.kotlincsv.client.CsvReader
import io.reactivex.Single
import java.io.BufferedReader
import java.net.URL

class RemoteDataImpl : RemoteData {

    override fun getRemoteCovid19PtData() = getRemote(DATA_URL)

    override fun getRemoteCovid19PtVaccination() = getRemote(VACCINATION_URL)

    override fun getRemoteCovid19PtVaccinationWeekly() = getRemote(VACCINATION_WEEKLY_URL)

    private fun getRemote(url: String): Single<List<Map<String, String>>> {
        return Single.create { emitter ->
            val remoteRawData = downloadDataWithHeader(url)
            if (remoteRawData.isNotEmpty()) {
                emitter.onSuccess(remoteRawData)

            } else {
                emitter.onError(Throwable("No data received!"))
            }
        }
    }

    private fun downloadDataWithHeader(url: String): List<Map<String, String>> {
        var rows: List<Map<String, String>> = emptyList()
        URL(url).openStream().use { input ->
            val reader = BufferedReader(input.reader())
            try {
                rows = CsvReader().readAllWithHeader(input)
            } catch (exception: Exception) {
            } finally {
                reader.close()
            }
        }
        return rows
    }

    companion object {
        private const val DATA_URL =
            "https://raw.githubusercontent.com/dssg-pt/covid19pt-data/master/data.csv"

        private const val VACCINATION_URL =
            "https://raw.githubusercontent.com/dssg-pt/covid19pt-data/master/vacinas.csv"

        private const val VACCINATION_WEEKLY_URL =
            "https://raw.githubusercontent.com/dssg-pt/covid19pt-data/master/vacinas_detalhe.csv"
    }
}
