# docker-Java-kubernetes-project
Starting with this basic java project and removing Code to containerize it and K8s Manifest files to start on my own

Credit: https://github.com/shazforiot/docker-Java-kubernetes-project#


command to start productcatalogue service --
java \
  --add-opens java.base/java.lang=ALL-UNNAMED \
  --add-opens java.base/java.util=ALL-UNNAMED \
  --add-opens java.base/java.lang.reflect=ALL-UNNAMED \
  -jar target/productcatalogue-0.0.1-SNAPSHOT.jar \
  server product-catalogue.yml


Command to start shopfront service ---

java -jar target/shopfront-0.0.1-SNAPSHOT.jar \
  --productCatalogueUri=http://localhost:8020 \
  --stockManagerUri=http://localhost:8030 \
  --server.port=8010


