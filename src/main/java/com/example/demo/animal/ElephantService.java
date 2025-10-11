package com.example.demo.animal;

import java.io.IOException;
import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ElephantService {

  @Autowired
  private ElephantRepository elephantRepository;

  /**
   * Method to get all elephants
   *
   * @return List of all elephants
   */
  public Object getAllElephants() {
    return elephantRepository.findAll();
  }

  /**
   * Method to get an elephant by ID
   *
   * @param elephantId The ID of the elephant to retrieve
   * @return The elephant with the specified ID
   */
  public Elephant getElephantById(@PathVariable long elephantId) {
    return elephantRepository.findById(elephantId).orElse(null);
  }

  /**
   * Method to get elephants by name
   *
   * @param name The name of the elephant to search for
   * @return List of elephants with the specified name
   */
  public Object getElephantsByName(String name) {
    return elephantRepository.getElephantsByName(name);
  }

  /**
   * Method to get elephants by gender
   *
   * @param gender The gender to search for
   * @return List of elephants with the specified gender
   */
  public Object getElephantsByGender(String gender) {
    return elephantRepository.getElephantsByGender(gender);
  }

  /**
   * Fetch all elephants with a certain age.
   *
   * @param age the threshold
   * @return the list of matching Elephants
   */
  public Object getAdultElephants(int age) {
    return elephantRepository.getAdultElephants(age);
  }

  /**
   * Method to add a new elephant
   *
   * @param elephant The elephant to add
   */
  public Elephant addElephant(Elephant elephant) {
    return elephantRepository.save(elephant);
  }

  /**
   * Method to update a elephant
   *
   * @param elephantId The ID of the elephant to update
   * @param elephant   The updated elephant information
   */
  public Elephant updateElephant(Long elephantId, Elephant elephant) {
    elephant.setElephantId(elephantId);
    return elephantRepository.save(elephant);
  }

  /**
   * Method to delete a elephant
   *
   * @param elephantId The ID of the elephant to delete
   */
  public void deleteElephant(Long elephantId) {
    elephantRepository.deleteById(elephantId);
  }

  /**
   * Method to write a elephant object to a JSON file
   *
   * @param elephant The elephant object to write
   */
  public String writeJson(Elephant elephant) {
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      objectMapper.writeValue(new File("elephants.json"), elephant);
      return "Elephant written to JSON file successfully";
    } catch (IOException e) {
      e.printStackTrace();
      return "Error writing elephant to JSON file";
    }

  }

  /**
   * Method to read a elephant object from a JSON file
   *
   * @return The elephant object read from the JSON file
   */
  public Object readJson() {
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      return objectMapper.readValue(new File("elephants.json"), Elephant.class);
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }

  }

}
