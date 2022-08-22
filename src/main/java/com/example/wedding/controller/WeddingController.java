package com.example.wedding.controller;

import com.example.wedding.entity.WeddingEntity;
import com.example.wedding.repository.WeddingDAO;
import com.example.wedding.service.WeddingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wedding")
@RequiredArgsConstructor
public class WeddingController {

    private final WeddingDAO weddingDAO;
    private final WeddingService weddingService;

    @GetMapping("/all")
    public List<WeddingEntity> getAllWeddings() {
        return weddingService.getAll();
    }

    @PutMapping
    public ResponseEntity<String> saveWedding(@RequestBody WeddingEntity wedding) {
        try {
            weddingService.saveWedding(wedding);
            return ResponseEntity.ok("Запись добавлена");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping("/nearest-wedding")
    public WeddingEntity getNearestWedding() {
        return weddingService.getNearestWedding();
    }

    @DeleteMapping("/{weddingId}")
    public ResponseEntity<String> deleteWedding(@PathVariable Long weddingId) {
        try {
            weddingDAO.deleteById(weddingId);
            return ResponseEntity.ok("Запись успешно удалена");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping("/my-future-money")
    public ResponseEntity<String> myFutureMoney() {
        try {
            return ResponseEntity.ok(weddingService.getMyFutureMoney().toString());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}
