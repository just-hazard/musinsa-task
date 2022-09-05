package com.task.musinsa.domain

import com.task.musinsa.common.BaseEntity
import javax.persistence.*

@Entity
class Category(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(nullable = false)
    var title: String,

    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.PERSIST])
    @JoinColumn(name = "category_id")
    var brands: MutableList<Brand> = mutableListOf()
): BaseEntity()