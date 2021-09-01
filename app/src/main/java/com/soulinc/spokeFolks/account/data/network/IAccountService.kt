package com.soulinc.spokeFolks.account.data.network

import com.soulinc.spokeFolks.account.data.model.Vehicle
import com.soulinc.spokeFolks.base.data.model.BaseResponse
import retrofit2.Response
import retrofit2.http.*

interface IAccountService {

  @POST("vehicle/addVehicle")
  suspend fun addVehicle(@Body vehicle: Vehicle) : Response<BaseResponse<Void>>

  @GET("vehicle/{vehicleId}")
  suspend fun getVehicleDetails(@Path("vehicleId") vehicleId: String): Response<BaseResponse<Vehicle>>

  @GET("vehicle/")
  suspend fun getAllVehicles(): Response<BaseResponse<List<Vehicle>>>

  @PUT("vehicle/updateVehicle/{vehicleId}")
  suspend fun updateVehicleDetails(@Body vehicle: Vehicle) : Response<BaseResponse<Void>>
}