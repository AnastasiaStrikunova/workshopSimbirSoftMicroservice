package org.example.controller;

import org.example.dto.PurseRequestDto;
import org.example.dto.PurseResponseDto;
import org.example.service.PurseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api-base-url}/purse")
public class PurseController {
    private final PurseService purseService;

    public PurseController(PurseService purseService) {
        this.purseService = purseService;
    }

    @GetMapping
    public ResponseEntity<List<PurseResponseDto>> findAll(){
        return ResponseEntity.ok(purseService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PurseResponseDto> findById(@PathVariable Long id){
        return ResponseEntity.ok(purseService.findById(id));
    }

    @PostMapping
    public ResponseEntity<PurseResponseDto> add(@RequestBody PurseRequestDto purseRequestDto){
        return ResponseEntity.ok(purseService.add(purseRequestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PurseResponseDto> change(@PathVariable Long id, @RequestBody PurseRequestDto purseRequestDto){
        return ResponseEntity.ok(purseService.change(id, purseRequestDto));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        purseService.delete(id);
    }
}
