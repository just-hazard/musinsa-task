## π Getting Started

```
./gradlew bootRun
java -jar build/libs/*.jar
```

## μκ΅¬μ¬ν­
* λͺ¨λ  μΉ΄νκ³ λ¦¬μ μνμ λΈλλ λ³λ‘ μμ λ‘­κ² μ ν ν λͺ¨λ  μνμ κ΅¬λ§€ν  λ μ΅μ κ° μ‘°ν API
* ν λΈλλμμ λͺ¨λ  μΉ΄νκ³ λ¦¬μ μνμ νκΊΌλ²μ κ΅¬λ§€ν  κ²½μ° μ΅μ κ° λ° λΈλλ μ‘°ν API
* κ° μΉ΄νκ³ λ¦¬ μ΄λ¦μΌλ‘ μ΅μ, μ΅λ κ°κ²© μ‘°ν API
* λΈλλ μν κ°κ²© μΆκ° / μμ  / μ­μ  API

## μ‘°κ±΄
* 
1. μμ
2. μμ°ν° 
3. λ°μ§
4. μ€λμ»€μ¦ 
5. κ°λ°©
6. λͺ¨μ
7. μλ§
8. μ‘μΈμλ¦¬

8κ°μ§ μΉ΄νκ³ λ¦¬ μνμ νλμ© κ΅¬λ§€νμ¬ μ½λλ₯Ό μμ±
* μΆκ°μ μΈ λΉμ©μ κ³ λ €νμ§ μκ³  λΈλλμ μΉ΄νκ³ λ¦¬ λ³ 1κ°μ μνμ μ‘΄μ¬ λ° μν νμ  μμΈ

## API

### μΉ΄νκ³ λ¦¬ λ³ μ΅μ κ° λΈλλ μ‘°ν
```
Request
GET apis/products/lowest-price

Response
HTTP/1.1 200 OK
{
    [
        "category" : μΉ΄νκ³ λ¦¬λͺ,
        "brand" : λΈλλλͺ,
        "lowestPrice" : μ΅μ κ°
    ],
    totalPrice : μ΄ν©κ°κ²©
}
```

### νΉμ  λΈλλμμ μνμ νκΊΌλ²μ κ΅¬λ§€ν  κ²½μ° μ΅μ κ° μΈ λΈλλ μ‘°ν
```
Request
GET apis/products/brands/lowest-price

Response
HTTP/1.1 200 OK
{
    "brandName" : λΈλλλͺ,
    "totalPrice" : μ΄ν©κ°κ²©
}
```

### νΉμ  μΉ΄νκ³ λ¦¬μ μ΅μ, μ΅λ κ°κ²© μ‘°ν
```
Request
GET apis/products/categories/min-max-price?categoryName={categoryName}

Response
HTTP/1.1 200 OK
{
    "lowestPriceBrandName" : μ΅μκ°κ²© λΈλλλͺ,
    "lowestPrice" : μ΅μκ°κ²©
    "mostExpensiveBrandName" : μ΅λκ°κ²© λΈλλλͺ,
    "mostExpensivePrice" : μ΅λκ°κ²©
}
```

### λΈλλ μν λ±λ‘
```
Request
POST apis/brands
{
    "title" : λΈλλλͺ,
    "price" : κ°κ²©,
    "categoryId" : μΉ΄νκ³ λ¦¬ ν€
}

Response
HTTP/1.1 201 CREATED
-H Location apis/brands/{id}
```

### λΈλλ μν μμ 
```
Request
PUT apis/brands/{id}
{
    "title" : λΈλλλͺ,
    "price" : κ°κ²©
}

Response
HTTP/1.1 204 NO_CONTENT
```

### λΈλλ μν μ­μ 
```
Request
DELETE apis/brands/{id}


Response
HTTP/1.1 204 NO_CONTENT
```