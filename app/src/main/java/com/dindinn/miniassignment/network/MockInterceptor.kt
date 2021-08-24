package com.dindinn.miniassignment.network

import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody

class MockInterceptor() : Interceptor {
    private lateinit var responseString: String

    override fun intercept(chain: Interceptor.Chain): Response {


        val uri = chain.request().url.toUri().toString()


        responseString = when {
            uri.endsWith("order_list") -> orderJson
            uri.endsWith("category_id=10") -> bantoItem
            uri.endsWith("category_id=20") -> mainItem
            uri.endsWith("category_id=30") -> appetizerItem
            uri.endsWith("category") -> categoryJson
            else -> ""
        }


        return chain.proceed(chain.request())
            .newBuilder()
            .code(200)
            .protocol(Protocol.HTTP_2)
            .message(responseString)
            .body(
                responseString.toByteArray()
                    .toResponseBody("application/json".toMediaTypeOrNull())
            )
            .addHeader("content-type", "application/json")
            .build()


    }


}

const val orderJson = """
{
	"status": {
		"success": true,
		"statusCode": 200,
		"message": "success"
	},
	"data": [{
		"id": 10,
		"title": "Special extra large fried rice",
		"addon": [{
			"id": 21,
			"title": "Fried Egg",
			"quantity": 3
		}],
		"quantity": 1,
		"created_at": "2021-06-10T15:00+00Z",
		"alerted_at": "2021-06-10T15:03+00Z",
		"expired_at": "2021-06-10T15:05+00Z"
	}, {
		"id": 11,
		"title": "Chicken Noodle",
		"addon": [{
			"id": 26,
			"title": "Extra chicken",
			"quantity": 2
		}, {
			"id": 27,
			"title": "Sambal",
			"quantity": 1
		}],
		"quantity": 5,
		"created_at": "2021-06-10T15:10+00Z",
		"alerted_at": "2021-06-10T15:13+00Z",
		"expired_at": "2021-06-10T15:25+00Z"
	}
]
}

"""


const val bantoItem = """
  {
	"status": {
		"success": true,
		"statusCode": 200,
		"message": "success",
		"id": 10

	},
		"items": [{
			"id": 21,
			"title": "Fried Egg",
			"quantity": 3,
			"isAvailable": true
		},{
			"id": 22,
			"title": "Egg",
			"quantity": 3,
			"isAvailable": true
		},{
			"id": 23,
			"title": "Egg fry",
			"quantity": 3,
			"isAvailable": true
		},{
			"id": 24,
			"title": "Egg noodles",
			"quantity": 3,
			"isAvailable": true
		}
]
		
	}  
    
    
    
"""
const val mainItem = """
  {
	"status": {
		"success": true,
		"statusCode": 200,
		"message": "success",
		"id": 20

	},
		"items": [{
			"id": 21,
			"title": "Fried rice",
			"quantity": 3,
			"isAvailable": true
		},{
			"id": 22,
			"title": "Fried noodles",
			"quantity": 3,
			"isAvailable": true
		}


]
		
	}  
    
    
    
"""
const val appetizerItem = """
  {
	"status": {
		"success": true,
		"statusCode": 200,
		"message": "success",
		"id": 30

	},
		"items": [
{
			"id": 24,
			"title": "Burger",
			"quantity": 3,
			"isAvailable": true
		},{
			"id": 26,
			"title": "Pizza",
			"quantity": 3,
			"isAvailable": true
		},{
			"id": 212,
			"title": "French fries",
			"quantity": 3,
			"isAvailable": false
		}
]
		
	}  
    
    
    
"""


const val ingredientJson = """
{
	"status": {
		"success": true,
		"statusCode": 200,
		"message": "success"
	},
	"data": [{
		"id": 10,
		"title": "Bento",
		"items": [{
			"id": 21,
			"title": "Fried Egg",
			"quantity": 3,
			"isAvailable": true
		}],
		
	}, {
		"id": 11,
		"title": "Main",
		"items": [{
			"id": 26,
			"title": "Extra chicken",
			"quantity": 2,
			"isAvailable": true

		}, {
			"id": 27,
			"title": "Sambal",
			"quantity": 1,
			"isAvailable": true

		}]
		
	}]
}
"""
const val categoryJson = """
{
	"status": {
		"success": true,
		"statusCode": 200,
		"message": "success"
	},
	"data": [{
			"id": 10,
			"title": "Bento"

		}, {
			"id": 20,
			"title": "Main"
		}, {
			"id": 30,
			"title": "Appetizer"
		}


	]
}
"""
