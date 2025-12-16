package com.nttmk.englishlearningserver.controllers;

import com.nttmk.englishlearningserver.dto.MiniGameDTO;
import com.nttmk.englishlearningserver.responses.ApiResponse;
import com.nttmk.englishlearningserver.responses.MiniGamePlayResponse;
import com.nttmk.englishlearningserver.responses.MiniGameResponse;
import com.nttmk.englishlearningserver.services.MiniGameService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MiniGameController {
    private final MiniGameService miniGameService;

    @PostMapping("/admin/mini-games")
    public ResponseEntity<ApiResponse<MiniGameResponse>> createMiniGame(@RequestBody @Valid MiniGameDTO dto) {
        MiniGameResponse response = miniGameService.createMiniGame(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(HttpStatus.CREATED.value(), "Mini-Game created", response));
    }

    @PutMapping("/admin/mini-games/{id}")
    public ResponseEntity<ApiResponse<MiniGameResponse>> updateMiniGame(@PathVariable String id, @RequestBody @Valid MiniGameDTO dto) {
        MiniGameResponse response = miniGameService.updateMiniGame(id, dto);

        return ResponseEntity.ok().body(new ApiResponse<>(HttpStatus.OK.value(), "Mini-Game updated", response));
    }

    @DeleteMapping("/admin/mini-games/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteMiniGame(@PathVariable String id) {
        miniGameService.deleteMiniGame(id);

        return ResponseEntity.ok().body(new ApiResponse<>(HttpStatus.OK.value(), "Mini-Game deleted", null));
    }

    @GetMapping("/admin/mini-games")
    public ResponseEntity<ApiResponse<List<MiniGameResponse>>> getAllMiniGame() {
        List<MiniGameResponse> responses = miniGameService.getAllMiniGames();

        return ResponseEntity.ok().body(new ApiResponse<>(HttpStatus.OK.value(), "Get all mini-games", responses));
    }

    @GetMapping("/admin/mini-games/{id}")
    public ResponseEntity<ApiResponse<MiniGameResponse>> getMiniGameById(@PathVariable String id) {
        MiniGameResponse response = miniGameService.getMiniGameById(id);

        return ResponseEntity.ok().body(new ApiResponse<>(HttpStatus.OK.value(), "Get mini-games by id", response));
    }

    @GetMapping("/mini-games/start/{count}")
    public ResponseEntity<ApiResponse<List<MiniGamePlayResponse>>> startMiniGame(@PathVariable int count) {
        List<MiniGamePlayResponse> responses = miniGameService.startGames(count);

        return ResponseEntity.ok().body(new ApiResponse<>(HttpStatus.OK.value(), "Start mini-games", responses));
    }

    @PostMapping("mini-games/{id}/submit")
    public ResponseEntity<ApiResponse<Boolean>> submit(@PathVariable String id, @RequestParam String answer) {
        boolean correct = miniGameService.submitAnswer(id, answer);

        return ResponseEntity.ok().body(new ApiResponse<>(HttpStatus.OK.value(), "Submit result", correct));
    }
}
