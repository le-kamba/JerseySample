![Java CI with Maven](https://github.com/le-kamba/JerseySample/workflows/Java%20CI%20with%20Maven/badge.svg?branch=feature%2Ffor_github_actions)

# JerseySample
JerseyでRESTfulAPIのサンプル(mavenプロジェクト)

## テストの実行（htmlレポートも出力)

```console
$ mvn clean \
    test -Dmaven.test.failure.ignore=true \
    site -DgenerateReports=false \
    -Daggregate=true \
    surefire-report:report
```
