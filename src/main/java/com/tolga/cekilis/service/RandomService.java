package com.tolga.cekilis.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tolga.cekilis.model.User;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class RandomService {

    private final File dbFile = new File("src/main/resources/db.json");

    public List<String> generateWinners(String title, int bd, int sd, int kk, String onay) {
        List<Integer> l1 = new ArrayList<>();
        List<String> winners = new ArrayList<>();
        Random random = new Random();

        // db.json dosyasından güncel user verilerini yükle
        User user = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            user = objectMapper.readValue(dbFile, User.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Kullanıcının aktifleştirildiğini ve başlıkta joker adının geçtiğini kontrol et
        if (user != null && user.isActivate() && title.contains(user.getJokerName())) {
            winners.add(String.valueOf(user.getJokerSayi()));
        }

        // Kazananları üret
        while (winners.size() < kk) {
            int rndsayi = random.nextInt(sd - bd + 1) + bd;

            // Aynı numara tekrar edebilir mi kontrol et
            if (onay.contains("E") || !l1.contains(rndsayi)) {
                winners.add(String.valueOf(rndsayi));
                l1.add(rndsayi);
            }
        }

        return winners;
    }
}
