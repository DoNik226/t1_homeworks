# Домашнее задание №1
### Запуск приложения
1. Из директории, куда установлена Kafka, запустить сервер (используются дефолтные файлы):
   - ./bin/kafka-storage.sh random-uuid
   - ./bin/kafka-storage.sh format -t ${UUID} -c config/kraft/server.properties  (вместо ${UUID} вставить результат прошлой команды)
   - ./bin/kafka-server-start.sh config/kraft/server.properties
Для windows те же шаги, но использовать файлы с расширением bat, находящиеся в директории bin/windows
2. Запустить класс src/main/java/com.homework/HomeworkApplication - отвечает за запуск продьюсера и консьюмера
3. Запустить класс src/main/java/com.homework/Main - посылает запросы на наш сервер, которые затем отправляются на kafka
