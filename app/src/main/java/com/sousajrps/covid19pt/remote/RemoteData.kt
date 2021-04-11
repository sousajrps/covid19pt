package com.sousajrps.covid19pt.remote

import io.reactivex.Single

interface RemoteData {
    fun getRemoteCovid19PtData(): Single<List<Map<String, String>>>
    fun getRemoteCovid19PtVaccination(): Single<List<Map<String, String>>>
}
