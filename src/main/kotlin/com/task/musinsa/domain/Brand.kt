package com.task.musinsa.domain

import com.task.musinsa.common.BaseEntity
import javax.persistence.*

@Entity
class Brand(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(nullable = false)
    var title: String,

    @Embedded
    var price: Price,

    @ManyToOne
    var category: Category
) : BaseEntity()