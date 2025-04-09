package com.backendstudyweek2.memo.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Memo {

    private Long id;
    private String content;

    @Builder
    public Memo(Long id, String content) {
        this.id = id;
        this.content = content;
    }
}
