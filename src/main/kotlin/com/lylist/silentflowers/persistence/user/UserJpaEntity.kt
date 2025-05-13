package com.lylist.silentflowers.persistence.user

import jakarta.persistence.*

@Table(name = "tbl_user")
@Entity
class UserJpaEntity(
    id: Long? = null,
    username: String,
    nickname: String,
    password: String
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = id
        protected set

    @Column(name = "username", unique = true, nullable = false, updatable = false, length = 30)
    var username: String = username
        protected set

    @Column(name = "nickname", nullable = false, updatable = false, length = 20)
    var nickname: String = nickname
        protected set

    @Column(name = "password", nullable = false, updatable = false, length = 80)
    var password: String = password
        protected set
}