package VeridiOculi.PrototpoArduino.controller;

import VeridiOculi.PrototpoArduino.repository.ReadingRepository;
import VeridiOculi.PrototpoArduino.service.ReadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/readings")
public class ReadingController {
    @Autowired
    private ReadingService readingService;

    @PostMapping
    public String saveReading(@RequestParam String data){
        readingService.saveReading(data);
        return "Data saved Successfully";
    }
}
