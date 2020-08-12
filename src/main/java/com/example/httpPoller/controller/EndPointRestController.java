package com.example.httpPoller.controller;

import com.example.httpPoller.model.EndPoint;
import com.example.httpPoller.repository.EndPointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RequestMapping("/api")
@RestController
public class EndPointRestController {
    @Autowired
    private EndPointRepository endPointRepository;

    public EndPointRestController(EndPointRepository endPointRepository) {
        this.endPointRepository = endPointRepository;
    }

    @GetMapping("/endpoints/{id}")
    public EndPoint one(@PathVariable String id) {
        return endPointRepository.findById(Integer.parseInt(id)).get();
    }

    @GetMapping("/endpoints/count")
    public Long count() {
        return endPointRepository.count();
    }

    @GetMapping("/endpoint")
    public EndPoint getEndPointById(@RequestParam Integer id) {
        return endPointRepository.findById(id).get();
    }

    @GetMapping("/endpoint/{id}")
    ResponseEntity<?> getEndPoint(@PathVariable Integer id) {
        Optional<EndPoint> endPoint = endPointRepository.findById(id);
        return endPoint.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/endpoints")
    public List<EndPoint> all() {
        return endPointRepository.findAll();
    }

    //create
    @PostMapping("/endpoint")
    public ResponseEntity<EndPoint> createEndpoint(@RequestBody EndPoint newEndPoint) throws URISyntaxException {
        EndPoint result = endPointRepository.save(newEndPoint);
        //HttpPollerApplication.restart();
        return ResponseEntity.created(new URI("/api/endpoint/" + result.getId()))
                .body(result);
    }

    //update
    @PutMapping("/endpoint")
    ResponseEntity<EndPoint> updateEndPoint(@Valid @RequestBody EndPoint endPoint) {
        //endPoint.setEndPointStatusList();
        EndPoint result = endPointRepository.save(endPoint);
        //HttpPollerApplication.restart();
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/endpoint/{id}")
    public ResponseEntity<?> deleteEndPoint(@PathVariable Integer id) {
        endPointRepository.deleteById(id);
        //HttpPollerApplication.restart();
        return ResponseEntity.ok().build();
    }

}
