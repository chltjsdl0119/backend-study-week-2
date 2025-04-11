package com.backendstudyweek2.memo.application.dto;

import com.backendstudyweek2.memo.domain.Memo;

public record GetMemoResponse(Long id, String content) {
    public GetMemoResponse(Memo memo) {
        this(memo.getId(), memo.getContent());
    }
}
