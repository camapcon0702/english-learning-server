package com.nttmk.englishlearningserver.services;

import com.nttmk.englishlearningserver.dto.MiniGameDTO;
import com.nttmk.englishlearningserver.exceptions.AlreadyExistsException;
import com.nttmk.englishlearningserver.exceptions.DataNotFoundException;
import com.nttmk.englishlearningserver.models.MiniGame;
import com.nttmk.englishlearningserver.repository.MiniGameRepository;
import com.nttmk.englishlearningserver.responses.MiniGamePlayResponse;
import com.nttmk.englishlearningserver.responses.MiniGameResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.rmi.AlreadyBoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MiniGameService {
    private final MiniGameRepository miniGameRepository;

    @Transactional
    public MiniGameResponse createMiniGame(MiniGameDTO dto) {
        if (miniGameRepository.existsByWord(dto.getWord())) {
            throw new AlreadyExistsException("Word existed: " + dto.getWord());
        }

        MiniGame miniGame = MiniGame.builder()
                .word(dto.getWord())
                .suggest(dto.getSuggest())
                .createdAt(LocalDateTime.now())
                .build();

        miniGameRepository.save(miniGame);

        return mapToResponse(miniGame);
    }

    @Transactional
    public MiniGameResponse updateMiniGame(String id, MiniGameDTO dto) {
        MiniGame miniGame = miniGameRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Không tồn tại id: " + id));

        miniGame.setWord(dto.getWord());
        miniGame.setSuggest(dto.getSuggest());

        miniGameRepository.save(miniGame);

        return mapToResponse(miniGame);
    }

    @Transactional
    public void deleteMiniGame(String id) {
        MiniGame miniGame = miniGameRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Không tồn tại id: " + id));

        miniGameRepository.delete(miniGame);
    }

    public List<MiniGameResponse> getAllMiniGames() {
        return miniGameRepository.findAll().stream().map(this::mapToResponse).toList();
    }

    public MiniGameResponse getMiniGameById(String id) {
        MiniGame miniGame = miniGameRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Không tồn tại id: " + id));

        return mapToResponse(miniGame);
    }

    public List<MiniGamePlayResponse> startGames(int count) {

        List<MiniGame> games = miniGameRepository.findAll();
        if (games.size() < count) {
            throw new DataNotFoundException("Mini-Games aren't enough");
        }

        Collections.shuffle(games);

        return games.stream()
                .limit(count)
                .map(this::toPlayResponse)
                .toList();
    }

    public boolean submitAnswer(String id, String userAnswer) {
        MiniGame game = miniGameRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mini game not found"));

        return game.getWord().equalsIgnoreCase(userAnswer);
    }

    private MiniGameResponse mapToResponse(MiniGame miniGame) {
        return MiniGameResponse.builder()
                .id(miniGame.getId())
                .word(miniGame.getWord())
                .suggest(miniGame.getSuggest())
                .createdAt(miniGame.getCreatedAt())
                .build();
    }

    private MiniGamePlayResponse toPlayResponse(MiniGame game) {
        List<String> letters = new ArrayList<>(
                game.getWord()
                        .chars()
                        .mapToObj(c -> String.valueOf((char) c))
                        .toList()
        );

        Collections.shuffle(letters);

        return MiniGamePlayResponse.builder()
                .id(game.getId())
                .scrambledLetters(letters)
                .suggest(game.getSuggest())
                .build();
    }
}