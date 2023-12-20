package dev.flynnpark.springcoreaop.exam;

import org.springframework.stereotype.Repository;

@Repository
public class ExamRepository {
    private static int seq = 0;

    /**
     * 5번에 한 번씩 실패하는 요청
     */
    public String save(String itemId) {
        if (seq++ % 5 == 0) {
            throw new IllegalStateException("예외 발생");
        }
        return itemId;
    }
}
