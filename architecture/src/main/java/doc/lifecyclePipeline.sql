#Creates or updates lifecycle policy. See ILM policy definition for definitions of policy components.

PUT _ilm/policy/my_policy
{
  "policy": {
    "phases": {
      "warm": {
        "min_age": "10d",
        "actions": {
          "forcemerge": {
            "max_num_segments": 1
          }
        }
      },
      "delete": {
        "min_age": "30d",
        "actions": {
          "delete": {}
        }
      }
    }
  }
}



GET _ilm/policy/my_policy


DELETE _ilm/policy/my_policy
    POST _ilm/move/my_index
    {
    "current_step": {
    "phase": "new",
    "action": "complete",
    "name": "complete"
    },
    "next_step": {
    "phase": "warm",
    "action": "forcemerge",
    "name": "forcemerge"
    }
    }

## 数据预处理管道
    PUT _ingest/pipeline/my-pipeline-id
    {
    "description" : "describe pipeline",
    "processors" : [
    {
    "set" : {
    "field": "foo",
    "value": "bar"
    }
    }
    ]
    }


GET /_xpack


GET twitter/_search?q= user :kimchy

