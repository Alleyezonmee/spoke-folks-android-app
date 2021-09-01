package com.soulinc.spokeFolks.account.data.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.soulinc.spokeFolks.account.data.model.Vehicle
import com.soulinc.spokeFolks.base.data.model.BaseResponse
import retrofit2.Response

interface IAccountRepository {

  suspend fun addVehicle(vehicle: Vehicle) : LiveData<Response<BaseResponse<Void>>>

  suspend fun getVehicleDetails(liveData: MutableLiveData<Response<BaseResponse<Vehicle>>>, vehicleId: String): LiveData<Response<BaseResponse<Vehicle>>>

  suspend fun getAllVehicles(liveData: MutableLiveData<Response<BaseResponse<List<Vehicle>>>>): LiveData<Response<BaseResponse<List<Vehicle>>>>

  suspend fun updateVehicle(vehicle: Vehicle) : LiveData<Response<BaseResponse<Void>>>
}