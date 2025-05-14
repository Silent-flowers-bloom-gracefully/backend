package com.lylist.silentflowers.domain.bucket.port.out.query

import com.lylist.silentflowers.domain.bucket.vo.BucketList
import com.lylist.silentflowers.domain.global.interfaces.Query
import com.lylist.silentflowers.domain.global.interfaces.QueryModel
import com.lylist.silentflowers.domain.user.vo.User

interface FindMyBucketListQuery : Query<FindMyBucketListQuery.Model, BucketList> {

    override fun invoke(model: Model): BucketList

    data class Model(
        val user: User
    ) : QueryModel
}