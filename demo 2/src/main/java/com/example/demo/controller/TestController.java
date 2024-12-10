package com.example.demo.controller;

import com.example.demo.dto.ResponseDto;
import com.example.demo.dto.TestRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController //restapi 구현임을 확인할 수 있음
@RequestMapping("test")
public class TestController {

    @GetMapping("/testGetMapping")
    public String testControllerWPath(){
        return "Hey 경민";
    }

    @GetMapping("/{id}")
    public String testControllerWPathVariable(@PathVariable(required = false)int id){
        return "Hey"+id;
    }

    @GetMapping("/testRequestParam")
    public String testControllerRequestParam(@RequestParam(required = false)int id) {
        return "hi"+id;
    }

    @GetMapping("/testRequestBody")
    public String testControllerRequestBody(@RequestBody TestRequestDto testRequestBodyDTO){
        return "hey ID:"+ testRequestBodyDTO.getId()+"Message: " + testRequestBodyDTO.getMessage();
    }

    @GetMapping("/testResponseBody")
    public ResponseDto<String> testControllerResponseBody() {
        List<String> list = new ArrayList<>();
        list.add("THis is ResponserDto");
        ResponseDto<String> response = ResponseDto.<String>builder().data(list).build();
        return  response;
    }

    @GetMapping("/testResponseEntity")
    public ResponseEntity<?> testControllerResponseEntity() {
        List<String> list = new ArrayList<>();
        list.add("THis is ResponserEntity + 400 ");
        ResponseDto<String> response = ResponseDto.<String>builder().data(list).build();
        return ResponseEntity.badRequest().body(response);

    }
}
