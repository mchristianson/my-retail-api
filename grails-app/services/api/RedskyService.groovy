package api

import grails.config.Config
import grails.core.support.GrailsConfigurationAware
import grails.plugins.rest.client.RestBuilder
import grails.plugins.rest.client.RestResponse
import groovy.transform.CompileDynamic
import groovy.transform.CompileStatic

@CompileStatic
class RedskyService implements GrailsConfigurationAware {

    String redskyUrl

    @Override
    void setConfiguration(Config co) {
        redskyUrl = co.getProperty('redsky.url', String, 'https://redsky.target.com/v2/')
    }

    @CompileDynamic
    Product getProductDetails(String id) {
        RestBuilder rest = new RestBuilder()
        String url = "${redskyUrl}/pdp/tcin/{id}?excludes=taxonomy,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics"
        Map params = [id: id]
        RestResponse restResponse = rest.get(url) {
            urlVariables params
        }

        if ( restResponse.statusCode.value() == 200 && restResponse.json ) {
            return Product.fromJSON(restResponse.json)
        }
    }

}
