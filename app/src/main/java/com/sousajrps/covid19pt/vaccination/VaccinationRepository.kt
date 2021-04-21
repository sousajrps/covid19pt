package com.sousajrps.covid19pt.vaccination

import com.sousajrps.covid19pt.local.Vaccination
import com.sousajrps.covid19pt.local.VaccinationWeekly
import io.reactivex.Single

interface VaccinationRepository {
    fun getVaccinationData(time: Long): Single<Pair<List<Vaccination>, List<VaccinationWeekly>>>
}
