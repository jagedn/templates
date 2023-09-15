package com.openfaas.function

import com.openfaas.model.AbstractHandler
import com.openfaas.model.IResponse
import com.openfaas.model.IRequest
import com.openfaas.model.Response
import groovy.json.JsonOutput

class Handler extends AbstractHandler {

    IResponse Handle(IRequest req) {

        def path = req.path
        if( path.size() != 1){
            return new Response(
                    statusCode: 400,
                    body: "number required"
            )
        }

        BigInteger n
        try{
            def entry = path.entrySet().first()
            if( entry.value ){
                return new Response(
                        statusCode: 400,
                        body: "Invalid request path"
                )
            }
            n = entry.key as BigInteger
        }catch(e){
            return new Response(
                    statusCode: 400,
                    body: "$e"
            )
        }

        Response res = new Response(
                body: JsonOutput.prettyPrint( JsonOutput.toJson(calculate(n)) ),
                contentType: "application/json"

        )	    
	    return res;
    }

    Map calculate(BigInteger n){
        def ret = [n:n, count : 0]
        while (!n.equals(BigInteger.ONE)) {
            if (n.mod(new BigInteger("2")) == BigInteger.ZERO) {
                n = n.divide(new BigInteger("2"))
            } else {
                n = n.multiply(new BigInteger("3"))
                n = n.add(new BigInteger("1"))
            }
            ret.count++
        }
        ret
    }

}
