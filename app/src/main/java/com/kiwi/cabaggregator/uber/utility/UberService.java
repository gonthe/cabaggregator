/*
 * Copyright 2014 Michael Evans <michaelcevans10@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.kiwi.cabaggregator.uber.utility;


import com.kiwi.cabaggregator.uber.entities.UberServiceDataWrapper;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

public interface UberService {

    @GET("/v1/products")
    void getProducts(@Query("latitude") double latitude, @Query("longitude") double longitude, Callback<UberServiceDataWrapper> callback);

    @GET("/v1/estimates/price")
    UberServiceDataWrapper getPricingEstimate(@Query("start_latitude") double start_latitude, @Query("start_longitude") double start_longitude, @Query("end_latitude") double end_latitude, @Query("end_longitude") double end_longitude);

   // @GET("/v1/estimates/time")
   // UberServiceDataWrapper getTimeEstimate(@Query("start_latitude") double start_latitude, @Query("start_longitude") double start_longitude, @Query("customer_uuid") String customerUUID, @Query("product_id") String product_id);

    @GET("/v1/estimates/time")
    void getTimeEstimate(@Query("start_latitude") double start_latitude, @Query("start_longitude") double start_longitude, Callback<UberServiceDataWrapper> callback);

}
