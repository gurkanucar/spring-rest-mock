package com.gucardev.springrestmock.controller;

import com.gucardev.springrestmock.model.MockData;
import com.gucardev.springrestmock.service.MockDataService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class IndexController {

  protected final MockDataService mockDataService;

  public IndexController(MockDataService mockDataService) {
    this.mockDataService = mockDataService;
  }

  @GetMapping(value = "/mocks/{id}")
  @ResponseBody
  public MockData getById(@PathVariable Long id) {
    return mockDataService.findById(id);
  }

  @GetMapping(value = "/")
  public String showWelcomePage() {
    return "index";
  }

  @GetMapping(value = "/mocks")
  public String showMocksPage(Model model) {
    model.addAttribute("mockDataList", mockDataService.findAll());
    return "mocks";
  }
}
