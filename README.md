- eureka+ribbon 7001 7002 c-p-p8001 c-p-p8002 order-80 
- zookeeper 安装zookeeper c-p-p8003 zk-order80
- consul 安装consul consul-payment8006 consul-order80

- openFeign 7001 7002 c-p-p8001 c-p-p8002 feign-order80 

- hystrix 7001 hystrix-payment8001  hystrix-order80 c-c-h-d9001

- gateway 7001  c-p-p8001 c-p-p8002  gateway9527

- config + bus 7001 3344 3355 3366

- stream 7001 cloud-stream-rabbitmq-provider8801 8802 8803

- sleuth 7001 c-p-p8001 order-80

- nacos alibaba9001 alibaba9002 nacos-consumer80 3377