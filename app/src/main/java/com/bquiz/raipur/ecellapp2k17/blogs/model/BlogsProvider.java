package com.bquiz.raipur.ecellapp2k17.blogs.model;

import com.bquiz.raipur.ecellapp2k17.blogs.view.OnBlogsReceived;

/**
 * Created by vrihas on 6/21/2017.
 */

public interface BlogsProvider {
    void requestBlogs(OnBlogsReceived onBlogsReceived);
}
