## skytecGames test task 

Реализация тетсового задания (обязатльная + дополнительная часть)


### Технологии

---
* JDK 18
* база данных: PostgreSQL
* Connection: HikariCP

### Запуск

---
* поднять базу данных PostgreSQL
* создать user: name - "skytec_user" password - "password" (если используете другого user то нужно поменять конфиги здесь src/main/java/com/tsoun/connection/DataSourceConfig.java)
* создать базу с названием game и owner "skytec_user"
* запустить скрипт заполнения базы данных src/main/resources/db.sql
* запустить TestMain


## task description

 есть объект клана, примерно такого вида

`class Clan {private long id;
private String name;
private int gold;
}`

 есть менеджер, в котором можно получить клан, а так же сохранить его

`class ClanManager {
public static Clan getClan(long clanId) {return null;}
public static boolean saveClan(long clanId) {
return false;
}
}`

контроллер

`class ClanController {
// Допустим этот метод вызывается параллельно пользователями 1000 раз в секунду в разных
потоках
public void incGold(long clanId, int gold) {
Clan clan = ClanManager.getClan(clanId);
clan.incGold(gold);
}
}`


Задача:

• Обязательная часть без использования Spring
Нужно реализовать логику добавления/уменьшения золота в клан
Предусмотреть конкурентный доступ с разных потоков (допустим ~1000
параллельных запросов в секунду от разных пользователей)
• Дополнительная часть без использования Spring
3. Реализовать трекеры добавления золота в клан каждым игроком
 класс должен иметь примерный вид
 
`class TrackerManager {
public static void trackerClanGold(long clanId, long userId, int gold) {
// здесь логика сохранения трекера в базу}}
// вызов добавления в трекер должен иметь вид
// сначала начисляем золото в клан
Clan clan = ClanManager.getClan(clanId);
clan.incGold(gold);
// далее сохраняем в трекер пользователя и кол-во золота
TrackerManager.trackGold(clanId, userId, gold)`
