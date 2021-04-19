package com.sousajrps.covid19pt.vaccination

import com.sousajrps.covid19pt.remote.models.Vaccination
import com.sousajrps.covid19pt.remote.models.VaccinationWeekly
import io.reactivex.Single

interface VaccinationRepository {
    fun getAllRemoteData(): Single<Pair<List<Vaccination>, List<VaccinationWeekly>>>
    fun getAllLocalData(): Single<Pair<List<Vaccination>, List<VaccinationWeekly>>>
}
