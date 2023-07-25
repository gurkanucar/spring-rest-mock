package com.gucardev.springrestmock;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class WelcomeController {

  private List<MockData> mockDataList = new ArrayList<>();

  @PostConstruct
  void setInitialData() {

    mockDataList.add(
        MockData.builder()
            .id(1L)
            .responseType(ResponseType.SUCCESS)
            .path("/api/auth/login")
            .httpMethod(HttpMethod.POST)
            .successStatus(200)
            .failureStatus(401)
            .successResponse("sa")
            .failureResponse("as")
            .build());

    mockDataList.add(
        MockData.builder()
            .id(2L)
            .responseType(ResponseType.SUCCESS)
            .path("/api/auth/login")
            .httpMethod(HttpMethod.POST)
            .successStatus(200)
            .failureStatus(401)
            .successResponse("sa")
            .failureResponse("as")
            .build());

    mockDataList.add(
        MockData.builder()
            .id(3L)
            .responseType(ResponseType.FAILURE)
            .path("/api/auth/register")
            .httpMethod(HttpMethod.POST)
            .successStatus(200)
            .failureStatus(401)
            .successResponse("sa")
            .failureResponse("as")
            .build());

    mockDataList.add(
        MockData.builder()
            .id(4L)
            .responseType(ResponseType.RANDOM)
            .path("/api/user/1")
            .httpMethod(HttpMethod.DELETE)
            .successStatus(200)
            .failureStatus(404)
            .successResponse("sa")
            .failureResponse("as")
            .build());

    mockDataList.add(
        MockData.builder()
            .id(5L)
            .responseType(ResponseType.FAILURE)
            .path("/api/user/profile")
            .httpMethod(HttpMethod.PUT)
            .successStatus(200)
            .failureStatus(401)
            .successResponse("sa")
            .failureResponse("as")
            .build());
  }

  @GetMapping(value = "/mocks/{id}")
  @ResponseBody
  public MockData getById(@PathVariable Long id) {
    return mockDataList.stream().filter(x -> x.getId().equals(id)).findFirst().orElse(null);
  }

  @GetMapping(value = "/")
  public String showWelcomePage() {
    return "index";
  }

  @GetMapping(value = "/mocks")
  public String showMocksPage(Model model) {
    model.addAttribute("mockDataList", mockDataList);
    return "mocks";
  }

  @PostMapping
  public String add(@RequestParam String todoItem, @RequestParam String status, Model model) {
    MockData todo = new MockData();
    todo.setPath(todoItem);
    todo.setId((long) (mockDataList.size() + 1));
    mockDataList.add(todo);
    model.addAttribute("mocks", mockDataList);
    return "redirect:/mocks";
  }

  @DeleteMapping
  public String delete(@PathVariable long id, Model model) {
    model.addAttribute("mocks", mockDataList);
    return "redirect:/mocks";
  }

  @PutMapping("/{id}")
  public String update(@PathVariable long id, Model model) {
    model.addAttribute("mocks", mockDataList);
    return "redirect:/mocks";
  }
}
