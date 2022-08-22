package com.example.wedding.service;

import com.example.wedding.entity.WeddingEntity;
import com.example.wedding.repository.WeddingDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@EnableScheduling
public class WeddingServiceImpl implements WeddingService {

    private final WeddingDAO weddingDAO;

    @Override
    public void saveWedding(WeddingEntity wedding) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(wedding.getWeddingDate());
        cal.add(Calendar.MONTH, 3);
        wedding.setDeadLine(cal.getTime());

        wedding.setLeftToPay(wedding.getPrice() - wedding.getPrepayment());
        wedding.setDeleted(false);

        weddingDAO.save(wedding);
    }

    @Override
    public List<WeddingEntity> getAll() {
        List<WeddingEntity> allWeddings = weddingDAO.findAllByDeletedFalse();
        return allWeddings.stream().sorted(Comparator.comparing(WeddingEntity::getWeddingDate)).collect(Collectors.toList());
    }

    @Override
    public WeddingEntity getNearestWedding() {
        List<WeddingEntity> allWeddings = weddingDAO.findAllByDeletedFalse();
        return allWeddings.stream().min(Comparator.comparing(WeddingEntity::getWeddingDate)).get();
    }

    @Override
    public Integer getMyFutureMoney() {
        List<WeddingEntity> allWeddings = weddingDAO.findAllByDeletedFalse();
        return allWeddings.stream().mapToInt(WeddingEntity::getLeftToPay).sum();
    }

    @Scheduled(cron = "0 0 4 * * ?")
    public void getDeleteWedding() {
        System.out.println("asd");
        List<WeddingEntity> allWeddings = weddingDAO.findAllByDeadLineBefore(new Date());

        allWeddings.forEach(weddingEntity -> {
            weddingEntity.setDeleted(true);
            weddingDAO.save(weddingEntity);
        });
    }
}
