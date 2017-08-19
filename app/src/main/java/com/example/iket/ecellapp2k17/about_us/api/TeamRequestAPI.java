package com.example.iket.ecellapp2k17.about_us.api;

import com.example.iket.ecellapp2k17.about_us.model.data.TeamData;
import com.example.iket.ecellapp2k17.about_us.model.data.TeamList;
import com.example.iket.ecellapp2k17.helper.Urls;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by vrihas on 6/23/2017.
 */

public interface TeamRequestAPI {
    @GET(Urls.REQUEST_ABOUTUS)
    Call<TeamList> getData();

}