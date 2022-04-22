package com.example.enetcom_intranet.ServiceImpl;

import com.example.enetcom_intranet.model.Feature;
import com.example.enetcom_intranet.repository.FeatureRepository;
import com.example.enetcom_intranet.service.FeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class FeatureServiceImpl implements FeatureService {
    @Autowired
    FeatureRepository featureRepository;


    @Override
    public List<Feature> getFeatures() {
        List<Feature> posts = new ArrayList<>();
        featureRepository.findAll().forEach(posts::add);
        return posts;
    }

    @Override
    public Feature getFeatureById(Integer id) {
        return featureRepository.findById(id).get();
    }

    @Override
    public Feature insert(Feature post) {
        return featureRepository.save(post);
    }

    @Override
    public void updateFeature(Integer id, Feature feature) {
        Feature featureFromDb = featureRepository.findById(id).get();
        System.out.println(featureFromDb.toString());
        featureFromDb.setDescription(feature.getDescription());
        featureFromDb.setImageUrl(feature.getImageUrl());
        featureFromDb.setTitle(feature.getTitle());


        featureRepository.save(featureFromDb);
    }

    @Override
    public void deleteFeature(Integer id) {
        featureRepository.deleteById(id);
    }

}
