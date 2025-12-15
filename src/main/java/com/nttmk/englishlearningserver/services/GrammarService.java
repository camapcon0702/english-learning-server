package com.nttmk.englishlearningserver.services;

import com.nttmk.englishlearningserver.dto.GrammarDTO;
import com.nttmk.englishlearningserver.exceptions.DataNotFoundException;
import com.nttmk.englishlearningserver.models.Grammar;
import com.nttmk.englishlearningserver.repository.GrammarRepository;
import com.nttmk.englishlearningserver.responses.GrammarResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GrammarService {
    private final GrammarRepository grammarRepository;

    @Transactional
    public GrammarResponse createGrammar(GrammarDTO dto) {
        Grammar grammar = Grammar.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .content(dto.getContent())
                .build();

        Grammar savedGrammar = grammarRepository.save(grammar);

        return mapToResponse(savedGrammar);
    }

    public List<GrammarResponse> getAllGrammars() {
        return grammarRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public GrammarResponse getGrammarById(String id) {
        Grammar grammar = grammarRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Grammar không tồn tại"));

        return mapToResponse(grammar);
    }

    @Transactional
    public GrammarResponse updateGrammar(String id, GrammarDTO dto) {
        Grammar grammar = grammarRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Grammar không tồn tại"));

        grammar.setName(dto.getName());
        grammar.setDescription(dto.getDescription());
        grammar.setContent(dto.getContent());

        Grammar updatedGrammar = grammarRepository.save(grammar);
        return mapToResponse(updatedGrammar);
    }

    @Transactional
    public void deleteGrammar(String id) {
        if (!grammarRepository.existsById(id)) {
            throw new DataNotFoundException("Grammar không tồn tại");
        }
        grammarRepository.deleteById(id);
    }

    private GrammarResponse mapToResponse(Grammar grammar) {
        return GrammarResponse.builder()
                .id(grammar.getId())
                .name(grammar.getName())
                .description(grammar.getDescription())
                .content(grammar.getContent())
                .build();
    }
}
