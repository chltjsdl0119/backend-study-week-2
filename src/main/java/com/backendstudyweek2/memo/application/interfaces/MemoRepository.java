package com.backendstudyweek2.memo.application.interfaces;

import com.backendstudyweek2.memo.domain.Memo;

public interface MemoRepository {

    boolean isExistMemo(Long id);
    void save(Memo memo);
    Memo getMemo(Long memoId);
}
