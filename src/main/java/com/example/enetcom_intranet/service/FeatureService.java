package com.example.enetcom_intranet.service;

import com.example.enetcom_intranet.model.Feature;
import com.example.enetcom_intranet.model.Post;

import java.util.List;




public interface FeatureService {
    List<Feature> getFeatures();

    Feature getFeatureById(Integer id);

    Feature insert(Feature f);

    void updateFeature(Integer id, Feature f);

    void deleteFeature(Integer id);
}