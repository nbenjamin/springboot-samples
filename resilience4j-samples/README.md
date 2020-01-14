# Resilience4J Samples

## BulkHead
Bulkhead pattern that can be used to limit the number of concurrent execution.

All the configurations can be found in application.yaml and the implementations can find under `DemoService.java`.
You can test and run the load using the following [Apache Batch command](https://httpd.apache.org/docs/2.4/programs/ab.html)

Can be tested using the following URL
```bash
ab -n 100 -c 100 -m GET localhost:8083/bulk/2
```

## CircuitBreaker 

Circuit breaker monitors all the failures. Once the failures reach a certain threshold, 
the circuit breaker trips (OPEN), and all further calls to the circuit breaker.


```bash
ab -n 100 -c 100 -m GET localhost:8083/circuitbreaker/0
```

## RateLimiter
Enables the throttling

```bash
ab -n 100 -c 100 -m GET localhost:8083/ratelimiter/0

```

## Retry

Enables an option to retry when requested services has an issue.

```bash
ab -n 100 -c 100 -m GET localhost:8083/retry/0

```

