package com.crm.utility;

import org.springframework.http.ResponseEntity;

public class ResponseEntityHelper {

    public static ResponseEntity<?> jsonCodeResponse(ResponseKind responseKind){
        return ResponseEntity.ok("{ \"code\": " + responseKind.ordinal() + "}");
    }

    public static ResponseEntity<?> jsonCodeResponse(int code){
        return ResponseEntity.ok("{ \"code\": " + code + "}");
    }

    public static ResponseEntity<?> jsonOkResponse(){
        return ResponseEntity.ok("{ \"response\": \"ok\"}");
    }

    public static ResponseEntity<?> jsonTokenResponse(String token){
        return ResponseEntity.ok("{ \"token\": \"" + token + "\"}");
    }

    public static ResponseEntity<?> jsonIdResponse(Long id) {
        return ResponseEntity.ok("{ \"id\": " + id + "}");
    }
}
