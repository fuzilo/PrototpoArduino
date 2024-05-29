package VeridiOculi.PrototpoArduino.serial;

import VeridiOculi.PrototpoArduino.service.ReadingService;
import com.fazecast.jSerialComm.SerialPort;


import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;


import java.io.BufferedReader;
import java.io.InputStreamReader;

@Component
public class SerialReader {
    @Autowired
    private ReadingService readingService;
    private SerialPort serialPort;
    private BufferedReader input;

    @PostConstruct
    public void initialize(){
        serialPort = SerialPort.getCommPort("COM5");
        serialPort.setComPortParameters(9600,8,1,0);
        serialPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0 ,0);

        if (serialPort.openPort()){
            input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
            new Thread(this:: readSerialData).start();

        }else {
            System.out.println("Could not open COM port");
        }

    }

    @PreDestroy
    public void close(){
        if (serialPort !=null){
            serialPort.closePort();

        }
    }

    private void readSerialData(){
        try{
            String inputLine;
            while((inputLine = input.readLine()) !=null){
                readingService.saveReading(inputLine);

            }
        }catch (Exception e){
            System.err.println(e.toString());
        }
    }

}
