package api

class UrlMappings {

    static mappings = {
        get "/api/v1/$controller/$id(.$format)?"(action:"show", namespace: 'v1')
        put "/api/v1/$controller/$id(.$format)?"(action:"update", namespace: 'v1')

        get "/api/v1/product/sample/$id"(controller: 'product', action:'sample')

        "500"(view: '/error')
        "404"(view: '/notFound')
    }
}
