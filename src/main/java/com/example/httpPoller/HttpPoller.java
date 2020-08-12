package com.example.httpPoller;

import com.example.httpPoller.model.EndPoint;
import com.example.httpPoller.repository.EndPointRepository;
import com.example.httpPoller.repository.EndPointStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class HttpPoller implements CommandLineRunner {

    private List<HttpPollerTask> pollerTasks = new ArrayList();

    @Autowired
    private EndPointRepository endPointRepository;

    private static final Logger logger = Logger.getLogger(HttpPoller.class.getName());

    public HttpPoller() {

    }

    public void stopAndRemoveAllTasks(){
        logger.info("Stop and remove all PollerTasks.. ");
        pollerTasks.forEach(httpPollerTask -> {
            httpPollerTask.stopTimer();
        });
        pollerTasks.clear();
    }

    @Override
    public void run(String... args) throws Exception {
        List<EndPoint> endpoints = endPointRepository.findAll();
        logger.info(String.format("Add Poller Tasks"));
        endpoints.forEach(endPoint -> {
            pollerTasks.add(new HttpPollerTask(endPoint, endPointRepository));
        });
        logger.info(String.format("Created %s poller tasks. Start polling...", pollerTasks.size()));

    }
}
