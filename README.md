## Overview
Matt Christianson's REST api Case Study for Target

![CircleCI Status](https://circleci.com/gh/mchristianson/my-retail-api.svg?style=shield&circle-token=de7c208809bc8b06bf9c6c777b185a5df7ea0ceb)

# My Retail RESTful Service
Built with Grails v3.3.6 with the rest-client-builder to connect to the redsky.target.com external API. It combines a few details from that service along with the price as persisted in Redis.

## Installation
#### Clone the project
`> git clone git@github.com:mchristianson/my-retail-api.git`

#### Install Redis (if not already installed)
```
> brew update
> brew install redis
> brew info redis
```
For more information see the [medium.com article](https://medium.com/@petehouston/install-and-config-redis-on-mac-os-x-via-homebrew-eb8df9a4f298). 

#### cd to the api app
`> cd my-retail-api`

#### Run the Grails app (bootRun is default)
`> ./gradlew`

#### Run the tests
`> ./gradlew tests && ./gradlew integrationTest`

#### Manual Testing Locally
Retrieve product information

`> curl -X GET http://localhost:8080/api/v1/product/13860428`


Attempt to retrieve product information Not Found

`> curl -X GET http://localhost:8080/api/v1/product/13860429`

Set price for product

`> curl -X PUT http://localhost:8080/api/v1/product/13860428?price=12.22`


#### Manual Testing AWS EBS Instance
The applicaiton has been deployed to AWS ElasticBeanstalk:

`> curl -X GET http://my-retail.us-east-2.elasticbeanstalk.com/api/v1/product/13860428`
