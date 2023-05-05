// //https://medium.com/@nandaras0103/in-spring-restcontroller-cannot-be-resolved-to-a-type-eclipse-ad0244634d4c

package com.learn.restAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/hello")
public class HelloController {

  @Autowired
  private HelloRepository helloRepository;

  @GetMapping("/uhuy")
  public String hello(){
    return "Hello World!";
  }

  @GetMapping("/{id}")
  public ResponseEntity<Hello> getById(@PathVariable Long id) {
      Hello hello = helloRepository.findById(id).orElse(null);
      if (hello == null) {
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
      return new ResponseEntity<>(hello, HttpStatus.OK);
  }

  @GetMapping("")
  public ResponseEntity<List<Hello>> getAll() {
      List<Hello> helloList = new ArrayList<>();
      helloRepository.findAll().forEach(helloList::add);
      if (helloList.isEmpty()) {
          return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      return new ResponseEntity<>(helloList, HttpStatus.OK);
  }

  @PostMapping("")
  public ResponseEntity<Hello> create(@RequestBody Hello hello) {
      Hello savedHello = helloRepository.save(hello);
      return new ResponseEntity<>(savedHello, HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Hello> update(@PathVariable Long id, @RequestBody Hello hello) {
      Hello existingHello = helloRepository.findById(id).orElse(null);
      if (existingHello == null) {
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
      existingHello.setMessage(hello.getMessage());
      Hello updatedHello = helloRepository.save(existingHello);
      return new ResponseEntity<>(updatedHello, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
      helloRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}