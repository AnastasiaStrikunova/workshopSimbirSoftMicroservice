package org.example.controller;

import org.example.dto.HistoryRequestDto;
import org.example.dto.HistoryResponseDto;
import org.example.service.HistoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api-base-url}/history")

public class HistoryController {
    private final HistoryService historyService;

    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }

    @GetMapping
    public ResponseEntity<List<HistoryResponseDto>> findAll(){
        return ResponseEntity.ok(historyService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HistoryResponseDto> findById(@PathVariable Long id){
        return ResponseEntity.ok(historyService.findById(id));
    }

    @PostMapping
    public ResponseEntity<HistoryResponseDto> add(@RequestBody HistoryRequestDto historyRequestDto){
        return ResponseEntity.ok(historyService.add(historyRequestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HistoryResponseDto> change(@PathVariable Long id, @RequestBody HistoryRequestDto historyRequestDto){
        return ResponseEntity.ok(historyService.change(id, historyRequestDto));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        historyService.delete(id);
    }
}
