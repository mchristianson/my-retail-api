## Overview
Matt Christianson's Case Study for Target

# My Retail RESTful Service
Built with Grails v3.3.6 with the rest-client-builder to connect to the redsky.target.com external API. It combines a few details from that service along with the price as persisted in Redis.

## Installation
#### Clone the project
`> git clone git@github.com:mchristianson/my-retail.git`

#### Install Redis (if not already installed)
```
> brew update
> brew install redis
> brew info redis
```
For more information see: https://medium.com/@petehouston/install-and-config-redis-on-mac-os-x-via-homebrew-eb8df9a4f298

#### cd to the api app
`> cd my-retail/api`

#### Run the Grails app (bootRun is default)
`> ./gradlew`

#### Run the tests
`> ./gradlew tests && ./gradlew integrationTest`

#### Manual Testing
Retrieve product information
`> curl -X GET http://localhost:8080/api/v1/product/13860428`

Attempt to retrieve product information Not Found
`> curl -X GET http://localhost:8080/api/v1/product/13860429`

Set price for product
`> curl -X PUT http://localhost:8080/api/v1/product/13860428?price=12.22`

#### Running instances
`> curl -X GET http://my-retail.us-east-2.elasticbeanstalk.com/api/v1/product/13860428`

# My Retail Angular Store Front
This Angular storefront was built with Angular 5.

#### cd to the client app
`> cd my-retail/my-retail-client`

#### Install Angular CLI
`> npm install -g @angular/cli`

#### Run the app
`> ng serve -o`
Navigate to `http://localhost:4200/product/15117729`

#### Run the functional tests
`> ng e2e`

#### Running instances
http://my-retail.smd-test.com/product/15117729
http://my-retail.smd-test.com/product/13860428
