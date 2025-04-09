package com.backendstudyweek2.memo.repository;

import com.backendstudyweek2.memo.application.interfaces.MemoRepository;
import com.backendstudyweek2.memo.domain.Memo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;

@Repository
public class MemoRepositoryImpl implements MemoRepository {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final File file = new File("memos.json");

    @Override
    public void save(Memo memo) {
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, memo);
        } catch (IOException e) {
            throw new RuntimeException("JSON 파일 저장 실패!", e);
        }
    }
}
