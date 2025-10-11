package com.example.demo.animal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ElephantController {

  @Autowired
  private ElephantService elephantService;

  /**
   * Endpoint to get all elephants
   *
   * @return List of all elephants
   */
  @GetMapping("/elephants")
  public Object getAllElephants() {
    return elephantService.getAllElephants();
  }

  /**
   * Endpoint to get an elephant by ID
   *
   * @param id The ID of the elephant to retrieve
   * @return The elephant with the specified ID
   */
  @GetMapping("/elephants/{id}")
  public Elephant getElephantById(@PathVariable long id) {
    return elephantService.getElephantById(id);
  }

  /**
   * Endpoint to get elephants by name
   *
   * @param name The name of the elephant to search for
   * @return List of elephants with the specified name
   */
  @GetMapping("/elephants/name")
  public Object getElephantsByName(@RequestParam String key) {
    if (key != null) {
      return elephantService.getElephantsByName(key);
    } else {
      return elephantService.getAllElephants();
    }

  }

  /**
   * Endpoint to get elephants by gender
   *
   * @param gender The gender to search for
   * @return List of elephants with the specified gender
   */
  @GetMapping("/elephants/gender/{gender}")
  public Object getElephantsByGender(@PathVariable String gender) {
    return elephantService.getElephantsByGender(gender);
  }

  /**
   * Endpoint to get adult elephants with age above a specified threshold
   *
   * @param age The age threshold for adult elephants
   * @return List of adult elephants with age above the specified threshold
   */
  @GetMapping("/elephants/adults")
  public Object getAdultElephants(@RequestParam(name = "age", defaultValue = "18") int age) {
    return new ResponseEntity<>(elephantService.getAdultElephants(age), HttpStatus.OK);

  }

  /**
   * Endpoint to add a new elephant
   *
   * @param elephant The elephant to add
   * @return List of all elephants
   */
  @PostMapping("/elephants")
  public Object addElephant(@RequestBody Elephant elephant) {
    return elephantService.addElephant(elephant);
  }

  /**
   * Endpoint to update a elephant
   *
   * @param id      The ID of the elephant to update
   * @param elephant The updated elephant information
   * @return The updated elephant
   */
  @PutMapping("/elephants/{id}")
  public Elephant updateElephant(@PathVariable Long id, @RequestBody Elephant elephant) {
    return elephantService.updateElephant(id, elephant);
  }

  /**
   * Endpoint to delete a elephant
   *
   * @param id The ID of the elephant to delete
   * @return List of all elephants
   */
  @DeleteMapping("/elephants/{id}")
  public Object deleteElephant(@PathVariable Long id) {
    elephantService.deleteElephant(id);
    return elephantService.getAllElephants();
  }

  /**
   * Endpoint to write a elephant to a JSON file
   *
   * @param elephant The elephant to write
   * @return An empty string indicating success
   */
  @PostMapping("/elephants/writeFile")
  public Object writeJson(@RequestBody Elephant elephant) {
    return elephantService.writeJson(elephant);
  }

  /**
   * Endpoint to read a JSON file and return its contents
   *
   * @return The contents of the JSON file
   */
  @GetMapping("/elephants/readFile")
  public Object readJson() {
    return elephantService.readJson();

  }

}