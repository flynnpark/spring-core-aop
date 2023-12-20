package dev.flynnpark.springcoreaop.exam;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExamService {
    private final ExamRepository examRepository;

    public String request(String itemId) {
        return examRepository.save(itemId);
    }
}
