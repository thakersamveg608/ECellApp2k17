package com.example.iket.ecellapp2k17.about_us.view;

import com.example.iket.ecellapp2k17.about_us.model.data.TeamData;

import java.util.List;

/**
 * Created by lenovo on 6/23/2017.
 */

public interface OnAboutusReceived {

    void onSuccess(List<TeamData> teamDataList);
    void onFailure();
}
