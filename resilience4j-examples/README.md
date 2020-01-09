#Spring boot Resilience4J Example

This code is to showcase how to use resilience4j with spring-boot2.
 
It has following implementation

    1. CircuitBreaker
    2. RateLimit
    3. Retry
    4. Bulkhead features.
    
    

### CircuitBreaker

```yaml
resilience4j.circuitbreaker:
  configs:
    default:
      slidingWindowType: COUNT_BASED
      slidingWindowSize: 10
      permittedNumberOfCallsInHalfOpenState: 5
      waitDurationInOpenState: 10s
      failureRateThreshold: 6
      registerHealthIndicator: true
      recordExceptions:
        - java.lang.ClassCastException
  instances:
    demo:
      baseConfig: default
```

## How to test
    ▶ http GET localhost:8080/test/0
        This endpoint will throw an exception and open the circuit breaker


 ```bash 
 ▶ http GET localhost:8080/test/0
 HTTP/1.1 200 OK
 Content-Length: 8
 Content-Type: text/plain;charset=UTF-8
 
 FallBack
 ```
 
    