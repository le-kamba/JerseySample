# JerseySample
JerseyでRESTfulAPIのサンプル(mavenプロジェクト)

## テストの実行（htmlレポートも出力)

```console
$ mvn clean \
    test -Dmaven.test.failure.ignore=true \
    site -DgenerateReports=false \
    surefire-report:report
```
