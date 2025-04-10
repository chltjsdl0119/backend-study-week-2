package com.backendstudyweek2.memo.application;

import com.backendstudyweek2.memo.application.dto.CreateMemoRequest;
import com.backendstudyweek2.memo.application.interfaces.MemoRepository;
import com.backendstudyweek2.memo.domain.Memo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemoService {

    private final MemoRepository memoRepository;

    public Long createMemo(CreateMemoRequest request) {
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
}
