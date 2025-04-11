package com.backendstudyweek2.memo.ui;

import com.backendstudyweek2.memo.application.MemoService;
import com.backendstudyweek2.memo.application.dto.CreateMemoRequest;
import com.backendstudyweek2.memo.application.dto.CreateMemoResponse;
import com.backendstudyweek2.memo.application.dto.GetMemoResponse;
import com.backendstudyweek2.memo.application.dto.UpdateMemoRequest;
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

    @PutMapping("/{id}")
    public ResponseEntity<String> updateMemo(@PathVariable Long id, @RequestBody UpdateMemoRequest request) {
        memoService.updateMemo(id, request);
        return ResponseEntity.status(HttpStatus.OK).body("메모 수정 성공!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMemo(@PathVariable Long id) {
        memoService.deleteMemo(id);
        return ResponseEntity.status(HttpStatus.OK).body("메모 삭제 성공!");
    }
}
