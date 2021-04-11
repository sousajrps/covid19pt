package com.sousajrps.covid19pt

import com.sousajrps.covid19pt.remote.models.Vaccination
import io.reactivex.Single

interface VaccinationRepository {
    fun getRemoteVaccination(): Single<List<Vaccination>>
    fun getLocalVaccination(): Single<List<Vaccination>>
}
