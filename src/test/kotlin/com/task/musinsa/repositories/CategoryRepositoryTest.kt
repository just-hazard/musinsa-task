package com.task.musinsa.repositories

import com.task.musinsa.global.config.message.ErrorMessage
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.jdbc.Sql
import javax.persistence.EntityNotFoundException

@DataJpaTest
class CategoryRepositoryTest {

    @Autowired
    private lateinit var categoryRepository: CategoryRepository

    @Test
    fun `카테고리 이름으로 데이터 조회`() {
        val actual = categoryRepository.findByTitle("상의")
        assertThat(actual).isNotNull
        assertThat(actual.get().title).isEqualTo("상의")
    }
}