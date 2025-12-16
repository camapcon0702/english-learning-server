package com.nttmk.englishlearningserver.controllers;

import com.nttmk.englishlearningserver.dto.GrammarDTO;
import com.nttmk.englishlearningserver.models.Grammar;
import com.nttmk.englishlearningserver.responses.ApiResponse;
import com.nttmk.englishlearningserver.responses.GrammarResponse;
import com.nttmk.englishlearningserver.services.GrammarService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
public class GrammarController {
    private final GrammarService grammarService;

    @PostMapping("/admin/grammars")
    public ResponseEntity<ApiResponse<GrammarResponse>> createGrammar(@RequestBody @Valid GrammarDTO dto) {
        GrammarResponse response = grammarService.createGrammar(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(HttpStatus.CREATED.value(), "Tạo công thức thành công", response));
    }

    @PutMapping("/admin/grammars/{id}")
    public ResponseEntity<ApiResponse<GrammarResponse>> updateGrammar(@PathVariable String id,@RequestBody @Valid GrammarDTO dto) {
        GrammarResponse response = grammarService.updateGrammar(id, dto);

        return ResponseEntity.ok().body(new ApiResponse<>(HttpStatus.OK.value(), "Cập nhật công thức thành công", response));
    }

    @DeleteMapping("/admin/grammars/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteGrammar(@PathVariable String id) {
        grammarService.deleteGrammar(id);

        return ResponseEntity.ok().body(new ApiResponse<>(HttpStatus.OK.value(), "Xoá công thức thành công", null));
    }

    @GetMapping("/grammars")
    public ResponseEntity<ApiResponse<List<GrammarResponse>>> getAllGrammars() {
        List<GrammarResponse> response = grammarService.getAllGrammars();

        return ResponseEntity.ok().body(new ApiResponse<>(HttpStatus.OK.value(), "Lấy công thức thành công", response));
    }

    @GetMapping("/grammars/{id}")
    public ResponseEntity<ApiResponse<GrammarResponse>> getGrammarsById(@PathVariable String id) {
        GrammarResponse response = grammarService.getGrammarById(id);

        return ResponseEntity.ok().body(new ApiResponse<>(HttpStatus.OK.value(), "Lấy công thức thành công", response));
    }
}
