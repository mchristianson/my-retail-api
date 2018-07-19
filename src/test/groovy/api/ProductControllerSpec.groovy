package api

import grails.testing.web.controllers.ControllerUnitTest
import spock.lang.Specification

class ProductControllerSpec extends Specification implements ControllerUnitTest<ProductController> {
    ProductService productService

    def setup() {
        productService = Mock()
        controller.productService = productService
    }

    def cleanup() {
    }

    void "product should be found"() {
        given:"product"
        String id = '1'
        Product product = new Product(id: id, name: 'Big Lebowski', currentPrice: 12.99)

        when:"show the product"
        controller.show(id)

        then:"product is available"
        1 * productService.get(id) >> product
        0 * _
        response.text == '{"id":"1","name":"Big Lebowski","currentPrice":12.99,"currencyCode":"USD"}'
    }

    void "product should render 404 if product not found"() {
        given:"product id"
        String productId = '2'

        when:"show the product"
        controller.show(productId)

        then:"product is not found"
        1 * productService.get(productId) >> null
        0 * _
        response.status == 404
    }

    void "should update price"() {
        given:"product id and new price"
        String productId = '123'
        Double price = 12.33

        when:"show the product"
        request.method = 'PUT'
        params['price'] = price
        controller.update(productId)

        then:"product is not found"
        1 * productService.updatePrice(productId, price)
        0 * _
        response.status == 202
    }

    void "should return 400 - bad request if no price"() {
        given:"product id"
        String productId = '123'

        when:"show the product"
        request.method = 'PUT'
        controller.update(productId)

        then:"product is not found"
        0 * _
        response.status == 400
    }

    void "should show 400 - bad request if price not number"() {
        given:"product id"
        String productId = '123'
        String price = 'notanumber'

        when:"show the product"
        request.method = 'PUT'
        params['price'] = price
        controller.update(productId)

        then:"product is not found"
        0 * _
        response.status == 400
    }

    void "GET method not allowed for update price"() {
        given:"product id"
        String productId = '123'
        String price = 'notanumber'

        when:"show the product"
        request.method = 'GET'
        params['price'] = price
        controller.update(productId)

        then:"product is not found"
        0 * _
        response.status == 405
    }
}
