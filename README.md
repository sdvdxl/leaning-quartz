## 启动一个mariadb

```shell
docker run -d --name mariadb --env MARIADB_USER=user --env MARIADB_PASSWORD=123456 --env MARIADB_ROOT_PASSWORD=123456 -p 3808:3808 mariadb:latest
```
