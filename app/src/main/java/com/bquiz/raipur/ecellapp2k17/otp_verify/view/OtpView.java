package com.bquiz.raipur.ecellapp2k17.otp_verify.view;

import com.bquiz.raipur.ecellapp2k17.otp_verify.model.OtpData;

/**
 * Created by samveg on 30/7/17.
 */

public interface OtpView {

    void showProgressBar(boolean show);
    void showMessage(String message);
    void OtpStatus(OtpData otpData);
    void checkNetwork();
    void verify_bttn_clickable();
}
