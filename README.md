# elasticsearch-example

docker run --rm --name elasticsearch_container -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" -e "xpack.security.enabled=false" elasticsearch:8.8.0

docker pull docker.elastic.co/elasticsearch/elasticsearch:8.8.0

docker run -p 9200:9200 -p 9300:9300  -e "discovery.type=single-node"  docker.elastic.co/elasticsearch/elasticsearch:8.8.0


localhost:9200
localhost:9200/animal_index
localhost:9200/animal_index/_search