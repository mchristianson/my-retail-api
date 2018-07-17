package api

import grails.plugins.redis.RedisService
import groovy.transform.CompileDynamic
import groovy.transform.CompileStatic

@CompileStatic
class ProductService {

    RedisService redisService
    RedskyService redskyService

    @CompileDynamic
    Product get(String id) {
        Product product = redskyService.getProductDetails(id)
        Double price = redisService.getProperty(getRedisId(id)) as Double
        if (product && price) {
            product.currentPrice = price
        }
        return product
    }

    @CompileDynamic
    void updatePrice(String id, Double price) {
        redisService.setProperty(getRedisId(id), price)
    }

    private static getRedisId(String id) {
        return "product:${id}:price"
    }
}
