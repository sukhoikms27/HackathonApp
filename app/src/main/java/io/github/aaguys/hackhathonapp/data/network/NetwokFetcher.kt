package io.github.aaguys.hackhathonapp.data.network

import androidx.lifecycle.LiveData
import io.github.aaguys.hackhathonapp.common.ConfData

interface NetwokFetcher {

    val confData:LiveData<ConfData>
}