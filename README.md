# Ecommerce
 
这是我的第一个正式的项目，微服务架构的 javaweb 程序。技术

项目使用spring cloud架构，通过nacos实现拉取配置及本服务调用，gateway网关实现跨域操作，通过feign实现服务内部接口的调用，秒杀部分使用redis缓存加快读写速度，并使用rabbitmq异步消息对数据库写入避开流量高峰。
