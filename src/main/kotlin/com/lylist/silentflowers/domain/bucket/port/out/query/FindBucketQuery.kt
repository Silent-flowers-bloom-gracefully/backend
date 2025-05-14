package com.lylist.silentflowers.domain.bucket.port.out.query

import com.lylist.silentflowers.domain.bucket.vo.Bucket
import com.lylist.silentflowers.domain.global.interfaces.Query
import com.lylist.silentflowers.domain.global.interfaces.QueryModel

interface FindBucketQuery : Query<FindBucketQuery.Model, Bucket?> {

    override fun invoke(model: Model): Bucket?

    data class Model(
        val id: Long
    ) : QueryModel
}