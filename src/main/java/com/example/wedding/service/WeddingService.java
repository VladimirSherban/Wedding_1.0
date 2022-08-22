package com.example.wedding.service;

import com.example.wedding.entity.WeddingEntity;

import java.util.List;

public interface WeddingService {

    void saveWedding(WeddingEntity wedding);

    List<WeddingEntity> getAll();

    WeddingEntity getNearestWedding();

    Integer getMyFutureMoney();
}
