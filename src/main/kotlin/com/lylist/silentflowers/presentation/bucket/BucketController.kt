package com.lylist.silentflowers.presentation.bucket

import com.lylist.silentflowers.domain.bucket.port.`in`.CreateBucketUseCase
import com.lylist.silentflowers.domain.bucket.port.`in`.DeleteBucketUseCase
import com.lylist.silentflowers.domain.bucket.port.`in`.MyBucketListUseCase
import com.lylist.silentflowers.presentation.bucket.dto.CreateBucketWebRequest
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RequestMapping("/bucket")
@RestController
class BucketController(
    private val myBucketListUseCase: MyBucketListUseCase,
    private val createBucketUseCase: CreateBucketUseCase,
    private val deleteBucketUseCase: DeleteBucketUseCase,
) {

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    fun getMyBucketList() = myBucketListUseCase.invoke()

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun createBucket(
        @Valid
        @RequestBody
        req: CreateBucketWebRequest
    ) = createBucketUseCase.invoke(req)

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    fun deleteBucket(
        @PathVariable
        id: Long
    ) = deleteBucketUseCase.invoke(id)
}