## SQL
POST /_sql?format=txt
{
    "query": "SELECT * FROM library WHERE release_date < '2000-01-01'"
}


GET /_cat/allocation?v



GET /_cat/ count /info1613964234241?v

## 健康 
GET /_cat/health?v


GET /_cat/shards

GET /_cat/indices


GET /_cluster/allocation?v


## docuemnt
GET twitter/_search
    PUT twitter/_doc/3
    {
    "counter" : 1,
    "tags" : ["red"]
    }

GET twitter/_doc/1?stored_fields=tags,counter
    PUT twitter/_doc/2?routing=user1
    {
    "counter" : 1,
    "tags" : ["white"]
    }

## 路由
GET twitter/_doc/2?routing=user1&stored_fields=tags,counter


DELETE /twitter/_doc/1?routing=kimchy

GET test/_search
    PUT test/_doc/1
    {
    "counter" : 1,
    "tags" : ["red"]
    }
    POST test/_update/1
    {
    "script" : {
    "source": "ctx._source.counter += params.count",
    "lang": "painless",
    "params" : {
    "count" : 4
    }
    }
    }
    POST test/_update/1
    {
    "script" : {
    "source": "ctx._source.tags.add(params.tag)",
    "lang": "painless",
    "params" : {
    "tag" : "green"
    }
    }
    }
    POST test/_update/1
    {
    "doc" : {
    "name" : "new_name"
    }
    }



GET /_mget
    {
    "docs" : [
    {
    "_index" : "twitter",
    "_id" : "1"
    },
    {
    "_index" : "twitter",
    "_id" : "2"
    }
    ]
    }
    POST _bulk
    { "index" : { "_index" : "test", "_id" : "1" } }
    { "field1" : "value1" }
    { "delete" : { "_index" : "test", "_id" : "2" } }
    { "create" : { "_index" : "test", "_id" : "3" } }
    { "field1" : "value3" }
    { "update" : {"_id" : "1", "_index" : "test"} }
    { "doc" : {"field2" : "value2"} }



GET /twitter/_termvectors/1? fields =message


GET /twitter/_termvectors/1
    POST clicklogs/_graph/explore
    {
    "query": {
    "match": {
    "query.raw": "midi"
    }
    },
    "vertices": [
    {
    "field": "product"
    }
    ],
    "connections": {
    "vertices": [
    {
    "field": "query.raw"
    }
    ]
    }
    }

















