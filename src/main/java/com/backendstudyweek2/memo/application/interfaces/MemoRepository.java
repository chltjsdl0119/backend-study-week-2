package com.backendstudyweek2.memo.application.interfaces;

import com.backendstudyweek2.memo.domain.Memo;

public interface MemoRepository {

    void save(Memo memo);
}
