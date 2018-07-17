package api

import org.grails.web.json.JSONElement

class Product {

    String id
    String name
    Double currentPrice
    String currencyCode = 'USD'

    static Product fromJSON(JSONElement jsonElement) {
        Product product = new Product()
        product.id = jsonElement.product.item.tcin
        product.name = jsonElement.product.item.product_description.title
        product.currentPrice = jsonElement.product.price.offerPrice.price
        return product
    }
}
