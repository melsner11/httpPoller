package com.example.httpPoller;

import com.example.httpPoller.model.EndPoint;
import com.example.httpPoller.model.EndPointStatus;
import com.example.httpPoller.repository.EndPointRepository;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

public class HttpPollerTask {
    private Timer timer;
    private EndPoint endPoint;

    private static final Logger logger = Logger.getLogger(HttpPoller.class.getName());

    public void stopTimer(){
        logger.info("Stop TimerTask for: " + endPoint.getURL() );
        timer.cancel();
        timer.purge();
    }

    public HttpPollerTask(EndPoint endPoint, EndPointRepository endPointRepository) {
        logger.info("New PollerTask for : " + endPoint.getURL() + " size: " +endPoint.getEndPointStatusList().size());
        this.timer = new Timer();
        this.endPoint = endPoint;
        timer.schedule(new TimerTask() {
            public void run() {
                HttpClient httpClient = HttpClientBuilder.create().build();
                HttpGet httpGet = new HttpGet(endPoint.getURL());
                httpGet.setHeader("Content-type", "application/xml");
                httpGet.setHeader("Accept", "application/xml");
                HttpResponse response = null;
                Date date = new Date();

                try {
                    response = httpClient.execute(httpGet);
                    // logger.info(String.format("Status code: %s  %s", httpGet, response.getStatusLine().getStatusCode()));
                    EndPointStatus eps = new EndPointStatus((response != null) ? response.getStatusLine().getStatusCode() : 0, new Timestamp(date.getTime()), response.getStatusLine().getReasonPhrase());
                    eps.setEndPoint(endPoint);
                    endPoint.addEndPointStatus(eps);
                    endPointRepository.save(endPoint);
                } catch (UnknownHostException e) {
                    logger.warning("Unknown host: " + endPoint.getURL() + " Message: "+ e.getMessage());

                    EndPointStatus eps = new EndPointStatus(0, new Timestamp(date.getTime()), "Unknown host : " + e.getMessage());
                    eps.setEndPoint(endPoint);
                    endPoint.addEndPointStatus(eps);
                    endPointRepository.save(endPoint);
                } catch (java.net.ConnectException e) {
                    logger.warning("connection exception: " + endPoint.getURL() + " Message: " + e.getMessage());

                    EndPointStatus eps = new EndPointStatus(0, new Timestamp(date.getTime()), "Connect exception : "  + e.getMessage());
                    eps.setEndPoint(endPoint);
                    endPoint.addEndPointStatus(eps);
                    endPointRepository.save(endPoint);
                } catch (IOException e) {
                    logger.warning("IO Exception: " + endPoint.getURL() + " Message: " + e.getMessage());
                    e.printStackTrace();
                } catch (Exception e) {
                    logger.warning("Any other Exception exception: " + endPoint.getURL() + " Message: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }, 0, 10000);
    }
}


