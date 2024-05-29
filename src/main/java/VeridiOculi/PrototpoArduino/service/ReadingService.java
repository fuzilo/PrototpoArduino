package VeridiOculi.PrototpoArduino.service;

import VeridiOculi.PrototpoArduino.entity.Reading;
import VeridiOculi.PrototpoArduino.repository.ReadingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReadingService {

    @Autowired
    private ReadingRepository readingRepository;

    public Reading saveReading(String data){

        Reading reading = new Reading();
        reading.setData(data);
        return readingRepository.save(reading);


    }
}
