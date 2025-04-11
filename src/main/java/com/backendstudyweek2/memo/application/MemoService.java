package com.backendstudyweek2.memo.application;

import com.backendstudyweek2.memo.application.dto.CreateMemoRequest;
import com.backendstudyweek2.memo.application.dto.UpdateMemoRequest;
import com.backendstudyweek2.memo.application.interfaces.MemoRepository;
import com.backendstudyweek2.memo.domain.Memo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemoService {

    private final MemoRepository memoRepository;

    public Long createMemo(CreateMemoRequest request) {
        if (memoRepository.isExistMemo(request.id())) {
            throw new RuntimeException("메모 ID가 이미 존재합니다!");
        }

        Memo memo = Memo.builder()
                .id(request.id())
                .content(request.content())
                .build();

        memoRepository.save(memo);
        return memo.getId();
    }

    public Memo getMemo(Long memoId) {
        Memo memo = memoRepository.getMemo(memoId);
        return memo;
    }

    public void updateMemo(Long id, UpdateMemoRequest request) {
        Memo memo = Memo.builder()
                .id(id)
                .content(request.content())
                .build();

        memoRepository.updateMemo(memo);
    }

    public void deleteMemo(Long id) {
        memoRepository.deleteMemo(id);
    }
}
