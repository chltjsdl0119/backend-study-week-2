package com.backendstudyweek2.memo.repository;

import com.backendstudyweek2.memo.application.interfaces.MemoRepository;
import com.backendstudyweek2.memo.domain.Memo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

@Repository
public class MemoRepositoryImpl implements MemoRepository {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final File file = new File("memos.json");

    @Override
    public boolean isExistMemo(Long id) {
        try {
            List<Memo> memos = new ArrayList<>();

            if (file.exists() && file.length() > 0) {
                memos = objectMapper.readValue(file, new TypeReference<List<Memo>>() {
                });
            }

            for (Memo m : memos) {
                if (m.getId().equals(id)) {
                    return true;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("메모 조회 실패", e);
        }

        return false;
    }

    @Override
    public void save(Memo memo) {
        try {
            List<Memo> memos = new ArrayList<>();

            // 파일이 존재하고 비어있지 않으면 기존 메모 리스트를 불러옴
            if (file.exists() && file.length() > 0) {
                memos = objectMapper.readValue(file, new TypeReference<List<Memo>>() {});
            }

            // 새 메모 추가
            memos.add(memo);

            // 전체를 다시 저장 (덮어쓰기)
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, memos);

        } catch (IOException e) {
            throw new RuntimeException("JSON 파일 저장 실패!", e);
        }
    }

    @Override
    public Memo getMemo(Long memoId) {
        try {
            if (!file.exists()) {
                return null;
            }

            List<Memo> memos = objectMapper.readValue(file, new TypeReference<List<Memo>>() {});

            return memos.stream()
                    .filter(m -> m.getId().equals(memoId))
                    .findFirst()
                    .orElseThrow(() -> new NoSuchElementException("메모를 찾을 수 없습니다."));

        } catch (IOException e) {
            throw new RuntimeException("메모 조회 실패", e);
        }
    }

    @Override
    public void updateMemo(Memo updatedMemo) {
        try {
            if (!file.exists() || file.length() == 0) {
                throw new RuntimeException("메모 파일이 존재하지 않거나 비어 있습니다.");
            }

            // 전체 메모 리스트 로드
            List<Memo> memos = objectMapper.readValue(file, new TypeReference<List<Memo>>() {});

            // 메모를 찾아서 수정
            boolean found = false;
            for (int i = 0; i < memos.size(); i++) {
                if (memos.get(i).getId().equals(updatedMemo.getId())) {
                    memos.set(i, updatedMemo);
                    found = true;
                    break;
                }
            }

            if (!found) {
                throw new NoSuchElementException("업데이트할 메모를 찾을 수 없습니다. ID: " + updatedMemo.getId());
            }

            // 다시 전체 저장
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, memos);

        } catch (IOException e) {
            throw new RuntimeException("메모 업데이트 실패", e);
        }
    }

    @Override
    public void deleteMemo(Long id) {
        try {
            if (!file.exists() || file.length() == 0) {
                throw new RuntimeException("메모 파일이 존재하지 않거나 비어 있습니다.");
            }

            // 전체 메모 리스트 로드
            List<Memo> memos = objectMapper.readValue(file, new TypeReference<LinkedList<Memo>>() {});

            // 메모를 찾아서 수정
            boolean found = false;
            for (int i = 0; i < memos.size(); i++) {
                if (memos.get(i).getId().equals(id)) {
                    memos.remove(i);
                    found = true;
                    break;
                }
            }

            if (!found) {
                throw new NoSuchElementException("삭제할 메모를 찾을 수 없습니다. ID: " + id);
            }

            // 다시 전체 저장
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, memos);
        } catch (IOException e) {
            throw new RuntimeException("메모 업데이트 실패", e);
        }
    }
}
