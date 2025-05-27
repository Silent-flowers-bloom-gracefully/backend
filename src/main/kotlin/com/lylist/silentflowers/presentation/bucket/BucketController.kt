package com.lylist.silentflowers.presentation.bucket

import com.lylist.silentflowers.domain.bucket.port.`in`.CreateBucketUseCase
import com.lylist.silentflowers.domain.bucket.port.`in`.DeleteBucketUseCase
import com.lylist.silentflowers.domain.bucket.port.`in`.MyBucketListUseCase
import com.lylist.silentflowers.domain.bucket.port.`in`.SuccessBucketUseCase
import com.lylist.silentflowers.presentation.bucket.dto.CreateBucketWebRequest
import jakarta.validation.Valid
import jakarta.validation.constraints.Positive
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RequestMapping("/bucket")
@RestController
class BucketController(
    private val myBucketListUseCase: MyBucketListUseCase,
    private val createBucketUseCase: CreateBucketUseCase,
    private val deleteBucketUseCase: DeleteBucketUseCase,
    private val successBucketUseCase: SuccessBucketUseCase
) {

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    fun getMyBucketList() = myBucketListUseCase()

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun createBucket(
        @Valid
        @RequestBody
        req: CreateBucketWebRequest
    ) = createBucketUseCase(req)

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    fun deleteBucket(
        @Valid
        @Positive
        @PathVariable
        id: Long
    ) = deleteBucketUseCase(id)

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/{id}/success")
    fun successBucket(
        @Valid
        @Positive
        @PathVariable
        id: Long
    ) = successBucketUseCase(id)
}