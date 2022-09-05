package com.task.musinsa.domain

import javax.persistence.Embeddable

@Embeddable
class Price(var price: Int) {
    fun update(price: Int) {
        this.price = price
    }
}