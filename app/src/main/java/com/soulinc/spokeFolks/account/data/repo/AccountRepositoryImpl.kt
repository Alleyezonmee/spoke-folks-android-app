package com.soulinc.spokeFolks.account.data.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.soulinc.spokeFolks.account.data.model.Vehicle
import com.soulinc.spokeFolks.account.data.network.IAccountService
import com.soulinc.spokeFolks.base.data.model.BaseResponse
import com.soulinc.spokeFolks.base.data.repository.BaseRepositoryImpl
import retrofit2.Response
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(private val service: IAccountService) : BaseRepositoryImpl(), IAccountRepository {
  override suspend fun addVehicle(vehicle: Vehicle) = callApi { service.addVehicle(vehicle) }

  override suspend fun getVehicleDetails(liveData: MutableLiveData<Response<BaseResponse<Vehicle>>>, vehicleId: String) = callApi(liveData) { service.getVehicleDetails(vehicleId) }

  override suspend fun getAllVehicles(liveData: MutableLiveData<Response<BaseResponse<List<Vehicle>>>>) = callApi(liveData) { service.getAllVehicles() }

  override suspend fun updateVehicle(vehicle: Vehicle) = callApi { service.updateVehicleDetails(vehicle) }

}