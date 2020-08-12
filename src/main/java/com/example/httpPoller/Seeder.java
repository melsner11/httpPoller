package com.example.httpPoller;

import com.example.httpPoller.model.EndPoint;
import com.example.httpPoller.model.EndPointStatus;
import com.example.httpPoller.repository.EndPointRepository;
import com.example.httpPoller.repository.EndPointStatusRepository;
import jdk.nashorn.internal.runtime.options.Option;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Configuration
public class Seeder {
    private static final Logger log = LoggerFactory.getLogger(Seeder.class);

    @Bean
    CommandLineRunner initDatabase(EndPointRepository endPointRepository, EndPointStatusRepository statusRepository) {

        return args -> {
            EndPoint e1 = new EndPoint("http://localhost:81");
            EndPoint e2 = new EndPoint("https://vix.digital");

            if (endPointRepository.count() == 0) {
                log.info("Preloading " + endPointRepository.save(e1));
                log.info("Preloading " + endPointRepository.save(e2));

                java.sql.Timestamp timestamp1 = java.sql.Timestamp.valueOf("2020-07-23 10:20:00.0");

                EndPointStatus es1 = new EndPointStatus(1,timestamp1);
                es1.setEndPoint(e1);
                EndPointStatus es2 = new EndPointStatus(1,timestamp1);
                es2.setEndPoint(e1);
                EndPointStatus es3 = new EndPointStatus(1,timestamp1);
                es3.setEndPoint(e1);



                List al = new ArrayList();
                al.add(es1);
                al.add(es2);
                al.add(es3);

                e1.setEndPointStatusList(al);
                endPointRepository.save(e1);

                endPointRepository.findAll().forEach(System.out::println);
            }
        };
    }
}

