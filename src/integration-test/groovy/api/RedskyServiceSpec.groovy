package api

import grails.testing.mixin.integration.Integration
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Specification

@Integration
class RedskyServiceSpec extends Specification {

    @Autowired
    RedskyService redskyService

    void "test request to redsky"() {
        given:"existing product id"
        String productId = '13860428'

        when:"build product from redsky api"
        Product product = redskyService.getProductDetails(productId)

        then:"verify details"
        product.id == productId
        product.name == 'The Big Lebowski (Blu-ray)'
    }
}
