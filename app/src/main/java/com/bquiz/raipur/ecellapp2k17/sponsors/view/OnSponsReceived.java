package com.bquiz.raipur.ecellapp2k17.sponsors.view;

import com.bquiz.raipur.ecellapp2k17.sponsors.model.data.SponsParent;

/**
 * Created by samveg on 23/6/17.
 */

public interface OnSponsReceived {
    void onFailure();
    void onSuccess(SponsParent sponsParent);
}

