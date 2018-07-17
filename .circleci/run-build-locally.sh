#!/usr/bin/env bash
curl --user 0db17d6f0bab863c3be35be17870e71e6f79167c: \
    --request POST \
    --form revision=e24741e38d6a19ad7b42c16d3a864daa305915c5\
    --form config=@config.yml \
    --form notify=false \
        https://circleci.com/api/v1.1/project/github.com/mchristianson/my-retail/tree/master
