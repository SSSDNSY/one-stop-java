## Index

DELETE /twitter
DELETE /test


GET /twitter/_search

## 查看索引信息
GET /twitter/_settings
GET /test/_mappings
GET /test/_alias

GET /twitter/_alias

## 新增索引
    PUT /twitter


## Index settings
    PUT /twitter
    {
    "settings" : {
    "index" : {
    "number_of_shards" : 3,
    "number_of_replicas" : 2
    }
    }
    }
    PUT /test
    {
    "settings" : {
    "number_of_shards" : 1
    },
    "mappings" : {
    "properties" : {
    "field1" : { "type" : "text" }
    }
    }
    }
    PUT /test
    {
    "aliases" : {
    "alias_1" : {},
    "alias_2" : {
    "filter" : {
    "term" : {"user" : "kimchy" }
    },
    "routing" : "kimchy"
    }
    }
    }
    PUT /test
    {
    "settings": {
    "index.write.wait_for_active_shards": "2"
    }
    }
    PUT /test?wait_for_active_shards=2


## 删除索引 
## 支持通配符 不支持别名

DELETE /twitter


DELETE /twitter/_alias/alias1
DELETE /_template/template_1


## 刷新索引

    POST /twitter/_flush
    POST /_flush

## 合并
    POST /twitter/_forcemerge
    POST /twitter/_freeze
    POST /twitter/_unfreeze


GET /twitter/_mapping/field/ user
    PUT /publications
    {
    "mappings": {
    "properties": {
    "id": { "type": "text" },
    "title": { "type": "text"},
    "abstract": { "type": "text"},
    "author": {
    "properties": {
    "id": { "type": "text" },
    "name": { "type": "text" }
    }
    }
    }
    }
    }

GET publications/_mapping/field/title

GET publications/_mapping/field/author.id,abstract, name

GET /twitter/_alias

GET /twitter/_settings

GET /twitter/_mapping

##映射
GET /_mapping

## Checks if an index exists.
    HEAD /twitter


GET /twitter/_recovery

GET /twitter/_segments


GET /twitter/_shard_stores

#Returns statistics for an index.
GET /twitter/_stats
    PUT _template/template_1
    {
    "index_patterns": ["te*", "bar*"],
    "settings": {
    "number_of_shards": 1
    },
    "mappings": {
    "_source": {
    "enabled": false
    },
    "properties": {
    "host_name": {
    "type": "keyword"
    },
    "created_at": {
    "type": "date",
    "format": "EEE MMM dd HH:mm:ss Z yyyy"
    }
    }
    }
    }
    PUT /twitter/_mapping
    {
    "properties": {
    "email": {
    "type": "keyword"
    }
    }
    }

## 刷新索引
    POST /twitter/_refresh
    POST /twitter/_shrink/shrunk-twitter- index











