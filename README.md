## 🚀 Getting Started

```
./gradlew bootRun
java -jar build/libs/*.jar
```

## 요구사항
* 모든 카테고리의 상품을 브랜드 별로 자유롭게 선택 후 모든 상품을 구매할 때 최저가 조회 API
* 한 브랜드에서 모든 카테고리의 상품을 한꺼번에 구매할 경우 최저가 및 브랜드 조회 API
* 각 카테고리 이름으로 최소, 최대 가격 조회 API
* 브랜드 상품 가격 추가 / 수정 / 삭제 API

## 조건
* 
1. 상의
2. 아우터 
3. 바지
4. 스니커즈 
5. 가방
6. 모자
7. 양말
8. 액세서리

8가지 카테고리 상품을 하나씩 구매하여 코디를 완성
* 추가적인 비용은 고려하지 않고 브랜드의 카테고리 별 1개의 상품은 존재 및 상품 품절 예외

## API

### 카테고리 별 최저가 브랜드 조회
```
Request
GET apis/products/lowest-price

Response
HTTP/1.1 200 OK
{
    [
        "category" : 카테고리명,
        "brand" : 브랜드명,
        "lowestPrice" : 최저가
    ],
    totalPrice : 총합가격
}
```

### 특정 브랜드에서 상품을 한꺼번에 구매할 경우 최저가 인 브랜드 조회
```
Request
GET apis/products/brands/lowest-price

Response
HTTP/1.1 200 OK
{
    "brandName" : 브랜드명,
    "totalPrice" : 총합가격
}
```

### 특정 카테고리의 최소, 최대 가격 조회
```
Request
GET apis/products/categories/min-max-price?categoryName={categoryName}

Response
HTTP/1.1 200 OK
{
    "lowestPriceBrandName" : 최소가격 브랜드명,
    "lowestPrice" : 최소가격
    "mostExpensiveBrandName" : 최대가격 브랜드명,
    "mostExpensivePrice" : 최대가격
}
```

### 브랜드 상품 등록
```
Request
POST apis/brands
{
    "title" : 브랜드명,
    "price" : 가격,
    "categoryId" : 카테고리 키
}

Response
HTTP/1.1 201 CREATED
-H Location apis/brands/{id}
```

### 브랜드 상품 수정
```
Request
PUT apis/brands/{id}
{
    "title" : 브랜드명,
    "price" : 가격
}

Response
HTTP/1.1 204 NO_CONTENT
```

### 브랜드 상품 삭제
```
Request
DELETE apis/brands/{id}


Response
HTTP/1.1 204 NO_CONTENT
```