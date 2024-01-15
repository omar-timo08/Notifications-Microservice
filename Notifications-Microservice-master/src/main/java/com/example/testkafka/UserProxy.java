package com.example.testkafka;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
@FeignClient(name = "users-registration")
public interface UserProxy {
    @GetMapping(value = "/listByBloodType/{blood_type}")
    List<Users> findByBloodType(@PathVariable("blood_type")String groupe);
}