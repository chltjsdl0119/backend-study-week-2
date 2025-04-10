package com.backendstudyweek2.memo.ui;

import com.backendstudyweek2.memo.application.MemoService;
import com.backendstudyweek2.memo.application.dto.CreateMemoRequest;
import com.backendstudyweek2.memo.application.dto.CreateMemoResponse;
import com.backendstudyweek2.memo.application.dto.GetMemoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/memo")
@RequiredArgsConstructor
public class MemoController {

    private final MemoService memoService;

    @PostMapping
    public ResponseEntity<CreateMemoResponse> createMemo(@RequestBody CreateMemoRequest request) {
        Long memoId = memoService.createMemo(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(new CreateMemoResponse(memoId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetMemoResponse> getMemo(@PathVariable Long id) {
        GetMemoResponse response = new GetMemoResponse(memoService.getMemo(id));
        return ResponseEntity.status(HttpStatus.FOUND).body(response);
    }
}
