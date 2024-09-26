### lecture index 생성

* kibana console : http://localhost:5601/app/dev_tools#/console
* Nori 설치 ``$ bin/elasticsearch-plugin install analysis-nori``

```sh
PUT lecture-1
{
  "mappings" : {
    "properties" : {
      "id" : {
        "type" : "long"
      },
      "title" : {
        "type" : "text",
        "analyzer": "nori_tokenizer"
      },
      "content" : {
        "type" : "text",
        "analyzer": "nori_tokenizer"
      },
      "capacity" : {
        "type" : "int"
      },
      "indexedAt": {
        "type": "date",
        "format": "date_optional_time||epoch_millis"
      }
    }
  },
  "settings" : {
    "index" : {
      "number_of_shards" : 1,
      "number_of_replicas" : 0
    },
    "analysis": {
      "tokenizer": {
        "nori_none": {
          "type": "nori_tokenizer",
          "decompound_mode": "none"
        },
        "nori_discard": {
          "type": "nori_tokenizer",
          "decompound_mode": "discard"
        },
        "nori_mixed": {
          "type": "nori_tokenizer",
          "decompound_mode": "mixed"
        }
      }
    }
  }
}




```