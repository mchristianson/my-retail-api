package api

import groovy.transform.CompileStatic
import org.grails.core.io.ResourceLocator
import org.springframework.http.HttpStatus

@CompileStatic
class ProductController {

	ProductService productService
    ResourceLocator grailsResourceLocator

    static namespace = "v1"

    static allowedMethods = [update: 'PUT', show: 'GET']

    static responseFormats = ['json']

    def show(String id) {
        respond productService.get(id)
    }

    //TODO this really should be protected
    def update(String id) {
        try {
            Double price = params.price as Double
            if (price) {
                productService.updatePrice(id, price)
                response.status = HttpStatus.OK.value()
            } else {
                response.status = HttpStatus.BAD_REQUEST.value()
                render 'Price is required.'
            }
        } catch (NumberFormatException e) {
            response.status = HttpStatus.BAD_REQUEST.value()
            render 'Price must be a number.'
        }
    }

    // Mock data for the my-retain-client app
    def sample(String id) {
        String fileName = "item-data-${id}.json"
        def resource = grailsResourceLocator.findResourceForURI("/${fileName}")
        def inputStream = resource.inputStream
        render file: inputStream, fileName: fileName
    }
}
