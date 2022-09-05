package com.task.musinsa.domain

import com.task.musinsa.common.BaseEntity
import javax.persistence.*

@Entity
class Brand(
    @Column(nullable = false)
    var title: String,

    @Embedded
    var price: Price,

    @ManyToOne
    var category: Category
) : BaseEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    fun update(title: String, price: Int) {
        this.title = title
        this.price.update(price)
    }
}