package com.backendstudyweek2.memo.ui;

import com.backendstudyweek2.memo.application.MemoService;
import com.backendstudyweek2.memo.application.dto.CreateMemoRequest;
import com.backendstudyweek2.memo.application.dto.CreateMemoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/memo")
@RequiredArgsConstructor
public class MemoController {

    private final MemoService memoService;

    @PostMapping
    public ResponseEntity<CreateMemoResponse> createMemo(@RequestBody CreateMemoRequest request) {
        Long memoId = memoService.createMemo(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(new CreateMemoResponse(request.id()));
    }
}
