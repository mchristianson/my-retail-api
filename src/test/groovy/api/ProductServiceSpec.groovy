package api

import grails.plugins.redis.RedisService
import grails.testing.services.ServiceUnitTest
import spock.lang.Specification

class ProductServiceSpec extends Specification implements ServiceUnitTest<ProductService> {

    RedisService redisService
    RedskyService redskyService

    def setup() {
        redisService = Mock()
        service.redisService = redisService

        redskyService = Mock()
        service.redskyService = redskyService
    }

    def cleanup() {
    }

    void "combine product info from service and price from redis"() {
        given:"product id and price"
        Double price = 12.33
        String productId = '123'
        Product returnedProduct = new Product(id: productId, currentPrice: price)

        when:"product is retrived"
        Product product = service.get(productId)

        then:"values are combined"
        1 * redskyService.getProductDetails(productId) >> returnedProduct
        1 * redisService.getProperty("product:${productId}:price") >> price
        0 * _
        product.currentPrice == price
        product.id == productId
    }

    void "update price"() {
        given:"product id and price"
        Double price = 12.33
        String productId = '123'

        when:"update price"
        service.updatePrice(productId, price)

        then:"redis service is called"
        1 * redisService.setProperty("product:${productId}:price", price)
        0 * _
    }
}
